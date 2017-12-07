package Parser;

/**
 * Created by babagay on 17.11.15.
 */
public class NotebookItem {

    private String Vendor;
    private String Model;
    private String Description;
    private String Price;

    public NotebookItem(String Vendor, String Model, String Description, String Price) {

        this.Vendor = Vendor;
        this.Model = Model;
        this.Description = Description;
        this.Price = Price;
    }

    public String toString() {
        return Vendor + " " + Model + " " + Description + " " + Price;
    }
}
