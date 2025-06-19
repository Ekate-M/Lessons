public class Main {
    public static void main(String[] args) {
        // Создаем фигуры
        GeometricShape circle = new Circle(5, "Красный", "Черный");
        GeometricShape rectangle = new Rectangle(4, 6, "Синий", "Белый");
        GeometricShape triangle = new Triangle(3, 4, 5, "Зеленый", "Желтый");

        // Выводим информацию о фигурах
        System.out.println("Характеристики круга:");
        circle.printInfo();

        System.out.println("Характеристики прямоугольника:");
        rectangle.printInfo();

        System.out.println("Характеристики треугольника:");
        triangle.printInfo();
    }
}
