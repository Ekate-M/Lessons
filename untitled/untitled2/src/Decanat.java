import java.util.Set;

public class Decanat {

    public static void removeUnderperformingStudents(Set<Student> students) {
        students.removeIf(student -> student.getAverageGrade() < 3);
    }

    public static void promoteStudents(Set<Student> students) {
        students.forEach(student -> {
            if (student.getAverageGrade() >= 3) {
                student.setCourse(student.getCourse() + 1);
            }
        });
    }

    public static void printStudents(Set<Student> students, int course) {
        students.stream()
                .filter(student -> student.getCourse() == course)
                .forEach(student -> System.out.println(student.getName() + " - " + student.getGroup()));
    }

    public static void printAllStudents(Set<Student> students) {
        students.forEach(System.out::println);
    }
}