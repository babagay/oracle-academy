package Parser;

/**
 * Created by babagay on 17.11.15.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Notebook {

    public String parsed_file;
    public String fileName = "/home/babagay/Документы/PTOCTXVB/wonderer/src/main/resources/book.htm";
    public ArrayList<NotebookItem> arr;

    public static void main(String[] args) throws FileNotFoundException {
        Notebook notebook = new Notebook();

        notebook.printArray();

    }

    public void printArray() {
        for (NotebookItem item : arr) {
            System.out.println(item);
        }
    }

    public Notebook() throws FileNotFoundException {

        parsed_file = read(fileName);

        arr = new ArrayList<NotebookItem>();

        Pattern p = Pattern.compile("(<h6 class=.name.><a)[\\n\\t\\s._\"]*(href.*)[\\n\\t\\s._\"]*(class.*)[\\n\\t\\s._\"]*(title=\"Ноутбук )([\\w]{2,20}) ([\\w \\d\\-\\(\\)]{2,100})(.*)[\\n\\t\\s]*(.*)[\\n\\t\\s]*(<p class=\"description\">)([\\d\\w\"\\., \\/\\n\\-а-яёА-Я]*)[\\n\\t\\s.&]*(.*)");

        Matcher m = p.matcher(parsed_file);

        while (m.find()) {
            String Vendor, Model, Description, Price;

            arr.add(
                    new NotebookItem(
                            Vendor = m.group(5),
                            Model = m.group(6),
                            Description = m.group(10),
                            Price = " вытаскивать цену - выше моих сил"
                    )
            );

        }


    }

    public String getByIndex(int ind) {
        return arr.get(ind) + "";
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
