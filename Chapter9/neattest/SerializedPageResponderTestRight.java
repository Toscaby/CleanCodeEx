package Chapter9.neattest;

import fitnesse.*;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Tosca
 * @date 28/1/2020
 */
public class SerializedPageResponderTestRight {
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

  @Test
  public void testGetPageHierarchyAsXml() throws Exception {
    makePages("PageOne", "PageOne.ChildOne", "PageTwo");

    submitRequest("root", "type:pages");

    assertResponseIsXML();
    assertResponseContains("<name>PageOne</name>", "<name>PageTwo</name>", "<name>ChildOne</name>");
  }

  @Test
  public void testGetPageHierarchyAsXmlDoesNotContainsSymbolicLinks() {
    WikiPage page = makePage("PageOne");
    makePages("PageOne.ChildOne", "PageTwo");

    addLinkTo(page, "SymPage", "PageTwo");

    submitRequest("root", "type:pages");

    assertResponseIsXML();
    assertResponseContains("<name>PageOne</name>", "<name>PageTwo</name>", "<name>ChildOne</name>");
    assertResponseDoesNotContains("SymPage");
  }

  @Test
  public void testGetDataAsHtml() throws Exception {
    makePageWithContent("TestPageOne", "test page");

    submitRequest("root", "type:data");

    assertResponseIsXML();
    assertResponseContains("test page", "<Test");
  }

  private void makePages(String... paths) {
    for (String path : paths) {
      crawler.addPage(root, PathParser.parse(path));
    }
  }

  private WikiPage makePage(String path) {
    return crawler.addPage(root, PathParser.parse(path));
  }

  private WikiPage makePageWithContent(String path, String content) {
    return crawler.addPage(root, PathParser.parse(path), content);
  }

  private void submitRequest(String src, String input) {
    request.setResource(src);
    String[] subStrings = input.split(":");
    request.addInput(subStrings[0], subStrings[1]);

    Responder responder = new SerializedPageResponder();
    response = (SimpleResponse) responder.makeResponse(
        new FitNesseContext(root), request);
    xml = response.getContent();
  }

  private void addLinkTo(WikiPage page, String str1, String str2) {
    PageData data = page.getData();
    WikiPageProperties properties = data.getProperties();
    WikiPageProperty symLinks = properties.set(SymbolicPage.PROPERTY_NAME);
    symLinks.set(str1, str2);
    page.commit(data);
  }

  private void assertResponseIsXML() {
    assert "text/xml".equals(response.getContentType());
  }

  private void assertResponseContains(String... strs) {
    for (String str : strs) {
      assert xml.contains(str);
    }
  }

  private void assertResponseDoesNotContains(String str) {
    assert !xml.contains(str);
  }
}
