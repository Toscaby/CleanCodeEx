package Chapter16;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * @author Tosca
 * @date 8/2/2020
 */
public class DateUtil {
  private static final DateFormatSymbols
      dateSymbols = new SimpleDateFormat("d-MMMM-yyyy", Locale.US)
      .getDateFormatSymbols();


  public static String[] getMonthNames() {
    return dateSymbols.getMonths();
  }

  public static String[] getShortMonthNames() {
    return dateSymbols.getShortMonths();
  }

  public static boolean isLeapYear(int year) {
    boolean fourth = year % 4 == 0;
    boolean hundredth = year % 100 == 0;
    boolean fourHundredth = year % 400 == 0;
    return fourth && (!hundredth || fourHundredth);
  }

  public static int lastDayOfMonth(Month month, int year) {
    if (month == Month.FEBRUARY && isLeapYear(year)) {
      return month.lastDay() + 1;
    } else {
      return month.lastDay();
    }
  }

  public static int leapYearCount(int yyyy) {
    int leap4 = (yyyy - 1896) / 4;
    int leap100 = (yyyy - 1800) / 100;
    int leap400 = (yyyy - 1600) / 400;
    return leap4 - leap100 + leap400;
  }
}
