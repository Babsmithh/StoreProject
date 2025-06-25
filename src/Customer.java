import java.time.LocalDateTime;

public class Customer extends Person implements Greeting, Comparable<Customer> {

    private double balance;
    private LocalDateTime arrivalTime;

    public Customer(String name, double balance) {
        super(name);
        this.balance = balance;
        this.arrivalTime = LocalDateTime.now();
    }
    public double getBalance() {
        return balance;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public void greet() {
        System.out.println("Thank you, " + getName() +"! Have a nice day!");
    }

    @Override
    public int compareTo(Customer other) {
        return this.arrivalTime.compareTo(other.arrivalTime);
    }
}
