package Chapter14.booleanandstring;

import Chapter14.booleanandstring.Args;

import java.text.ParseException;

/**
 * @author Tosca
 * @date 30/1/2020
 */
public class BooleanAndStringArgsTest {
  public static void main(String[] args) {
    // args: -ld true -t abc -K def
    try {
      Args arguments = new Args("fld#,tK*", args);
      print("l", arguments.getBoolean('l'));
      print("t", arguments.getString('t'));
      print("K", arguments.getString('K'));
      print("d", arguments.getBoolean('d'));
    } catch (ParseException e) {

    }
  }

  private static void print(String s, boolean b) {
    System.out.println("arg " + s + " :" + b);
  }

  private static void print(String s, String b) {
    System.out.println("arg " + s + " :" + b);
  }
}
