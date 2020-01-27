package Chapter7.case2;

/**
 * 依调用者需要定义异常类
 * @author Tosca
 * @date 27/1/2020
 */
public class RightCase {
  public void openPort() {
    LocalPort port = new LocalPort();
    try {
      port.open();
    } catch (PortDeviceFailure e) {
      reportPortError(e);
    } finally {
      // ...
    }
  }

  private void reportPortError(Throwable e) {}

  // 打包三方API,解耦实际业务与三方API
  public static class LocalPort {
    private ACMEPort innerPort;

    public LocalPort() {
      innerPort = new ACMEPort();
    }

    public void open() {
      try {
        innerPort.open();
      } catch (ATM1212UnlockedException e) {
        throw new PortDeviceFailure(e);
      } catch (DeviceResponseException e) {
        throw new PortDeviceFailure(e);
      } catch (GMXError e) {
        throw new PortDeviceFailure(e);
      }
    }
  }

  public static class PortDeviceFailure extends RuntimeException {
    public PortDeviceFailure(Throwable e) {}
  }
}
