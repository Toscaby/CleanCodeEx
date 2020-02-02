package Chapter15.transition;

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
  private String compactExpected;
  private String compactActual;
  private int prefixLength; // N1
  private int suffixLength; //N1

  public ComparisonCompactor(int contextLength, String expected, String actual) {
    this.contextLength = contextLength;
    this.expected = expected;
    this.actual = actual;
  }

  public String formatCompactedComparison(String msg) { //N7
    if (canBeCompact()) { //G29
      compactExpectedAndActual(); // G30
      return Assert.format(msg, compactExpected, compactActual);
    } else {
      return Assert.format(msg, expected, actual);
    }
  }

  private boolean canBeCompact() { //G29
    return expected != null && actual != null && !areStringsEqual();
  }

  private boolean areStringsEqual() {
    return expected.equals(actual);
  }

  private void compactExpectedAndActual() {
    findCommonPrefixAndSuffix();
    compactExpected = compactString(expected);
    compactActual = compactString(actual);
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

  private String compactString(String source) {
    return
        computeCommonPrefix() +
        DELTA_START +
        source.substring(prefixLength, source.length() - suffixLength) +
        DELTA_END +
        computeCommonSuffix();
  }

  private String computeCommonPrefix() {
    return (prefixLength > contextLength ? ELLIPSIS : "") +
        expected.substring(Math.max(0, prefixLength - contextLength), prefixLength);
  }

  private String computeCommonSuffix() {
    int end = Math.min(expected.length() - suffixLength + contextLength, expected.length());
    return expected.substring(expected.length() - suffixLength, end) +
        (expected.length() - suffixLength < expected.length() - contextLength ? ELLIPSIS : "");
  }
}
