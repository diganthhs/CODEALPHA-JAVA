import java.util.*;

class Stock {
    String symbol;
    double price;

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }
}

class Portfolio {
    Map<String, Integer> holdings = new HashMap<>();
    double balance;

    public Portfolio(double balance) {
        this.balance = balance;
    }

    public void buyStock(String symbol, int quantity, double price) {
        double cost = quantity * price;
        if (cost > balance) {
            System.out.println("Insufficient balance!");
            return;
        }
        balance -= cost;
        holdings.put(symbol, holdings.getOrDefault(symbol, 0) + quantity);
        System.out.println("Bought " + quantity + " shares of " + symbol);
    }

    public void sellStock(String symbol, int quantity, double price) {
        if (!holdings.containsKey(symbol) || holdings.get(symbol) < quantity) {
            System.out.println("Not enough shares to sell!");
            return;
        }
        balance += quantity * price;
        holdings.put(symbol, holdings.get(symbol) - quantity);
        if (holdings.get(symbol) == 0) holdings.remove(symbol);
        System.out.println("Sold " + quantity + " shares of " + symbol);
    }

    public void viewPortfolio() {
        System.out.println("Portfolio:");
        for (Map.Entry<String, Integer> entry : holdings.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " shares");
        }
        System.out.println("Balance: $" + balance);
    }
}

public class StockTrading {
    static List<Stock> market = new ArrayList<>();
    static Portfolio portfolio = new Portfolio(10000); // Starting balance

    public static void main(String[] args) {
        initializeMarket();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (choice) {
                case 1:
                    viewMarket();
                    break;
                case 2:
                    tradeStock(scanner, true);
                    break;
                case 3:
                    tradeStock(scanner, false);
                    break;
                case 4:
                    portfolio.viewPortfolio();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void initializeMarket() {
        market.add(new Stock("AAPL", 150.0));
        market.add(new Stock("GOOGL", 2800.0));
        market.add(new Stock("AMZN", 3500.0));
        market.add(new Stock("TSLA", 900.0));
    }

    private static void viewMarket() {
        System.out.println("Market Data:");
        for (Stock stock : market) {
            System.out.println(stock.symbol + " - $" + stock.price);
        }
    }

    private static void tradeStock(Scanner scanner, boolean isBuying) {
        System.out.print("Enter stock symbol: ");
        String symbol = scanner.nextLine().toUpperCase();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        
        Stock stock = market.stream().filter(s -> s.symbol.equals(symbol)).findFirst().orElse(null);
        if (stock == null) {
            System.out.println("Stock not found!");
            return;
        }

        if (isBuying) {
            portfolio.buyStock(symbol, quantity, stock.price);
        } else {
            portfolio.sellStock(symbol, quantity, stock.price);
        }
    }
}
