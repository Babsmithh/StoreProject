public class Staff extends Store{
    public String gender;
    public double salary;

    public Staff(String name, String gender, double salary) {
        super(name);
        this.gender = gender;
        this.salary = salary;
    }
    public void staffDuty() {
        System.out.println(name + " is currently working in the store.");
    }
}

