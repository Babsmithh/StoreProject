import java.io.*;
import java.util.*;

public class Main {
    private static final String filePath = "C:\\Users\\HP\\IdeaProjects\\StoreProject\\src\\Product.txt";
    private static final PriorityQueue<Customer> arrivalQueue = new PriorityQueue<>();

    public static void newProducts(List<Product> products, String file) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write("Product, Price, Quantity");
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
        Manager manager = new Manager("Smith", 3000);
        manager.staffDuty();

        System.out.print("Enter Cashier's name: ");
        String cashierName = scanner.nextLine();
        System.out.println("Enter Cashier's salary");
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
            int price;
            while (true) {
                System.out.print("Price: ");
                try{
                    price = scanner.nextInt();
                    scanner.nextLine();
                    if (price <= 0) {
                        System.out.println("Invalid Price!");
                    } else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please input a valid number.");
                    scanner.nextLine();
                }
            }
            int quantity;
            while (true) {
                System.out.println("Quantity: ");
                try {
                    quantity = scanner.nextInt();
                    scanner.nextLine();
                    if (quantity <= 0) {
                        System.out.println("Invalid Quantity!");
                    } else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.nextLine();
                }
            }
            myProducts.add(new Product(name, price, quantity));
        }
        newProducts(myProducts, filePath);

        if (myProducts.isEmpty()) {
            System.out.println("File is empty.");
        }
        store.openStore();

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
            store.shopping(customer, myProducts);
                if (customer.getTotalItems() == 0) {
                    System.out.println(customerName + " is with empty cart");
                } else {
                    arrivalQueue.add(customer);
                    System.out.println(customerName + " joined the queue");
                    System.out.println();
                }
            }

            while (!arrivalQueue.isEmpty()) {
                Customer attendingCustomer = arrivalQueue.poll();
                System.out.println("\nAttending to: " + attendingCustomer.getName() +
                        " (Items in Cart: " + attendingCustomer.getTotalItems() +
                        ", Arrived at: " + attendingCustomer.getArrivalTime());

                System.out.println("\nTotal Queue: " + arrivalQueue.size());
                cashier.greet();

                System.out.println("\nAttending to: " + attendingCustomer.getName());

                List<Map.Entry<Product, Integer>> cartItems = new ArrayList<>(attendingCustomer.getCart().entrySet());
                if (cartItems.isEmpty()) {
                    System.out.println("Attending customer " + attendingCustomer.getName() + " has an empty cart. Nothing to process.");
                } else {
                    boolean successfulPurchase = false;
                    for (Map.Entry<Product, Integer> items : cartItems) {
                        Product cartProducts = items.getKey();
                        int quantityOfProducts = items.getValue();

                        double totalPrice = cartProducts.getPrice() * quantityOfProducts;

                        if (totalPrice > attendingCustomer.getBalance()) {
                            System.out.println("Insufficient balance to buy " + cartProducts.getName() + ".");
                            continue;
                        }
                        attendingCustomer.setBalance(attendingCustomer.getBalance() - totalPrice);
                        successfulPurchase = true;

                        System.out.println("Purchase Successful!");
                        System.out.println();
                    }

                    if (successfulPurchase) {
                        System.out.println("Thank you for your purchase!");
                    } else if (attendingCustomer.getTotalItems() > 0) {
                        System.out.println("\n" + attendingCustomer.getName() + "'s transaction could not be completed. All items in cart could not be purchased due to insufficient balance.");
                        System.out.println("Final remaining balance: " + attendingCustomer.getBalance());
                    }
                }
                System.out.println();
                cashier.dispenseReceipt(attendingCustomer);
                attendingCustomer.greet();
                System.out.println("Thank you for visiting ");
                System.out.println();

                System.out.println("\n--- Current Store Products ---");
                for (Product product : myProducts) {
                    System.out.println("Product: " + product.getName() + ", Remaining Quantity: " + product.getQuantity());
                    }
            }
        newProducts(myProducts, filePath);
        store.closeStore();
        }
    }
