
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TriangleAreaTest {

    @Test
    void calculateArea_ShouldReturnCorrectValue() {
        // Arrange
        double base = 5;
        double height = 4;
        double expected = 10;

        // Act
        double actual = TriangleArea.calculateArea(base, height);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void calculateArea_ShouldThrowExceptionForInvalidInput() {
        // Arrange
        double base = 0;
        double height = 4;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            TriangleArea.calculateArea(base, height);
        });
    }
}
