package collections;

import Student.Student;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by panov on 30.11.15.
 */
public class StudentUtils {

    public static void main(String[] args) throws FileNotFoundException {

        List<Student> st = new ArrayList<>();
        st.add(new Student("Misha", "Ivanov", 1));
        st.add(new Student("Velidar", "Petrov", 5));
        st.add(new Student("Masha", "Pavlova", 4));
        st.add(new Student("Sergey", "Popov", 2));
        st.add( new Student( "Lida", "Grishenko", 3 ) );

        StudentUtils studentUtils = new StudentUtils(st);

        studentUtils.createMapFromList( st );

        printStudents( st, 2 );

       // System.out.println(sortStudent(st));

       // System.out.println(calculateWordsFreq(read("./src/main/resources/Romeo.txt")));

        System.out.println(calculateWordsFreq(read("./src/main/resources/Romeo.txt"), MapSort.ASC_FREQ));
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public StudentUtils(List<Student> students) {
        this.students = students;
    }

    private static List<Student> students;

    public static Map<String, Student> createMapFromList(List<Student> list) {

        Map<String, Student> map = new HashMap<>();
        for (Student i : list)
            map.put(i.getName() + " " + i.getSurname(), i);

        return map;
    }

    public static void printStudents(List<Student> students, int course) {

        Iterator<Student> itr = students.iterator();
        while (itr.hasNext()) {
            Student st = itr.next();
            if (st.getCourse() == course) {
                System.out.println(st);
            }
        }
    }

    public static List<Student> sortStudent(List<Student> students) {
        return students.stream().sorted((o1, o2) -> o1.getName().compareTo(o2.getName())).
                collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    public static Map<String, Integer> calculateWordsFreq(String text) {
        int counter = 1;

        Map<String, Integer> map = new HashMap<>();
        for (String o : text.split("\\W+")) {

            if (map.get(o) == null) {
                map.put(o, 1);
            } else {
                counter = map.get(o);
                map.put(o, ++counter);
            }
        }
        return map;
    }

    public static Map<String, Integer> calculateWordsFreq(String text, MapSort type) {

        Comparator<Map.Entry<String, Integer>> comparator = null;

        Map<String,Integer> unsortedMap = calculateWordsFreq(text);

        // Собираем список из отображения
        List<Map.Entry<String, Integer>> list = new LinkedList<>(unsortedMap.entrySet());

        Map<String, Integer> mapOrigin = new LinkedHashMap<>();

        switch (type) {
            case ASC_ALPHABETH:

                Map<String, Integer> sortedMap = new TreeMap<>( ( o1, o2 ) -> o1.compareTo( o2 ) );
                sortedMap.putAll( unsortedMap );
                return sortedMap;

            case DESC_ALPHABETH:
                comparator = (o1, o2) -> o1.getKey().compareTo(o2.getKey());
                comparator.reversed();
                Collections.sort(list, comparator);
                break;

            case ASC_FREQ:
                // https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html

                // Можно так
                list = list.stream().sorted(  (o1, o2) -> o2.getValue().compareTo(o1.getValue())  ).collect( Collectors.toList() );

                // Можно и так
                // list.sort( ( o1, o2 ) -> o2.getValue().compareTo(o1.getValue()) );

                break;

            case DESC_FREQ:
                Collections.sort(list, (o1, o2) -> o1.getValue().compareTo(o2.getValue()));
                break;
        }

        for (Map.Entry<String, Integer> stringIntegerEntry : list) {
            mapOrigin.put(stringIntegerEntry.getKey(), stringIntegerEntry.getValue());
        }

        return mapOrigin;
    }

    /**
     * File reader
     *
     * @param fileName
     * @return
     * @throws FileNotFoundException
     */
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

    /*
    Reader v2

    StringBuilder textFromFile=new StringBuilder("");

        BufferedReader bufRead=new BufferedReader(new FileReader(filePath));
        String text=null;

        while ((text = bufRead.readLine()) != null) {
            textFromFile.append(text).append(System.getProperty("line.separator"));
        }
     */
}
