public class Manager extends Staff{

        public Manager(String name, String gender, double salary) {
            super(name, gender, salary);
        }

        @Override
        public void staffDuty() {
            System.out.println(name + " is the manager of the store");
        }
    }
