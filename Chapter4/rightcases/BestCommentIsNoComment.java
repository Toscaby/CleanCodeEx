package Chapter4.rightcases;

/**
 * 用方法名代替注释例子
 *
 * @author Tosca
 * @date 2020/1/22
 */
public abstract class BestCommentIsNoComment {
  public class Responder {

  }

  // 用方法名代替注释
  // case 1 before:
  // Returns an instance of the Responder being tested.
  protected abstract Responder responderInstance();

  // case 1 after:
  protected abstract Responder responderBeingTested();
}
