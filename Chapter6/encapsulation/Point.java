package Chapter6.encapsulation;

/**
 * @author Tosca
 * @date 26/1/2020
 */
public interface Point {
  // 这个封装提供一种规则，分别获取，但必须一次设置
  double getX();
  double getY();
  void setCartesian(double x, double y);

  double getR();
  double getTheta();
  void setPolar(double r, double theta);
}
