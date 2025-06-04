import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Store store = new Store("Smith Store");
        Manager manager = new Manager("Smith", "Male", 15000);
        manager.staffDuty();

        System.out.print("Enter Cashier's name: ");
        String cashierName = scanner.nextLine();
        Cashier cashier = new Cashier(cashierName, "Female", 1500);
        store.hireCashier(cashier);

        System.out.println();

        Product[] myProducts = new Product[4];
        myProducts[0] = new Product("Pepsi", 500, 2);
        myProducts[1] = new Product("Gala", 250, 3);
        myProducts[2] = new Product("Water", 1400, 1);
        myProducts[3] = new Product("Bread", 1250, 81);

        System.out.println("Available Products: ");
        for (Product product : myProducts) {
            System.out.println(product.getName() + " - Price: " + product.getPrice() + ", Quantity: " + product.getQuantity());
        }
        System.out.println();

        System.out.print("Customer, kindly input your name: ");
        String customerName = scanner.nextLine();

        System.out.println("Enter your Initial Balance");
        double initialBalance = scanner.nextDouble();
        System.out.println();

        Customer customer = new Customer(customerName, initialBalance);

        boolean multiplePurchase = true;

        while (multiplePurchase) {
            System.out.println("Enter the product you want to buy: ");
            scanner.nextLine();
            String productName = scanner.nextLine();

            Product customerOrder = null;
            for (Product product : myProducts) {
                if (product.getName().equalsIgnoreCase(productName)) {
                    customerOrder = product;
                    break;
                }
            }

            if (customerOrder != null) {
                if (customer.getBalance() >= customerOrder.getPrice()) {
                    customer.setBalance(customer.getBalance() - customerOrder.getPrice());
                    System.out.print("You have successfully purchased one " + customerOrder.getName() + " at " + customerOrder.getPrice() +
                            ", Remaining balance: " + customer.getBalance());
                } else if (customer.getBalance() < customerOrder.getPrice()) {
                    System.out.println("Insufficient balance to buy " + customerOrder.getName() + ".");
                }
            } else {
                System.out.println("Product not found: " + productName);
            }
            System.out.println();

            System.out.println(customerName + ", do you wish to perform another operation?: Y/N ");
            String nextStep = scanner.nextLine();
            if (nextStep.equalsIgnoreCase("Y")) {
                multiplePurchase = true;
            } else if (nextStep.equalsIgnoreCase("N")) {
                multiplePurchase = false;
            } else {
                System.out.println("Invalid input. Please enter Y or N.");
        }
            System.out.println("Thank you for visiting " + store.name + "!");

        }
}
}