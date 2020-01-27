package Chapter7.case4;

/**
 * @author Tosca
 * @date 27/1/2020
 */
public class DoNotPassNull {
  public void test1(String s1, String s2) {
    if (s1 == null || s2 == null) {
      throw new InvalidArgumentException("Invalid argument ...");
    }
    // ...
  }

  public void test2(String s1, String s2) {
    assert s1 != null : "s1 should not be null";
    assert s2 != null : "s2 should not be null";
    // ...
  }

  // for android
  // public void test3(@NonNull String s1, @NonNull String s2) {}
}
