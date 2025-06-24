import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FactorialCalculatorTest {
    @Test
    void testFactorial() {
        assertEquals(1, FactorialCalculator.calculateFactorial(0));
        assertEquals(120, FactorialCalculator.calculateFactorial(5));
        assertThrows(IllegalArgumentException.class, () -> FactorialCalculator.calculateFactorial(-1));
    }
}
