package Chapter4.wrongcases;

/**
 * @author Tosca
 * @date 2020/1/22
 */
public class BadComments {
  // 多余的注释:注释没提供比代码更多的信息
  private boolean closed = false;

  // Utility method that returns when this.closed is true.Throws an exception
  // if the timeout is reached.
  public synchronized void waitForClose(final long timeoutMillis) throws Exception {
    if (!closed) {
      wait(timeoutMillis);
      if (!closed) {
        throw new Exception("Could not be closed!");
      }
    }
  }

  // 误导性注释：当closed不为true时 并不立即返回 而是进入wait 结束wait后 closed为true会抛异常

  // Utility method that returns when this.closed is true.Throws an exception
  // if the timeout is reached.
  public synchronized void waitForClose2(final long timeoutMillis) throws Exception {
    if (!closed) {
      wait(timeoutMillis);
      if (!closed) {
        throw new Exception("Could not be closed!");
      }
    }
  }


  // 废话注释：(中招

  /**
   * Default constructor.
   */
  protected BadComments() {}

  /** the day of the month. */
  private int dayOfMonth;

  /**
   * Returns the day of the month.
   * @return the day of the month
   */
  public int getDayOfMonth() {return dayOfMonth;}


  // 非本地信息:注释中提到的fitnesseport的default值此方法根本无法控制到
  // 如例子中，实际default值可能已经被修改为8080了，但此处注释中信息没同步
  /**
   * Port on which fitness would run. Defaults to 8082
   *
   * @param fitnessePort
   */
  private int fitnessePort = 8080;
  public void setFitnessePort(int fitnessePort) {
    this.fitnessePort = fitnessePort;
  }

  // 不明显的联系: filter bytes是什么? 为什么header info要200
  int pngWidth, pngHeight;
  /**
   * start with an array that is big enough to hold all the pixels
   * (plus filter bytes), and an extra 200 bytes for header info
   */
  byte[] pngBytes = new byte[(pngWidth + 1) * pngHeight * 3 + 200];


  // 还有其他的

  // 位置标记 （少用  e.g. // xxxx //////////////
  // 归属与签名
  // 注释掉的代码
  // HTML注释  如Javadoc （使用工具!!!
  // 信息过多 如无关紧要的描述 历史细节

}
