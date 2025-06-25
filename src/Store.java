public class Store {
    protected String name;

    public Store(String name) {
        this.name = name;
    }

    public void hireCashier(Cashier cashier) {
        System.out.println(cashier.getName() + " has been hired as cashier of " + name);
    }

}
