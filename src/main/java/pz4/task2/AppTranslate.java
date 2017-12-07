package pz4.task2;

import pz2.Markdown;
import pz5.Translator;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by sasha on 09.12.15.
 *
 * Поднять словари - init
 * Взять имя файла с текстом
 * Перевести (пополняя словари)
 * Перед выходом сохранить словари
 */
public class AppTranslate {

    static String piece01 = "./src/main/resources/translatePiece_01.txt";

    static BufferedReader reader;
    static  private String direction;
    static Map<Integer,String> text = new TreeMap<>();

    public static void main(String[] args) throws InterruptedException {

        direction = "en-ru";

        translateFile(piece01);

        for (String v : text.values()) {
            System.out.printf(v + " ");
        }
    }

    private static void translateFile(String name) throws InterruptedException {
        Markdown.setPath(piece01);

        try ( BufferedReader reader = Markdown.getReader() ) {

            //TODO parralelSream
            long f = reader.lines().count();

            /**
             * Разбить на слова
             * Собрать мапу "индекс - слово"
             * Наплодить потоков-переводчиков
             *
             * Как правильно освободить ресурсы, занятые потоком?
             */



            text.put(1,"Hello");
            text.put(2,"world");

            for (Map.Entry<Integer, String> item : text.entrySet()) {
                Thread thread = factory(item.getValue(), item.getKey());
                thread.start();
                thread.join();
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Thread factory(String word, int index){

         return new Thread( () -> {
            Translator translator = new Translator(direction);
             String str = translator.translate(word);
             text.put(index,str);
         });

    }

}
