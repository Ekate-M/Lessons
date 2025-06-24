import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class FactorialCalculatorTestNG {
    @Test
    public void testFactorial() {
        assertEquals(FactorialCalculator.calculateFactorial(0), 1L);
        assertEquals(FactorialCalculator.calculateFactorial(5), 120L);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNegativeInput() {
        FactorialCalculator.calculateFactorial(-1);
    }
}
