import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NumberComparatorTest {
    @Test
    void testComparison() {
        assertEquals("Numbers are equal", NumberComparator.compare(5, 5));
        assertEquals("First number is greater", NumberComparator.compare(10, 5));
        assertEquals("Second number is greater", NumberComparator.compare(5, 10));
    }
}
