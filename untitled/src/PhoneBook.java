import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBook {

    private final Map<String, List<String>> phoneBook = new HashMap<>();


    public void add(String surname, String phone) {

        phoneBook.computeIfAbsent(surname, k -> new ArrayList<>()).add(phone);
    }


    public List<String> get(String surname) {
        return phoneBook.getOrDefault(surname, new ArrayList<>());
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, List<String>> entry : phoneBook.entrySet()) {
            sb.append(entry.getKey())
                    .append(": ")
                    .append(String.join(", ", entry.getValue()))
                    .append("\n");
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        PhoneBook book = new PhoneBook();

        book.add("Иванов", "123-456");
        book.add("Петров", "555-123");
        book.add("Иванов", "789-012");


        System.out.println("Телефоны Иванова: " + book.get("Иванов"));
        System.out.println("Телефоны Сидорова: " + book.get("Сидоров"));

        System.out.println("\nПолный справочник:");
        System.out.println(book);
    }
}

