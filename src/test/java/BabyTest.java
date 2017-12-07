import Parser.Baby;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by babagay on 17.11.15.
 */
public class BabyTest {

    private static Baby baby;


    @BeforeClass
    public static void setUpBeforClass() throws Exception {
        baby = new Baby();
    }

    @Test
    public void Test01() throws Exception {
        assertEquals("Test 01", "1000 Yurem Elianna", baby.getItemByIndex(997));
    }


}
