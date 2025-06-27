
public class Park {
    private String parkName;
    private Attraction[] attractions;

    // Конструктор парка
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
            System.out.println("Стоимость: " + price + " ₽ ");
            System.out.println("-----------------------------");
        }
    }

    // Метод для вывода информации о парке и его аттракционах
    public void printParkInfo() {
        System.out.println("Добро пожаловать в парк: " + parkName);
        System.out.println("Наши аттракционы:");
        for (Attraction attraction : attractions) {
            attraction.printInfo();
        }
    }

    // Пример использования
    public static void main(String[] args) {
        // Создаем аттракционы
        Park.Attraction[] attractions = new Park.Attraction[3];
        attractions[0] = new Park.Attraction("Американские горки", "10:00-20:00", 450.);
        attractions[1] = new Park.Attraction("Колесо обозрения", "09:00-22:00", 250);
        attractions[2] = new Park.Attraction("Водные горки", "11:00-19:00", 1000);

        // Создаем парк
        Park myPark = new Park("Развлечений", attractions);

        // Выводим информацию о парке
        myPark.printParkInfo();
