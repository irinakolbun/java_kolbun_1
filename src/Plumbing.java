class Plumbing {        // Model
    private int idNum;
    private String kind;
    private String manufacturer;
    private String name;
    private int quantity;
    private double price;
    private String model;

    Plumbing(int idNum, String kind, String manufacturer,
             String name, int quantity, double price, String model) {
        this.idNum = idNum;
        this.kind = kind;
        this.manufacturer = manufacturer;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.model = model;
    }

    public int getId() {
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

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getModel() {
        return model;
    }

    @Override
    public String toString() {
        return String.format("%-50s   %-7s   %15s %18s %16s %n",
                getName().substring(0, Math.min(getName().length(), 50)), getPrice(),
                getModel(), getKind(), getManufacturer());
    }
}
