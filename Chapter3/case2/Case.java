package Chapter3.case2;

/**
 * @author Tosca
 * @date 2020/1/21
 */
public class Case {
  public class Cryptographer {
    public String decrypt(String codePhrase, String password) {
      return null;
    }
  }

  public class User {
    public String getPhraseEncodedByPassword() {
      return "";
    }
  }

  public class UserGateway {
    public User findByName(String userName) {
      return new User();
    }
  }

  public class Session {
    public void initialized() {}
  }

  public class UserValidator {
    private Cryptographer cryptographer;

    /** WRONG CASE */
    public boolean checkPassword(String userName, String password) {
      User user = new UserGateway().findByName(userName);
      if (user != null) {
        String codedPhrase = user.getPhraseEncodedByPassword();
        String phrase = cryptographer.decrypt(codedPhrase, password);
        if ("Valid Password".equals(phrase)) {
          /** 初始化会话和校验密码时序性耦合，造成checkPassword只应该初始化会话的时候调用 */
          new Session().initialized();
          return true;
        }
      }
      return false;
    }

    /** 修改为 */
    public boolean checkPasswordAndInitializeSession(String userName, String password) {
      User user = new UserGateway().findByName(userName);
      if (user != null) {
        String codedPhrase = user.getPhraseEncodedByPassword();
        String phrase = cryptographer.decrypt(codedPhrase, password);
        if ("Valid Password".equals(phrase)) {
          new Session().initialized();
          return true;
        }
      }
      return false;
    }

    private boolean isUserExist(String userName) {
      return true;
    }
  }
}
