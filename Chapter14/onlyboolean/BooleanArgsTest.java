package Chapter14.onlyboolean;

/**
 * @author Tosca
 * @date 29/1/2020
 */
public class BooleanArgsTest {
  public static void main(String[] args) {
    Args arguments = new Args("Fld", args);
    print("l", arguments.getBoolean('l'));
    print("F", arguments.getBoolean('F'));
    print("d", arguments.getBoolean('d'));
  }

  private static void print(String s, boolean b) {
    System.out.println("arg " + s + " :" + b);
  }
}
