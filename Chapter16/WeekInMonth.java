package Chapter16;

/**
 * @author Tosca
 * @date 7/2/2020
 */
public enum WeekInMonth {
  FIRST(1), SECOND(2), THIRD(3), FOURTH(4), LAST(0);

  public final int index;
  WeekInMonth(int index) {this.index = index;}
}
