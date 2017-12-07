import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * [!] If U ran this class, U got 16 tests. Another way, double click on maven's "test" will get U 36 (!) tests
 * Because of maven performs all classes from test folder step by step.
 * <p>
 * Created by babagay on 10.11.15.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        //ArrSumTest.class,
        //ArayProdTest.class
        StudentTest.class
})
public class TestAll {
}
