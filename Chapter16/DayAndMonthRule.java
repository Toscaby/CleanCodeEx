package Chapter16;

/**
 * @author Tosca
 * @date 7/2/2020
 */
public class DayAndMonthRule extends AnnualDateRule {
  private int dayOfMonth;
  private Month month;

  public DayAndMonthRule() {
    this(1, Month.JANUARY);
  }

  public DayAndMonthRule(int dayOfMonth, Month month) {
    this.setMonth(month);
    this.setDayOfMonth(dayOfMonth);
  }

  public DayDate getDate(int yyyy) {
    return DayDateFactory.makeDate(this.dayOfMonth, this.month, yyyy);
  }

  public int getDayOfMonth() {
    return this.dayOfMonth;
  }

  public Month getMonth() {
    return this.month;
  }

  public void setDayOfMonth(int dayOfMonth) {
    if (dayOfMonth >= 1 && dayOfMonth <= this.month.lastDay()) {
      this.dayOfMonth = dayOfMonth;
    } else {
      throw new IllegalArgumentException("DayAndMonthRule(): dayOfMonth outside valid range.");
    }
  }

  public void setMonth(Month month) {
    this.month = month;

  }
}
