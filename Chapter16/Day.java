package Chapter16;

import java.util.Calendar;

/**
 * @author Tosca
 * @date 7/2/2020
 */
public enum Day {
  MONDAY(Calendar.MONDAY),
  TUESDAY(Calendar.TUESDAY),
  WEDNESDAY(Calendar.WEDNESDAY),
  THURSDAY(Calendar.THURSDAY),
  FRIDAY(Calendar.FRIDAY),
  SATURDAY(Calendar.SATURDAY),
  SUNDAY(Calendar.SUNDAY);

  public final int index;
  Day(int index) {
    this.index = index;
  }

  public static Day make(int weekdayIndex) {
    for (Day d : Day.values()) {
      if (d.index == weekdayIndex) {
        return d;
      }
    }
    throw new IllegalArgumentException("Invalid day index " + weekdayIndex);
  }
}

