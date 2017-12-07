package calc;

/**
 * Input sets for testing Calc suite
 * Created by babagay on 08.11.15.
 */
public class CalcParams {

    private int[][] set = {
            {2, 3, 4},
            {-2, 3, -4},
            {},
            {1, 9, 0},
    };

    public int[] getSet(int key) {
        return set[key];
    }

}
