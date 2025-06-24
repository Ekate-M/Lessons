import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class NumberComparatorTestNG {
    @Test
    public void testComparison() {
        assertEquals(NumberComparator.compare(5, 5), "Numbers are equal");
        assertEquals(NumberComparator.compare(10, 5), "First number is greater");
        assertEquals(NumberComparator.compare(5, 10), "Second number is greater");
    }
}
