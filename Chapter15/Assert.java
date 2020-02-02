package Chapter15;

/**
 * @author Tosca
 * @date 1/2/2020
 */
public class Assert {
  public static String format(String message, Object expected, Object actual) {
    String formatted = "";
    if (message != null && !"".equals(message)) {
      formatted = message + " ";
    }

    String expectedString = String.valueOf(expected);
    String actualString = String.valueOf(actual);
    return equalsRegardingNull(expectedString, actualString) ? formatted + "expected: " + formatClassAndValue(expected, expectedString) + " but was: " + formatClassAndValue(actual, actualString) : formatted + "expected:<" + expectedString + "> but was:<" + actualString + ">";
  }

  private static boolean equalsRegardingNull(Object expected, Object actual) {
    if (expected == null) {
      return actual == null;
    } else {
      return isEquals(expected, actual);
    }
  }

  private static boolean isEquals(Object expected, Object actual) {
    return expected.equals(actual);
  }

  private static String formatClassAndValue(Object value, String valueString) {
    String className = value == null ? "null" : value.getClass().getName();
    return className + "<" + valueString + ">";
  }
}
