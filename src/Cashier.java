public class Cashier extends Person implements Greeting {

    public final double salary;

    public Cashier(String name, double salary) {
        super(name);
        this.salary = salary;
    }

    public void greet() {
        System.out.println("My name is " + name + ". You are welcome to our store. I'm your attending cashier to walk you through your shopping exercise");
    }

}