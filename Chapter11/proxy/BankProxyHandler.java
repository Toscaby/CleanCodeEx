package Chapter11.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 对实际操作数据库进行封装，并转换给代理类
 *
 * @author Tosca
 * @date 9/2/2020
 */
public class BankProxyHandler implements InvocationHandler {
  private Bank bank;

  public BankProxyHandler(Bank bank) {
    this.bank = bank;
  }

  // method defined in InvocationHandler
  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    String methodName = method.getName();
    if (methodName.equals("getAccounts")) {
      bank.setAccounts(getAccountsFromDatabase());
      return bank.getAccounts();
    } else if (methodName.equals("setAccounts")) {
      bank.setAccounts((Collection<Account>) args[0]);
      setAccountsToDatabase(bank.getAccounts());
      return null;
    } else {
      //...
      return null;
    }
  }

  protected Collection<Account> getAccountsFromDatabase() {
    return new ArrayList<>();
  }

  protected void setAccountsToDatabase(Collection<Account> accounts) {}
}
