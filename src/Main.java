import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    private static final String filePath = "C:\\Users\\HP\\IdeaProjects\\StoreProject\\src\\Product.txt";
    private static PriorityQueue<Customer> customerQueue = new PriorityQueue<>();

    public static void newProducts(List<Product> products, String file) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write("Product, price, quantity");
            bw.newLine();
            for (Product product : products) {
                String line = product.getName() + "," + product.getPrice() +
                        "," + product.getQuantity();
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Can't write to file: " + e.getMessage());
        }
    }

    public static List<Product> storeProducts(String file) {
        List<Product> productList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            reader.readLine();
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Store store = new Store("Smith Store");
        Manager manager = new Manager("Smith", "Male", 3000);
        manager.staffDuty();

        System.out.print("Enter Cashier's name: ");
        String cashierName = scanner.nextLine();
        System.out.println();
        Cashier cashier = new Cashier(cashierName, 1500);
        manager.greet();

        List<Product> myProducts = storeProducts(filePath);
        System.out.println();
        System.out.println("Kindly upload products details(Or type Done to end): ");

        while (true) {
            System.out.print("Product Name: ");
            String name = scanner.nextLine();
            if (name.equalsIgnoreCase("Done")) {
                break;
            }
            System.out.print("Price: ");
            int price = Integer.parseInt(scanner.nextLine());
            System.out.print("Quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine());

            myProducts.add(new Product(name, price, quantity));
        }
        newProducts(myProducts, filePath);

        if (myProducts.isEmpty()) {
            System.out.println("File is empty.");
        }

        System.out.println();
        System.out.println("\n" + store.name + " Open");
        boolean moreCustomers = true;
        while (moreCustomers) {
            System.out.print("Customer Name: (Or type 'Done' to end) ");
            String customerName = scanner.nextLine();

            if (customerName.equalsIgnoreCase("done")) {
                moreCustomers = false;
                continue;
            }
            System.out.println("Enter your Initial Balance");
            double initialBalance = scanner.nextDouble();
            scanner.nextLine();

            Customer customer = new Customer(customerName, initialBalance);
            customerQueue.add(customer);
            System.out.println(customerName + " added to queue");

            while (!customerQueue.isEmpty()) {
                Customer attendingCustomer = customerQueue.poll();
                System.out.println("Attending to: " + attendingCustomer.getName() +
                        " (Arrived at: " + attendingCustomer.getArrivalTime());

                cashier.greet();

                boolean multiplePurchase = true;
                while (multiplePurchase) {
                    System.out.println("Available Products: ");
                    for (Product product : myProducts) {
                        System.out.println("Name: " + product.getName() + " - Price: " +
                                product.getPrice() + ", Quantity: " + product.getQuantity());
                    }

                    System.out.println("Enter the product you want to buy: ");
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
                        continue;
                    }
                    System.out.println("how many pieces of " + productName + " would you like to purchase");
                    int productQuantity = scanner.nextInt();
                    scanner.nextLine();

                    if (productQuantity <= 0) {
                        System.out.println("Invalid Quantity!");
                        continue;
                    }

                    if (productQuantity > customerOrder.getQuantity()) {
                        System.out.println("Sorry, Product not available in " + productQuantity + " pieces");
                        System.out.println("Available Quantity: " + customerOrder.getQuantity());
                        continue;
                    }
                    double totalPrice = productQuantity * customerOrder.getPrice();

                    if (totalPrice > attendingCustomer.getBalance()) {
                        System.out.println("Insufficient balance to buy " + customerOrder.getName() + ".");
                        continue;
                    }
                    attendingCustomer.setBalance(attendingCustomer.getBalance() - totalPrice);
                    customerOrder.setQuantity(customerOrder.getQuantity() - productQuantity);
                    newProducts(myProducts, filePath);

                    System.out.println("Purchase Successful!");
                    System.out.println("You purchased " + productQuantity + " " + customerOrder.getName() +
                            " at " + totalPrice);
                    System.out.println("Remaining balance: " + attendingCustomer.getBalance());
                    System.out.println("Quantity of " + customerOrder.getName() + " now available: " + customerOrder.getQuantity());

                    System.out.println();
                    System.out.println(attendingCustomer.getName() + ", Perform another operation?: Y/N ");
                    String checkOut = scanner.nextLine();
                    if (checkOut.equalsIgnoreCase("N")) {
                        multiplePurchase = false;
                    } else if (!checkOut.equalsIgnoreCase("Y")) {
                        System.out.println("Invalid input. Please enter Y or N.");
                    }
                }
                attendingCustomer.greet();
                System.out.println("Thank you for visiting " + store.name + "!");
            }
        }
        System.out.println("\nCLOSING STORE");

    }
}