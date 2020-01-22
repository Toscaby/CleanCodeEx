package Chapter3.case1;

/**
 * @author Tosca
 * @date 2020/1/21
 */
public class WrongCase {

  // WRONG CASE --------------------------------------
  public class Employee {
    public static final int COMMISSIONED = 0;
    public static final int HOURLY = 1;
    public static final int SALARIED = 2;

    int type;
  }

  public class Payroll {
    public class InvalidEmployeeType extends Exception{

    }

    public float calculatePay(Employee e) throws InvalidEmployeeType {
      switch (e.type) {
        case Employee.COMMISSIONED:
          return calculateCommissionedPay(e);
        case Employee.HOURLY:
          return calculateHourlyPay(e);
        case Employee.SALARIED:
          return calculateSalariedPay(e);
        default:
          throw new InvalidEmployeeType();
      }
    }

    private float calculateCommissionedPay(Employee e) {
      return 0f;
    }

    private float calculateHourlyPay(Employee e) {
      return 0f;
    }

    private float calculateSalariedPay(Employee e) {
      return 0f;
    }
  }
  // WRONG CASE END--------------------------------------

}

