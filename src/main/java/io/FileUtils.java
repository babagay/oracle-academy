package io;

import sun.misc.IOUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by sasha on 05.12.15.
 */
public class FileUtils {

    private static Stream<String> reader;
    private static String fileName   = "./src/main/resources/test01.txt";
    private static String fileName02 = "./src/main/resources/test02.txt";
    private static String fileName03 = "./src/main/resources/test03.txt";
    private static String fileName04 = "./src/main/resources/test04.txt";
    private static String fileName05 = "./src/main/resources/test05.txt";
    private static String fileName06 = "./src/main/resources/test06.txt";
    private static String fileName07 = "./src/main/resources/test07.txt";
    private static List<Integer> list;
    private static List<String> list02;
    private static Path path;

    public static void main(String[] args) throws IOException {

        /** Task 1 */
        //fill();

        /** Task 2 */
//        path = Paths.get(fileName);
//        streamReader();
//
//        list = reader.map(e -> Integer.valueOf(e)).collect(Collectors.toList());
//
//        System.out.println(list);
//
//        //TODO попробовать параллельную сортировку
//        Collections.sort(list);
//
//        System.out.println(list);
//
//        reader.close();

        /** Task 3 */
//        path = Paths.get(fileName02);
//        streamReader();
//
//        fileFilter();
//
//        reader.close();

        //invert();

        //copy();

        /**
         * Создать класс Студент и Группа. Группа содержит коллекцию студентов
         * Выполнить сериализацию-десериализацию ГРуппы
         */


    }

    public static long returnSeconds(int year, int month, int date) {
        final Calendar cal = Calendar.getInstance();
        cal.set(year, month, date, 0, 0, 0);
        cal.set( Calendar.MILLISECOND, 0 );
        return cal.getTimeInMillis() / 1000;
    }

    /**
     * Скопировать содержимое файла в другой файл, используя буфер. и небуф. потоки.
     * unbufResTime  100  65
     * bufResTime    10   4
     * http://www.studytrails.com/java-io/character-file-reading-writing.jsp
     */
    public static  void copy(){

        /** Unbuffered */
        Date unbufStart = new Date();

        FileReader reader = null;
        FileWriter writer = null;
        try {
            reader = new FileReader(fileName05);
            writer = new FileWriter(fileName06);
            int a = 0;
            while ((a = reader.read()) != -1) {
                writer.write(a);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ////Date m1 = Calendar.getInstance().getTime();

        Date unbufEnd = new Date();

        long unbufResTime = unbufEnd.getTime() - unbufStart.getTime();

        System.out.println(unbufResTime);

        Date bufStart = new Date();

        /** buffered */
        try (
                BufferedReader bufferedReader = new BufferedReader( new FileReader( fileName05 ) );
                BufferedWriter bufferedWriter = new BufferedWriter( new FileWriter( fileName06 ) )
                ) {

            String s = "";
            while ((s = bufferedReader.readLine()) != null) {
                bufferedWriter.write(s);
                // write a new line
                bufferedWriter.newLine();
                // [!] Использовать flush(), если try БЕЗ ресурсов. А иначе, он не нужен
                // bufferedWriter.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // close without throwing exception. FIXME не работает
            // IOUtils.closeQuietly(bufferedReader);
            // IOUtils.closeQuietly(bufferedWriter);
        }

        Date bufEnd = new Date();

        long bufResTime = bufEnd.getTime() - bufStart.getTime();

        System.out.println(bufResTime);
    }

    /**
     * Fill file by Ints
     */
    static void fill(){

        Random random = new Random();

        File file = new File(fileName);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                 BufferedWriter bufferedWriter = new BufferedWriter(fw)
        ) {

            for(int i = 0; i < 10; i++) {
                int j = random.nextInt();
                bufferedWriter.write( j + "\n" );
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     */
    static void fileFilter(){

        list02 = reader.map( e -> {
            String[] a = e.split("=");
            String s = a[0].replaceAll("\\W*", "") + "|" + a[1].replaceAll("\\W*", "");
            return s;
        } ).
                filter( d -> {
                            String[] arr = d.split("\\|");
                            return Integer.valueOf(arr[1]) > 90;
                        }
                ).
                collect(Collectors.toList());

        File file = new File(fileName03);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (FileWriter fw = new FileWriter(file.getAbsoluteFile());
             BufferedWriter bufferedWriter = new BufferedWriter(fw)) {

            for (String s : list02) {
                s = s.replaceAll("\\|", " = ");
                bufferedWriter.write( s + "\n" );
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**   FIXME
     *
     *      Поменять местами первое и последнее слова в предложении     *
     *      Вычислять и сохранять длину строки
     *      Складывать текст в одну переменную
     *      Разбить на предложения
     *      Произвести инверсию
     *      Писать в новый файл, откусывая подстроки известной длинны
     * */
    static void invert(){

        path = Paths.get(fileName04);
        streamReader();

        String buffer = "";
        String result = "";
        String tmp = "";

        ArrayList<Integer> arrayList = new ArrayList<>();

        List<String> list = reader.collect( Collectors.toList() );

        for (String s : list) {
            buffer += s + " ";
            arrayList.add(s.length() + 1);
        }

        reader.close();

        String[] arr = buffer.split("(\\. )");

        for (int i=0; i < arr.length; i++) {
            result +=  arr[i].replaceAll("^(?<start>[\\S\\.]*)(?<middle>\\s*[\\w\\d, ']*\\s*)\\b(?<end>[\\w\\.\\S]{1,})$", "${end}${middle}${start}") + ". ";
        }

        Integer sum = arrayList.stream().mapToInt(Integer::intValue).sum();

        try (
                BufferedWriter bufferedWriter = new BufferedWriter( new FileWriter( fileName07 ) )
        ) {
            for (Integer e : arrayList) {
                bufferedWriter.write( result.substring(0, e) );
                bufferedWriter.newLine();
                result = result.substring( e );
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        for (Integer e : arrayList) {
            tmp = result.substring(a,e );
            result = result.substring(e );
            sum -= e;

            //result = result.substring(e,sum);

            int b = result.indexOf(" ",0);
            if(b > 0){
                //tmp += result.substring(a,b);
                int l = result.length();
                // result = result.substring(b);
            }

           // System.out.println("="+result);

           //System.out.println(tmp);
        }
    */


    }

    static void streamReader(){

        try {
            reader = Files.lines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
