package Chapter3.case1;

/**
 * @author Tosca
 * @date 2020/1/21
 */
public class RightCase {
  public abstract class Employee {
    public static final int COMMISSIONED = 0;
    public static final int HOURLY = 1;
    public static final int SALARIED = 2;

    public abstract boolean isPayDay();
    public abstract float calculatePay();
    public abstract void deliverPay(float amount);
  }

  public class CommissionedEmployee extends Employee {
    @Override public boolean isPayDay() {
      return false;
    }

    @Override public float calculatePay() {
      return 0;
    }

    @Override public void deliverPay(float amount) {

    }
  }

  public class HourlyEmployee extends Employee {
    @Override public boolean isPayDay() {
      return false;
    }

    @Override public float calculatePay() {
      return 0;
    }

    @Override public void deliverPay(float amount) {

    }
  }

  public class SalariedEmployee extends Employee {
    @Override public boolean isPayDay() {
      return false;
    }

    @Override public float calculatePay() {
      return 0;
    }

    @Override public void deliverPay(float amount) {
      // DO SOMETHING
    }
  }

  // to be considered
  public class EmployeeRecord {
    int type;
  }

  public class InvalidEmployeeType extends Exception{
    // Exception
  }

  public interface EmployeeFactory {
    Employee makeEmployee(EmployeeRecord r) throws InvalidEmployeeType;
  }

  public class EmployeeFactoryImpl implements EmployeeFactory {
    @Override public Employee makeEmployee(EmployeeRecord r) throws InvalidEmployeeType {
      switch (r.type) {
        case Employee.COMMISSIONED:
          return new CommissionedEmployee();
        case Employee.HOURLY:
          return new HourlyEmployee();
        case Employee.SALARIED:
          return new SalariedEmployee();
        default:
          throw new InvalidEmployeeType();
      }
    }
  }

  public class Payroll {
    Employee employee;

    public Payroll(Employee e) {
      this.employee = e;
    }

    public void calculateAndDeliverPay() {
      employee.deliverPay(employee.calculatePay());
    }
  }

  public class Client {
    public Client() throws InvalidEmployeeType {
      EmployeeRecord r = new EmployeeRecord();
      r.type = Employee.COMMISSIONED;
      Payroll payroll = new Payroll(new EmployeeFactoryImpl().makeEmployee(r));
      payroll.calculateAndDeliverPay();
    }
  }

}


