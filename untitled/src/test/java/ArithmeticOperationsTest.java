
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArithmeticOperationsTest {

    @Test
    void add_ShouldReturnCorrectSum() {
        // Arrange
        int a = 5;
        int b = 3;
        int expected = 8;

        // Act
        int actual = ArithmeticOperations.add(a, b);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void divide_ShouldThrowExceptionWhenDivideByZero() {
        // Arrange
        int a = 5;
        int b = 0;

        // Act & Assert
        assertThrows(ArithmeticException.class, () -> {
            ArithmeticOperations.divide(a, b);
        });
    }
}


