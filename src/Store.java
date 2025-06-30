import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Store {
    protected String name;

    public Store(String name) {
        this.name = name;
    }
    public void openStore(){
        System.out.println("\n" + name + " is Opened");
    }
    public void closeStore() {
        System.out.println("\n" + "CLOSING STORE");
    }

    public void shopping(Customer customer, List<Product> myProducts) {
        Scanner scanner = new Scanner(System.in);
        boolean shoppingDone = false;
        while (!shoppingDone) {
            System.out.println("\nAvailable products:");
            for (Product product : myProducts) {
                System.out.println("Name: " + product.getName() + " - Price: "
                        + product.getPrice() + ", -Quantity: " + product.getQuantity());
            }

            System.out.println();
            System.out.println("Enter the product you want to buy(type 'Done' to end): ");
            String productName = scanner.nextLine();

            if (productName.equalsIgnoreCase("Done")) {
                shoppingDone = true;
                continue;
            }
            Product customerOrder = null;
            for (Product product : myProducts) {
                if (product.getName().equalsIgnoreCase(productName)) {
                    customerOrder = product;
                    break;
                }
            }
            if (customerOrder == null) {
                System.out.println("Product not found: " + productName);
                continue;
            }
            if (customerOrder.getQuantity() <= 0) {
                System.out.println("Sorry, '" + customerOrder.getName() + "' is out of stock.");
                continue;
            }
            int productQuantity;
            while (true) {
                System.out.println("how many pieces of " + customerOrder.getName() + " would you like to purchase");
                try {
                    productQuantity = scanner.nextInt();
                    scanner.nextLine();
                    if (productQuantity <= 0) {
                        System.out.println("Invalid Quantity!");
                    } else if (productQuantity > customerOrder.getQuantity()) {
                        System.out.println("Sorry, Product not available in " + productQuantity + " pieces");
                        System.out.println("Available Quantity: " + customerOrder.getQuantity());
                    } else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.nextLine();
                }
            }

            customer.addToCart(customerOrder, productQuantity);
            customerOrder.setQuantity(customerOrder.getQuantity() - productQuantity);

            System.out.println("Current items in " + customer.getName() + "'s cart: " + customer.getTotalItems());
        }
    }
}
