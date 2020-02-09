package Chapter11.proxy;

import java.lang.reflect.Proxy;

/**
 * 代理模式的例子
 * Q：什么情景下需要代理？
 * 个人理解：1. 远程或者因为安全性限制 2. 不能直接对数据进行操作
 *
 * @author Tosca
 * @date 9/2/2020
 */
public class BankTest {
  public static void main(String[] args) {
    Bank bank = (Bank) Proxy.newProxyInstance(
        Bank.class.getClassLoader(),
        new Class[] {Bank.class},
        new BankProxyHandler(new BankImpl())
    );

  }
}
