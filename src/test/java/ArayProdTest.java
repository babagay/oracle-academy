import calc.ArrayProd;
import calc.CalcParams;
import calc.ZeroLengthException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by babagay on 10.11.15.
 */
public class ArayProdTest {
    private static CalcParams cParams;

    private static ArrayProd aProd;

    @BeforeClass
    public static void setUpBeforClass() throws Exception {

        cParams = new CalcParams();
        aProd = new ArrayProd();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        cParams = null;
        aProd = null;
    }

    @Test
    public void TestArrProdParam01() throws ZeroLengthException {
        assertEquals("Test Param01", 24, aProd.setArray(cParams.getSet(0)).calculate().getResult(), 0.01);
    }

    @Test
    public void TestArrProdParam02() throws ZeroLengthException {
        assertEquals("Test Param02", 24, aProd.setArray(cParams.getSet(1)).calculate().getResult(), 0.01);
    }

    @Test(expected = ZeroLengthException.class)
    public void TestArrProdParam03() throws Exception {
        aProd.setArray(cParams.getSet(2)).calculate().getResult();
    }

    @Test
    public void TestArrProdParam04() throws ZeroLengthException {
        assertEquals("Test Param04", 0, aProd.setArray(cParams.getSet(3)).calculate().getResult(), 0.01);
    }
}
