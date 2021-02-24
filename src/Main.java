import java.net.SocketOption;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        PlumbingWarehouse warehouse = new PlumbingWarehouse();
        Integer option;
        Scanner in = new Scanner(System.in);
        do {
            do {
                String menu = "\n Будь ласка, виберіть опцію меню."
                        + "\n1 Вивести усі доступні товари."
                        + "\n2 Отримати список сантехніки заданої фірми."
                        + "\n3 Отримати список сантехніки заданого виду і менше вказаної ціни."
                        + "\n0 Вийти з програми. \n";
                System.out.println(menu);
                option = in.nextInt();
            } while(option < 0 || option > 3); // This will make the menu repeat if option is higher than 6 or lowen than 0.

            switch(option) {
                case 1:
                    printPlumbings(warehouse.getPlumbings());
                    break;
                case 2:
                    Scanner manufacturerIn = new Scanner(System.in);
                    System.out.println("Введіть назву фірми: ");
                    String manufacturerStr = manufacturerIn.nextLine();
                    printPlumbings(getByManufacturer( manufacturerStr, warehouse.getPlumbings()));
                    break;
                case 3:
                    Scanner kindIn = new Scanner(System.in);
                    System.out.println("Введіть вид товару: ");
                    String kindStr = kindIn.nextLine();
                    Scanner priceIn = new Scanner(System.in);
                    System.out.println("Введіть максимальну ціну: ");
                    Double priceDouble = priceIn.nextDouble();
                    printPlumbings(getByKindCostLessThan(kindStr, priceDouble, warehouse.getPlumbings()));
                    break;
                default:
                    System.out.println("\n");
                    break; //I always use this break, even when not needed.
            }
        } while (option != 0);

    }

    public static void printPlumbings(Plumbing[] plumbings) {
        PlumbingView view = new PlumbingView();
        System.out.println("-".repeat(119) + "-");
        System.out.println("Name" + " ".repeat(48) + " Price " + " ".repeat(13) + " Model " + " ".repeat(10) + " Kind " + " ".repeat(10) + " Manufacturer |");
        System.out.println("-".repeat(119) + "-");
        for (Plumbing plumbing : plumbings) {
            PlumbingController controller = new PlumbingController(plumbing, view);
            controller.updateView();
        }
        System.out.println();
    }

    public static Plumbing[] getByManufacturer(String manufacturer, Plumbing[] plumbings) {
        List<Plumbing> filtered_plumbings = new ArrayList<Plumbing>();
        PlumbingView view = new PlumbingView();
        for (Plumbing plumbing : plumbings) {
            PlumbingController controller = new PlumbingController(plumbing, view);
            if (controller.getPlumbingManufacturer().toLowerCase().equals(manufacturer.toLowerCase()))
            {
                filtered_plumbings.add(plumbing);
            }
        }
        return filtered_plumbings.toArray(new Plumbing[0]);
    }

    public static Plumbing[] getByKindCostLessThan(String kind, double max_price, Plumbing[] plumbings) {
        List<Plumbing> filtered_plumbings = new ArrayList<Plumbing>();
        PlumbingView view = new PlumbingView();
        for (Plumbing plumbing : plumbings) {
            PlumbingController controller = new PlumbingController(plumbing, view);
            if (controller.getPlumbingKind().toLowerCase().equals(kind.toLowerCase()) && controller.getPlumbingPrice() < max_price)
            {
                filtered_plumbings.add(plumbing);
            }
        }
        return filtered_plumbings.toArray(new Plumbing[0]);
    }
}

class Plumbing {        // Model
    private Integer idNum;
    private String kind;
    private String manufacturer;
    private String name;
    private Integer quantity;
    private Double price;
    private String model;

    Plumbing(Integer idNum, String kind, String manufacturer,
             String name, Integer quantity, Double price, String model) {
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

    public Double getPrice() {
        return price;
    }

    public String getModel() {
        return model;
    }

}

class PlumbingView {        // View
    public void printPlumbing(String itemName, Double itemPrice, String itemModel, String itemKind, String itemManufacturer){
        System.out.printf("%-50s   %-7s   %15s %18s %16s %n", itemName.substring(0, Math.min(itemName.length(), 50)), itemPrice, itemModel, itemKind, itemManufacturer);
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

    public Double getPlumbingPrice() {
        return model.getPrice();
    }

    public String getPlumbingModel() {
        return model.getModel();
    }

    public void updateView(){
        view.printPlumbing(model.getName(), model.getPrice(), model.getModel(), model.getKind(), model.getManufacturer());
    }

    @Override
    public String toString() {
        return (
                "Name: " + getPlumbingName() + '\n' +
                "Price: " + getPlumbingPrice().toString() + '\n' +
                "Model: " + getPlumbingModel()
        );
    }
}

class PlumbingWarehouse {
    private Plumbing[] plumbings = new Plumbing[10];
    PlumbingWarehouse()
    {
        plumbings[0] = new Plumbing(0, "toilet", "Jysk", "WALKER CLOSE-COUPLED TOILET DUAL-FLUSH", 0, 109.99, "527HG");
        plumbings[1] = new Plumbing(1, "mixer", "Hygienic Bathrooms", "Hygienic Bathrooms Kitchen Mixer", 30, 14.24, "1121");
        plumbings[2] = new Plumbing(2, "mixer", "Hygienic Bathrooms", "Hygienic Bathrooms Two Hole Kitchen Sink Mixer", 80, 42.22, "1018");
        plumbings[3] = new Plumbing(3, "thermostat", "Nest", "STAND FOR NEST 3RD GENERATION DIGITAL LEARNING THERMOSTAT", 60, 35.00, "AT3000GB");
        plumbings[4] = new Plumbing(4, "thermostat", "Nest", "NEST 3RD GENERATION BLACK DIGITAL LEARNING WI-FI THERMOSTAT", 2, 170.00, "T3029EX");
        plumbings[5] = new Plumbing(5, "thermostat", "Nest", "NEST 3RD GENERATION STAINLESS STEEL DIGITAL LEARNING WI-FI THERMOSTAT", 5, 180.00, "T3028GB");
        plumbings[6] = new Plumbing(6, "pipe", "FloPlast", "FLOPLAST OVERFLOW PIPE WHITE 21.5MM X 3M", 30, 2.59, "68593");
        plumbings[7] = new Plumbing(7, "pipe", "FloPlast", "FLOPLAST SOLVENT WELD PIPES WHITE 50MM X 3M", 100, 10.68, "88188");
        plumbings[8] = new Plumbing(8, "toilet", "AuthenticPlumbing", "TOILET-TO-GO CLOSE-COUPLED TOILET", 0, 300.99, "0A312");
        plumbings[9] = new Plumbing(9, "toilet", "CASSELLIE", "MONTEGO COMFORT HEIGHT CLOSE-COUPLED TOILET DUAL-FLUSH", 0, 129.99, "892GV");
    }

    public Plumbing[] getPlumbings() { return plumbings; }
}