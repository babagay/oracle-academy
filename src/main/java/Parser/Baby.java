package Parser;

/**
 * Created by babagay on 17.11.15.
 */

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Baby {

    private final String parsed_file;
    private final String fileName = "/home/babagay/Документы/PTOCTXVB/wonderer/src/main/resources/baby.htm";
    private final ArrayList<BabyItem> arr;
    private BufferedReader reader;

    public static void main(String[] args) throws IOException {

        Baby b = new Baby();
        b.printArray();
    }

    private void printArray() {

        for (BabyItem item : arr) {
            System.out.println(item);
        }
    }

    public String getItemByIndex(int index) {
        return String.valueOf(arr.get(index));
    }

    public Baby() throws IOException {

        arr = new ArrayList<BabyItem>();

        parsed_file = read(fileName);

        Pattern p = Pattern.compile("(<tr align=.right.>[\\n\\s.]*<td>)(\\d{1,4})(</td>[\\n\\s.]*<td>)(\\w{2,10})(</td>[\\n\\s.]*<td>)(\\w{2,10})");
        Matcher m = p.matcher(parsed_file);

        while (m.find()) {
            //  arr.add(new BabyItem(Integer.parseInt(m.group(2)), m.group(4), m.group(6)));
            arr.add(new BabyItem(Integer.parseInt(m.group(2)), m.group(4), m.group(6)));
        }
    }

    public static String read(String fileName) throws FileNotFoundException {

        StringBuilder sb = new StringBuilder();

        File file = new File(fileName);

        try {

            BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            try {

                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {

                in.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return sb.toString();
    }
}
