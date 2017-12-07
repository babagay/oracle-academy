package calc;

/**
 * Created by student on 08.11.2015.
 */
public abstract class Calc {

    protected int[] numbers;
    protected int result;

    /*
    public static void main(String[] args) throws Exception {

        Calc calc = new Calc();

        int[] arr = {30,20};

        int res;

        res = calc.arraySum(arr);

        System.out.println(res);

    }
    */

    public void Calc() {
        System.out.println("Test started");
    }

    public int getResult() {
        return result;
    }

    public Calc setArray(int[] arr) {
        numbers = arr;
        return this;
    }

    public abstract Calc calculate() throws ZeroLengthException;

    public void validate() throws ZeroLengthException {
        if (numbers.length == 0)
            throw new ZeroLengthException("Zero length");
    }


    public int sum(String str) {

        String[] strArr = str.split(",");

        int summ = 0;

        for (String s : strArr) {
            summ += Integer.parseInt(s);
        }

        return summ;
    }

    /**
     * Суммирование элементов массива
     *
     * @param args
     * @return
     * @throws ZeroLengthException
     */
    public int arraySum(int[] args) throws ZeroLengthException {

        if (args.length == 0)
            throw new ZeroLengthException("Zero length");

        if (args.length == 1)
            return args[0];

        int sum = args[0];

        int i = 1;

        for (int number : args) {

            if (i == 1) {
                i++;
                continue;
            }
            sum += number;
        }

        return sum;
    }

}

/*
class ArraySumm extends Calc {

    int[] nums;

    public ArraySumm(int[] arr){
        nums = arr;
    }

    private int ArrSumm(){

        int sum = 0;
        int i = 1;

        for (int number: nums){

            if(i == 1){ i++; continue; }
            sum += number;
        }

        return sum;
    }
}
*/