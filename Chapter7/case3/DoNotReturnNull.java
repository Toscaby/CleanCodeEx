package Chapter7.case3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Tosca
 * @date 27/1/2020
 */
public class DoNotReturnNull {
  public void test() {
    List<Employee> employees = getEmployees();
    float totalPay = 0f;
    // 不需要判断 if (employees != null)
    for (Employee e : employees) {
      totalPay += e.getPay();
    }
  }

  private List<Employee> getEmployees() {
    if (!hasEmployees()) {
      // 返回空列表而不是返回null
      return Collections.emptyList();
    } else {
      List<Employee> list = new ArrayList<>();
      // ...
      return list;
    }
  }

  private boolean hasEmployees() {return false;}
}
