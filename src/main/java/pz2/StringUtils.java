package pz2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by panov on 16.11.15.
 */
public class StringUtils {

    final static int THRESHOLD_DOWN = 10;
    final static int THRESHOLD_UP = 12;

    public static String sent = "";
    public static String delimiter = ".";

    public static void main(String[] args) throws Exception {

        String str = "I like coding on Java";

//      System.out.println(str);
//      System.out.println("reverse: " + reverse(str));
//      System.out.println("ispoly: " + isPoly("asddsA"));
//      System.out.println("switchWords: [" + switchWords("  Start     ") + "]");

/*
        System.out.println(switchWordsByLines(
                "When we have switched to Ziggeo we have changed the logic not to convert videos (cause conversion takes server time and a lot of resources, \n" +
                "and as soon as lot of users will start uploading videos server can fall down at all). \n" +
                "\n" +
                "The best option would be to use Ziggeo native upload functionality. Also you will not need S3 server at all and no need to pay for it. \n" +
                "\n" +
                "The only small drawback is that we will need to use native Ziggeo interface for uploading files, and our  current upload button will not work. \n" +
                "But I think this is reasonable adjustment, as all functionality connected with video and conversion will be done by Ziggeo and there will be no need to pay for Amazon S3 server."));
*/

        String sparseMarkdown =
                         "just text \n" +
                "#Simple h1 header \n" +
                         "best option would. is that we will need to use   \n" +
                "##Simple h2 header   \n" +
                        "can fall down \n" +
                "###Simple h3 header    " +
                        "I think this is reasonable \n" +
                        "Ziggeo native upload functionality.\n" +
                        "##Simple h2 header\n"+
                        "will start uploading.\n" +
                 "#Simple h1 header two\n" +
                 "Also you will\n"
                ;
        parseMarkdown(sparseMarkdown);


    }

    public static String reverse(String s) {
        return new StringBuffer(s).reverse().toString();
    }

    public static boolean isPoly(String s) throws Exception {

        char[] arr = s.toLowerCase().replaceAll("[\\s]", "").toCharArray();

        if (s.isEmpty()) throw new Exception("String is Empty");

        int i, j, boundary;

        boolean result = false;

        if ((arr.length % 2) == 0)
            boundary = arr.length / 2;
        else
            boundary = (arr.length - 1) / 2;


        for (i = 0, j = arr.length - 1; i <= boundary; i++, j--) {
            String a = String.valueOf(arr[ i ]);
            String b = String.valueOf(arr[ j ]);

            if (i == boundary) {
                if (a.equals(b)) {
                    result = true;
                }
            }

            if (!a.equals(b))
                break;
        }

        return result;
    }

    /**
     * @param s String
     * @return
     * @throws StringIsTooShortException
     */
    public static String switchWords(String s) throws StringIsTooShortException {

        s = s.replaceAll("^\\s{1,}", "");
        s = s.replaceAll("\\s{1,}$", "");
        s = s.replaceAll("\\s{2,}", " ");

        if ( !s.contains(" ") )
            throw new StringIsTooShortException("");

        Pattern p = Pattern.compile("^\\b([\\w]{1,})([\\s\\w]*)\\b([\\w]{1,})$");
        Matcher m = p.matcher(s);

        while (m.find()) {
            try {
                s = m.group(3) + m.group(2) + m.group(1);
            } catch (IndexOutOfBoundsException e) {
                throw new StringIsTooShortException("");
            }
        }

        return s;
    }

    public static String cutByLength(String s) {

        if (s.length() > THRESHOLD_DOWN) {
            s = s.substring(0, 6);

        } else {

            int i = 1;
            String string = "";
            for (int j = 0; j < THRESHOLD_UP; j++) {
                if (j < s.length())
                    string += s.charAt(j);
                else
                    string += "o";
            }
            s = string;
        }


        return s;
    }

    private static String _switch(String s) throws StringIsTooShortException {

        Pattern pattern = Pattern.compile("^([\\n\\t\\s]{0,})\\b([\\wа-яёА-ЯЁ\\d\\.:;,'!\\(\\)\\?\\s\\-]{1,})\\b([:;,'!\\?\\)\\.\\n\\t\\s]{0,})$");
        Matcher matcher = pattern.matcher(s);

        String group = "";
        String prefix = "";
        String tail = "";

        while  (matcher.find()){
            try {
                prefix = matcher.group(1);
                group = matcher.group(2);
                tail = matcher.group(3);

            } catch (IndexOutOfBoundsException e){
            }
        }

        Pattern p = Pattern.compile("^\\b([\\w]{1,})([\\wа-яёА-ЯЁ\\d\\(\\)\\.:;'!,\\?\\s\\-]*)\\b([\\w]{1,})$");
        Matcher m = p.matcher(group);

        while (m.find()) {
            try {

                group = m.group(3) + m.group(2) + m.group(1);
            } catch (IndexOutOfBoundsException e) {
                throw new StringIsTooShortException("");
            }
        }

        return prefix + group + tail;
    }

    private static String _toStr(Object[] arr) {

        String s = "";

        for (Object item : arr) {
            Character c = (Character) item;
            s += c;
        }

        return s;
    }

    /**
     * FIXME
     *
     * Идея была - разбивать текст на части, опираясь на разделитель - точку -
     * и затем, откусив и преобразовав первую часть, рекурсивно вызывать функцию, скармливая ей остаток.
     * Проблема обработки многоточий показалась неоправданно трудоёмкой.

     * @deprecated
     */
    public static String switchWordsBySentenses(String s) throws StringIsTooShortException {

        int ind = s.indexOf(new Character('.'));

        String sentence = null;

        String delimiter = ".";


        if (("" + s.charAt(ind + 1)).equals(".") && ("" + s.charAt(ind + 2)).equals(".")) {
            // Cчитать троеточием
            delimiter = "...";

            Pattern pattern = Pattern.compile("(.)*\\.{3,}(.*)\\.");

            Matcher matcher = pattern.matcher(s);

            String group = null;

            if (matcher.find()){
                try {
                     group = matcher.group(0);
                } catch (IndexOutOfBoundsException e){
                }
            }

            if( group.lastIndexOf('.') == group.length()-1 )
                group = group.substring(0,group.length()-1);

            s = s.substring(group.length(), s.length());
            sent += _switch(sentence) + delimiter;

        } else {
            if (("" + s.charAt(ind + 1)).equals(".")) {
                // Cчитать двоеточием
                delimiter = "..";
                sentence = s.substring(0, ind);
                s = s.substring(ind+2, s.length());
                sent += _switch(sentence) + delimiter;

            } else {
                // Единичная точка

                sentence = s.substring(0, ind);
                s = s.substring(ind+1, s.length());
                sent += _switch(sentence) + delimiter;
            }
        }

        // FIXME
        if(ind == 0)
            // But if s.length() > 0 do ?
            return sent;

        return switchWordsBySentenses(s);
    }

    public static String _switchWordsBySlices(String s) throws StringIsTooShortException {

        if (s.indexOf( new Character( '.' ) ) == -1)
            return _switch(s);

        ArrayList<Character> arr = new ArrayList<>();

        for (char c : s.toCharArray()) {
            if (!(c + "").equals(delimiter)) {
                arr.add(c);
            } else {

                sent += _switch(
                        _toStr(arr.toArray())
                );
                arr.clear();
                sent += c;
            }
        }

        if(arr.size() > 0)
            sent += _switch(
                    _toStr(arr.toArray())
            );

        return sent;
    }

    public static String switchWordsBySentences(String s) throws StringIsTooShortException {
        sent = "";
        return _switchWordsBySlices(s);
    }

    public static String switchWordsByLines(String s) throws StringIsTooShortException {

        sent = "";

        delimiter = "\n";

        String[] arr = s.split("[\\n]{1,}");

        for (int i=0; i<arr.length; i++) {

            sent += _switch(arr[i]);

            if( (i+1) != arr.length )
                sent += delimiter;
        }

        return sent;
    }

    public static boolean checkABC(String s)   {

        int index_a, index_b, index_c;
        int index_A, index_B, index_C;

        index_a = s.indexOf('a');
        index_A = s.indexOf('A');

        if(index_a > 0 || index_A > 0){
            index_b = s.indexOf('b');
            index_B = s.indexOf('B');
            if(index_b > 0 || index_B > 0){
                  index_c = s.indexOf('c');
                  index_C = s.indexOf('C');
                if(index_c > 0 || index_C > 0){
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean isDate(String string ) throws StringIsTooShortException {

        Pattern p = Pattern.compile("^([1-12]{2})\\.([1-31]{2})\\.([\\d]{4})$");
        Matcher m = p.matcher(string);

        String one = "";
        String two = "";
        String three = "";

        try {
            while (m.find()) {
                one = m.group(1);
                two = m.group(2);
                three = m.group(3);
            }
        } catch (IndexOutOfBoundsException e){
            throw new StringIsTooShortException("");
        }

        if( !one.equals("") && !two.equals("") && !three.equals("") )
            return true;

        return false;
    }

    public static boolean isAddr(String s) throws StringIsTooShortException {

        Pattern p = Pattern.compile("^([\\wа-яёА-ЯЁ ]{3,100})\\n([\\wа-яёА-ЯЁ \\d,\\-\\.]{3,200})\\n([\\wа-яёА-ЯЁ \\-]{3,100})\\n([\\wа-яёА-ЯЁ \\-]{3,100})\\n([\\d]{5,12})$");
        Matcher m = p.matcher(s);

        String name = "";
        String street = "";
        String city = "";
        String country = "";
        String zip = "";

        try {
            while (m.find()) {
                name = m.group(1);
                street = m.group(2);
                city = m.group(3);
                country = m.group(4);
                zip = m.group(5);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new StringIsTooShortException("");
        }

        if (!name.equals("") && !street.equals("") && !city.equals("") && !country.equals("") && !zip.equals(""))
            return true;

        return false;
    }

    public static String[] getPhones(String s) throws StringIsTooShortException {

        ArrayList<String> arrayList = new ArrayList<>(  );

        Pattern p = Pattern.compile( "(\\+\\d\\(\\d\\d\\d\\)\\d\\d\\d-\\d\\d-\\d\\d)" );
        Matcher m = p.matcher( s );

        try {
            while (m.find()) {
                arrayList.add( m.group(1) );
            }
        } catch (IndexOutOfBoundsException e) {
            throw new StringIsTooShortException("");
        }

        String T[] = new String[arrayList.size()];

        int i = 0;
        for ( String s1 : arrayList ) {
            T[i] = s1;
            i++;
        }

        return T;
    }

    /**
     *
     * @param s
     * @return
     *
     * ([\wа-яёА-ЯЁ\.,\?!-: _\d]{1,}\n{1,}) string
     */
    public  static String parseMarkdown(String s) throws StringIsTooShortException {

        ArrayList<String> arrayList = new ArrayList<>(  );

        //String pStr = "([\\wа-яёА-ЯЁ\\.,\\?!-: _\\d]{1,}\\n{1,})";
        String pStr = "[\\wа-яёА-ЯЁ\\.,\\?!-: _\\d]{1,}";

        Pattern p = Pattern.compile( "(#{1}"+pStr+"\\n+)*" );
        //Pattern p = Pattern.compile( "(.*\\n*\\t*\\s*)(#{1}"+pStr+"\\n+)*(.*\\n*\\t*\\s*)" );
        //Pattern p = Pattern.compile( "(.*\\n*)(#{1}"+pStr+"\\n+)*(.*\\n*)(#{2}"+pStr+"\\n+)*(.*\\n*)" );
        Matcher m = p.matcher( s );

        try {
            while (m.find()) {
               // arrayList.add( m.group(0) );
                System.out.println( m.group(0) );
                System.out.println( "[" + m.group(1) );
                System.out.println( "<" + m.group(2) );
                System.out.println( "=" + m.group(3) );
                System.out.println( m.group(4) );
            }
        } catch (IndexOutOfBoundsException e) {
          //  throw new StringIsTooShortException("");
        }

        return s;
    }

}


