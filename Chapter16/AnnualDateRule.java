package Chapter16;

/**
 * @author Tosca
 * @date 7/2/2020
 */
public abstract class AnnualDateRule implements Cloneable {
  protected AnnualDateRule() {
  }

  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

  public abstract DayDate getDate(int var1);
}