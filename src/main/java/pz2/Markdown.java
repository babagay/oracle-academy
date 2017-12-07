package pz2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.exit;

/**
 * Created by panov on 27.11.15.
 */
public class Markdown {

    private static String path = "";
    private static BufferedReader reader = null;

    private static String result = "";

    private static boolean isHeader = false;

    public static void main(String[] args) throws IOException {

        Markdown markdown = new Markdown();

        System.out.println(markdown.getResult());
    }

    public Markdown(String p) throws IOException {
        setPath(p);
        doParsing();
    }

    public static void setPath(String p) {
        path = p;
    }

    public Markdown() throws IOException {
        doParsing();
    }

    private static void doParsing() throws IOException {
        getReader().lines().forEach(Markdown::Parse);
        reader.close();
    }

    static void Parse(String line) {

        line = Processor1(line);
        line = Processor2(line);
        line = Processor3(line);
        line = Processor4(line);

        if (isHeader)
            isHeader = false;
        else
            line = "<p>" + line + "</p>";

        result += line + "\n";
    }

    /**
     * Headers
     *
     * @param s
     * @return
     */
    static String Processor1(String s) {

        Pattern p = Pattern.compile("(#{1,})([\\w \\d]{1,})");
        Matcher m = p.matcher(s);

        while (m.find()) {
            try {
                s = "<h" + m.group(1).length() + ">" + m.group(2) + "</h" + m.group(1).length() + ">";
                isHeader = true;
            } catch (IndexOutOfBoundsException e) {
            }
        }

        return s;
    }

    /**
     * Links
     *
     * @param s
     * @return
     */
    static String Processor2(String s) {

        Pattern p = Pattern.compile("\\[(?<title>[\\w \\d\\.'\\-]*)\\]\\((?<link>http(s*):\\/\\/[\\w\\.\\d]*)\\)");
        String replacePattern = "<a href=\"${link}\">${title}</a> ";

        Matcher m = p.matcher(s);

        if (m.find())
            s = m.replaceAll(replacePattern);

        return s;
    }

    static String Processor3(String s) {

        String PatternSearch = "\\*{2}(?<emfazi>[\\w\\d]+)\\*{2}";

        String PatternReplace = "<em>${emfazi}</em>";

        return s.replaceAll(PatternSearch, PatternReplace);

    }

    static String Processor4(String s) {

        String PatternSearch = "\\*(?<bold>[\\w\\d]+)\\*";

        String PatternReplace = "<strong>${bold}</strong>";

        return s.replaceAll(PatternSearch, PatternReplace);

    }

    public String getResult() {
        return "<html>\n<body>\n" + result + "</body>\n</html>";
    }

    public static BufferedReader getReader() throws IOException {

        reader = Files.newBufferedReader(
                Paths.get(path), StandardCharsets.UTF_8
        );

        return reader;
    }

}
