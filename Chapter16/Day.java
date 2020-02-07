package Chapter16;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

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
  private static final DateFormatSymbols
      dateSymbols = new SimpleDateFormat("d-MMMM-yyyy", Locale.US)
      .getDateFormatSymbols();

  Day(int index) {
    this.index = index;
  }

  public static Day make(int weekdayIndex) {
    for (Day d : Day.values()) {
      if (d.index == weekdayIndex) {
        return d;
      }
    }
    throw new IllegalArgumentException(
        String.format("Illegal day index: d%.",  weekdayIndex));
  }

  public static Day parse(String s) {
    final String[] shortWeekdayNames
        = dateSymbols.getShortWeekdays();
    final String[] weekDayNames = dateSymbols.getWeekdays();

    s = s.trim();
    for (Day day : Day.values()) {
      if (s.equalsIgnoreCase(shortWeekdayNames[day.index]) ||
          s.equalsIgnoreCase(weekDayNames[day.index])) {
        return day;
      }
    }
    throw new IllegalArgumentException(String.format("%s is not a valid weekday string", s));
  }

  public String toString() {
    return dateSymbols.getWeekdays()[index];
  }
}

