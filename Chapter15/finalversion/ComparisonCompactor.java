package Chapter15.finalversion;

import Chapter15.Assert;

/**
 * transition version
 * @author Tosca
 * @date 2/2/2020
 */
public class ComparisonCompactor {
  private static final String ELLIPSIS = "...";
  private static final String DELTA_END = "]";
  private static final String DELTA_START = "[";

  private int contextLength;
  private String expected;
  private String actual;
  private int prefixLength; // N1
  private int suffixLength; //N1

  public ComparisonCompactor(int contextLength, String expected, String actual) {
    this.contextLength = contextLength;
    this.expected = expected;
    this.actual = actual;
  }

  public String formatCompactedComparison(String msg) { //N7
    String compactExpected = expected;
    String compactActual = actual;
    if (shouldBeCompacted()) { //G29
      findCommonPrefixAndSuffix();
      compactExpected = compact(expected);
      compactActual = compact(actual);
    }
    return Assert.format(msg, compactExpected, compactActual);
  }

  private boolean shouldBeCompacted() { //G29
    return expected != null && actual != null && !areStringsEqual();
  }

  private boolean areStringsEqual() {
    return expected.equals(actual);
  }

  private void findCommonPrefixAndSuffix() {
    findCommonPrefix(); //G31 暴露时序性耦合
    suffixLength = 0;
    for (; !suffixOverlapsPrefix(suffixLength); suffixLength++) {
      if (charFromEnd(expected, suffixLength) != charFromEnd(actual, suffixLength)) {
        break;
      }
    }
  }

  private boolean suffixOverlapsPrefix(int suffixLength) {
    return actual.length() <= suffixLength + prefixLength ||
        expected.length() <= suffixLength + prefixLength;
  }

  private char charFromEnd(String s, int i) {
    return s.charAt(s.length() - 1 - i);
  }

  private void findCommonPrefix() {
    prefixLength = 0;
    int end = Math.max(expected.length(), actual.length());
    for (; prefixLength < end; prefixLength++) {
      if (expected.charAt(prefixLength) != actual.charAt(suffixLength)) {
        break;
      }
    }
  }

  private String compact(String source) {
    return new StringBuffer()
        .append(startingEllipsis())
        .append(startingContext())
        .append(DELTA_START)
        .append(delta(source))
        .append(DELTA_END)
        .append(endingContext())
        .append(endingEllipsis())
        .toString();
  }

  private String startingEllipsis() {
    return prefixLength > contextLength ? ELLIPSIS : "";
  }

  private String startingContext() {
    int contextStart = Math.max(0, prefixLength - contextLength);
    int contextEnd = prefixLength;
    return expected.substring(contextStart, contextEnd);
  }

  private String delta(String s) {
    int deltaStart = prefixLength;
    int deltaEnd = s.length() - suffixLength;
    return s.substring(deltaStart, deltaEnd);
  }

  private String endingContext() {
    int contextStart = expected.length() - suffixLength;
    int contextEnd = Math.min(contextStart + contextLength, expected.length());
    return expected.substring(contextStart, contextEnd);
  }

  private String endingEllipsis() {
    return suffixLength > contextLength ? ELLIPSIS : "";
  }
}
