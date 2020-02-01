package Chapter14.finalversion;

import Chapter14.ArgsException;

import java.util.*;

/**
 * version #8: refactor ErrorCode and ArgsException, then remove error code from Args
 * @author Tosca
 * @date 1/2/2020
 */
@SuppressWarnings("unused")
public class Args {
  private String schema;
  private Map<Character, ArgumentMarshaller> marshallers = new HashMap<>();
  private Set<Character> argsFound = new HashSet<>();
  private List<String> argsList;
  private ListIterator<String> currentArgument;

  public Args(String schema, String[] args) throws ArgsException {
    this.schema = schema;
    argsList = Arrays.asList(args);
    parse();
  }

  private void parse() throws ArgsException {
    parseSchema();
    parseArguments();
  }

  private void parseSchema() throws ArgsException {
    for (String element : schema.split(",")) {
      if (element.length() > 0) {
        String trimmedElement = element.trim();
        parseSchemaElement(trimmedElement);
      }
    }
  }

  private void parseSchemaElement(String element) throws ArgsException {
    String str = element.substring(0, element.length() - 1);
    String elementTail = element.substring(element.length() - 1);
    validateSchemaElement(str, elementTail);
    for (int i = 0; i < str.length(); ++i) {
      parseSchemaElement(str.charAt(i), elementTail);
    }
  }

  // 我把这里改成正则了
  private void validateSchemaElement(String element, String tail) throws ArgsException {
    if (!element.matches("[a-zA-Z]+")) {
      throw new ArgsException("Bad character in: " + schema,
          ArgsException.ErrorCode.INVALID_ARGUMENT_NAME, tail.charAt(0));
    }
  }

  private void parseSchemaElement(Character c, String tail) throws ArgsException {
    if (isBooleanSchemaElement(tail)) {
      marshallers.put(c, new BooleanArgumentMarshaller());
    } else if (isStringSchemaElement(tail)) {
      marshallers.put(c, new StringArgumentMarshaller());
    } else if (isIntegerSchemaElement(tail)) {
      marshallers.put(c, new IntegerArgumentMarshaller());
    } else if (isDoubleSchemaElement(tail)) {
      marshallers.put(c, new DoubleArgumentMarshaller());
    } else {
      throw new ArgsException(
          String.format("Arguments: %c has invalid for: %s.", c, tail),
            ArgsException.ErrorCode.INVALID_FORMAT, tail.charAt(0));
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

  private void parseArguments() throws ArgsException {
    for (currentArgument = argsList.listIterator(); currentArgument.hasNext(); ) {
      String arg = currentArgument.next();
      parseArgument(arg);
    }
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
      throw new ArgsException(ArgsException.ErrorCode.UNEXPECTED_ARGUMENT, null, argChar);
    }
  }

  private boolean setArgument(char argChar) throws ArgsException {
    ArgumentMarshaller am = marshallers.get(argChar);
    if (am == null) {
      return false;
    }
    try {
      am.set(currentArgument);
      return true;
    } catch (ArgsException e) {
      e.setErrorArgumentId(argChar);
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

  public boolean getBoolean(char arg) {
    ArgumentMarshaller am = marshallers.get(arg);
    try {
      return am != null && (Boolean) am.get();
    } catch (ClassCastException e) {
      return false;
    }
  }

  public String getString(char arg) {
    ArgumentMarshaller am = marshallers.get(arg);
    try {
      return am == null ? "" : (String) am.get();
    } catch (ClassCastException e) {
      return "";
    }
  }

  public int getInteger(char arg) {
    ArgumentMarshaller am = marshallers.get(arg);
    try {
      return am == null ? 0 : (Integer) am.get();
    } catch (ClassCastException e) {
      return 0;
    }
  }

  public double getDouble(char arg) {
    ArgumentMarshaller am = marshallers.get(arg);
    try {
      return am == null ? 0.0 : (Double) am.get();
    } catch (ClassCastException e) {
      return 0.0;
    }
  }

  public boolean has(char arg) {
    return argsFound.contains(arg);
  }

  private abstract class ArgumentMarshaller {
    protected String arg;

    public void set(ListIterator<String> it) throws ArgsException {
      try {
        setValue(valueOf(it));
      } catch (NoSuchElementException e) {
        handleNoSuchElementException();
      } catch (ArgsException e) {
        handleArgsException(e);
        throw e;
      }
    }

    private String valueOf(ListIterator<String> it) {
      arg = currentArgument.next();
      currentArgument.previous();
      return arg;
    }

    protected void handleArgsException(ArgsException ae) {}

    public abstract Object get();
    protected abstract void setValue(String val) throws ArgsException;
    protected abstract void handleNoSuchElementException() throws ArgsException;
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
    protected void handleNoSuchElementException() throws ArgsException {
      throw new ArgsException(ArgsException.ErrorCode.MISSING_BOOLEAN);
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
    protected void handleNoSuchElementException() throws ArgsException {
      throw new ArgsException(ArgsException.ErrorCode.MISSING_STRING);
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
    protected void handleNoSuchElementException() throws ArgsException {
      throw new ArgsException(ArgsException.ErrorCode.MISSING_INTEGER);
    }

    @Override
    protected void handleArgsException(ArgsException ae) {
      ae.setErrorParameter(arg);
      ae.setErrorCode(ArgsException.ErrorCode.INVALID_INTEGER);
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
    protected void handleNoSuchElementException() throws ArgsException {
      throw new ArgsException(ArgsException.ErrorCode.MISSING_DOUBLE);
    }

    @Override
    protected void handleArgsException(ArgsException ae) {
      ae.setErrorParameter(arg);
      ae.setErrorCode(ArgsException.ErrorCode.INVALID_DOUBLE);
    }
  }
}
