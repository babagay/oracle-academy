package Parser;

/**
 * Created by babagay on 17.11.15.
 */
public class BabyItem {

    private int Rank;
    private String mName;
    private String femaleName;

    public BabyItem(int Rank, String mName, String femaleName) {

        this.Rank = Rank;
        this.mName = mName;
        this.femaleName = femaleName;
    }

    public String toString() {
        return Rank + " " + mName + " " + femaleName;
    }

}
