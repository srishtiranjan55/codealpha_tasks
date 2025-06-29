 public class Stock {
    String symbol, name;
    double price;

    public Stock(String symbol, String name, double price) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
    }

    public String toString() {
        return symbol + " - " + name + ": " + price;
    }
}
