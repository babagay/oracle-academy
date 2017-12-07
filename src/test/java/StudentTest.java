import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import Student.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by panov on 11.11.15.
 */
public class StudentTest {

    private static Student stud;

    private static String testStudName = "Vasya";

    private static BufferedReader reader;


    @BeforeClass
    public static void setUpBeforClass() throws Exception {

        stud = new Student(testStudName);

        reader = Files.newBufferedReader(Paths.get("exams.txt"), StandardCharsets.UTF_8);


        // TODO explode  line 'History/8/02.06.2012' and stud.addExam( "His",8 );
        reader.lines().forEach(str -> {
            String[] arr = str.split("/");

            ////stud.addExam( arr[0], Integer.parseInt(arr[1]) );
        });


    }

    @AfterClass
    public static void down() throws IOException {
        ////reader.close();
    }

    @Test
    public void Test00() {
        assertEquals("Test Stud 01", testStudName, stud.getName());

    }

    @Test
    @Ignore
    public void Test01() {
        //assertEquals("Test Stud 01", exp, act);
        //fail();
    }


}
