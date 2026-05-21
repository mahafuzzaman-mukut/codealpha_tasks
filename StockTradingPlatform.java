import java.util.*;

class Stock {
    String symbol;
    double price;

    Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    double getPrice() {
        return price;
    }
}

class Portfolio {
    HashMap<String, Integer> holdings = new HashMap<>();

    void addStock(String symbol, int quantity) {
        holdings.put(symbol,
                holdings.getOrDefault(symbol, 0) + quantity);
    }

    void removeStock(String symbol, int quantity) {
        int current = holdings.getOrDefault(symbol, 0);

        if (current <= quantity) {
            holdings.remove(symbol);
        } else {
            holdings.put(symbol, current - quantity);
        }
    }

    void display(HashMap<String, Stock> market) {

        double total = 0;

        System.out.println("PORTFOLIO:");

        for (String symbol : holdings.keySet()) {

            int qty = holdings.get(symbol);

            double price = market.get(symbol).getPrice();

            double value = qty * price;

            total += value;

            System.out.println(
                    symbol +
                    " Shares: " + qty +
                    " Price: " + price +
                    " Value: " + value
            );
        }

    System.out.println("Total Value: " + total);
    }
}
  class User {

    double balance;

    Portfolio portfolio = new Portfolio();

    User(double balance) {
        this.balance = balance;
    }

    void buyStock(String symbol,
                  int quantity,
                  HashMap<String, Stock> market) {

        if (!market.containsKey(symbol)) {
            System.out.println("Stock not found");
            return;
        }

        double cost =
                market.get(symbol).getPrice() * quantity;

        if (balance >= cost) {

            balance -= cost;

            portfolio.addStock(symbol, quantity);

            System.out.println(
                    "Bought " + quantity +
                    " shares of " + symbol
            );

        } else {

            System.out.println("Insufficient balance");
        }
    }

    void sellStock(String symbol,
                   int quantity,
                   HashMap<String, Stock> market) {

        int owned =
                portfolio.holdings.getOrDefault(symbol, 0);

        if (owned >= quantity) {

            double money =
                    market.get(symbol).getPrice() * quantity;

            balance += money;

            portfolio.removeStock(symbol, quantity);

            System.out.println(
                    "Sold " + quantity +
                    " shares of " + symbol
            );

        } else {

            System.out.println("Not enough shares");
        }
    }

    void showPortfolio(HashMap<String, Stock> market) {

        portfolio.display(market);

        System.out.println("Balance: " + balance);
    }
}

public class Main {

    public static void main(String[] args) {

        HashMap<String, Stock> market =
                new HashMap<>();

        market.put("AAPL", new Stock("AAPL", 150));

        market.put("GOOG", new Stock("GOOG", 2800));

        User user = new User(10000);

        user.buyStock("AAPL", 10, market);

        user.sellStock("AAPL", 5, market);

        user.showPortfolio(market);
    }
}
