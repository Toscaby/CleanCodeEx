package Chapter4.rightcases;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * 解释/阐释/warning
 *
 * @author Tosca
 * @date 2020/1/22
 */
public class ExplanationComents {

  // 解释意图： 这里不用SimpleDateFormat.getInstance()的原因
  public static SimpleDateFormat makeStandardHttpDateFormat() {

    // SimpleDateFormat is not thread safe,
    // so we need to create each instance independently.
    SimpleDateFormat df = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
    df.setTimeZone(TimeZone.getTimeZone("GMT"));
    return df;
  }

  // 解释意图：for循环开很多线程
  public void testConcurrentAddWidgets() {
    // do something

    // This is our best attempt to get a race condition
    // by creating large nuber of threads.
    for (int i = 0; i < 25000; ++i) {
      // start new threads here..
    }

    // do something
  }

  // 警示型
  // Don't run unless you have some time to kill.
  public void testWithReallyBigFile() {
    writeLines(100000);
    // do something
    // assert..
  }

  private void writeLines(int lineNmuber) {}


  //阐释：解释某些晦涩难懂的参数或者返回值
  public void testCompareTo() {
    String a = "abc", b = "def";

    assertTrue(a.compareTo(b) == -1); // a < b
    assertTrue(a.compareTo(b) != 0); // a != b
    assertTrue(a.compareTo(b) == 1); // a > b
  }

  public void assertTrue(boolean b) {
    assert b;
  }
}
