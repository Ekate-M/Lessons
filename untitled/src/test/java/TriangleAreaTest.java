import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TriangleAreaTest {
    @Test
    void testAreaCalculation() {
        assertEquals(10.0, TriangleArea.calculateArea(5, 4));
        assertThrows(IllegalArgumentException.class, () -> TriangleArea.calculateArea(-1, 5));
    }
}