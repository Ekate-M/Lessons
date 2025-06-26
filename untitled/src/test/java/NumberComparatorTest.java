
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NumberComparatorTest {

    @Test
    void compare_ShouldReturnCorrectComparisonString() {
        // Arrange
        int a = 5;
        int b = 3;
        String expected = "5 > 3";

        // Act
        String actual = NumberComparator.compare(a, b);

        // Assert
        assertEquals(expected, actual);
    }
}
