public class Park {
    private String parkName;
    private Attraction[] attractions;


    public class Attraction {
        private String name;
        private String workingHours;
        private double price;

        public Attraction(String name, String workingHours, double price) {
            this.name = name;
            this.workingHours = workingHours;
            this.price = price;
        }

        public void printInfo() {
            System.out.println("Аттракцион: " + name);
            System.out.println("Время работы: " + workingHours);
            System.out.println("Стоимость: $" + price);
            System.out.println("----------------------");
        }
    }


    public Park(String parkName, Attraction[] attractions) {
        this.parkName = parkName;
        this.attractions = attractions;
    }


    public void printParkInfo() {
        System.out.println("Парк: " + parkName);
        System.out.println("Аттракционы:");
        for (Attraction attraction : attractions) {
            attraction.printInfo();
        }
    } }

