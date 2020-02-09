package Chapter11.proxy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 代理的实际实现
 *
 * @author Tosca
 * @date 9/2/2020
 */
public class BankImpl implements Bank {
  private List<Account> accounts;

  @Override
  public Collection<Account> getAccounts() {
    return accounts;
  }

  @Override
  public void setAccounts(Collection<Account> accounts) {
    this.accounts = new ArrayList<>();
    for (Account account : accounts) {
      this.accounts.add(account);
    }
  }
}
