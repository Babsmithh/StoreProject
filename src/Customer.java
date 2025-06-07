public class Customer extends Person {

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


}
