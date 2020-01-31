package Chapter14.tdd;

import Chapter14.ArgsException;

import java.text.ParseException;
import java.util.*;

/**
 * boolean and String version #2: adding IntegerArgument
 * @author Tosca
 * @date 31/1/2020
 */
@SuppressWarnings("unused")
public class Args2 {
  private String schema;
  private String[] args;
  private boolean valid = true;
  private Set<Character> unexpectedArguments = new TreeSet<>();
  private Map<Character, ArgumentMarshaller> booleanArgs = new HashMap<>();
  private Map<Character, ArgumentMarshaller> stringArgs = new HashMap<>();
  private Map<Character, ArgumentMarshaller> integerArgs = new HashMap<>();
  private Set<Character> argsFound = new HashSet<>();
  private int currentArgument;
  private char errorArgumentId = '\0';
  private String errorParameter;

  private ErrorCode errorCode = ErrorCode.OK;
  enum ErrorCode {
    OK,
    MISSING_BOOLEAN,
    MISSING_STRING,
    MISSING_INTEGER,
    INVALID_INTEGER,
  }

  public Args2(String schema, String[] args) throws ParseException, ArgsException {
    this.schema = schema;
    this.args = args;
    valid = parse();
  }

  private boolean parse() throws ParseException, ArgsException {
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
    } else if (isIntegerSchemaElement(elementTail)) {
      parseIntegerSchemaElement(str);
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
        stringArgs.put(c, new StringArgumentMarshaller());
      }
    }
  }

  private void parseIntegerSchemaElement(String element) {
    for (int i = 0; i < element.length(); ++i) {
      char c;
      if (Character.isLetter(c = element.charAt(i))) {
        integerArgs.put(c, new IntegerArgumentMarshaller());
      }
    }
  }

  private boolean isBooleanSchemaElement(String elementTail) {
    return elementTail.equals("#");
  }

  private boolean isStringSchemaElement(String elementTail) {
    return elementTail.equals("*");
  }

  private boolean isIntegerSchemaElement(String elementTail) {return elementTail.equals("@");}

  private boolean parseArguments() throws ArgsException {
    for (currentArgument = 0; currentArgument < args.length; ++currentArgument) {
      String arg = args[currentArgument];
      parseArgument(arg);
    }
    return true;
  }

  private void parseArgument(String arg) throws ArgsException{
    if (arg.startsWith("-")) {
      parseElements(arg);
    }
  }

  private void parseElements(String arg) throws ArgsException {
    for (int i = 1; i < arg.length(); ++i) {
      parseElement(arg.charAt(i));
    }
  }

  private void parseElement(char argChar) throws ArgsException {
    if (setArgument(argChar)) {
      argsFound.add(argChar);
    } else {
      unexpectedArguments.add(argChar);
      valid = false;
    }
  }

  private boolean setArgument(char argChar) throws ArgsException {
    boolean set = true;
    if (isBooleanArg(argChar)) {
      setBooleanArg(argChar);
    } else if (isStringArg(argChar)) {
      setStringArg(argChar);
    } else if (isIntegerArg(argChar)) {
      setIntegerArgs(argChar);
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

  private boolean isIntegerArg(char argChar) {
    return integerArgs.containsKey(argChar);
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
        case MISSING_BOOLEAN:
          return String.format("Could not find boolean parameter for -%c.", errorArgumentId);
        case MISSING_STRING:
          return String.format("Could not find string parameter for -%c.", errorArgumentId);
        case MISSING_INTEGER:
          return String.format("Could not find integer parameter for -%c.", errorArgumentId);
        case INVALID_INTEGER:
          return String.format("Could not parse integer parameter for -%c.", errorParameter);
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

  private void setBooleanArg(char argChar) throws ArgsException {
    int index = currentArgument + 1;
    try {
      booleanArgs.get(argChar).setBoolean(Boolean.parseBoolean(args[index]));
    } catch (ArrayIndexOutOfBoundsException e) {
      valid = false;
      errorArgumentId = argChar;
      errorCode = ErrorCode.MISSING_BOOLEAN;
      throw new ArgsException();
    }
  }

  public boolean getBoolean(char arg) {
    ArgumentMarshaller am = booleanArgs.get(arg);
    return am != null && am.getBoolean();
  }

  private void setStringArg(char argChar) throws ArgsException {
    int index = currentArgument + 1;
    try {
      stringArgs.get(argChar).setString(args[index]);
    } catch (ArrayIndexOutOfBoundsException e) {
      valid = false;
      errorArgumentId = argChar;
      errorCode = ErrorCode.MISSING_STRING;
      throw new ArgsException();
    }
  }

  public String getString(char arg) {
    ArgumentMarshaller am = stringArgs.get(arg);
    return am == null ? "" : am.getString();
  }

  private void setIntegerArgs(char argChar) throws ArgsException {
    int index = currentArgument + 1;
    try {
      integerArgs.get(argChar).setInteger(Integer.parseInt(args[index]));
    } catch (ArrayIndexOutOfBoundsException e) {
      valid = false;
      errorArgumentId = argChar;
      errorCode = ErrorCode.MISSING_INTEGER;
      throw new ArgsException();
    } catch (NumberFormatException e) {
      valid = false;
      errorArgumentId = argChar;
      errorParameter = args[index];
      errorCode = ErrorCode.MISSING_INTEGER;
      throw new ArgsException();
    }
  }

  public int getInteger(char arg) {
    ArgumentMarshaller am = integerArgs.get(arg);
    return am == null ? 0 : am.getInteger();
  }

  public boolean has(char arg) {
    return argsFound.contains(arg);
  }

  public boolean isValid() {
    return valid;
  }

  private class ArgumentMarshaller {
    private boolean booleanValue = false;
    private String stringValue;
    private int integerValue;

    public void setBoolean(boolean booleanValue) {
      this.booleanValue = booleanValue;
    }

    public boolean getBoolean() {
      return booleanValue;
    }

    public void setString(String stringValue) {
      this.stringValue = stringValue;
    }

    public String getString() {
      return stringValue == null ? "" : stringValue;
    }

    public void setInteger(int integerValue) {
      this.integerValue = integerValue;
    }

    public int getInteger() {
      return integerValue;
    }
  }

  private class BooleanArgumentMarshaller extends ArgumentMarshaller {}

  private class StringArgumentMarshaller extends ArgumentMarshaller {}

  private class IntegerArgumentMarshaller extends ArgumentMarshaller {}
}
