package Chapter9.eachtestoneassert;

import org.jfree.date.SerialDate;
import org.junit.Test;

/**
 * 原则2：每个测试函数一个概念
 * @author Tosca
 * @date 28/1/2020
 */
public class AddMonthTest {
  // 反例 ???
  // 完全不知道在干嘛...
  @Test
  public void testAddMonths() {
    SerialDate d1 = SerialDate.createInstance(31, 5, 2004);

    SerialDate d2 = SerialDate.addMonths(1, d1);
    assertEquals(30, d2.getDayOfMonth());
    assertEquals(6, d2.getMonth());
    assertEquals(2004, d2.getYYYY());

    SerialDate d3 = SerialDate.addMonths(2, d1);
    assertEquals(31, d2.getDayOfMonth());
    assertEquals(7, d2.getMonth());
    assertEquals(2004, d2.getYYYY());

    // ??????
    SerialDate d4 = SerialDate.addMonths(1, SerialDate.addMonths(1, d1));
    assertEquals(30, d2.getDayOfMonth());
    assertEquals(7, d2.getMonth());
    assertEquals(2004, d2.getYYYY());
  }

  private void assertEquals(int val1, int val2) {
    assert val1 == val2;
  }
}
