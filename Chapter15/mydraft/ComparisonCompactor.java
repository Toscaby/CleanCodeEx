package Chapter15.mydraft;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tosca
 * @date 1/2/2020
 */
public class ComparisonCompactor {
  private int ctxt;
  private String s1;
  private String s2;
  private int pfx;
  private int sfx;

  public ComparisonCompactor(int ctxt, String s1, String s2) {
    this.ctxt = ctxt;
    this.s1 = s1;
    this.s2 = s2;
  }

  public String compact(String msg) {
    // todo 有没有null

    String arg1 = s1, arg2 = s2;
    // 0. 判断是不是equal
    if (s1.equals(s2)) {
      return failureMsg(arg1, arg2);
    } else if (s2.startsWith(s1)) {
      // 1. 判断是不是substring
      // logic here
      return failureMsg(arg1, arg2);
    } else if (s2.endsWith((s1))) {
      // 1. 判断是不是substring
      // logic here
      return failureMsg(arg1, arg2);
    } else if (s1.length() == s2.length()) {
      // 2. 长度是否相等

    } else if (s1.length() > s2.length()) {

    } else if (s1.length() < s2.length()) {

    }
    return "";
  }

  private Map<String, String> compareSameLength(String s1, String s2) {
    Map<String, String> map = new HashMap<>();
    int i = 0, start = 0, end = 0;
    for (; i < s1.length(); ++i) {
      if (s1.charAt(i) != s2.charAt(i)) {
        break;
      }
    }

    for (start = i++; i < s1.length(); ++i) {
      if (s1.charAt(i) == s2.charAt(i)) {
        end = i;
        break;
      }
    }

    map.put(s1.substring(start, end), s2.substring(start, end));
    return map;
  }

  private String failureMsg(String s1, String s2) {
    return "";
  }


}
