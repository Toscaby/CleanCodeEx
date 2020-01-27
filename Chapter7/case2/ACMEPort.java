package Chapter7.case2;

/**
 * @author Tosca
 * @date 27/1/2020
 */
public class ACMEPort {
  public void open() throws ATM1212UnlockedException,
          DeviceResponseException, GMXError {
    String msg = "";
    if (msg == "1212") {
      throw new ATM1212UnlockedException(msg);
    } else if (msg == "response") {
      throw new DeviceResponseException(msg);
    } else if (msg == "GMX") {
      throw new GMXError(msg);
    } else {
      // ...
    }
  }
}
