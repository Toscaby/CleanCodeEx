package Chapter6.geo.oocase;

import Chapter6.encapsulation.Point;

/**
 * @author Tosca
 * @date 26/1/2020
 */
public class Rectangle implements Shape {
  private Point topLeft;
  private double width;
  private double height;

  @Override
  public double area() {
    return width * height;
  }
}
