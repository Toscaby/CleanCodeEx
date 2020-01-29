package Chapter10.isolation;

import java.util.HashMap;
import java.util.Map;

/**
 * 类应该依赖抽线而不是具体细节
 * @author Tosca
 * @date 29/1/2020
 */
public class Portfolio {
  // 通过StockExchange隔离策略和具体交易市场
  private StockExchange exchange;
  private Map<String, Integer> portfolioMap;

  public Portfolio(StockExchange exchange) {
    this.exchange = exchange;
    portfolioMap = new HashMap<>();
  }

  public void add(String symbol, int quantity) {
    portfolioMap.put(symbol, portfolioMap.getOrDefault(symbol, 0) + quantity);
  }

  public float value() {
    float priceOfAll = 0f;
    for (String symbol: portfolioMap.keySet()) {
      priceOfAll += exchange.currentPrice(symbol) * portfolioMap.get(symbol);
    }
    return priceOfAll;
  }
}
