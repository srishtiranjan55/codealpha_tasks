 import java.util.*;
 public class Market {
    Map<String, Stock> stocks = new HashMap<>();

    public void addStock(Stock stock) {
        stocks.put(stock.symbol, stock);
    }

    public void displayMarket() {
        System.out.println("\n Current Market:");
        for (Stock stock : stocks.values()) {
            System.out.println(stock);
        }
    }
    public Stock getStock(String symbol) {
        return stocks.get(symbol);
    }
    public void updatePrices() {
        Random rand = new Random();
        for (Stock stock : stocks.values()) {
            double changePercent = (rand.nextDouble() * 10) - 5; // -5% to +5%
            double changeAmount = stock.price * (changePercent / 100);
            stock.price += changeAmount;
            stock.price = Math.round(stock.price * 100.0) / 100.0; // Round to 2 decimal places
        }
    }
}


