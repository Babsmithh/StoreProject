import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Customer extends Person implements Greeting, Comparable<Customer> {

    private double balance;
    private int totalItems;
    private final LocalDateTime arrivalTime;
    private final Map<Product, Integer> cart;

    public Customer(String name, double balance) {
        super(name);
        this.balance = balance;
        this.totalItems = 0;
        this.arrivalTime = LocalDateTime.now();
        this.cart = new HashMap<>();
    }

    public double getBalance() {
        return balance;
    }
    public int getTotalItems() {
        return totalItems;
    }
    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }
    public Map<Product, Integer> getCart() {
        return cart;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void greet() {
        System.out.println("Thank you, " + getName() +"! Have a nice day!");
    }
    public void addToCart(Product product, int quantity) {
        cart.put(product, cart.getOrDefault(product, 0) + quantity);
        this.totalItems += quantity;
    }
    @Override
    public int compareTo(Customer other) {
        int quantityCompare = Integer.compare(other.totalItems, this.totalItems);
        if (quantityCompare != 0){
            return quantityCompare;
        }
        return this.arrivalTime.compareTo(other.arrivalTime);
    }
}
