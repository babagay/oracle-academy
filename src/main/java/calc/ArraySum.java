package calc;

/**
 * Created by babagay on 08.11.15.
 */
public class ArraySum extends Calc {

    public static void main(String[] args) throws ZeroLengthException {
        ArraySum arrS = new ArraySum(new int[]{12, 2});

        System.out.println(arrS.getResult());
    }

    public ArraySum() {
    }

    public ArraySum(int[] arr) throws ZeroLengthException {
        numbers = arr;

        arrSum();
    }

    private void arrSum() throws ZeroLengthException {

        validate();

        if (numbers.length == 1) {
            this.result = numbers[0];
        } else {

            this.result = numbers[0];

            int i = 1;

            for (int number : numbers) {

                if (i == 1) {
                    i++;
                    continue;
                }
                this.result += number;
            }
        }
    }

    @Override
    public Calc calculate() throws ZeroLengthException {
        arrSum();
        return this;
    }
}
