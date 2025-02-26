import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Store store = new Store("Smith Store", "Male");
        Manager manager = new Manager("Smith", "Male", 15000);
        manager.staffDuty();

        System.out.print("Enter Cashier's name: ");
        String cashierName = scanner.nextLine();
        Cashier cashier = new Cashier(cashierName, "Female", 1500);
        System.out.print("Cashier employed: ");
        cashier.staffDuty();

        Product product = new Product("Pepsi", 1000, 5);

        System.out.println("Available products:");
        System.out.println(product.getName() + " - Price: " + product.getPrice() + ", Quantity: " + product.getQuantity());

        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();

        Customer customer = new Customer(customerName, 2567);
        System.out.println("Current balance: " + customer.getBalance());
        customer.buyProducts();


        if (customer.getBalance() >= product.getPrice()) {
            customer.setBalance(customer.getBalance() - product.getPrice());
            System.out.print("You have successfully purchased one " + product.getName() + " at " + product.getPrice() +
                    ", Remaining balance: " + customer.getBalance());

        } else {
            System.out.println("Insufficient balance to buy " + product.getName() + ".");
        }
    }
}