public class Store {
    protected String name;
    protected String gender;

    public String getName() {
        return name;
    }

    public Store(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    public void hireCashier(Cashier cashier) {
        System.out.println(cashier.name + " has been hired as cashier of " + name);
    }
}
