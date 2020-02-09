package Chapter11.proxy;

import java.util.Collection;

/**
 * 代理
 *
 * @author Tosca
 * @date 9/2/2020
 */
public interface Bank {
  Collection<Account> getAccounts();
  void setAccounts(Collection<Account> accounts);
}
