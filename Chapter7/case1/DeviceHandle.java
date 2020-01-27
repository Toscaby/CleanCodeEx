package Chapter7.case1;

/**
 * @author Tosca
 * @date 27/1/2020
 */
public class DeviceHandle {
  public static final DeviceHandle INVALID = new DeviceHandle(0);
  public static final DeviceHandle DEVICE_SUSPENDED = new DeviceHandle(1);

  public DeviceHandle(int state) {

  }
}
