package Chapter14.continuerefactor;

import Chapter14.ArgsException;

import java.text.ParseException;
import java.util.*;

/**
 * version #7: add double args
 * @author Tosca
 * @date 1/2/2020
 */
@SuppressWarnings("unused")
public class Args7 {
  private String schema;
  private boolean valid = true;
  private Set<Character> unexpectedArguments = new TreeSet<>();
  private Map<Character, ArgumentMarshaller> arguments = new HashMap<>();
  private Set<Character> argsFound = new HashSet<>();
  private char errorArgumentId = '\0';
  private String errorParameter = "TILT";
  private List<String> argsList;
  private ListIterator<String> currentArgument;

  private ErrorCode errorCode = ErrorCode.OK;
  enum ErrorCode {
    OK,
    MISSING_BOOLEAN,
    MISSING_STRING,
    MISSING_INTEGER,
    INVALID_INTEGER,
    MISSING_DOUBLE,
    INVALID_DOUBLE,
    UNEXPECTED_ARGUMENT
  }

  public Args7(String schema, String[] args) throws ParseException, ArgsException {
    this.schema = schema;
    argsList = Arrays.asList(args);
    valid = parse();
  }

  private boolean parse() throws ParseException {
    if (schema.length() == 0 && argsList.size() == 0) {
      return true;
    }
    parseSchema();
    try {
      parseArguments();
    } catch (ArgsException e) {

    }
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
    validateSchemaElement(str);
    for (int i = 0; i < str.length(); ++i) {
      parseSchemaElement(str.charAt(i), elementTail);
    }
  }

  // 我把这里改成正则了
  private void validateSchemaElement(String element) throws ParseException {
    if (!element.matches("[a-zA-Z]+")) {
      throw new ParseException("Bad character in: " + schema, 0);
    }
  }

  private void parseSchemaElement(Character c, String tail) throws ParseException {
    if (isBooleanSchemaElement(tail)) {
      arguments.put(c, new BooleanArgumentMarshaller());
    } else if (isStringSchemaElement(tail)) {
      arguments.put(c, new StringArgumentMarshaller());
    } else if (isIntegerSchemaElement(tail)) {
      arguments.put(c, new IntegerArgumentMarshaller());
    } else if (isDoubleSchemaElement(tail)) {
      arguments.put(c, new DoubleArgumentMarshaller());
    } else {
      throw new ParseException(
          String.format("Arguments: %c has invalid for: %s.", c, tail), 0);
    }
  }

  private boolean isBooleanSchemaElement(String elementTail) {
    return elementTail.equals("#");
  }

  private boolean isStringSchemaElement(String elementTail) {
    return elementTail.equals("*");
  }

  private boolean isIntegerSchemaElement(String elementTail) {
    return elementTail.equals("@");
  }

  private boolean isDoubleSchemaElement(String elementTail) {
    return elementTail.equals("%");
  }

  private boolean parseArguments() throws ArgsException {
    for (currentArgument = argsList.listIterator(); currentArgument.hasNext(); ) {
      String arg = currentArgument.next();
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
    ArgumentMarshaller am = arguments.get(argChar);
    if (am == null) {
      return false;
    }
    try {
      am.set(currentArgument);
      return true;
    } catch (ArgsException e) {
      valid = false;
      errorArgumentId = argChar;
      throw e;
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
    switch (errorCode) {
      case OK:
        throw new Exception("TILT: Should not get here.");
      case UNEXPECTED_ARGUMENT:
        return unexpectedArgumentMessage();
      case MISSING_BOOLEAN:
        return String.format("Could not find boolean parameter for -%c.", errorArgumentId);
      case MISSING_STRING:
        return String.format("Could not find string parameter for -%c.", errorArgumentId);
      case MISSING_INTEGER:
        return String.format("Could not find integer parameter for -%c.", errorArgumentId);
      case INVALID_INTEGER:
        return String.format("Could not parse integer parameter for -%c.", errorParameter);
      case MISSING_DOUBLE:
        return String.format("Could not find double parameter for -%c.", errorArgumentId);
      case INVALID_DOUBLE:
        return String.format("Could not parse double parameter for -%c.", errorParameter);
    }
    return "";
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
    ArgumentMarshaller am = arguments.get(arg);
    try {
      return am != null && (Boolean) am.get();
    } catch (ClassCastException e) {
      return false;
    }
  }

  public String getString(char arg) {
    ArgumentMarshaller am = arguments.get(arg);
    try {
      return am == null ? "" : (String) am.get();
    } catch (ClassCastException e) {
      return "";
    }
  }

  public int getInteger(char arg) {
    ArgumentMarshaller am = arguments.get(arg);
    try {
      return am == null ? 0 : (Integer) am.get();
    } catch (ClassCastException e) {
      return 0;
    }
  }

  public double getDouble(char arg) {
    ArgumentMarshaller am = arguments.get(arg);
    try {
      return am == null ? 0.0 : (Double) am.get();
    } catch (ClassCastException e) {
      return 0.0;
    }
  }

  public boolean has(char arg) {
    return argsFound.contains(arg);
  }

  public boolean isValid() {
    return valid;
  }

  private abstract class ArgumentMarshaller {
    protected String arg;

    public void set(ListIterator<String> it) throws ArgsException {
      try {
        setValue(valueOf(it));
      } catch (NoSuchElementException e) {
        handleNoSuchElementException();
        throw new ArgsException();
      } catch (ArgsException e) {
        handleArgsException();
        throw e;
      }
    }

    private String valueOf(ListIterator<String> it) {
      arg = currentArgument.next();
      currentArgument.previous();
      return arg;
    }

    protected void handleArgsException() {}

    public abstract Object get();
    protected abstract void setValue(String val) throws ArgsException;
    protected abstract void handleNoSuchElementException();
  }

  private class BooleanArgumentMarshaller extends ArgumentMarshaller {
    protected boolean booleanValue = false;

    @Override
    public Object get() {
      return booleanValue;
    }

    @Override
    protected void setValue(String value) {
      this.booleanValue = Boolean.parseBoolean(value);
    }

    @Override
    protected void handleNoSuchElementException(){
      errorCode = ErrorCode.MISSING_BOOLEAN;
    }
  }

  private class StringArgumentMarshaller extends ArgumentMarshaller {
    private String stringValue;

    @Override
    public Object get() {
      return stringValue;
    }

    @Override
    protected void setValue(String value) {
      this.stringValue = value;
    }

    @Override
    protected void handleNoSuchElementException() {
      errorCode = ErrorCode.MISSING_STRING;
    }
  }

  private class IntegerArgumentMarshaller extends ArgumentMarshaller {
    private int integerValue;

    @Override
    public Object get() {
      return integerValue;
    }

    @Override
    protected void setValue(String value) throws ArgsException {
      try {
        this.integerValue = Integer.parseInt(value);
      } catch (NumberFormatException e) {
        throw new ArgsException();
      }
    }

    @Override
    protected void handleNoSuchElementException() {
      errorCode = ErrorCode.MISSING_INTEGER;
    }

    @Override
    protected void handleArgsException() {
      errorParameter = arg;
      errorCode = ErrorCode.INVALID_INTEGER;
    }
  }

  private class DoubleArgumentMarshaller extends ArgumentMarshaller {
    private double doubleValue;

    @Override
    public Object get() {
      return doubleValue;
    }

    @Override
    protected void setValue(String val) throws ArgsException {
      try {
        this.doubleValue = Double.parseDouble(val);
      } catch (NumberFormatException e) {
        throw new ArgsException();
      }
    }

    @Override
    protected void handleNoSuchElementException() {
      errorCode = ErrorCode.MISSING_DOUBLE;
    }

    @Override
    protected void handleArgsException() {
      errorParameter = arg;
      errorCode = ErrorCode.INVALID_DOUBLE;
    }
  }
}
