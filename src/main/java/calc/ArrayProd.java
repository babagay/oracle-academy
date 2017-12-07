package calc;

/**
 * Created by babagay on 10.11.15.
 */
public class ArrayProd extends Calc {

    @Override
    public Calc calculate() throws ZeroLengthException {
        multiply();
        return this;
    }

    private void multiply() throws ZeroLengthException {

        validate();

        if (numbers.length == 1) {
            result = numbers[0];
        } else {

            result = numbers[0];

            int i = 1;

            for (int number : numbers) {

                if (i == 1) {
                    i++;
                    continue;
                }
                result *= number;
            }
        }
    }
}
