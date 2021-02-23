public class Main {

    public static void main(String[] args) {

    }
}


class Plumbing {   // Model
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