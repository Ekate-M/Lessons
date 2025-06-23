import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<Student> students = new HashSet<>();

        // Добавляем студентов
        students.add(new Student("Иван Иванов", "Группа 101", 1,
                Map.of("Математика", 4, "Физика", 5, "Химия", 3)));
        students.add(new Student("Петр Петров", "Группа 102", 2,
                Map.of("Математика", 2, "Физика", 3, "Химия", 2)));
        students.add(new Student("Сергей Сергеев", "Группа 201", 2,
                Map.of("Математика", 5, "Физика", 5, "Химия", 5)));
        students.add(new Student("Анна Аннова", "Группа 101", 1,
                Map.of("Математика", 3, "Физика", 3, "Химия", 3)));

        System.out.println("Все студенты:");
        students.forEach(System.out::println);

        // Удаляем студентов с средним баллом < 3
        removeUnderperformingStudents(students);
        System.out.println("\nСтуденты после отчисления (средний балл >= 3):");
        students.forEach(System.out::println);

        // Переводим студентов на следующий курс (если средний балл >= 3)
        promoteStudents(students);
        System.out.println("\nСтуденты после перевода на следующий курс:");
        students.forEach(System.out::println);

        // Печатаем студентов 2-го курса
        System.out.println("\nСтуденты 2-го курса:");
        printStudents(students, 2);
    }

    // Метод для удаления студентов с средним баллом < 3
    public static void removeUnderperformingStudents(Set<Student> students) {
        students.removeIf(student -> student.getAverageGrade() < 3);
    }

    // Метод для перевода студентов на следующий курс (если средний балл >= 3)
    public static void promoteStudents(Set<Student> students) {
        for (Student student : students) {
            if (student.getAverageGrade() >= 3) {
                student.setCourse(student.getCourse() + 1);
            }
        }
    }

    // Метод для печати студентов определенного курса
    public static void printStudents(Set<Student> students, int course) {
        students.stream()
                .filter(student -> student.getCourse() == course)
                .forEach(student -> System.out.println(student.getName() + " - " + student.getGroup()));
    }
}
