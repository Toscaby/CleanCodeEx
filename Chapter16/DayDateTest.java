package Chapter16;

import junit.framework.TestCase;

import java.util.GregorianCalendar;

import static Chapter16.Day.*;
import static Chapter16.DayDate.*;
import static Chapter16.Month.*;

/**
 * cp from jcommon-1.0.23/src/test/java/org/jfree/date
 *
 * Some JUnit tests for the {@link DayDate} class.
 * @author Tosca
 * @date 2/2/2020
 */
public class DayDateTest extends TestCase {

  public void testStringToWeekdayCode() throws Exception {
    assertEquals(MONDAY, Day.parse("Monday"));
    assertEquals(MONDAY, Day.parse(" Monday "));
    assertEquals(MONDAY, Day.parse("Mon"));
//todo    assertEquals(MONDAY, Day.parse("monday"));
//    assertEquals(MONDAY, Day.parse("MONDAY"));
//    assertEquals(MONDAY, Day.parse("mon"));

    assertEquals(TUESDAY, Day.parse("Tuesday"));
    assertEquals(TUESDAY, Day.parse(" Tue "));
//    assertEquals(TUESDAY, Day.parse("tuesday"));
//    assertEquals(TUESDAY, Day.parse("TUESDAY"));
//    assertEquals(TUESDAY, Day.parse("tue"));
//    assertEquals(TUESDAY, Day.parse("tues"));

    assertEquals(WEDNESDAY, Day.parse("Wed"));
    assertEquals(WEDNESDAY, Day.parse("Wednesday"));
//    assertEquals(WEDNESDAY, Day.parse("wednesday"));
//    assertEquals(WEDNESDAY, Day.parse("WEDNESDAY"));
//    assertEquals(WEDNESDAY, Day.parse("wed"));

    assertEquals(THURSDAY, Day.parse("Thursday"));
    assertEquals(THURSDAY, Day.parse("Thu"));
//    assertEquals(THURSDAY, Day.parse("thursday"));
//    assertEquals(THURSDAY, Day.parse("THURSDAY"));
//    assertEquals(THURSDAY, Day.parse("thu"));
//    assertEquals(THURSDAY, Day.parse("thus"));

    assertEquals(FRIDAY, Day.parse("Friday"));
    assertEquals(FRIDAY, Day.parse("Fri"));
//    assertEquals(FRIDAY, Day.parse("fri"));
//    assertEquals(FRIDAY, Day.parse("friday"));
//    assertEquals(FRIDAY, Day.parse("FRIDAY"));

    assertEquals(SATURDAY, Day.parse("Saturday"));
    assertEquals(SATURDAY, Day.parse("Sat"));
//    assertEquals(SATURDAY, Day.parse("sat"));
//    assertEquals(SATURDAY, Day.parse("saturday"));
//    assertEquals(SATURDAY, Day.parse("SATURDAY"));

    assertEquals(SUNDAY, Day.parse("Sunday"));
    assertEquals(SUNDAY, Day.parse("Sun"));
//    assertEquals(SUNDAY, Day.parse("sun"));
//    assertEquals(SUNDAY, Day.parse("sunday"));
//    assertEquals(SUNDAY, Day.parse("SUNDAY"));

    try {
      Day.parse("hello");
      fail("Invalid day index exception should be thrown.");
    } catch (IllegalArgumentException e) {

    }

  }

  public void testWeekdayCodeToString() throws Exception {
    assertEquals("Sunday", SUNDAY.toString());
    assertEquals("Monday", MONDAY.toString());
    assertEquals("Tuesday", TUESDAY.toString());
    assertEquals("Wednesday", WEDNESDAY.toString());
    assertEquals("Thursday", THURSDAY.toString());
    assertEquals("Friday", FRIDAY.toString());
    assertEquals("Saturday", SATURDAY.toString());
  }

  public void testMonthToQuarter() throws Exception {
    assertEquals(1, JANUARY.quarter());
    assertEquals(1, FEBRUARY.quarter());
    assertEquals(1, MARCH.quarter());
    assertEquals(2, APRIL.quarter());
    assertEquals(2, MAY.quarter());
    assertEquals(2, JUNE.quarter());
    assertEquals(3, JULY.quarter());
    assertEquals(3, AUGUST.quarter());
    assertEquals(3, SEPTEMBER.quarter());
    assertEquals(4, OCTOBER.quarter());
    assertEquals(4, NOVEMBER.quarter());
    assertEquals(4, DECEMBER.quarter());
  }

  public void testMonthCodeToString() throws Exception {
    assertEquals("January", JANUARY.toString());
    assertEquals("February", FEBRUARY.toString());
    assertEquals("March", MARCH.toString());
    assertEquals("April", APRIL.toString());
    assertEquals("May", MAY.toString());
    assertEquals("June", JUNE.toString());
    assertEquals("July", JULY.toString());
    assertEquals("August", AUGUST.toString());
    assertEquals("September", SEPTEMBER.toString());
    assertEquals("October", OCTOBER.toString());
    assertEquals("November", NOVEMBER.toString());
    assertEquals("December", DECEMBER.toString());

    assertEquals("Jan", JANUARY.toShortString());
    assertEquals("Feb", FEBRUARY.toShortString());
    assertEquals("Mar", MARCH.toShortString());
    assertEquals("Apr", APRIL.toShortString());
    assertEquals("May", MAY.toShortString());
    assertEquals("Jun", JUNE.toShortString());
    assertEquals("Jul", JULY.toShortString());
    assertEquals("Aug", AUGUST.toShortString());
    assertEquals("Sep", SEPTEMBER.toShortString());
    assertEquals("Oct", OCTOBER.toShortString());
    assertEquals("Nov", NOVEMBER.toShortString());
    assertEquals("Dec", DECEMBER.toShortString());
  }

  public void testStringToMonthCode() throws Exception {
    assertEquals(JANUARY, Month.parse("1"));
    assertEquals(FEBRUARY, Month.parse("2"));
    assertEquals(MARCH, Month.parse("3"));
    assertEquals(APRIL, Month.parse("4"));
    assertEquals(MAY, Month.parse("5"));
    assertEquals(JUNE, Month.parse("6"));
    assertEquals(JULY, Month.parse("7"));
    assertEquals(AUGUST, Month.parse("8"));
    assertEquals(SEPTEMBER, Month.parse("9"));
    assertEquals(OCTOBER, Month.parse("10"));
    assertEquals(NOVEMBER, Month.parse("11"));
    assertEquals(DECEMBER, Month.parse("12"));

    for (int m = 1; m <= 12; ++m) {
      assertEquals(Month.fromInt(m), Month.parse(Month.fromInt(m).toString()));
      assertEquals(Month.fromInt(m), Month.parse(Month.fromInt(m).toShortString()));
    }

    try {
      Month.parse("0");
      Month.parse("13");
      Month.parse("Hello");
      fail("Invalid month string should throw exception");
    } catch (IllegalArgumentException e) {

    }

//    assertEquals(JANUARY, stringToMonthCode("Jan"));
//    assertEquals(FEBRUARY, stringToMonthCode("Feb"));
//    assertEquals(MARCH, stringToMonthCode("Mar"));
//    assertEquals(APRIL, stringToMonthCode("Apr"));
//    assertEquals(MAY, stringToMonthCode("May"));
//    assertEquals(JUNE, stringToMonthCode("Jun"));
//    assertEquals(JULY, stringToMonthCode("Jul"));
//    assertEquals(AUGUST, stringToMonthCode("Aug"));
//    assertEquals(SEPTEMBER, stringToMonthCode("Sep"));
//    assertEquals(OCTOBER, stringToMonthCode("Oct"));
//    assertEquals(NOVEMBER, stringToMonthCode("Nov"));
//    assertEquals(DECEMBER, stringToMonthCode("Dec"));
//
//    assertEquals(JANUARY, stringToMonthCode("JAN"));
//    assertEquals(FEBRUARY, stringToMonthCode("FEB"));
//    assertEquals(MARCH, stringToMonthCode("MAR"));
//    assertEquals(APRIL, stringToMonthCode("APR"));
//    assertEquals(MAY, stringToMonthCode("MAY"));
//    assertEquals(JUNE, stringToMonthCode("JUN"));
//    assertEquals(JULY, stringToMonthCode("JUL"));
//    assertEquals(AUGUST, stringToMonthCode("AUG"));
//    assertEquals(SEPTEMBER, stringToMonthCode("SEP"));
//    assertEquals(OCTOBER, stringToMonthCode("OCT"));
//    assertEquals(NOVEMBER, stringToMonthCode("NOV"));
//    assertEquals(DECEMBER, stringToMonthCode("DEC"));
//
//    assertEquals(JANUARY, stringToMonthCode("January"));
//    assertEquals(FEBRUARY, stringToMonthCode("February"));
//    assertEquals(MARCH, stringToMonthCode("March"));
//    assertEquals(APRIL, stringToMonthCode("April"));
//    assertEquals(MAY, stringToMonthCode("May"));
//    assertEquals(JUNE, stringToMonthCode("June"));
//    assertEquals(JULY, stringToMonthCode("July"));
//    assertEquals(AUGUST, stringToMonthCode("August"));
//    assertEquals(SEPTEMBER, stringToMonthCode("September"));
//    assertEquals(OCTOBER, stringToMonthCode("October"));
//    assertEquals(NOVEMBER, stringToMonthCode("November"));
//    assertEquals(DECEMBER, stringToMonthCode("December"));
//
//    assertEquals(JANUARY, stringToMonthCode("JANUARY"));
//    assertEquals(FEBRUARY, stringToMonthCode("FEBRUARY"));
//    assertEquals(MARCH, stringToMonthCode("MARCH"));
//    assertEquals(APRIL, stringToMonthCode("APRIL"));
//    assertEquals(MAY, stringToMonthCode("MAY"));
//    assertEquals(JUNE, stringToMonthCode("JUNE"));
//    assertEquals(JULY, stringToMonthCode("JULY"));
//    assertEquals(AUGUST, stringToMonthCode("AUGUST"));
//    assertEquals(SEPTEMBER, stringToMonthCode("SEPTEMBER"));
//    assertEquals(OCTOBER, stringToMonthCode("OCTOBER"));
//    assertEquals(NOVEMBER, stringToMonthCode("NOVEMBER"));
//    assertEquals(DECEMBER, stringToMonthCode("DECEMBER"));
  }

  public void testIsLeapYear() throws Exception {
    assertFalse(DayUtil.isLeapYear(1900));
    assertFalse(DayUtil.isLeapYear(1901));
    assertFalse(DayUtil.isLeapYear(1902));
    assertFalse(DayUtil.isLeapYear(1903));
    assertTrue(DayUtil.isLeapYear(1904));
    assertTrue(DayUtil.isLeapYear(1908));
    assertFalse(DayUtil.isLeapYear(1955));
    assertTrue(DayUtil.isLeapYear(1964));
    assertTrue(DayUtil.isLeapYear(1980));
    assertTrue(DayUtil.isLeapYear(2000));
    assertFalse(DayUtil.isLeapYear(2002));
    assertFalse(DayUtil.isLeapYear(2100));
  }

  public void testLastDayOfMonth() throws Exception {
    assertEquals(31, DayUtil.lastDayOfMonth(JANUARY, 1901));
    assertEquals(28, DayUtil.lastDayOfMonth(FEBRUARY, 1901));
    assertEquals(31, DayUtil.lastDayOfMonth(MARCH, 1901));
    assertEquals(30, DayUtil.lastDayOfMonth(APRIL, 1901));
    assertEquals(31, DayUtil.lastDayOfMonth(MAY, 1901));
    assertEquals(30, DayUtil.lastDayOfMonth(JUNE, 1901));
    assertEquals(31, DayUtil.lastDayOfMonth(JULY, 1901));
    assertEquals(31, DayUtil.lastDayOfMonth(AUGUST, 1901));
    assertEquals(30, DayUtil.lastDayOfMonth(SEPTEMBER, 1901));
    assertEquals(31, DayUtil.lastDayOfMonth(OCTOBER, 1901));
    assertEquals(30, DayUtil.lastDayOfMonth(NOVEMBER, 1901));
    assertEquals(31, DayUtil.lastDayOfMonth(DECEMBER, 1901));
    assertEquals(29, DayUtil.lastDayOfMonth(FEBRUARY, 1904));
  }

  public void testAddDays() throws Exception {
    DayDate newYears = d(1, JANUARY, 1900);
    assertEquals(d(2, JANUARY, 1900), newYears.plusDays(1));
    assertEquals(d(1, FEBRUARY, 1900), newYears.plusDays(31));
    assertEquals(d(1, JANUARY, 1901), newYears.plusDays(365));
    assertEquals(d(31, DECEMBER, 1904), newYears.plusDays(5 * 365));
  }

  private static SpreadsheetDate d(int day, Month month, int year) {
    return new SpreadsheetDate(day, month, year);
  }

  public void testPlusMonths() throws Exception {
    assertEquals(d(1, FEBRUARY, 1900), d(1, JANUARY, 1900).plusMonths(1));
    assertEquals(d(28, FEBRUARY, 1900), d(31, JANUARY, 1900).plusMonths(1));
    assertEquals(d(28, FEBRUARY, 1900), d(30, JANUARY, 1900).plusMonths(1));
    assertEquals(d(28, FEBRUARY, 1900), d(29, JANUARY, 1900).plusMonths(1));
    assertEquals(d(28, FEBRUARY, 1900), d(28, JANUARY, 1900).plusMonths(1));
    assertEquals(d(27, FEBRUARY, 1900), d(27, JANUARY, 1900).plusMonths(1));

    assertEquals(d(30, JUNE, 1900), d(31, JANUARY, 1900).plusMonths(5));
    assertEquals(d(30, JUNE, 1901), d(31, JANUARY, 1900).plusMonths(17));

    assertEquals(d(29, FEBRUARY, 1904), d(31, JANUARY, 1900).plusMonths(49));
  }

  public void testAddYears() throws Exception {
    assertEquals(d(1, JANUARY, 1901), d(1, JANUARY, 1900).plusYears(1));
    assertEquals(d(28, FEBRUARY, 1905), d(29, FEBRUARY, 1904).plusYears(1));
    assertEquals(d(28, FEBRUARY, 1905), d(28, FEBRUARY, 1904).plusYears(1));
    assertEquals(d(28, FEBRUARY, 1904), d(28, FEBRUARY, 1903).plusYears(1));
  }

  public void testGetPreviousDayOfWeek() throws Exception {
    assertEquals(d(24, FEBRUARY, 2006), d(1, MARCH, 2006).getPreviousDayOfWeek(FRIDAY));
    assertEquals(d(22, FEBRUARY, 2006), d(1, MARCH, 2006).getPreviousDayOfWeek(WEDNESDAY));
    assertEquals(d(29, FEBRUARY, 2004), d(3, MARCH, 2004).getPreviousDayOfWeek(SUNDAY));
    assertEquals(d(29, DECEMBER, 2004), d(5, JANUARY, 2005).getPreviousDayOfWeek(WEDNESDAY));
  }

  public void testGetFollowingDayOfWeek() throws Exception {
    assertEquals(d(1, JANUARY, 2005), d(25, DECEMBER, 2004).getFollowingDayOfWeek(SATURDAY));
    assertEquals(d(1, JANUARY, 2005), d(26, DECEMBER, 2004).getFollowingDayOfWeek(SATURDAY));
    assertEquals(d(3, MARCH, 2004), d(28, FEBRUARY, 2004).getFollowingDayOfWeek(WEDNESDAY));
  }

  public void testGetNearestDayOfWeek() throws Exception {
    assertEquals(d(16, APRIL, 2006), d(16, APRIL, 2006).getNearestDayOfWeek(SUNDAY));
    assertEquals(d(16, APRIL, 2006), d(17, APRIL, 2006).getNearestDayOfWeek(SUNDAY));
    assertEquals(d(16, APRIL, 2006), d(18, APRIL, 2006).getNearestDayOfWeek(SUNDAY));
    assertEquals(d(16, APRIL, 2006), d(19, APRIL, 2006).getNearestDayOfWeek(SUNDAY));
    assertEquals(d(23, APRIL, 2006), d(20, APRIL, 2006).getNearestDayOfWeek(SUNDAY));
    assertEquals(d(23, APRIL, 2006), d(21, APRIL, 2006).getNearestDayOfWeek(SUNDAY));
    assertEquals(d(23, APRIL, 2006), d(22, APRIL, 2006).getNearestDayOfWeek(SUNDAY));

    assertEquals(d(17, APRIL, 2006), d(16, APRIL, 2006).getNearestDayOfWeek(MONDAY));
    assertEquals(d(17, APRIL, 2006), d(17, APRIL, 2006).getNearestDayOfWeek(MONDAY));
    assertEquals(d(17, APRIL, 2006), d(18, APRIL, 2006).getNearestDayOfWeek(MONDAY));
    assertEquals(d(17, APRIL, 2006), d(19, APRIL, 2006).getNearestDayOfWeek(MONDAY));
    assertEquals(d(17, APRIL, 2006), d(20, APRIL, 2006).getNearestDayOfWeek(MONDAY));
    assertEquals(d(24, APRIL, 2006), d(21, APRIL, 2006).getNearestDayOfWeek(MONDAY));
    assertEquals(d(24, APRIL, 2006), d(22, APRIL, 2006).getNearestDayOfWeek(MONDAY));

    assertEquals(d(18, APRIL, 2006), d(16, APRIL, 2006).getNearestDayOfWeek(TUESDAY));
    assertEquals(d(18, APRIL, 2006), d(17, APRIL, 2006).getNearestDayOfWeek(TUESDAY));
    assertEquals(d(18, APRIL, 2006), d(18, APRIL, 2006).getNearestDayOfWeek(TUESDAY));
    assertEquals(d(18, APRIL, 2006), d(19, APRIL, 2006).getNearestDayOfWeek(TUESDAY));
    assertEquals(d(18, APRIL, 2006), d(20, APRIL, 2006).getNearestDayOfWeek(TUESDAY));
    assertEquals(d(18, APRIL, 2006), d(21, APRIL, 2006).getNearestDayOfWeek(TUESDAY));
    assertEquals(d(25, APRIL, 2006), d(22, APRIL, 2006).getNearestDayOfWeek(TUESDAY));

    assertEquals(d(19, APRIL, 2006), d(16, APRIL, 2006).getNearestDayOfWeek(WEDNESDAY));
    assertEquals(d(19, APRIL, 2006), d(17, APRIL, 2006).getNearestDayOfWeek(WEDNESDAY));
    assertEquals(d(19, APRIL, 2006), d(18, APRIL, 2006).getNearestDayOfWeek(WEDNESDAY));
    assertEquals(d(19, APRIL, 2006), d(19, APRIL, 2006).getNearestDayOfWeek(WEDNESDAY));
    assertEquals(d(19, APRIL, 2006), d(20, APRIL, 2006).getNearestDayOfWeek(WEDNESDAY));
    assertEquals(d(19, APRIL, 2006), d(21, APRIL, 2006).getNearestDayOfWeek(WEDNESDAY));
    assertEquals(d(19, APRIL, 2006), d(22, APRIL, 2006).getNearestDayOfWeek(WEDNESDAY));


    assertEquals(d(13, APRIL, 2006), d(16, APRIL, 2006).getNearestDayOfWeek(THURSDAY));
    assertEquals(d(20, APRIL, 2006), d(17, APRIL, 2006).getNearestDayOfWeek(THURSDAY));
    assertEquals(d(20, APRIL, 2006), d(18, APRIL, 2006).getNearestDayOfWeek(THURSDAY));
    assertEquals(d(20, APRIL, 2006), d(19, APRIL, 2006).getNearestDayOfWeek(THURSDAY));
    assertEquals(d(20, APRIL, 2006), d(20, APRIL, 2006).getNearestDayOfWeek(THURSDAY));
    assertEquals(d(20, APRIL, 2006), d(21, APRIL, 2006).getNearestDayOfWeek(THURSDAY));
    assertEquals(d(20, APRIL, 2006), d(22, APRIL, 2006).getNearestDayOfWeek(THURSDAY));

    assertEquals(d(14, APRIL, 2006), d(16, APRIL, 2006).getNearestDayOfWeek(FRIDAY));
    assertEquals(d(14, APRIL, 2006), d(17, APRIL, 2006).getNearestDayOfWeek(FRIDAY));
    assertEquals(d(21, APRIL, 2006), d(18, APRIL, 2006).getNearestDayOfWeek(FRIDAY));
    assertEquals(d(21, APRIL, 2006), d(19, APRIL, 2006).getNearestDayOfWeek(FRIDAY));
    assertEquals(d(21, APRIL, 2006), d(20, APRIL, 2006).getNearestDayOfWeek(FRIDAY));
    assertEquals(d(21, APRIL, 2006), d(21, APRIL, 2006).getNearestDayOfWeek(FRIDAY));
    assertEquals(d(21, APRIL, 2006), d(22, APRIL, 2006).getNearestDayOfWeek(FRIDAY));

    assertEquals(d(15, APRIL, 2006), d(16, APRIL, 2006).getNearestDayOfWeek(SATURDAY));
    assertEquals(d(15, APRIL, 2006), d(17, APRIL, 2006).getNearestDayOfWeek(SATURDAY));
    assertEquals(d(15, APRIL, 2006), d(18, APRIL, 2006).getNearestDayOfWeek(SATURDAY));
    assertEquals(d(22, APRIL, 2006), d(19, APRIL, 2006).getNearestDayOfWeek(SATURDAY));
    assertEquals(d(22, APRIL, 2006), d(20, APRIL, 2006).getNearestDayOfWeek(SATURDAY));
    assertEquals(d(22, APRIL, 2006), d(21, APRIL, 2006).getNearestDayOfWeek(SATURDAY));
    assertEquals(d(22, APRIL, 2006), d(22, APRIL, 2006).getNearestDayOfWeek(SATURDAY));
  }

  public void testEndOfCurrentMonth() throws Exception {
    DayDate d = DayDate.createInstance(2);
    assertEquals(d(31, JANUARY, 2006), d(1, JANUARY, 2006).getEndOfCurrentMonth());
    assertEquals(d(28, FEBRUARY, 2006), d(1, FEBRUARY, 2006).getEndOfCurrentMonth());
    assertEquals(d(31, MARCH, 2006), d(1, MARCH, 2006).getEndOfCurrentMonth());
    assertEquals(d(30, APRIL, 2006), d(1, APRIL, 2006).getEndOfCurrentMonth());
    assertEquals(d(31, MAY, 2006), d(1, MAY, 2006).getEndOfCurrentMonth());
    assertEquals(d(30, JUNE, 2006), d(1, JUNE, 2006).getEndOfCurrentMonth());
    assertEquals(d(31, JULY, 2006), d(1, JULY, 2006).getEndOfCurrentMonth());
    assertEquals(d(31, AUGUST, 2006), d(1, AUGUST, 2006).getEndOfCurrentMonth());
    assertEquals(d(30, SEPTEMBER, 2006), d(1, SEPTEMBER, 2006).getEndOfCurrentMonth());
    assertEquals(d(31, OCTOBER, 2006), d(1, OCTOBER, 2006).getEndOfCurrentMonth());
    assertEquals(d(30, NOVEMBER, 2006), d(1, NOVEMBER, 2006).getEndOfCurrentMonth());
    assertEquals(d(31, DECEMBER, 2006), d(1, DECEMBER, 2006).getEndOfCurrentMonth());
    assertEquals(d(29, FEBRUARY, 2008), d(1, FEBRUARY, 2008).getEndOfCurrentMonth());
  }

  public void testCreateInstanceFromDDMMYYYY()  throws Exception {
    DayDate date = createInstance(1, JANUARY, 1900);
    assertEquals(1, date.getDayOfMonth());
    assertEquals(JANUARY, date.getMonth());
    assertEquals(1900, date.getYear());
    assertEquals(2, date.getOrdinalDay());
  }

  public void testCreateInstanceFromSerial()  throws Exception {
    assertEquals(d(1, JANUARY, 1900), createInstance(2));
    assertEquals(d(1, JANUARY, 1901), createInstance(367));
  }

  public void testCreateInstanceFromJavaDate()  throws Exception {
    assertEquals(d(1, JANUARY, 1900),
        createInstance(new GregorianCalendar(1900, 0, 1).getTime()));
    assertEquals(d(1, JANUARY, 2006),
        createInstance(new GregorianCalendar(2006, 0, 1).getTime()));
  }

  public static void main(String[] args) {
    junit.textui.TestRunner.run(DayDateTest.class);
  }
}
