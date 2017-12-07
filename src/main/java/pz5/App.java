package pz5;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.Runtime.getRuntime;

/**
 * Created by panov on 09.12.15.
 *
 * Написать приложение переводчик. В качестве хранилища словарей использовать текстовый файл.
 * Текст для перевода брать из файла. Перевод печатать в консоль.
 *
 * Детальное описание:
 1. Сделать консольный интерфейс.
 2. Должно поддерживаться несколько языков и перевод в обоих направлениях;
 3. Направления перевода и языки определять автоматически по файлам словарей (их имена)
 (лежат на диске в заданной директории).
 Поиск файлов(директорий) и работа с ними осуществляется через class File,
 обратите внимание на методы .exists(), .listFiles(), .getName() этого класса.
 4. При старте приложения словарь переводчика считывается из файла и сохраняется в HashMap.
 5. Должен быть метод перевести, который в качестве аргументов получает путь к файлу
 который надо переводить (String) и словарь который использовать (HashMap).
 6. Реализовать проверку на доступность выбранного направления перевода, если язык или направление отсутствует кинуть эксепшин.
 7. Вывести переведенный текст в консоль.

 TODO
 Поднять словари
 Загрузить их в хэш-мапы
 Поднять файл
 Разбить на слова
 Перевести каждое слово - если его нет в словаре, спросить у яндекса
 Записать результат в файл
 */
public class App  {

    public static void main( String[] args ) {

        Scanner scanner = new Scanner( System.in, "UTF-8" );

        String command;
        int commandCode;

        do {
            menuBasic();

            command = scanner.next();
            commandCode = Integer.parseInt( command );

        } while ( commandCode != 0 );

    }

    private static void menuBasic(){

        try { //FIXME
            Runtime.getRuntime().exec("cls");
            Runtime.getRuntime().exec("clear");
        } catch ( IOException e ) {
            //e.printStackTrace();
        }

        System.out.println( "[Меню]" );
        System.out.println( "1 - ..." );
        System.out.println( "0 - выход" );
        System.out.println( "Выберите пункт" );
    }

}



