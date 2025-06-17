import java.util.Arrays;

 class MethodsCollection {

    // 1. Метод, выводящий три слова в столбец
    public static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    // 2. Метод, проверяющий знак суммы двух чисел
    public static void checkSumSign() {
        int a = 5;
        int b = -3;
        int sum = a + b;
        if (sum >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    // 3. Метод, определяющий цвет по значению
    public static void printColor() {
        int value = 50;
        if (value <= 0) {
            System.out.println("Красный");
        } else if (value <= 100) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }

    // 4. Метод, сравнивающий два числа
    public static void compareNumbers() {
        int a = 10;
        int b = 20;
        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    // 5. Метод, проверяющий, находится ли сумма в диапазоне [10, 20]
    public static boolean isSumInRange(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    // 6. Метод, определяющий знак числа (0 считается положительным)
    public static void printNumberSign(int number) {
        if (number >= 0) {
            System.out.println("Число положительное");
        } else {
            System.out.println("Число отрицательное");
        }
    }

    // 7. Метод, возвращающий true, если число отрицательное
    public static boolean isNegative(int number) {
        return number < 0;
    }

    // 8. Метод, печатающий строку указанное количество раз
    public static void printStringMultipleTimes(String str, int times) {
        for (int i = 0; i < times; i++) {
            System.out.println(str);
        }
    }

    // 9. Метод, определяющий високосный год
    public static boolean isLeapYear(int year) {
        return (year % 400 == 0) || (year % 100 != 0 && year % 4 == 0);
    }

    // 10. Метод, инвертирующий значения в массиве (0 -> 1, 1 -> 0)
    public static void invertArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i] == 0 ? 1 : 0;
        }
    }

    // 11. Метод, заполняющий массив числами от 1 до 100
    public static int[] fillArray1To100() {
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        return array;
    }

    // 12. Метод, умножающий на 2 числа меньше 6 в массиве
    public static void multiplyLessThan6(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 6) {
                array[i] *= 2;
            }
        }
    }

    // 13. Метод, заполняющий диагонали квадратного массива единицами
    public static void fillDiagonals(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][i] = 1;
            matrix[i][matrix.length - 1 - i] = 1;
        }
    }

    // 14. Метод, создающий массив заданной длины с одинаковыми значениями
    public static int[] createArray(int len, int initialValue) {
        int[] array = new int[len];
        for (int i = 0; i < array.length; i++) {
            array[i] = initialValue;
        }
        return array;
    }

    public static void main(String[] args) {
        // Примеры вызова методов
        printThreeWords();
        checkSumSign();
        printColor();
        compareNumbers();
        System.out.println(isSumInRange(5, 10));
        printNumberSign(-5);
        System.out.println(isNegative(-5));
        printStringMultipleTimes("Hello", 3);
        System.out.println(isLeapYear(2024));

        int[] array = {1, 1, 0, 0, 1};
        invertArray(array);
        System.out.println(Arrays.toString(array));

        System.out.println(Arrays.toString(fillArray1To100()));

        int[] array2 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        multiplyLessThan6(array2);
        System.out.println(Arrays.toString(array2));

        int[][] matrix = new int[5][5];
        fillDiagonals(matrix);
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }

        System.out.println(Arrays.toString(createArray(5, 10)));
    }
}

