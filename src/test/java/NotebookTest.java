import Parser.Notebook;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by babagay on 17.11.15.
 */
public class NotebookTest {

    private static Notebook notebook;

    @BeforeClass
    public static void setUpBeforClass() throws Exception {
        notebook = new Notebook();
    }

    @Test
    public void Test01() {
        assertEquals("Test 01", "Lenovo IdeaPad G500A (59-381061) 15,6\" / 1366x768 / Поверхность экрана - глянцевая /\n" +
                        "                                                Процессор - Intel Core i3-3120M, 2,5 ГГц / 4 Гб / Объем HDD - 1 Тб / AMD\n" +
                        "                                                Radeon HD 8750M, 2 Гб / DVD-RW / 100 Мбит/с / 802.11 b/g/n / Bluetooth /\n" +
                        "                                                HDMI / USB 2.0 - 1 шт. / USB 3.0 - 2 шт. / Кард-ридер / Web-камера - 0,3\n" +
                        "                                                Мп / Батарея - 6 ячеек / ОС - Dos / черный / 376,9х33,8х249,9 мм / 2,6\n" +
                        "                                                кг  вытаскивать цену - выше моих сил",
                notebook.getByIndex(23));
    }
}
