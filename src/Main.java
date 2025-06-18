import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String filePath = "C:\\Users\\HP\\IdeaProjects\\StoreProject\\src\\Product.txt";

    public static List<Product> storeProducts(String file) {
        List<Product> productList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    try {
                        String name = parts[0].trim();
                        int price = Integer.parseInt(parts[1].trim());
                        int quantity = Integer.parseInt(parts[2].trim());
                        productList.add(new Product(name, price, quantity));
                    } catch (NumberFormatException e) {
                        System.err.println("Warning: Skipping malformed line: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Cannot load file: " + e.getMessage());
        }
        return productList;
    }
    public static void newProducts(List<Product> products, String file) {
        for (Product product : products) {
            String line = product.getName() + "," + product.getPrice() + "," + product.getQuantity();
            System.out.println(line);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Product product : products) {
                String line = product.getName() + "," + product.getPrice() + "," + product.getQuantity();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Can't write to file: " + e.getMessage());
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Store store = new Store("Smith Store");
        Manager manager = new Manager("Smith", "Male", 3000);
        manager.staffDuty();
        System.out.print("Enter Cashier's name: ");
        String cashierName = scanner.nextLine();
        System.out.println();
        System.out.println("Input your gender: ");
        String cashierGender = scanner.nextLine();
        Cashier cashier = new Cashier(cashierName,cashierGender, 1500);
        manager.greet();
        System.out.println();
        List<Product> myProducts = storeProducts(filePath);
        if (myProducts.isEmpty()) {
            System.out.println("File is empty.");
        }
        System.out.println("Available Products: ");
        for (Product product : myProducts) {
            System.out.println(product.getName() + " - Price: " + product.getPrice() + ", Quantity: " + product.getQuantity());
        }
        System.out.println();
        System.out.print("Customer, kindly input your name: ");
        String customerName = scanner.nextLine();
        cashier.greet();
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
            if (customerOrder == null) {
                System.out.println("Product not found: " + productName);
                continue;
            }
            if (customerOrder.getQuantity() <= 0) {
                System.out.println("Sorry, Out of stock");
            } else {
                System.out.println("how many pieces of " + productName + " would you like to purchase");
                int productQuantity = scanner.nextInt();
                scanner.nextLine();
                if (productQuantity > customerOrder.getQuantity()) {
                    System.out.println("Sorry, the product is not available in " + productQuantity + " pieces");
                    System.out.println("Available quantity: " + customerOrder.getQuantity());
                } else {
                    double totalPrice = productQuantity * customerOrder.getPrice();
                    if (customer.getBalance() >= totalPrice) {
                        customer.setBalance(customer.getBalance() - totalPrice);
                        customerOrder.setQuantity(customerOrder.getQuantity() - productQuantity);
                        newProducts(myProducts, filePath);

                        System.out.println("You have successfully purchased " + productQuantity + " " + customerOrder.getName() + " at " + totalPrice +
                                ", Remaining balance: " + customer.getBalance());
                        System.out.println("Quantity of " + customerOrder.getName() + " now available: " + customerOrder.getQuantity());
                    } else {
                        System.out.println("Insufficient balance to buy " + customerOrder.getName() + ".");
                    }
                }
            }
            System.out.println();
            System.out.println(customerName + ", do you wish to perform another operation?: Y/N ");
            String checkOut = scanner.nextLine();
            if (checkOut.equalsIgnoreCase("Y")) {
            } else if (checkOut.equalsIgnoreCase("N")) {
                multiplePurchase = false;
            } else {
                System.out.println("Invalid input. Please enter Y or N.");
            }
        }
        customer.greet();
        System.out.println("Thank you for visiting " + store.name + "!");
    }
}