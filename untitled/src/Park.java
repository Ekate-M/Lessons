public class Park {
    private String parkName;
    private Attraction[] attractions;

    public Park(String name, Attraction[] attractions) {
        this.parkName = name;
        this.attractions = attractions;
    }

    // Внутренний класс для аттракционов
    public static class Attraction {
        private String attractionName;
        private String workingHours;
        private double price;

        public Attraction(String name, String hours, double price) {
            this.attractionName = name;
            this.workingHours = hours;
            this.price = price;
        }

        public void printInfo() {
            System.out.println("Аттракцион: " + attractionName);
            System.out.println("Время работы: " + workingHours);
            System.out.println("Стоимость: " + price + " ₽");
            System.out.println("-----------------------------");
        }
    }

    public void printParkInfo() {
        System.out.println("Добро пожаловать в парк: " + parkName);
        System.out.println("Наши аттракционы:");
        for (Attraction attraction : attractions) {
            attraction.printInfo();
        }
    }
}