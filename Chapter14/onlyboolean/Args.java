package Chapter14.onlyboolean;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * only boolean version
 * @author Tosca
 * @date 29/1/2020
 */
public class Args {
  private String schema;
  private String[] args;
  private boolean valid;
  private Set<Character> unexpectedArguments = new TreeSet<>();
  private Map<Character, Boolean> booleanArgs = new HashMap<>();
  private int numberOfArguments = 0;

  public Args(String schema, String[] args) {
    this.schema = schema;
    this.args = args;
    valid = parse();
  }

  private boolean parse() {
    if (schema.length() == 0 && args.length == 0) {
      return true;
    }
    parseSchema();
    parseArguments();
    return valid;
  }

  private boolean parseSchema() {
    for (String element : schema.split(",")) {
      parseSchemaElement(element);
    }
    return true;
  }

  private void parseSchemaElement(String element) {
    if (element.length() > 0) {
      parseBooleanSchemaElement(element);
    }
  }

  private void parseBooleanSchemaElement(String element) {
    for (int i = 0; i < element.length(); ++i) {
      char c;
      if (Character.isLetter(c = element.charAt(i))) {
        booleanArgs.put(c, false);
      }
    }
  }

  private boolean parseArguments() {
    for (String arg : args) {
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
    if (isBooleanArg(argChar)) {
      numberOfArguments++;
      setBooleanArg(argChar);
    } else {
      unexpectedArguments.add(argChar);
    }
  }

  private boolean isBooleanArg(char argChar) {
    return booleanArgs.containsKey(argChar);
  }

  private void setBooleanArg(char argChar) {
    booleanArgs.put(argChar, true);
  }

  public int cardinality() {
    return numberOfArguments;
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
    return booleanArgs.get(arg);
  }

  public boolean isValid() {
    return valid;
  }
}
