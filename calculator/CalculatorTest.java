

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CalculatorTest.
 *
 * @author  Keshav Saharia
 */
public class CalculatorTest
{
    private Calculator calculator;
    /**
     * Default constructor for test class CalculatorTest
     */
    public CalculatorTest()
    {
        calculator = new Calculator();
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }
    
    @Test
    public void testAdd()
    {
        assertEquals(calculator.add(1, 1), 2);
        assertEquals(calculator.add(4, 6), 10);
        assertEquals(calculator.add(-5, 5), 0);
    }
    
    @Test
    public void testSubtract()
    {
        assertEquals(calculator.subtract(5, 2), 3);
        assertEquals(calculator.subtract(6, 1), 5);
        assertEquals(calculator.subtract(5, -3), 8);
    }
    
    @Test
    public void testMultiply()
    {
        assertEquals(calculator.multiply(3, 4), 12);
        assertEquals(calculator.multiply(1, 1), 1);
        assertEquals(calculator.multiply(500, 0), 0);
        assertEquals(calculator.multiply(-3, 4), -12);
        assertEquals(calculator.multiply(6, 9), 54);
    }
    
    @Test
    public void testDivide()
    {
        assertEquals(calculator.divide(6, 3), 2);
        assertEquals(calculator.divide(7, 2), 3);
        assertEquals(calculator.divide(9, 3), 3);
        assertEquals(calculator.divide(-9, 6), -1);
    }

    @Test
    public void whichIsGreaterOf2()
    {
        assertEquals(calculator.whichIsGreater(6, 3), 6);
        assertEquals(calculator.whichIsGreater(1, 1), 1);
        assertEquals(calculator.whichIsGreater(5, -5), 5);
    }
    
    @Test
    public void whichIsGreaterOf3()
    {
        assertEquals(calculator.whichIsGreater(6, 6, 6), 6);
        assertEquals(calculator.whichIsGreater(6, 6, 3), 6);
        assertEquals(calculator.whichIsGreater(6, 3, 6), 6);
        assertEquals(calculator.whichIsGreater(3, 6, 6), 6);
        assertEquals(calculator.whichIsGreater(3, 2, 1), 3);
        assertEquals(calculator.whichIsGreater(5, 0, -5), 5);
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
}
