package collections;

/**
 * Created by babagay on 01.12.15.
 */
public enum MapSort {

    DESC_ALPHABETH,
    ASC_ALPHABETH,
    DESC_FREQ,
    ASC_FREQ;

    private String type;

    MapSort(String type) {
        this.type = type;
    }

    MapSort() {
    }

    public String getType() {
        return type;
    }

}
