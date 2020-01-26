package Chapter6.encapsulation;

/**
 * 错误例子
 * @author Tosca
 * @date 26/1/2020
 */
public class CartesianPoint {
  // 暴露实现
//  public double x;
//  public double y;

  // 实际上还是暴露了实现
  private double x;
  private double y;

  public double getX() {
    return x;
  }

  public void setX(double x) {
    this.x = x;
  }

  public double getY() {
    return y;
  }

  public void setY(double y) {
    this.y = y;
  }
}
