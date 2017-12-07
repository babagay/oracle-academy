import calc.Calc;
import calc.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test cases for Calc class
 * Created by student on 08.11.2015.
 */
public class CalcTest {

    private static Calc calc;

    @BeforeClass
    public static void onlyOnce() {
        calc = new CalcHelper();
    }

    @AfterClass
    public static void onceAgain() {
        calc = null;
    }

    @Test
    public void TestSummSimple() {

        int expected = 4;
        int actual = calc.sum("2,2");

        assertEquals("Test 1 - simple sum", expected, actual, 0.01);
    }

    @Test
    public void TestArraySummOneArg() throws Exception {
        int expected = 30;
        int actual = calc.arraySum(new int[]{30});

        assertEquals("Test array sum", expected, actual, 0.01);
    }

    @Test
    public void TestArraySumm() throws Exception {
        int expected = 30;
        int actual = calc.arraySum(new int[]{10, 20});

        assertEquals("Test array 3", expected, actual, 0.01);
    }

    @Test(expected = ZeroLengthException.class)
    public void TestZeroLengthException() throws Exception {
        calc.arraySum(new int[]{});
    }


    @Test
    @Ignore("Каркас нового теста")
    public void NewTest() throws Exception {
        fail();
    }

    @Test
    public void ArraySumTestNegative() throws Exception {

        assertEquals("Test for negative", 0, calc.arraySum(new int[]{-10, 10}), 0.01);

    }

    private static class CalcHelper extends Calc {
        @Override
        public Calc calculate() throws ZeroLengthException {
            return null;
        }
    }
}
