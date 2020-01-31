package Chapter14.tdd;

import java.text.ParseException;
import java.util.*;

/**
 * boolean and String version #0: using BooleanArgumentMarshaller
 * @author Tosca
 * @date 30/1/2020
 */
public class Args0 {
  private String schema;
  private String[] args;
  private boolean valid = true;
  private Set<Character> unexpectedArguments = new TreeSet<>();
  private Map<Character, ArgumentMarshaller> booleanArgs = new HashMap<>();
  private Map<Character, String> stringArgs = new HashMap<>();
  private Set<Character> argsFound = new HashSet<>();
  private int currentArgument;
  private char errorArgument = '\0';

  private ErrorCode errorCode = ErrorCode.OK;
  enum ErrorCode {
    OK,
    MISSING_STRING
  }

  public Args0(String schema, String[] args) throws ParseException {
    this.schema = schema;
    this.args = args;
    valid = parse();
  }

  private boolean parse() throws ParseException {
    if (schema.length() == 0 && args.length == 0) {
      return true;
    }
    parseSchema();
    parseArguments();
    return valid;
  }

  private boolean parseSchema() throws ParseException {
    for (String element : schema.split(",")) {
      if (element.length() > 0) {
        String trimmedElement = element.trim();
        parseSchemaElement(trimmedElement);
      }
    }
    return true;
  }

  private void parseSchemaElement(String element) throws ParseException {
    String str = element.substring(0, element.length() - 1);
    String elementTail = element.substring(element.length() - 1);
    if (isBooleanSchemaElement(elementTail)) {
      parseBooleanSchemaElement(str);
    } else if (isStringSchemaElement(elementTail)) {
      parseStringSchemaElement(str);
    }
  }

  private void parseBooleanSchemaElement(String element) {
    for (int i = 0; i < element.length(); ++i) {
      char c;
      if (Character.isLetter(c = element.charAt(i))) {
        booleanArgs.put(c, new BooleanArgumentMarshaller());
      }
    }
  }

  private void parseStringSchemaElement(String element) {
    for (int i = 0; i < element.length(); ++i) {
      char c;
      if (Character.isLetter(c = element.charAt(i))) {
        stringArgs.put(c, "");
      }
    }
  }

  private boolean isBooleanSchemaElement(String elementTail) {
    return elementTail.equals("#");
  }

  private boolean isStringSchemaElement(String elementTail) {
    return elementTail.equals("*");
  }

  private boolean parseArguments() {
    for (currentArgument = 0; currentArgument < args.length; ++currentArgument) {
      String arg = args[currentArgument];
      parseArgument(arg);
    }
    return true;
  }

  private void parseArgument(String arg) {
    if (arg.startsWith("-")) {
      parseElements(arg);
    }
  }

  private void parseElements(String arg) {
    for (int i = 1; i < arg.length(); ++i) {
      parseElement(arg.charAt(i));
    }
  }

  private void parseElement(char argChar) {
    if (setArgument(argChar)) {
      argsFound.add(argChar);
    } else {
      unexpectedArguments.add(argChar);
      valid = false;
    }
  }

  private boolean setArgument(char argChar) {
    boolean set = true;
    if (isBooleanArg(argChar)) {
      setBooleanArg(argChar, true);
    } else if (isStringArg(argChar)) {
      setStringArg(argChar);
    } else {
      set = false;
    }
    return set;
  }

  private boolean isBooleanArg(char argChar) {
    return booleanArgs.containsKey(argChar);
  }

  private boolean isStringArg(char argChar) {
    return stringArgs.containsKey(argChar);
  }

  private void setBooleanArg(char argChar, boolean value) {
    booleanArgs.get(argChar).setBoolean(value);
  }

  private void setStringArg(char argChar) {
    int index = currentArgument + 1;
    try {
      stringArgs.put(argChar, args[index]);
    } catch (ArrayIndexOutOfBoundsException e) {
      valid = false;
      errorArgument = argChar;
      errorCode = ErrorCode.MISSING_STRING;
    }
  }

  public int cardinality() {
    return argsFound.size();
  }

  public String usage() {
    if (schema.length() > 0) {
      return "-[" + schema + "]";
    } else {
      return "";
    }
  }

  public String errorMessage() throws Exception {
    if (unexpectedArguments.size() > 0) {
      return unexpectedArgumentMessage();
    } else {
      switch (errorCode) {
        case OK:
          throw new Exception("TILT: Should not get here.");
        case MISSING_STRING:
          return String.format("Could not find string parameter for -%c.", errorArgument);
      }
      return "";
    }
  }

  private String unexpectedArgumentMessage() {
    StringBuffer msg = new StringBuffer("Argument(s) -");
    for (char c : unexpectedArguments) {
      msg.append(c);
    }
    msg.append(" unexpected.");
    return msg.toString();
  }

  public boolean getBoolean(char arg) {
    ArgumentMarshaller am = booleanArgs.get(arg);
    return am != null && am.getBoolean();
  }

  public String getString(char arg) {
    return blankIfNull(stringArgs.get(arg));
  }

  private String blankIfNull(String s) {
    return s == null ? "" : s;
  }

  public boolean has(char arg) {
    return argsFound.contains(arg);
  }

  public boolean isValid() {
    return valid;
  }

  private class ArgumentMarshaller {
    private boolean booleanValue = false;

    public void setBoolean(boolean booleanValue) {
      this.booleanValue = booleanValue;
    }

    public boolean getBoolean() {
      return booleanValue;
    }
  }

  private class BooleanArgumentMarshaller extends ArgumentMarshaller {}

  private class StringArgumentMarshaller extends ArgumentMarshaller {}

  private class IntegerArgumentMarshaller extends ArgumentMarshaller {}
}
