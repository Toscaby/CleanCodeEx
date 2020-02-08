package Chapter16;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Locale;

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

  private final int index;
  private static final DateFormatSymbols
      dateSymbols = new SimpleDateFormat("d-MMMM-yyyy", Locale.US)
      .getDateFormatSymbols();
  static final int[] LAST_DAY_OF_MONTH =
      {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

  Month(int index) {
    this.index = index;
  }

  public static Month fromInt(int monthIndex) {
    for (Month m : Month.values()) {
      if (m.index == monthIndex) {
        return m;
      }
    }
    throw new IllegalArgumentException("Invalid month index " + monthIndex);
  }

  public static Month parse(String s) {
    s = s.trim();
    for (Month month : Month.values()) {
      if (month.matches(s)) {
        return month;
      }
    }

    try {
      return fromInt(Integer.parseInt(s));
    } catch (NumberFormatException e) {
    }
    throw new IllegalArgumentException("Invalid month " + s);
  }

  private boolean matches(String s) {
    return s.equalsIgnoreCase(toString()) ||
        s.equalsIgnoreCase(toShortString());
  }

  public int lastDay() {
    return LAST_DAY_OF_MONTH[index];
  }

  public int quarter() {
    return 1+ ( index - 1) / 3;
  }

  public String toString() {
    return dateSymbols.getMonths()[index - 1];
  }

  public String toShortString() {
    return dateSymbols.getShortMonths()[index - 1];
  }

  public int toInt() {return index;}
}

