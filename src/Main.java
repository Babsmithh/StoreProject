import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String Path = "C:\\Users\\HP\\OneDrive\\Dokumente\\Products.txt";
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Store store = new Store("Smith Store", "Undisclosed");
        Manager manager = new Manager("Smith", "Male", 15000);
        manager.staffDuty();

        System.out.print("Enter Cashier's name: ");
        String cashierName = scanner.nextLine();
        Cashier cashier = new Cashier(cashierName, "Female", 1500);
        store.hireCashier(cashier);

        System.out.println();

        List<Product> myProducts = loadProductsFromFile();

        System.out.println("Available Products: ");
        for (Product product : myProducts) {
            System.out.println(product.getName() + " - Price: " + product.getPrice() + ", Quantity: " + product.getQuantity());

        }

        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();

        System.out.println("Enter your Initial Balance");
        double initialBalance = scanner.nextDouble();

        Customer customer = new Customer(customerName, initialBalance);

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
            if (customer.getBalance() >= customerOrder.getPrice() && customerOrder.getQuantity() > 0) {
                customer.setBalance(customer.getBalance() - customerOrder.getPrice());
                customerOrder.setQuantity(customerOrder.getQuantity() - 1);
                System.out.print("You have successfully purchased one " + customerOrder.getName() + " at " + customerOrder.getPrice() +
                        ", Remaining balance: " + customer.getBalance());
            } else if(customerOrder.getQuantity() <= 0) {
                System.out.println("Sorry, " + customerOrder.getName() + " is OUT OF STOCK");
            } else {
                System.out.println("Insufficient balance to buy " + customerOrder.getName() + ".");
            }
        } else {
            System.out.println("Product not found: " + productName);
        }

        saveProductsToFile(myProducts);

        System.out.println();
        System.out.println("Thank you for visiting " + store.name + "!");

    }

    private static List<Product> loadProductsFromFile() {
        List<Product> products = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(Path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(","); // Assuming the format: name,price,quantity
                String name = data[0];
                double price = Double.parseDouble(data[1]);
                int quantity = Integer.parseInt(data[2]);
                products.add(new Product(name, price, quantity));
            }
        } catch (IOException e) {
            System.err.println("Error reading products from file: " + e.getMessage());
        }
        return products;
    }

    private static void saveProductsToFile(List<Product> products) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Path))) {
            for (Product product : products) {
                writer.write(product.getName() + "," + product.getPrice() + "," + product.getQuantity());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing products to file: " + e.getMessage());
        }
    }
}

