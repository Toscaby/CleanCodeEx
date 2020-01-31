package Chapter14.tdd;


/**
 * @author Tosca
 * @date 31/1/2020
 */
public class Test {
  public static void main(String[] args) {
    // args: -ld true -t abc -K def -s 10 -mq 100
    try {
      Args4 arguments = new Args4("fld#,tK*,qsm@", args);
      print("l", arguments.getBoolean('l'));
      print("t", arguments.getString('t'));
      print("K", arguments.getString('K'));
      print("d", arguments.getBoolean('d'));
      print("s", arguments.getInteger('s'));
      print("m", arguments.getInteger('m'));
    } catch (Exception e) {

    }
  }

  private static void print(String s, boolean b) {
    System.out.println("arg " + s + " :" + b);
  }

  private static void print(String s, String b) {
    System.out.println("arg " + s + " :" + b);
  }

  private static void print(String s, int b) {
    System.out.println("arg " + s + " :" + b);
  }
}
