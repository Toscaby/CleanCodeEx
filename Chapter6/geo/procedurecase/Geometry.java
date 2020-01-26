package Chapter6.geo.procedurecase;

/**
 * @author Tosca
 * @date 26/1/2020
 */
public class Geometry {
  // 过程式代码方便在不改动既有数据结构的前提下添加新方法，如此类增加primeter()，其他形状类数据结构不受影响
  // 反过来说，难以添加新的数据结构，因为需要修改方法
  public final double PI = 3.141592653589793;

  public double area(Object shape) throws NoSuchFieldException {
    if (shape instanceof Square) {
      Square s = (Square) shape;
      return s.side * s.side;
    } else if (shape instanceof Rectangle) {
      Rectangle r = (Rectangle) shape;
      return r.width * r.height;
    } else if (shape instanceof Circle) {
      Circle c = (Circle) shape;
      return PI * c.radius * c.radius;
    }
    throw new NoSuchFieldException();
  }
}
