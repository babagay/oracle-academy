import Parser.Baby;
import org.junit.BeforeClass;
import org.junit.Test;
import pz2.Markdown;

import static junit.framework.Assert.assertEquals;

/**
 * Created by babagay on 29.11.15.
 */
public class MarkdownTest {

    private static Markdown markdown;

    private static String result01 = "<html>\n" +
            "<body>\n" +
            "<h2>Header line</h2>\n" +
            "<p>Simple <strong>line</strong> with <strong>strong</strong> text</p>\n" +
            "<p>Simple line with <em>emfazi</em> text</p>\n" +
            "<p>Simple line with <a href=\"https://www.google.com\">I'm an inline-style link</a>   link</p>\n" +
            "<h6>Header line six</h6>\n" +
            "<p>Line <strong>with</strong> many <em>elements</em> and link <a href=\"https://www.facebook.com\">Link to FB</a>  here</p>\n" +
            "<p>Just a text</p>\n" +
            "</body>\n" +
            "</html>";

    @BeforeClass
    public static void setUpBeforClass() throws Exception {
        markdown = new Markdown("./src/main/resources/Markdown.txt");
    }

    @Test
    public void Test01() throws Exception {
        assertEquals("Test 01", result01, markdown.getResult());
    }

}
