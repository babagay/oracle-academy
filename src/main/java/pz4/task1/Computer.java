package pz4.task1;

import java.util.Comparator;

/**
 * Created by babagay on 29.11.15.
 */
public class Computer implements /*Comparator,*/ Comparable {

    private String monitor;
    private String CPU;
    private String RAM;

    public Computer(String monitor, String CPU, String RAM) {
        this.monitor = monitor;
        this.CPU = CPU;
        this.RAM = RAM;
    }

    public String getMonitor() {
        return monitor;
    }

    public void setMonitor(String monitor) {
        this.monitor = monitor;
    }

    public String getCPU() {
        return CPU;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public String getRAM() {
        return RAM;
    }

    public void setRAM(String RAM) {
        this.RAM = RAM;
    }

    /*
    @Override
    public int compare(Object o1, Object o2) {

        Computer c1 = (Computer) o1;
        Computer c2 = (Computer) o2;

        String s1 = c1.getMonitor() + c1.getCPU() + c1.getRAM();
        String s2 = c2.getMonitor() + c2.getCPU() + c2.getRAM();

        return s1.compareTo(s2);
    }
    */

    /**
     * Сортировка по алфавиту
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Object o) {

        Computer c = (Computer) o;

        String s1 = c.getMonitor() + c.getCPU() + c.getRAM();
        String s2 = getMonitor() + getCPU() + getRAM();

        return s2.compareTo(s1);
    }

    public String toString() {
        return getMonitor();
    }
}
