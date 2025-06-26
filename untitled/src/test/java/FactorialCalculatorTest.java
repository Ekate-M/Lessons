
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FactorialCalculatorTest {

    @Test
    void calculateFactorial_ShouldReturnCorrectValue() {
        // Arrange
        int input = 5;
        long expected = 120;

        // Act
        long actual = FactorialCalculator.calculateFactorial(input);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void calculateFactorial_ShouldThrowExceptionForNegativeInput() {
        // Arrange
        int input = -1;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            FactorialCalculator.calculateFactorial(input);
        });
    }
}
