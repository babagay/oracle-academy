package Student;


import java.util.ArrayList;
import java.util.Date;

/**
 * Created by babagay on 15.11.15.
 */
public class Student {

    private String Name;
    private String Surname;
    private Group myGroup;
    private int course;
    private ArrayList<Exam> exams;

    public static void main(String[] args) {
    }

    public Student() {
    }

    public Student(String Name) {
        this.setName(Name);
    }
    public Student(String Name,String Surname,int course)
    {
        this.setName(Name);
        this.setSurname(Surname);
        this.setCourse(course);
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public Student.Group getMyGroup() {
        return myGroup;
    }

    public void setMyGroup(Student.Group myGroup) {
        this.myGroup = myGroup;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public ArrayList<Student.Exam> getExams() {
        return exams;
    }

    public void setExams(ArrayList<Student.Exam> exams) {
        this.exams = exams;
    }


    public class Exam {
        private String Subject;
        private int Ball;
        private Date PassDate;

        Exam(String subj, int ball) {
            Ball = ball;
            Subject = subj;
        }
    }

    public static class Group {
        private int course;
        private String faculty;
    }

    public void addExam(String subj, int ball) {
        getExams().add(new Exam(subj, ball));
    }

    //узнать наивысшую оценку среди всех экзаменов по данному предмету
    void getMaxBall() {

    }

    public String toString() {
        return getName() + " " + getSurname() + " course: " + getCourse();
    }

}
