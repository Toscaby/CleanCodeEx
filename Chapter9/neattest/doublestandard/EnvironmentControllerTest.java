package Chapter9.neattest.doublestandard;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Tosca
 * @date 28/1/2020
 */
public class EnvironmentControllerTest {
  private static final int WAY_TOW_COLD = 0;
  private Controller controller;
  private MockControllerHardware hw;

  @Before
  public void initialized() {
    controller = new Controller();
    hw = new MockControllerHardware();
  }

  // before refactor
  @Test
  public void turnOnLoTempAlarmAtThresholdBefore() throws Exception {
    hw.setTemp(WAY_TOW_COLD);
    controller.tic();

    assertTrue(hw.heaterState());
    assertTrue(hw.blowerState());
    assertFalse(hw.coolerState());
    assertFalse(hw.hiTempAlarm());
    assertTrue(hw.loTempAlarm());
  }

  private void assertTrue(boolean b) throws Exception {
//    assert b;
    if (!b) {
      throw new Exception("...");
    }
  }

  private void assertFalse(boolean b) throws Exception {
//    assert !b;
    if (b) {
      throw new Exception("...");
    }
  }

  // after refactor
  // 重构后的测试代码 简单得多，具有足够的表达力
  @Test
  public void turnOnLoTempAlarmAtThreshold() throws Exception {
    wayTooCold();
    assertEquals("HBchL", hw.getState());
  }

  @Test
  public void turnOnHiTempAlarmAtThreshold() throws Exception {
    wayTooHot();
    assertEquals("hBCHl", hw.getState());
  }

  @Test
  public void turnOnCoolerAndBlowerIfTooHot() throws Exception {
    tooHot();
    assertEquals("hBChl", hw.getState());
  }

  @Test
  public void turnOnHeaterAndBlowerIfTooCold() throws Exception {
    tooCold();
    assertEquals("HBchl", hw.getState());
  }

  private void wayTooCold() {
    hw.setTemp(WAY_TOW_COLD);
    controller.tic();
  }

  private void wayTooHot() {
    // ...
  }

  private void tooCold() {
    // ...
  }

  private void tooHot() {
    // ...
  }

  // 实际应该是抛异常
  private void assertEquals(String expectedState, String actualState) throws Exception {
//    assert expectedState.equals(actualState);
    if (!expectedState.equals(actualState)) {
      throw new Exception(".....");
    }
  }
}
