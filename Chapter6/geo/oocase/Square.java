package Chapter6.geo.oocase;

import Chapter6.encapsulation.Point;

/**
 * @author Tosca
 * @date 26/1/2020
 */
public class Square implements Shape {
  private Point topLeft;
  private double side;

  @Override
  public double area() {
    return side * side;
  }
}
