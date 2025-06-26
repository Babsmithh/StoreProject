public class Product implements Comparable<Product>{
    private final String name;
    private final int price;
    private int quantity;

    public Product(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    public int getPrice() {
        return price;
    }
    public String getName() {
        return name;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }
    @Override
    public int compareTo(Product other) {
        return Integer.compare(this.quantity, other.quantity);
    }
}

