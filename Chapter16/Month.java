package Chapter16;

/**
 * @author Tosca
 * @date 7/2/2020
 */
public enum Month {
  JANUARY(1),
  FEBRUARY(2),
  MARCH(3),
  APRIL(4),
  MAY(5),
  JUNE(6),
  JULY(7),
  AUGUST(8),
  SEPTEMBER(9),
  OCTOBER(10),
  NOVEMBER(11),
  DECEMBER(12);

  Month(int index) {
    this.index = index;
  }

  public static Month make(int monthIndex) {
    for (Month m : Month.values()) {
      if (m.index == monthIndex) {
        return m;
      }
    }
    throw new IllegalArgumentException("Invalid month index " + monthIndex);
  }

  public final int index;
}

