package Chapter9.neattest.doublestandard;

/**
 * @author Tosca
 * @date 28/1/2020
 */
public class MockControllerHardware {
  private boolean heater;
  private boolean blower;
  private boolean cooler;
  private boolean hiTempAlarm;
  private boolean loTempAlarm;

  // before refactor
  public void setTemp(int temp) {}

  public boolean heaterState() {return heater;}

  public boolean blowerState() {return blower;}

  public boolean coolerState() {return cooler;}

  public boolean hiTempAlarm() {return hiTempAlarm;}

  public boolean loTempAlarm() {return loTempAlarm;}

  // after refactor
  public String getState() {
    String state = "";
    state += heater ? "H" : "h";
    state += blower ? "B" : "b";
    state += cooler ? "C" : "c";
    state += hiTempAlarm ? "H" : "h";
    state += loTempAlarm ? "L" : "l";
    return state;
  }
}
