public class Manager extends Staff implements Greeting{

        public Manager(String name, String gender, double salary) {
            super(name, salary);
        }
    public void greet() {
        System.out.println("My name is " + name + ". You are welcome to our store as the Cashier of our store");
    }

        @Override
        public void staffDuty() {
            System.out.println(name + " is the manager of the store");
        }
    }
