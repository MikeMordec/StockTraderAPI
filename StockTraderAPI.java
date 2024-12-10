// Repository Name: StockTraderAPI

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/trade")
public class TradingController {

    // Simulated user data
    private double balance = 10.00; // User's cash balance
    private final Map<String, Stock> portfolio = new HashMap<>(); // User's portfolio

    // Inner class to represent a stock
    static class Stock {
        String symbol;
        double price;
        int quantity;

        Stock(String symbol, double price, int quantity) {
            this.symbol = symbol;
            this.price = price;
            this.quantity = quantity;
        }
    }

    // Mocked method to simulate fetching a stock's price
    private double fetchStockPrice(String symbol) {
        // Mock prices for simplicity
        switch (symbol.toUpperCase()) {
            case "AAPL": return 9.50;
            case "TSLA": return 7.25;
            case "AMZN": return 6.80;
            default: return -1; // Indicates stock not found
        }
    }

    // Endpoint to buy stocks
    @PostMapping("/buy")
    public ResponseEntity<String> buyStock(@RequestParam String symbol, @RequestParam int quantity) {
        double price = fetchStockPrice(symbol);

        if (price == -1) {
            return ResponseEntity.badRequest().body("Stock symbol not found: " + symbol);
        }

        double totalCost = price * quantity;
        if (totalCost > balance) {
            return ResponseEntity.badRequest().body("Insufficient funds. Available balance: $" + balance);
        }

        // Update balance and portfolio
        balance -= totalCost;
        portfolio.putIfAbsent(symbol, new Stock(symbol, price, 0));
        Stock stock = portfolio.get(symbol);
        stock.quantity += quantity;

        return ResponseEntity.ok("Bought " + quantity + " shares of " + symbol + " for $" + totalCost);
    }

    // Endpoint to sell stocks
    @PostMapping("/sell")
    public ResponseEntity<String> sellStock(@RequestParam String symbol, @RequestParam int quantity) {
        Stock stock = portfolio.get(symbol);

        if (stock == null || stock.quantity < quantity) {
            return ResponseEntity.badRequest().body("Not enough shares of " + symbol + " to sell.");
        }

        double earnings = stock.price * quantity;
        stock.quantity -= quantity;
        balance += earnings;

        if (stock.quantity == 0) {
            portfolio.remove(symbol); // Remove stock if fully sold
        }

        return ResponseEntity.ok("Sold " + quantity + " shares of " + symbol + " for $" + earnings);
    }

    // Endpoint to view current portfolio
    @GetMapping("/portfolio")
    public ResponseEntity<Map<String, String>> viewPortfolio() {
        Map<String, String> portfolioSummary = new HashMap<>();

        portfolio.forEach((symbol, stock) -> {
            portfolioSummary.put(symbol, "Shares: " + stock.quantity + ", Price: $" + stock.price);
        });

        portfolioSummary.put("Cash Balance", "$" + balance);
        return ResponseEntity.ok(portfolioSummary);
    }

    // Endpoint to check a stock's price
    @GetMapping("/price")
    public ResponseEntity<String> getStockPrice(@RequestParam String symbol) {
        double price = fetchStockPrice(symbol);

        if (price == -1) {
            return ResponseEntity.badRequest().body("Stock symbol not found: " + symbol);
        }

        return ResponseEntity.ok("Current price of " + symbol + ": $" + price);
    }

    // Endpoint to check cash balance
    @GetMapping("/balance")
    public ResponseEntity<String> getBalance() {
        return ResponseEntity.ok("Current cash balance: $" + balance);
    }
}

