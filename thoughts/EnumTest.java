package thoughts;

/**
 * @author Tosca
 * @date 21/2/2020
 */
public enum EnumTest {
  A(2001),
  B(A.index + 1);



  private final int index;
  EnumTest(int index) {
    this.index = index;
  }

  public static int previousVal() {
    return values()[values().length - 1].index;
  }

  public static void main(String[] args) {
    System.out.println(A.index);
    System.out.println(B.index);
  }
}
