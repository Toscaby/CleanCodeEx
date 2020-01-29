package Chapter10.isolation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Tosca
 * @date 29/1/2020
 */
public class PortfolioTest {
  private FixedStockExchangeStub exchange;
  private Portfolio portfolio;

  @Before
  public void setUp() {
    exchange = new FixedStockExchangeStub();
    exchange.fix("MSFT", 100);
    exchange.fix("TENCENT", 70);
    portfolio = new Portfolio(exchange);
  }

  @Test
  public void GivenFixedMSFTTotalShouldBe500() {
    portfolio.add("MSFT", 5);
    portfolio.add("TENCENT", 8);
    Assert.assertEquals(1060, portfolio.value(), 0.0001f);
  }
}
