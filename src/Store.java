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
}
