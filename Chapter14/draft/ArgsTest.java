package Chapter14.draft;

/**
 * @author Tosca
 * @date 29/1/2020
 */
public class ArgsTest {
  public static void main(String[] args) {
    try {
      Args arguments = new Args("l,p#,d*", args);
      boolean logging = arguments.getBoolean('l');
      int port = arguments.getInt('p');
      String dir = arguments.getString('d');

      System.out.println("log: " + logging);
      System.out.println("port: " + port);
      System.out.println("dir: " + dir);
    } catch (Exception e) {

    }
  }
}
