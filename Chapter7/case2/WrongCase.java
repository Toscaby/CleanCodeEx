package Chapter7.case2;

/**
 * @author Tosca
 * @date 27/1/2020
 */
public class WrongCase {
  public void openPort() {
    ACMEPort port = new ACMEPort();

    try {
      port.open();
    } catch (DeviceResponseException e) {
      reportPortError(e);
      // log..
    } catch (ATM1212UnlockedException e) {
      reportPortError(e);
      // log..
    } catch (GMXError e) {
      reportPortError(e);
      // log..
    } finally {
      // ...
    }
  }

  private void reportPortError(Throwable e) {}
}
