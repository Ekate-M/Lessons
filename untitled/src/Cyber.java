public class Cyber {

    public static void main(String[] args) {
        Cyber main = new Cyber();
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
            System.out.println("Сумма correct: " + main.sumArray(correct));
            System.out.println("Сумма incorrectSize: " + main.sumArray(incorrectSize));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.err.println(e.getMessage());
        }

        try {
            System.out.println("Сумма incorrectData: " + main.sumArray(incorrectData));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.err.println(e.getMessage());
        }

        // Генерация и обработка ArrayIndexOutOfBoundsException
        try {
            int[] arr = new int[3];
            System.out.println(arr[10]);  // Выход за границы массива
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Ошибка: выход за границы массива! " + e.getMessage());
        }
    }


    public int sumArray(String[][] arr) throws MyArraySizeException, MyArrayDataException {
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



