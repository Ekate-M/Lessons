import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class TriangleAreaTestNG {
    @Test
    public void testAreaCalculation() {
        assertEquals(TriangleArea.calculateArea(5, 4), 10.0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testInvalidInput() {
        TriangleArea.calculateArea(-1, 5);
    }
}
