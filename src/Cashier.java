import java.awt.*;
import java.util.Map;

public class Cashier extends Person implements Greeting {

    public final double salary;

    public Cashier(String name, double salary) {
        super(name);
        this.salary = salary;
    }

    public void greet() {
        System.out.println("My name is " + name + ". You are welcome to our store. I'm your attending cashier to walk you through your shopping exercise");
    }

    public void dispenseReceipt(Customer customer) {
        System.out.println("\n--- RECEIPT ---");
        System.out.println("Customer: " + customer.getName());
        System.out.println();
        double totalBill = 0;

        for (Map.Entry<Product, Integer> receipt : customer.getCart().entrySet()) {
            Product product = receipt.getKey();
            int quantity = receipt.getValue();
            double itemTotal = product.getPrice() * quantity;

            System.out.println(product.getName() + " (" + quantity + " x $" + product.getPrice() + ") - $" + itemTotal);

            totalBill += itemTotal;
        }
        System.out.println("GRAND TOTAL: $" + totalBill);
        System.out.println("-----------------");
    }

}