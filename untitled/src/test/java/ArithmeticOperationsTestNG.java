import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class ArithmeticOperationsTestNG {
    @Test
    public void testOperations() {
        assertEquals(ArithmeticOperations.add(10, 5), 15);
        assertEquals(ArithmeticOperations.subtract(10, 5), 5);
        assertEquals(ArithmeticOperations.multiply(10, 5), 50);
        assertEquals(ArithmeticOperations.divide(10, 5), 2.0);
    }

    @Test(expectedExceptions = ArithmeticException.class)
    public void testDivisionByZero() {
        ArithmeticOperations.divide(10, 0);
    }
}
