package Chapter5;

import java.io.FileInputStream;
import java.io.InputStream;

public class Principles {
  // 空白行区分不同的方法或者代码块

  // 紧密相关的代码应该相互靠近

  // 本地变量声明尽可能靠近其使用位置，实体变量在类顶部

  // 相关方法写在一起, eg:
  public class Request {
    public String getResource() {return null;}
  }
  public class Response {

  }
  public class SimpleResponse extends Response {

  }

  public class FitnessContext {}
  public class WikiPage {}
  public class WikiPagePath {}
  public class PageData {}
  public class PageCrawler {}

  public interface SecureResponder {
    Response makeResponse(FitnessContext context, Request request) throws Exception;
  }

  public class WikiPageResponder implements SecureResponder {
    protected WikiPage page;
    protected PageData pageData;
    protected String pageTitle;
    protected Request request;
    protected PageCrawler crawler;

    public Response makeResponse(FitnessContext context, Request request) throws Exception {
      // 这样写不会把常量埋在底层private方法中
      String pageName = getPageNameOrDefault(request, "FrontPage");
      loadPage(pageName, context);
      if (page == null) {
        return notFoundResponse(context, request);
      } else {
        return nakePageResponse(context);
      }
    }

    private String getPageNameOrDefault(Request request, String defaultPageName) {
      String pageName = request.getResource();
      if (isEmpty(pageName)) {
        pageName = defaultPageName;
      }
      return pageName;
    }

    private boolean isEmpty(String str) {
      return !(str != null && !str.equals(""));
    }

    private void loadPage(String resource, FitnessContext context) throws Exception {
      // do something
    }

    private Response notFoundResponse(FitnessContext context, Request request) throws Exception {
      // do something
      return null;
    }

    private SimpleResponse nakePageResponse(FitnessContext context) throws Exception {
      // do something
      return null;
    }
  }

  // 概念相关 应当放在一起
  // e.g. :
  public void assertFalse(String msg, boolean condition) {}

  public void assertTrue(boolean condition) {
    assertTrue(null, condition);
  }

  public void assertTrue(String msg, boolean condition) {}
}
