package Chapter9.eachtestoneassert;

import fitnesse.*;
import org.junit.Before;

/**
 * 单个断言版本
 * 原则1：每个测试函数一个断言 例子
 * @author Tosca
 * @date 28/1/2020
 */
public class SerializedPageResponderTest {
  private PageCrawler crawler;
  private WikiPage root;
  private Request request;
  private SimpleResponse response;
  private String xml;

  @Before
  public void initialize() {
    crawler = new PageCrawler();
    root = new WikiPage();
    request = new Request();
  }

  // 原则1：每个测试函数一个断言
  // 符合given-when-then
  public void testGetPageHierarchyAsXml() throws Exception {
    givenPages("PageOne", "PageOne.ChildOne", "PageTwo");

    whenRequestIsIssued("root", "type:pages");

    thenResponseShouldBeXML();
  }

  public void testGetPageHierarchyHasRightTags() throws Exception {
    givenPages("PageOne", "PageOne.ChildOne", "PageTwo");

    whenRequestIsIssued("root", "type:pages");

    thenResponseShouldContain("<name>PageOne</name>", "<name>PageTwo</name>", "<name>ChildOne</name>");
  }

  private void givenPages(String... paths) {
    for (String path : paths) {
      crawler.addPage(root, PathParser.parse(path));
    }
  }

  private void whenRequestIsIssued(String src, String input) {
    request.setResource(src);
    String[] subStrings = input.split(":");
    request.addInput(subStrings[0], subStrings[1]);

    Responder responder = new SerializedPageResponder();
    response = (SimpleResponse) responder.makeResponse(
        new FitNesseContext(root), request);
    xml = response.getContent();
  }

  private void thenResponseShouldBeXML() {
    assert "text/xml".equals(response.getContentType());
  }

  private void thenResponseShouldContain(String... strs) {
    for (String str : strs) {
      assert xml.contains(str);
    }
  }
}
