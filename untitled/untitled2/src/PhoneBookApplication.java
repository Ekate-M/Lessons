public class PhoneBookApplication {

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        // Добавление номеров
        phoneBook.add("Иванов", "123-456");
        phoneBook.add("Петров", "555-123");
        phoneBook.add("Иванов", "789-012");

        // Попытка добавить дубликаты
        phoneBook.add("Иванов", "123-456");  // Дубликат номера у того же человека
        phoneBook.add("Сидоров", "555-123"); // Номер уже существует у другого человека

        // Получение номеров
        System.out.println("Телефоны Иванова: " + phoneBook.get("Иванов"));
        System.out.println("Телефоны Сидорова: " + phoneBook.get("Сидоров"));

        // Проверка наличия номера
        System.out.println("Номер 789-012 существует: " + phoneBook.containsPhone("789-012"));

        // Вывод всего справочника
        System.out.println("\nПолный справочник:");
        System.out.println(phoneBook);
    }
}
