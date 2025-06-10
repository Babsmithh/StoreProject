public class Cashier extends Person {

    private final int salary;

    public Cashier(String name, int salary) {
        super(name);
        this.salary = salary;
    }

    public void greet() {
        System.out.println("My name is " + name + ". You are welcome to our store. How may we help you?");
    }
}