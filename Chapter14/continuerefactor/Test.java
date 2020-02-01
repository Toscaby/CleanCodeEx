package Chapter14.continuerefactor;

/**
 * @author Tosca
 * @date 1/2/2020
 */
public class Test {
  public static void main(String[] args) {
    // args: -ld true -t abc -K def -s 10 -mq 100 -p 0.065 -z 0.33
    try {
      Args7 arguments = new Args7("fld#,tK*,qsm@,pz%", args);
      print("l", arguments.getBoolean('l'));
      print("t", arguments.getString('t'));
      print("K", arguments.getString('K'));
      print("d", arguments.getBoolean('d'));
      print("f", arguments.getBoolean('f'));
      print("s", arguments.getInteger('s'));
      print("m", arguments.getInteger('m'));
      print("q", arguments.getInteger('q'));
      print("p", arguments.getDouble('p'));
      print("z", arguments.getDouble('z'));
    } catch (Exception e) {

    }
  }

  private static void print(String s, boolean b) {
    System.out.println("arg " + s + " :" + b);
  }

  private static void print(String s, String vs) {
    System.out.println("arg " + s + " :" + vs);
  }

  private static void print(String s, int i) {
    System.out.println("arg " + s + " :" + i);
  }

  private static void print(String s, double d) {
    System.out.println("arg " + s + " :" + d);
  }
}
