public class Cashier extends Staff implements Greeting {

    public Cashier(String name, double salary) {
        super(name, salary);
    }

    public void greet() {
        System.out.println("My name is " + name + ". You are welcome to our store. I'm your attending cashier to walk you through your shopping exercise");
    }
}