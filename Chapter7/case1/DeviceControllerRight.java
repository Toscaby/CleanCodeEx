package Chapter7.case1;

/**
 * 使用异常而非错误返回码
 * @author Tosca
 * @date 27/1/2020
 */
public class DeviceControllerRight {
  //...
  public void sendShutDown() {
    try {
      tryToShutDown();
    } catch (DeviceShutDownError e) {
      e.printStackTrace();
    }
  }

  private void tryToShutDown() throws DeviceShutDownError {
    DeviceHandle handle = getHandle();
    retrieveDeviceRecord(handle);
    pauseDevice(handle);
    clearDeviceWorkQueue(handle);
    closeDevice(handle);
  }

  private DeviceHandle getHandle() {
    // ...
    throw new DeviceShutDownError("Invalid handle for: .." );
    //...
    // throw new DeviceShutDownError("Device suspended, unable to shut down");
  }
  private void retrieveDeviceRecord(DeviceHandle handle) {}
  private void pauseDevice(DeviceHandle handle) {}
  private void clearDeviceWorkQueue(DeviceHandle handle) {}
  private void closeDevice(DeviceHandle handle) {}
}
