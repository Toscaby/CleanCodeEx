package Chapter3.case3;

/**
 * @author Tosca
 * @date 2020/1/22
 */
public class Case {
  public void delete(int page) {
    try {
      // 抽离try/catch模块
      deletePageAndAllReferences(page);
    } catch (Exception e) {
      log(e.getMessage());
    }
  }

  // 使用异常代替返回码
  private void deletePageAndAllReferences(int page) throws Exception {
   // do something
  }

  // 错误处理
  private void log(String msg) {
    // log msg
  }
}
