public class Staff extends Store{
    public double salary;

    public Staff(String name, double salary) {
        super(name);
        this.salary = salary;
    }
    public void staffDuty() {
        System.out.println(name + " is currently working in the store.");
    }
}

