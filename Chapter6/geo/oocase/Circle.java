package Chapter6.geo.oocase;

import Chapter6.encapsulation.Point;

/**
 * @author Tosca
 * @date 26/1/2020
 */
public class Circle implements Shape {
  // oo方便在不改变方法(接口)的情况下增加新类
  // 反过来说，不容易增加新的接口，因为实现类都需要更改
  private final double PI = 3.141592653589793;
  private Point center;
  private double radius;

  @Override
  public double area() {
    return PI * radius * radius;
  }
}
