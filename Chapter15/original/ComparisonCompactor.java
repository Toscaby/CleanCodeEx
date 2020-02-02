package Chapter15.original;

import Chapter15.Assert;

/**
 * Can not pass all the test cases
 * @author Tosca
 * @date 1/2/2020
 */
public class ComparisonCompactor {
  private static final String ELLIPSIS = "...";
  private static final String DELTA_END = "]";
  private static final String DELTA_START = "[";

  private int contextLength;
  private String expected;
  private String actual;
  private int prefix;
  private int suffix;

  public ComparisonCompactor(int contextLength, String expected, String actual) {
    this.contextLength = contextLength;
    this.expected = expected;
    this.actual = actual;
  }

  public String compact(String msg) {
    if (expected == null || actual == null || areStringsEqual()) {
      return Assert.format(msg, expected, actual);
    }

    findCommonPrefix();
    findCommonSuffix();
    String expected = compactString(this.expected);
    String actual = compactString(this.actual);
    return Assert.format(msg, expected, actual);
  }

  private boolean areStringsEqual() {
    return expected.equals(actual);
  }

  private void findCommonPrefix() {
    prefix = 0;
    int end = Math.max(expected.length(), actual.length());
    for (; prefix < end; prefix++) {
      if (expected.charAt(prefix) != actual.charAt(suffix)) {
        break;
      }
    }
  }

  private void findCommonSuffix() {
    int expectedSuffix = expected.length() - 1;
    int actualSuffix = actual.length() - 1;
    for (; expectedSuffix >= prefix && actualSuffix >= prefix; expectedSuffix--, actualSuffix--) {
      if (expected.charAt(expectedSuffix) != actual.charAt(actualSuffix)) {
        break;
      }
    }
    suffix = expected.length() - expectedSuffix - 1;
  }

  private String compactString(String source) {
    String result = DELTA_START +
        source.substring(prefix, source.length() - suffix) + DELTA_END;
    if (prefix > 0) {
      result = computeCommonPrefix() + result;
    }
    if (suffix > 0) {
      result = result + computeCommonSuffix();
    }
    return result;
  }

  private String computeCommonPrefix() {
    return (prefix > contextLength ? ELLIPSIS : "") +
        expected.substring(Math.max(0, prefix - contextLength), prefix);
  }

  private String computeCommonSuffix() {
    int end = Math.min(expected.length() - suffix + contextLength, expected.length());
    return expected.substring(expected.length() - suffix, end) +
        (expected.length() - suffix < expected.length() - contextLength ? ELLIPSIS : "");
  }
}
