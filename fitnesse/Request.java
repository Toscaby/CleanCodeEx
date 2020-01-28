package fitnesse;

/**
 * @author Tosca
 * @date 27/1/2020
 */
public class Request {
  public void setResource(String src) {
    System.out.println(src);
  }
  public void addInput(String type, String pages) {
    System.out.println("type: " + type + "  pages: " + pages);
  }
}
