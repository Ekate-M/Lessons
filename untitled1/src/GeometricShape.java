
interface GeometricShape {
    default double calculatePerimeter() {
        return 0;
    }

    // Абстрактные методы, которые должны быть реализованы
    double calculateArea();
    String getFillColor();
    String getBorderColor();

    // Дефолтный метод для вывода информации
    default void printInfo() {
        System.out.println("Периметр: " + calculatePerimeter());
        System.out.println("Площадь: " + calculateArea());
        System.out.println("Цвет фона: " + getFillColor());
        System.out.println("Цвет границ: " + getBorderColor());
        System.out.println("----------------------");
    }
}

