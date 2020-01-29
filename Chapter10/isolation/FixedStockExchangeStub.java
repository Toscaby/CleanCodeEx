package Chapter10.isolation;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tosca
 * @date 29/1/2020
 */
public class FixedStockExchangeStub implements StockExchange {
  private Map<String, Float> priceList;

  public FixedStockExchangeStub() {
    priceList = new HashMap<>();
  }

  public void fix(String symbol, float price) {
    priceList.put(symbol, price);
  }

  @Override
  public float currentPrice(String symbol) {
    return priceList.get(symbol);
  }
}
