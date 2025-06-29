import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.*;
import java.io.*;

 public class User {
    String username;
    double balance;
    Map<String, Integer> portfolio = new HashMap<>();

    public User(String username, double balance) {
        this.username = username;
        this.balance = balance;
    }
        public void buyStock(Market market, String symbol, int quantity) {
            Stock stock = market.getStock(symbol);
            if (stock == null) {
                System.out.println("Stock not found in market.");
                return;
            }

            double totalCost = stock.price * quantity;

            if (balance >= totalCost) {
                balance -= totalCost;
                portfolio.put(symbol, portfolio.getOrDefault(symbol, 0) + quantity);
                System.out.println("Bought " + quantity + " shares of " + symbol);
            } else {
                System.out.println("Not enough balance to buy " + quantity + " shares of " + symbol);
            }
        }

        public void sellStock(Market market, String symbol, int quantity) {
            if (!portfolio.containsKey(symbol) || portfolio.get(symbol) < quantity) {
                System.out.println("You don't own enough shares to sell.");
                return;
            }

            Stock stock = market.getStock(symbol);
            if (stock == null) {
                System.out.println("Stock not found in market.");
                return;
            }

            double totalGain = stock.price * quantity;
            balance += totalGain;
            portfolio.put(symbol, portfolio.get(symbol) - quantity);
            System.out.println("Sold " + quantity + " shares of " + symbol);
        }

        public void displayPortfolio(Market market) {
            System.out.println("\nYour Portfolio:");
            if (portfolio.isEmpty()) {
                System.out.println("You don't own any stocks yet.");
            } else {
                for (String symbol : portfolio.keySet()) {
                    int qty = portfolio.get(symbol);
                    double price = market.getStock(symbol).price;
                    System.out.println(symbol + ": " + qty + " shares (₹" + price + " each)");
                }
            }
            System.out.println("Remaining Balance: ₹" + balance);
        }
        public void displayTotalPortfolioValue(Market market) {
            double totalStockValue = 0; 
            for (String symbol : portfolio.keySet()) {
                int qty = portfolio.get(symbol);
                Stock stock = market.getStock(symbol);
                if (stock != null) {
                    totalStockValue += qty * stock.price;
                }
            }
            double totalValue = balance + totalStockValue;
            System.out.println("\nTotal Portfolio Value Report:");
            System.out.println("Cash Balance: Rs. " + balance);
            System.out.println("Stock Value: Rs. " + totalStockValue);
            System.out.println("sTotal Net Worth: Rs. " + totalValue);
        }
        public void saveToFile(String filename) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println(balance); // Save balance
            for (Map.Entry<String, Integer> entry : portfolio.entrySet()) {
                writer.println(entry.getKey() + "," + entry.getValue()); // stock,quantity
            }
            System.out.println("Portfolio saved to file: " + filename);
            } catch (IOException e) {
                System.out.println("Error saving file: " + e.getMessage());
            }
        }
        public void loadFromFile(String filename) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                portfolio.clear(); // clear previous data
                balance = Double.parseDouble(reader.readLine()); // Load balance
                String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String symbol = parts[0];
                    int quantity = Integer.parseInt(parts[1]);
                    portfolio.put(symbol, quantity);
                }
            }
            System.out.println("Portfolio loaded from file: " + filename);
            } catch (IOException | NumberFormatException e) {
                System.out.println("No saved portfolio found. Starting fresh.");
            }
        }       
    public String toString() {
        return "User: " + username + " Balance: " + balance;
    }
}
