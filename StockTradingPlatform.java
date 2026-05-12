import java.util.*;

class Stock {
    private String symbol;
    private double price;
Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }
public String getSymbol() {
        return symbol;
    } 
public double getPrice() {
        return price;
    }
public void setPrice(double price){
        this.price = price;
    }
} 

class Transaction {
    private String type;
    private String symbol;
    private int quantity;
    private double price;

public Transaction(String type, String symbol, int quantity, double price){
    this.type=type; 
    this.symbol=symbol; 
    this.quantity=quantity; 
    this.price=price;
    } 
 @Override
    
public String toString() {
    return type + "." + symbol + ". Quantity: " + quantity + ". Price: " +price;
    }
} 

class Portfolio {
    private HashMap <String, Integer> holdings;
    public Portfolio() {
        holdings = new HashMap<>();
    }
public void addStock(String symbol, int quantity) {
    holdings.put(symbol,
        holdings.getOrDefault(symbol,0)+quantity); 
} 
public void removeStock(String symbol, int quantity) {

    int current = holdings.getOrDefault(symbol, 0);
    if (current <= quantity) {
    holdings.remove(symbol);
} 
else {
    holdings.put(symbol, current - quantity);
}
} 
public HashMap <String, Integer> getHoldings() {
    return holdings;
} 
public void displayPortfolio(HashMap<String, Stock> market) {
    System.out.println(" PORTFOLIO: ");
    double total = 0; 

    for (String symbol: holdings.keySet()) {
        int quantity = holdings.get(symbol);

Stock stock = market.get(symbol);
if (stock == null) {
    System.out.println(symbol + " | Data not available");
    continue;
}
double stockPrice = stock.getPrice();
        double value = quantity * stockPrice;
        total += value;

    System.out.println(symbol + " | Shares: " + quantity + 
              " | Current Price: " + stockPrice + " | Value: " + value);
}
 System.out.println("Total Portfolio Value: " +total);
    }
} 
class User {
    private double balance;
    private Portfolio portfolio;

public User(String name, double balance) {
        this.balance = balance;
        this.portfolio = new Portfolio();
    }
public void buyStock(String symbol, int quantity, HashMap<String, Stock> market) {
    if (!market.containsKey(symbol)) {
        System.out.println("Stock not found.");
        return;
        }
    double price = market.get(symbol).getPrice();
    double cost = price * quantity;

    if (balance >= cost) {
        balance -= cost;
        portfolio.addStock(symbol, quantity);
        System.out.println("Bought " + quantity + " shares of " + symbol);
    } 
    else {
        System.out.println("Insufficient balance.");
        }
    }
public void sellStock(String symbol, int quantity, HashMap<String, Stock> market) {

    if (!market.containsKey(symbol)) {
        System.out.println("Stock not found.");
        return;
    }
int owned = portfolio.getHoldings().getOrDefault(symbol, 0);

    if (owned >= quantity) {
        double price = market.get(symbol).getPrice();
        balance += price * quantity;
        portfolio.removeStock(symbol, quantity);

        System.out.println("Sold " + quantity + " shares of " + symbol);
    } 
    else {
        System.out.println("Not enough shares to sell.");
    }
}
public void showPortfolio(HashMap<String, Stock> market) {
    portfolio.displayPortfolio(market);

    System.out.println("Balance: " + balance);
    }
}
public class StockTradingPlatform {
    public static void main(String[] args) {

    HashMap<String, Stock> market = new HashMap<>();
    market.put("AAPL", new Stock("AAPL", 150));
    market.put("GOOG", new Stock("GOOG", 2800));

    User user = new User("Alice", 10000);

    user.buyStock("AAPL", 10, market);
    user.sellStock("AAPL", 5, market);

    user.showPortfolio(market);
    }
}
