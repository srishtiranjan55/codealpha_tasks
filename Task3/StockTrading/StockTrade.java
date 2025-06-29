import java.util.*;

public class StockTrade {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Stock Trading Simulator!");

        
        Market market = new Market();
        market.addStock(new Stock("TCS", "Tata Consultancy Services", 3450.0));
        market.addStock(new Stock("INFY", "Infosys", 1550.0));
        market.displayMarket();

        User user = new User("Srishti", 10000);
        user.loadFromFile("portfolio.txt");
        System.out.println(user);

       while (true) {
            System.out.println("\n==== MENU ====");
            System.out.println("1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. View Total Portfolio Value"); 
            System.out.println("6. Save Portfolio");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    market.updatePrices(); 
                    market.displayMarket();
                    break;
                case 2:
                    System.out.print("Enter stock symbol to buy: ");
                    String buySymbol = sc.next().toUpperCase();
                    System.out.print("Enter quantity to buy: ");
                    if (sc.hasNextInt()) {
                        int buyQty = sc.nextInt();
                        user.buyStock(market, buySymbol, buyQty);
                    } else {
                        System.out.println("Invalid quantity. Please enter a number.");
                        sc.next(); 
                    }
                    break;
                case 3:
                    System.out.print("Enter stock symbol to sell: ");
                    String sellSymbol = sc.next().toUpperCase();
                    System.out.print("Enter quantity to sell: ");
                    if (sc.hasNextInt()) {
                        int sellQty = sc.nextInt();
                        user.sellStock(market, sellSymbol, sellQty);
                    } else {
                        System.out.println("Invalid quantity. Please enter a number.");
                        sc.next(); 
                    }
                    break;
                case 4:
                    user.displayPortfolio(market);
                    break;
                case 5:
                    user.displayTotalPortfolioValue(market);
                    break;
                case 6:
                    user.saveToFile("portfolio.txt");
                    break;
                case 7:
                    System.out.println("Exiting... Thank you!");
                    user.saveToFile("portfolio.txt");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }       
        }
    }
}

