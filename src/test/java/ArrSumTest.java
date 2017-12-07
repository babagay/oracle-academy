import calc.ArraySum;
import calc.CalcParams;
import calc.ZeroLengthException;
import org.junit.*;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for ArrSum class
 * Created by babagay on 08.11.15.
 * <p>
 * Using of 'extends CalcTest' forces to perform test cases from parent class
 */
public class ArrSumTest extends CalcTest {

    private static CalcParams cParams;

    private static ArraySum aSum;

    @BeforeClass
    public static void setUpBeforClass() throws Exception {
        // Код выполняется перед исполнением набора тестов
        cParams = new CalcParams();
        aSum = new ArraySum();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        cParams = null;
        aSum = null;
    }

    @Before
    public void setUpBeforTest() throws Exception {
        // Выполняется перед каждымм тестом
    }

    @After
    public void tearDownAfterTest() throws Exception {
    }

    @Test(timeout = 100)
    public void TestArrSum() throws ZeroLengthException {

        int expected = 6;
        int actual = new ArraySum(new int[]{1, 2, 3}).getResult();

        assertEquals("TestArrSum 1 - 2 numbers sum", expected, actual, 0.01);
    }

    @Test(expected = ZeroLengthException.class)
    public void TestZeroLengthException() throws Exception {
        new ArraySum(new int[]{}).getResult();
    }

    @Test
    public void TestArrSumParam01() throws ZeroLengthException {
        assertEquals("Test Param01", 9, aSum.setArray(cParams.getSet(0)).calculate().getResult(), 0.01);
    }

    @Test
    public void TestArrSumParam02() throws ZeroLengthException {
        assertEquals("Test Param02", -3, aSum.setArray(cParams.getSet(1)).calculate().getResult(), 0.01);
    }

    @Test(expected = ZeroLengthException.class)
    public void TestArrSumParam03() throws Exception {
        aSum.setArray(cParams.getSet(2)).calculate().getResult();
    }

    @Test
    public void TestArrSumParam04() throws ZeroLengthException {
        assertEquals("Test Param04", 10, aSum.setArray(cParams.getSet(3)).calculate().getResult(), 0.01);
    }

}
