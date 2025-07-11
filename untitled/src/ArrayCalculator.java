public class ArrayCalculator {

    public static void main(String[] args) {

        String[][] correct = {
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"}
        };
        String[][] incorrectSize = {
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"}
        };
        String[][] incorrectData = {
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "X", "4"}
        };

        try {
            System.out.println("Сумма correct: " + sumArray(correct));  // Прямой вызов
            System.out.println("Сумма incorrectSize: " + sumArray(incorrectSize));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.err.println(e.getMessage());
        }


        try {
            int[] arr = new int[3];
            System.out.println(arr[10]);  // Выход за границы массива
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Ошибка: выход за границы массива! " + e.getMessage());
        }
    }


    public static int sumArray(String[][] arr) throws MyArraySizeException, MyArrayDataException {

        if (arr.length != 4) {
            throw new MyArraySizeException("Ошибка: массив должен быть 4x4 (строк: " + arr.length + ")");
        }


        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length != 4) {
                throw new MyArraySizeException(
                        String.format("Ошибка: в строке %d должно быть 4 столбца (найдено %d)", i, arr[i].length)
                );
            }
        }


        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    sum += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(
                            String.format("Ошибка в ячейке [%d][%d]: '%s' — не число", i, j, arr[i][j])
                    );
                }
            }
        }
        return sum;
    }
}

