import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.BeforeClass;
import org.junit.Test;
import pz2.*;

import java.util.Arrays;

import static junit.framework.Assert.assertEquals;

/**
 * Created by panov on 16.11.15.
 *
 * [!] Remove Ignore anno before use
 */
public class StringUtilsTest {

    private static String str01;
    private static String str02;
    private static String str03;
    private static String str04;
    private static String str05;
    private static String str06;
    private static String str07;

    @BeforeClass
    public static void setUpBeforClass() throws Exception {
        str01 = "I like coding on Java";
        str02 = "abcddcba";
        str03 = "abcdZdcba";
        str04 = "А роза упала на лапу Азора";
        str05 = "Start your own project and do perform it till the end";
        str06 = "   Start   your own project     and do perform it till   the end                   ";
        str07 = "your                            end";
    }

    @Test @Ignore
    public void Test01() {
        assertEquals("Test 01", "avaJ no gnidoc ekil I", pz2.StringUtils.reverse(str01));
    }

    @Test @Ignore
    public void Test02() throws Exception {
        assertEquals("Test 02", false, pz2.StringUtils.isPoly( str01 ));
    }

    @Test @Ignore
    public void Test03() throws Exception {
        assertEquals("Test 03", true, pz2.StringUtils.isPoly(str02));
    }

    @Test @Ignore
    public void Test04() throws Exception {
        assertEquals("Test 04", true, pz2.StringUtils.isPoly(str03));
    }

    @Test @Ignore
    public void Test05() throws Exception {
        assertEquals("Test 05", true, pz2.StringUtils.isPoly(str04));
    }

    @Test @Ignore
    public void Test06() throws Exception {
        assertEquals("Test 06", "end your own project and do perform it till the Start", pz2.StringUtils.switchWords( str05 ));
    }

    @Test(expected = StringIsTooShortException.class)
    public void Test07() throws Exception {
        pz2.StringUtils.switchWords( "Start" );
    }

    @Test @Ignore
    public void Test08() throws Exception {
        assertEquals( "Test 08", "end your", pz2.StringUtils.switchWords( str07 ) );
    }

    @Test(expected = StringIsTooShortException.class) @Ignore
    public void Test09() throws Exception {
        pz2.StringUtils.switchWords( "   end      " );
    }

    @Test @Ignore
    public void Test10() throws Exception {
        assertEquals( "Test 10", "end your own project and do perform it till the Start", pz2.StringUtils.switchWords( str06 ) );
    }

    @Test @Ignore
    public void Test11() throws Exception {
        assertEquals("Test 11", "as s d", pz2.StringUtils.cutByLength( "as s d ggg hhh tt" ));
    }

    @Test @Ignore
    public void Test12() throws Exception {
        assertEquals("Test 12", "as soooooooo", pz2.StringUtils.cutByLength("as s"));
    }

    @Test @Ignore
    public void Test13() throws Exception {
        assertEquals("Test 13", "oooooooooooo", pz2.StringUtils.cutByLength(""));
    }

    @Test @Ignore
    public void Test14() throws Exception {
        assertEquals("Test 14",
                "\n\t\n"+"back will change it Intellij.   POM you have auto import enabled on a Maven If..compiler in that Maven pom you don't have the And...settings. Ok",
                pz2.StringUtils.switchWordsBySentences(
                        "\n" +
                                "\t\n" +
                                "Intellij will change it back.   If you have auto import enabled on a Maven POM..And in that Maven pom you don't have the compiler...settings. Ok"
                ));


    }
    @Test @Ignore
    public void Test15() throws StringIsTooShortException {
        assertEquals("Test 15",
                "back will change it Intellij. POM you have auto import enabled on a Maven If... settings in that Maven pom you don't have the compiler And. Ok",
                pz2.StringUtils.switchWordsBySentences(
                        "Intellij will change it back. If you have auto import enabled on a Maven POM... And in that Maven pom you don't have the compiler settings. Ok"
                ));
    }

    @Test @Ignore
    public void Test16() throws StringIsTooShortException {
        assertEquals("Test 16",
                "Java like coding on I.",
                pz2.StringUtils.switchWordsBySentences(
                        "I like coding on Java."
                ));
    }

    @Test @Ignore
    public void Test17() throws StringIsTooShortException {
        assertEquals("Test 17", false, pz2.StringUtils.checkABC( "Java like coding on I." ));
    }

    @Test @Ignore
    public void Test18() throws StringIsTooShortException {
        assertEquals("Test 18", true, pz2.StringUtils.checkABC("Java bike Coding on I."));
    }

    @Test @Ignore
    /**
     *  [!] Считаем, что разделителем строк в результирующем тексте служит единственный символ "\n"
     */
    public void Test19() throws StringIsTooShortException {
        assertEquals("Test 19",
                "resources we have switched to Ziggeo we have changed the logic not to convert videos (cause conversion takes server time and a lot of When, \n" +
                        "all as soon as lot of users will start uploading videos server can fall down at and). \n" +
                        "it best option would be to use Ziggeo native upload functionality. Also you will not need S3 server at all and no need to pay for The. \n" +
                        "work only small drawback is that we will need to use native Ziggeo interface for uploading files, and our  current upload button will not The. \n" +
                        "server I think this is reasonable adjustment, as all functionality connected with video and conversion will be done by Ziggeo and there will be no need to pay for Amazon S3 But.",
                pz2.StringUtils.switchWordsByLines(
                        "When we have switched to Ziggeo we have changed the logic not to convert videos (cause conversion takes server time and a lot of resources, \n" +
                                "and as soon as lot of users will start uploading videos server can fall down at all). \n" +
                                "\n" +
                                "The best option would be to use Ziggeo native upload functionality. Also you will not need S3 server at all and no need to pay for it. \n" +
                                "\n" +
                                "The only small drawback is that we will need to use native Ziggeo interface for uploading files, and our  current upload button will not work. \n" +
                                "But I think this is reasonable adjustment, as all functionality connected with video and conversion will be done by Ziggeo and there will be no need to pay for Amazon S3 server."
                ));
    }

    @Test    @Ignore
    public void Test20() throws StringIsTooShortException {
        assertEquals("Test 20", false, pz2.StringUtils.isDate( "45.23.2001" ));
    }

    @Test    @Ignore
    public void Test21() throws StringIsTooShortException {
        assertEquals("Test 21", true, pz2.StringUtils.isDate("12.23.2001"));
    }

    @Test @Ignore
    /**
     * Cчитаем адрес таким:
     *
     * Вася Петрович Иванов
     * проспект Мамина-Сибиряка, 8-А, корпус 1, кв. 210
     * Петропавловск-камчатский
     * Российская империя
     * 61034
     */
    public void Test22() throws StringIsTooShortException {
        assertEquals("Test 22", true, pz2.StringUtils.isAddr(
                        "Вася Петрович Иванов \n" +
                                "проспект Мамина-Сибиряка, 8-А, корпус 1, кв. 210 \n" +
                                "Петропавловск-камчатский \n" +
                                "Российская империя \n" +
                                "61034"
                ));
    }

    @Test @Ignore
    public void Test23() throws StringIsTooShortException {
        String[] expected = {"+7(067)125-23-00", "+8(050)236-22-88"};
        String actual[] = pz2.StringUtils.getPhones( "Test +7(067)125-23-00 Test +8(050)236-22-88 Test" );
        assertEquals( "Test 23", expected.equals(actual), actual.equals(expected) );
    }

    @Test
    //TODO
    public void Test24() throws StringIsTooShortException {
        String expected = "";
        String actual = pz2.StringUtils.parseMarkdown( "Test +7(067)125-23-00 Test +8(050)236-22-88 Test" );
        assertEquals( "Test 24", expected, actual );
    }

    @Test
    @Ignore
    public void Test() {
        //assertEquals("Test Stud 01", exp, act);
        //fail();
    }


}
