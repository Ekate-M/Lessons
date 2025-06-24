public class NumberComparator {
    public static String compare(int a, int b) {
        if (a == b) return "Numbers are equal";
        return a > b ? "First number is greater" : "Second number is greater";
    }
}
