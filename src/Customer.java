public class Customer extends Person implements Greeting  {

    private double balance;

    public Customer(String name, double balance) {
        super(name);
        this.balance = balance;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public void greet() {
        System.out.println("Thank you, have a nice day!");
    }
}
