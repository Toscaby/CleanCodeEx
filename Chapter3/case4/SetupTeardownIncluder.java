package Chapter3.case4;

/**
 * @author Tosca
 * @date 2020/1/16
 */
public class SetupTeardownIncluder {
  private PageData pageData;
  private boolean isSuite;
  private WikiPage testPage;
  private StringBuffer newPageContent;
  private PageCrawler pageCrawler;

  public static String render(PageData pageData) throws Exception {
    return render(pageData, false);
  }

  public static String render(PageData pageData, boolean isSuite) throws Exception {
    // 这里
    return new SetupTeardownIncluder(pageData).render(isSuite);
  }

  private SetupTeardownIncluder(PageData pageData) {
    this.pageData = pageData;
    testPage = pageData.getWikiPage();
    pageCrawler = testPage.getPageCrawler();
    newPageContent = new StringBuffer();
  }

  private String render(boolean isSuite) throws Exception {
    this.isSuite = isSuite;
    if (isTestPage()) {
      includeSetupAndTeardownPages();
    }
    return pageData.getHtml();
  }

  private boolean isTestPage() throws Exception {
    return pageData.hasAttribute("Test");
  }

  private void includeSetupAndTeardownPages() throws Exception {
    // setup page
    includeSetupPages();
    // page content
    includePageContent();
    // teardown page
    includeTeardownPages();
    // update
    updatePageContent();
  }

  private void includeSetupPages() throws Exception {
    if (isSuite) {
      includeSuiteSetupPage();
    }
    includeSetupPage();
  }

  private void includeSuiteSetupPage() throws Exception {
    include("SUITE_SETUP_NAME", "-setup");
  }

  private void includeSetupPage() throws Exception {
    include("Setup", "-setup");
  }

  private void includePageContent() throws Exception {
    newPageContent.append(pageData.getContent());
  }

  private void includeTeardownPages() throws Exception {
    includeTeardownPage();
    if (isSuite) {
      includeSuiteTeardownPage();
    }
  }

  private void includeTeardownPage() throws Exception {
    include("SUITE_TEARDOWN_NAME", "-teardown");
  }

  private void includeSuiteTeardownPage() throws Exception {
    include("SUITE_TEARDOWN_NAME", "-teardown");
  }

  private void updatePageContent() throws Exception {
    pageData.setContent(newPageContent.toString());
  }

  private void include(String pageName, String arg) throws Exception {
    WikiPage inheritedPage = findInheritedPage(pageName);
    if (inheritedPage != null) {
      String pagePathName = getPathNameForPage(inheritedPage);
      buildIncludeDirective(pagePathName, arg);
    }
  }

  private WikiPage findInheritedPage(String pageName) throws Exception {
    // do something
    return new WikiPage();
  }

  private String getPathNameForPage(WikiPage page) throws Exception {
    WikiPagePath pagePath = pageCrawler.getFullPath(page);
    return PathParser.render(pagePath);
  }

  private void buildIncludeDirective(String pagePathName, String arg) {
    newPageContent
        .append("\n!include ")
        .append(arg)
        .append(" .")
        .append(pagePathName)
        .append("\n");
  }
}
