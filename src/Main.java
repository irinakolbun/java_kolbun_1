import java.net.SocketOption;
import java.sql.SQLOutput;

public class Main {

    public static void main(String[] args) {

    }
}


class Plumbing {        // Model
    private Integer idNum;
    private String kind;
    private String manufacturer;
    private String name;
    private Integer quantity;
    private Float price;
    private String model;

    Plumbing(Integer idNum, String kind, String manufacturer,
             String name, Integer quantity, Float price, String model) {
        this.idNum = idNum;
        this.kind = kind;
        this.manufacturer = manufacturer;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.model = model;
    }

    public Integer getId() {
        return idNum;
    }

    public String getKind() {
        return kind;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Float getPrice() {
        return price;
    }

    public String getModel() {
        return model;
    }

}

class PlumbingView {        // View
    public void printPlumbing(String itemName, Float itemPrice, String itemModel){
        System.out.println("Name: " + itemName);
        System.out.println("Price: " + itemPrice.toString());
        System.out.println("Model: " + itemModel);
    }
}

class PlumbingController {      // Controller
    private Plumbing model;
    private PlumbingView view;

    public PlumbingController(Plumbing model, PlumbingView view){
        this.model = model;
        this.view = view;
    }

    public Integer getPlumbingId() { return model.getId(); }

    public String getPlumbingKind() {
        return model.getKind();
    }

    public String getPlumbingManufacturer() {
        return model.getManufacturer();
    }

    public String getPlumbingName() {
        return model.getName();
    }

    public Integer getPlumbingQuantity() {
        return model.getQuantity();
    }

    public Float getPlumbingPrice() {
        return model.getPrice();
    }

    public String getPlumbingModel() {
        return model.getModel();
    }

    public void updateView(){
        view.printPlumbing(model.getName(), model.getPrice(), model.getModel());
    }

}