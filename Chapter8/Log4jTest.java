package Chapter8;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.junit.Test;

/**
 * 学习性测试
 * @author Tosca
 * @date 27/1/2020
 */
public class Log4jTest {
  private Logger logger;

  @Test
  public void testLogCreate() {
    Logger logger = Logger.getLogger("MyLogger");
    logger.removeAllAppenders();
    logger.addAppender(new ConsoleAppender(new PatternLayout("%p %t %m%n"),
            ConsoleAppender.SYSTEM_OUT));
    logger.info("hello logger");
  }
}
