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

  public void testStringWeekdayCode() throws Exception {
    assertEquals(MONDAY, stringToWeekdayCode("Monday"));
    assertEquals(MONDAY, stringToWeekdayCode(" Monday "));
    assertEquals(MONDAY, stringToWeekdayCode("Mon"));
//todo    assertEquals(MONDAY, stringToWeekdayCode("monday"));
//    assertEquals(MONDAY, stringToWeekdayCode("MONDAY"));
//    assertEquals(MONDAY, stringToWeekdayCode("mon"));

    assertEquals(TUESDAY, stringToWeekdayCode("Tuesday"));
    assertEquals(TUESDAY, stringToWeekdayCode(" Tue "));
//    assertEquals(TUESDAY, stringToWeekdayCode("tuesday"));
//    assertEquals(TUESDAY, stringToWeekdayCode("TUESDAY"));
//    assertEquals(TUESDAY, stringToWeekdayCode("tue"));
//    assertEquals(TUESDAY, stringToWeekdayCode("tues"));

    assertEquals(WEDNESDAY, stringToWeekdayCode("Wed"));
    assertEquals(WEDNESDAY, stringToWeekdayCode("Wednesday"));
//    assertEquals(WEDNESDAY, stringToWeekdayCode("wednesday"));
//    assertEquals(WEDNESDAY, stringToWeekdayCode("WEDNESDAY"));
//    assertEquals(WEDNESDAY, stringToWeekdayCode("wed"));

    assertEquals(THURSDAY, stringToWeekdayCode("Thursday"));
    assertEquals(THURSDAY, stringToWeekdayCode("Thu"));
//    assertEquals(THURSDAY, stringToWeekdayCode("thursday"));
//    assertEquals(THURSDAY, stringToWeekdayCode("THURSDAY"));
//    assertEquals(THURSDAY, stringToWeekdayCode("thu"));
//    assertEquals(THURSDAY, stringToWeekdayCode("thus"));

    assertEquals(FRIDAY, stringToWeekdayCode("Friday"));
    assertEquals(FRIDAY, stringToWeekdayCode("Fri"));
//    assertEquals(FRIDAY, stringToWeekdayCode("fri"));
//    assertEquals(FRIDAY, stringToWeekdayCode("friday"));
//    assertEquals(FRIDAY, stringToWeekdayCode("FRIDAY"));

    assertEquals(SATURDAY, stringToWeekdayCode("Saturday"));
    assertEquals(SATURDAY, stringToWeekdayCode("Sat"));
//    assertEquals(SATURDAY, stringToWeekdayCode("sat"));
//    assertEquals(SATURDAY, stringToWeekdayCode("saturday"));
//    assertEquals(SATURDAY, stringToWeekdayCode("SATURDAY"));

    assertEquals(SUNDAY, stringToWeekdayCode("Sunday"));
    assertEquals(SUNDAY, stringToWeekdayCode("Sun"));
//    assertEquals(SUNDAY, stringToWeekdayCode("sun"));
//    assertEquals(SUNDAY, stringToWeekdayCode("sunday"));
//    assertEquals(SUNDAY, stringToWeekdayCode("SUNDAY"));

    try {
      stringToWeekdayCode("hello");
      fail("Invalid day index exception should be thrown.");
    } catch (IllegalArgumentException e) {

    }

  }

  public void testWeekdayCodeToString() throws Exception {
    assertEquals("Sunday", weekdayCodeToString(SUNDAY));
    assertEquals("Monday", weekdayCodeToString(MONDAY));
    assertEquals("Tuesday", weekdayCodeToString(TUESDAY));
    assertEquals("Wednesday", weekdayCodeToString(WEDNESDAY));
    assertEquals("Thursday", weekdayCodeToString(THURSDAY));
    assertEquals("Friday", weekdayCodeToString(FRIDAY));
    assertEquals("Saturday", weekdayCodeToString(SATURDAY));
  }

  public void testMonthToQuarter() throws Exception {
    assertEquals(1, monthCodeToQuarter(JANUARY));
    assertEquals(1, monthCodeToQuarter(FEBRUARY));
    assertEquals(1, monthCodeToQuarter(MARCH));
    assertEquals(2, monthCodeToQuarter(APRIL));
    assertEquals(2, monthCodeToQuarter(MAY));
    assertEquals(2, monthCodeToQuarter(JUNE));
    assertEquals(3, monthCodeToQuarter(JULY));
    assertEquals(3, monthCodeToQuarter(AUGUST));
    assertEquals(3, monthCodeToQuarter(SEPTEMBER));
    assertEquals(4, monthCodeToQuarter(OCTOBER));
    assertEquals(4, monthCodeToQuarter(NOVEMBER));
    assertEquals(4, monthCodeToQuarter(DECEMBER));
  }

  public void testMonthCodeToString() throws Exception {
    assertEquals("January", monthCodeToString(JANUARY));
    assertEquals("February", monthCodeToString(FEBRUARY));
    assertEquals("March", monthCodeToString(MARCH));
    assertEquals("April", monthCodeToString(APRIL));
    assertEquals("May", monthCodeToString(MAY));
    assertEquals("June", monthCodeToString(JUNE));
    assertEquals("July", monthCodeToString(JULY));
    assertEquals("August", monthCodeToString(AUGUST));
    assertEquals("September", monthCodeToString(SEPTEMBER));
    assertEquals("October", monthCodeToString(OCTOBER));
    assertEquals("November", monthCodeToString(NOVEMBER));
    assertEquals("December", monthCodeToString(DECEMBER));

    assertEquals("Jan", monthCodeToString(JANUARY, true));
    assertEquals("Feb", monthCodeToString(FEBRUARY, true));
    assertEquals("Mar", monthCodeToString(MARCH, true));
    assertEquals("Apr", monthCodeToString(APRIL, true));
    assertEquals("May", monthCodeToString(MAY, true));
    assertEquals("Jun", monthCodeToString(JUNE, true));
    assertEquals("Jul", monthCodeToString(JULY, true));
    assertEquals("Aug", monthCodeToString(AUGUST, true));
    assertEquals("Sep", monthCodeToString(SEPTEMBER, true));
    assertEquals("Oct", monthCodeToString(OCTOBER, true));
    assertEquals("Nov", monthCodeToString(NOVEMBER, true));
    assertEquals("Dec", monthCodeToString(DECEMBER, true));
  }

  public void testStringToMonthCode() throws Exception {
    assertEquals(JANUARY, stringToMonthCode("1"));
    assertEquals(FEBRUARY, stringToMonthCode("2"));
    assertEquals(MARCH, stringToMonthCode("3"));
    assertEquals(APRIL, stringToMonthCode("4"));
    assertEquals(MAY, stringToMonthCode("5"));
    assertEquals(JUNE, stringToMonthCode("6"));
    assertEquals(JULY, stringToMonthCode("7"));
    assertEquals(AUGUST, stringToMonthCode("8"));
    assertEquals(SEPTEMBER, stringToMonthCode("9"));
    assertEquals(OCTOBER, stringToMonthCode("10"));
    assertEquals(NOVEMBER, stringToMonthCode("11"));
    assertEquals(DECEMBER, stringToMonthCode("12"));

    for (int m = 1; m <= 12; ++m) {
      assertEquals(Month.make(m), stringToMonthCode(monthCodeToString(Month.make(m), false)));
      assertEquals(Month.make(m), stringToMonthCode(monthCodeToString(Month.make(m), true)));
    }

    try {
      stringToMonthCode("0");
      stringToMonthCode("13");
      stringToMonthCode("Hello");
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

  public void testIsValidWeekInMothCode() throws Exception {
    for (int w = 0; w <= 4; ++w) {
      assertTrue(isValidWeekInMonthCode(w));
    }
    assertFalse(isValidWeekInMonthCode(5));
    assertFalse(isValidWeekInMonthCode(-1));
  }

  public void testIsLeapYear() throws Exception {
    assertFalse(isLeapYear(1900));
    assertFalse(isLeapYear(1901));
    assertFalse(isLeapYear(1902));
    assertFalse(isLeapYear(1903));
    assertTrue(isLeapYear(1904));
    assertTrue(isLeapYear(1908));
    assertFalse(isLeapYear(1955));
    assertTrue(isLeapYear(1964));
    assertTrue(isLeapYear(1980));
    assertTrue(isLeapYear(2000));
    assertFalse(isLeapYear(2002));
    assertFalse(isLeapYear(2100));
  }

  public void testLeapYearCount() throws Exception {
    assertEquals(0, leapYearCount(1900));
    assertEquals(0, leapYearCount(1901));
    assertEquals(0, leapYearCount(1902));
    assertEquals(0, leapYearCount(1903));
    assertEquals(1, leapYearCount(1904));
    assertEquals(1, leapYearCount(1905));
    assertEquals(1, leapYearCount(1906));
    assertEquals(1, leapYearCount(1907));
    assertEquals(2, leapYearCount(1908));
    assertEquals(24, leapYearCount(1999));
    assertEquals(25, leapYearCount(2001));
    assertEquals(49, leapYearCount(2101));
    assertEquals(73, leapYearCount(2201));
    assertEquals(97, leapYearCount(2301));
    assertEquals(122, leapYearCount(2401));
  }

  public void testLastDayOfMonth() throws Exception {
    assertEquals(31, lastDayOfMonth(JANUARY, 1901));
    assertEquals(28, lastDayOfMonth(FEBRUARY, 1901));
    assertEquals(31, lastDayOfMonth(MARCH, 1901));
    assertEquals(30, lastDayOfMonth(APRIL, 1901));
    assertEquals(31, lastDayOfMonth(MAY, 1901));
    assertEquals(30, lastDayOfMonth(JUNE, 1901));
    assertEquals(31, lastDayOfMonth(JULY, 1901));
    assertEquals(31, lastDayOfMonth(AUGUST, 1901));
    assertEquals(30, lastDayOfMonth(SEPTEMBER, 1901));
    assertEquals(31, lastDayOfMonth(OCTOBER, 1901));
    assertEquals(30, lastDayOfMonth(NOVEMBER, 1901));
    assertEquals(31, lastDayOfMonth(DECEMBER, 1901));
    assertEquals(29, lastDayOfMonth(FEBRUARY, 1904));
  }

  public void testAddDays() throws Exception {
    DayDate newYears = d(1, JANUARY, 1900);
    assertEquals(d(2, JANUARY, 1900), addDays(1, newYears));
    assertEquals(d(1, FEBRUARY, 1900), addDays(31, newYears));
    assertEquals(d(1, JANUARY, 1901), addDays(365, newYears));
    assertEquals(d(31, DECEMBER, 1904), addDays(5 * 365, newYears));
  }

  private static SpreadsheetDate d(int day, Month month, int year) {
    return new SpreadsheetDate(day, month, year);
  }

  public void testAddMonths() throws Exception {
    assertEquals(d(1, FEBRUARY, 1900), addMonths(1, d(1, JANUARY, 1900)));
    assertEquals(d(28, FEBRUARY, 1900), addMonths(1, d(31, JANUARY, 1900)));
    assertEquals(d(28, FEBRUARY, 1900), addMonths(1, d(30, JANUARY, 1900)));
    assertEquals(d(28, FEBRUARY, 1900), addMonths(1, d(29, JANUARY, 1900)));
    assertEquals(d(28, FEBRUARY, 1900), addMonths(1, d(28, JANUARY, 1900)));
    assertEquals(d(27, FEBRUARY, 1900), addMonths(1, d(27, JANUARY, 1900)));

    assertEquals(d(30, JUNE, 1900), addMonths(5, d(31, JANUARY, 1900)));
    assertEquals(d(30, JUNE, 1901), addMonths(17, d(31, JANUARY, 1900)));

    assertEquals(d(29, FEBRUARY, 1904), addMonths(49, d(31, JANUARY, 1900)));
  }

  public void testAddYears() throws Exception {
    assertEquals(d(1, JANUARY, 1901), addYears(1, d(1, JANUARY, 1900)));
    assertEquals(d(28, FEBRUARY, 1905), addYears(1, d(29, FEBRUARY, 1904)));
    assertEquals(d(28, FEBRUARY, 1905), addYears(1, d(28, FEBRUARY, 1904)));
    assertEquals(d(28, FEBRUARY, 1904), addYears(1, d(28, FEBRUARY, 1903)));
  }

  public void testGetPreviousDayOfWeek() throws Exception {
    assertEquals(d(24, FEBRUARY, 2006), getPreviousDayOfWeek(FRIDAY, d(1, MARCH, 2006)));
    assertEquals(d(22, FEBRUARY, 2006), getPreviousDayOfWeek(WEDNESDAY, d(1, MARCH, 2006)));
    assertEquals(d(29, FEBRUARY, 2004), getPreviousDayOfWeek(SUNDAY, d(3, MARCH, 2004)));
    assertEquals(d(29, DECEMBER, 2004), getPreviousDayOfWeek(WEDNESDAY, d(5, JANUARY, 2005)));
  }

  public void testGetFollowingDayOfWeek() throws Exception {
    assertEquals(d(1, JANUARY, 2005), getFollowingDayOfWeek(SATURDAY, d(25, DECEMBER, 2004)));
    assertEquals(d(1, JANUARY, 2005), getFollowingDayOfWeek(SATURDAY, d(26, DECEMBER, 2004)));
    assertEquals(d(3, MARCH, 2004), getFollowingDayOfWeek(WEDNESDAY, d(28, FEBRUARY, 2004)));
  }

  public void testGetNearestDayOfWeek() throws Exception {
    assertEquals(d(16, APRIL, 2006), getNearestDayOfWeek(SUNDAY, d(16, APRIL, 2006)));
    assertEquals(d(16, APRIL, 2006), getNearestDayOfWeek(SUNDAY, d(17, APRIL, 2006)));
    assertEquals(d(16, APRIL, 2006), getNearestDayOfWeek(SUNDAY, d(18, APRIL, 2006)));
    assertEquals(d(16, APRIL, 2006), getNearestDayOfWeek(SUNDAY, d(19, APRIL, 2006)));
    assertEquals(d(23, APRIL, 2006), getNearestDayOfWeek(SUNDAY, d(20, APRIL, 2006)));
    assertEquals(d(23, APRIL, 2006), getNearestDayOfWeek(SUNDAY, d(21, APRIL, 2006)));
    assertEquals(d(23, APRIL, 2006), getNearestDayOfWeek(SUNDAY, d(22, APRIL, 2006)));

    assertEquals(d(17, APRIL, 2006), getNearestDayOfWeek(MONDAY, d(16, APRIL, 2006)));
    assertEquals(d(17, APRIL, 2006), getNearestDayOfWeek(MONDAY, d(17, APRIL, 2006)));
    assertEquals(d(17, APRIL, 2006), getNearestDayOfWeek(MONDAY, d(18, APRIL, 2006)));
    assertEquals(d(17, APRIL, 2006), getNearestDayOfWeek(MONDAY, d(19, APRIL, 2006)));
    assertEquals(d(17, APRIL, 2006), getNearestDayOfWeek(MONDAY, d(20, APRIL, 2006)));
    assertEquals(d(24, APRIL, 2006), getNearestDayOfWeek(MONDAY, d(21, APRIL, 2006)));
    assertEquals(d(24, APRIL, 2006), getNearestDayOfWeek(MONDAY, d(22, APRIL, 2006)));

    assertEquals(d(18, APRIL, 2006), getNearestDayOfWeek(TUESDAY, d(16, APRIL, 2006)));
    assertEquals(d(18, APRIL, 2006), getNearestDayOfWeek(TUESDAY, d(17, APRIL, 2006)));
    assertEquals(d(18, APRIL, 2006), getNearestDayOfWeek(TUESDAY, d(18, APRIL, 2006)));
    assertEquals(d(18, APRIL, 2006), getNearestDayOfWeek(TUESDAY, d(19, APRIL, 2006)));
    assertEquals(d(18, APRIL, 2006), getNearestDayOfWeek(TUESDAY, d(20, APRIL, 2006)));
    assertEquals(d(18, APRIL, 2006), getNearestDayOfWeek(TUESDAY, d(21, APRIL, 2006)));
    assertEquals(d(25, APRIL, 2006), getNearestDayOfWeek(TUESDAY, d(22, APRIL, 2006)));

    assertEquals(d(19, APRIL, 2006), getNearestDayOfWeek(WEDNESDAY, d(16, APRIL, 2006)));
    assertEquals(d(19, APRIL, 2006), getNearestDayOfWeek(WEDNESDAY, d(17, APRIL, 2006)));
    assertEquals(d(19, APRIL, 2006), getNearestDayOfWeek(WEDNESDAY, d(18, APRIL, 2006)));
    assertEquals(d(19, APRIL, 2006), getNearestDayOfWeek(WEDNESDAY, d(19, APRIL, 2006)));
    assertEquals(d(19, APRIL, 2006), getNearestDayOfWeek(WEDNESDAY, d(20, APRIL, 2006)));
    assertEquals(d(19, APRIL, 2006), getNearestDayOfWeek(WEDNESDAY, d(21, APRIL, 2006)));
    assertEquals(d(19, APRIL, 2006), getNearestDayOfWeek(WEDNESDAY, d(22, APRIL, 2006)));


    assertEquals(d(13, APRIL, 2006), getNearestDayOfWeek(THURSDAY, d(16, APRIL, 2006)));
    assertEquals(d(20, APRIL, 2006), getNearestDayOfWeek(THURSDAY, d(17, APRIL, 2006)));
    assertEquals(d(20, APRIL, 2006), getNearestDayOfWeek(THURSDAY, d(18, APRIL, 2006)));
    assertEquals(d(20, APRIL, 2006), getNearestDayOfWeek(THURSDAY, d(19, APRIL, 2006)));
    assertEquals(d(20, APRIL, 2006), getNearestDayOfWeek(THURSDAY, d(20, APRIL, 2006)));
    assertEquals(d(20, APRIL, 2006), getNearestDayOfWeek(THURSDAY, d(21, APRIL, 2006)));
    assertEquals(d(20, APRIL, 2006), getNearestDayOfWeek(THURSDAY, d(22, APRIL, 2006)));

    assertEquals(d(14, APRIL, 2006), getNearestDayOfWeek(FRIDAY, d(16, APRIL, 2006)));
    assertEquals(d(14, APRIL, 2006), getNearestDayOfWeek(FRIDAY, d(17, APRIL, 2006)));
    assertEquals(d(21, APRIL, 2006), getNearestDayOfWeek(FRIDAY, d(18, APRIL, 2006)));
    assertEquals(d(21, APRIL, 2006), getNearestDayOfWeek(FRIDAY, d(19, APRIL, 2006)));
    assertEquals(d(21, APRIL, 2006), getNearestDayOfWeek(FRIDAY, d(20, APRIL, 2006)));
    assertEquals(d(21, APRIL, 2006), getNearestDayOfWeek(FRIDAY, d(21, APRIL, 2006)));
    assertEquals(d(21, APRIL, 2006), getNearestDayOfWeek(FRIDAY, d(22, APRIL, 2006)));

    assertEquals(d(15, APRIL, 2006), getNearestDayOfWeek(SATURDAY, d(16, APRIL, 2006)));
    assertEquals(d(15, APRIL, 2006), getNearestDayOfWeek(SATURDAY, d(17, APRIL, 2006)));
    assertEquals(d(15, APRIL, 2006), getNearestDayOfWeek(SATURDAY, d(18, APRIL, 2006)));
    assertEquals(d(22, APRIL, 2006), getNearestDayOfWeek(SATURDAY, d(19, APRIL, 2006)));
    assertEquals(d(22, APRIL, 2006), getNearestDayOfWeek(SATURDAY, d(20, APRIL, 2006)));
    assertEquals(d(22, APRIL, 2006), getNearestDayOfWeek(SATURDAY, d(21, APRIL, 2006)));
    assertEquals(d(22, APRIL, 2006), getNearestDayOfWeek(SATURDAY, d(22, APRIL, 2006)));
  }

  public void testEndOfCurrentMonth() throws Exception {
    DayDate d = DayDate.createInstance(2);
    assertEquals(d(31, JANUARY, 2006), d.getEndOfCurrentMonth(d(1, JANUARY, 2006)));
    assertEquals(d(28, FEBRUARY, 2006), d.getEndOfCurrentMonth(d(1, FEBRUARY, 2006)));
    assertEquals(d(31, MARCH, 2006), d.getEndOfCurrentMonth(d(1, MARCH, 2006)));
    assertEquals(d(30, APRIL, 2006), d.getEndOfCurrentMonth(d(1, APRIL, 2006)));
    assertEquals(d(31, MAY, 2006), d.getEndOfCurrentMonth(d(1, MAY, 2006)));
    assertEquals(d(30, JUNE, 2006), d.getEndOfCurrentMonth(d(1, JUNE, 2006)));
    assertEquals(d(31, JULY, 2006), d.getEndOfCurrentMonth(d(1, JULY, 2006)));
    assertEquals(d(31, AUGUST, 2006), d.getEndOfCurrentMonth(d(1, AUGUST, 2006)));
    assertEquals(d(30, SEPTEMBER, 2006), d.getEndOfCurrentMonth(d(1, SEPTEMBER, 2006)));
    assertEquals(d(31, OCTOBER, 2006), d.getEndOfCurrentMonth(d(1, OCTOBER, 2006)));
    assertEquals(d(30, NOVEMBER, 2006), d.getEndOfCurrentMonth(d(1, NOVEMBER, 2006)));
    assertEquals(d(31, DECEMBER, 2006), d.getEndOfCurrentMonth(d(1, DECEMBER, 2006)));
    assertEquals(d(29, FEBRUARY, 2008), d.getEndOfCurrentMonth(d(1, FEBRUARY, 2008)));
  }

  public void testWeekInMonthToString() throws Exception {
    assertEquals("First", weekInMonthToString(FIRST_WEEK_IN_MONTH));
    assertEquals("Second", weekInMonthToString(SECOND_WEEK_IN_MONTH));
    assertEquals("Third", weekInMonthToString(THIRD_WEEK_IN_MONTH));
    assertEquals("Fourth", weekInMonthToString(FOURTH_WEEK_IN_MONTH));
    assertEquals("Last", weekInMonthToString(LAST_WEEK_IN_MONTH));

    try {
      weekInMonthToString(-1);
      fail("Invalid week code should throw exception");
    } catch (IllegalArgumentException e) {

    }
  }

  public void testRelativeToString() throws Exception {
    assertEquals("Preceding", relativeToString(PRECEDING));
    assertEquals("Nearest", relativeToString(NEAREST));
    assertEquals("Following", relativeToString(FOLLOWING));

    try {
      relativeToString(-1000);
      fail("Invalid relative code should throw exception");
    } catch (IllegalArgumentException e) {

    }
  }

  public void testCreateInstanceFromDDMMYYYY()  throws Exception {
    DayDate date = createInstance(1, JANUARY, 1900);
    assertEquals(1, date.getDayOfMonth());
    assertEquals(JANUARY, date.getMonth());
    assertEquals(1900, date.getYYYY());
    assertEquals(2, date.toSerial());
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
