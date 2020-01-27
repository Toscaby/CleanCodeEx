package Chapter7.case1;

/**
 * @author Tosca
 * @date 27/1/2020
 */
public class DeviceControllerWrong {
  //...

  // 问题：调用者必须在调用之后立刻检查错误，容易遗忘
  public void sendShutDown() {
    DeviceHandle handle = getHandle();
    // Check the state of the device
    if (handle != DeviceHandle.INVALID) {
      // Save the device status to the record field
      retrieveDeviceRecord(handle);
      // if not suspended, shut down
      if (handle != DeviceHandle.DEVICE_SUSPENDED) {
        pauseDevice(handle);
        clearDeviceWorkQueue(handle);
        closeDevice(handle);
      } else {
        // log "Device suspended, unable to shut down"
      }
    } else {
      // log "invalid handle ..."
    }
  }

  private DeviceHandle getHandle() {return null;}
  private void retrieveDeviceRecord(DeviceHandle handle) {}
  private void pauseDevice(DeviceHandle handle) {}
  private void clearDeviceWorkQueue(DeviceHandle handle) {}
  private void closeDevice(DeviceHandle handle) {}
}
