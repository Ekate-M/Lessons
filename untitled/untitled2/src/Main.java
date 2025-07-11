import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Set<Student> students = new HashSet<>();
        students.add(new Student("Иван Иванов", "Группа 101", 1,
                Map.of("Математика", 4, "Физика", 5, "Химия", 3)));
        students.add(new Student("Петр Петров", "Группа 102", 2,
                Map.of("Математика", 2, "Физика", 3, "Химия", 2)));
        students.add(new Student("Сергей Сергеев", "Группа 201", 2,
                Map.of("Математика", 5, "Физика", 5, "Химия", 5)));
        students.add(new Student("Анна Аннова", "Группа 101", 1,
                Map.of("Математика", 3, "Физика", 3, "Химия", 3)));

        System.out.println("Все студенты:");
        Decanat.printAllStudents(students);

        Decanat.removeUnderperformingStudents(students);
        System.out.println("\nПосле отчисления:");
        Decanat.printAllStudents(students);

        Decanat.promoteStudents(students);
        System.out.println("\nПосле перевода:");
        Decanat.printAllStudents(students);

        System.out.println("\nСтуденты 2-го курса:");
        Decanat.printStudents(students, 2);


        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Иванов", "123-456");
        phoneBook.add("Петров", "555-123");
        phoneBook.add("Иванов", "789-012");
        phoneBook.add("Иванов", "123-456");  // Дубликат
        phoneBook.add("Сидоров", "555-123"); // Номер занят

        System.out.println("\nТелефоны Иванова: " + phoneBook.get("Иванов"));
        System.out.println("Телефоны Сидорова: " + phoneBook.get("Сидоров"));
        System.out.println("Номер 789-012 существует: " + phoneBook.containsPhone("789-012"));
        System.out.println("\n" + phoneBook);
    }
}