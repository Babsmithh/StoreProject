public class Staff extends Store{
    public String gender;
    public double salary;

    public Staff(String name, String gender, double salary) {
        super(name);
        this.gender = gender;
        this.salary = salary;
    }
    public void staffDuty() {
        System.out.println(name + " is currently working in the store.");
    }
}
class Manager extends Staff {
    public Manager(String name, String gender, double salary) {
        super(name, gender, salary);
    }

    @Override
    public void staffDuty() {
        System.out.println(name + " is the manager of the store");
    }
}

class Cashier extends Staff {
    public Cashier(String name, String gender, double salary) {
        super(name, gender, salary);
    }
}

class Customer {
    public static String name;
    private double balance;

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

}
