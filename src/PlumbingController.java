import java.util.Arrays;

class PlumbingController {      // Controller
    private PlumbingView view;
    private PlumbingStorage storage;

    public PlumbingController(){
        this.storage = new PlumbingStorage();
        storage.getModelsFromDatabase();
        this.view = new PlumbingView();
    }

    public void run()
    {
        int option;
        do {
            do {
                option = view.menuPrompt();
            } while(option < 0 || option > 3);

            switch(option) {
                case 1:
                    System.out.print(storage);
                    break;
                case 2:
                    String manufacturer = view.manufacturerPrompt();
                    PlumbingStorage search;
                    search = getByManufacturer(manufacturer, storage.getModels());
                    System.out.print(search);
                    break;
                case 3:
                    String kind = view.kindPrompt();
                    double price = view.maxPricePrompt();
                    search = getByKindCostLessThan(kind, price, storage.getModels());
                    System.out.print(search);
                    break;
                default:
                    break;
            }
        } while (option != 0);
    }

    public static PlumbingStorage getByManufacturer(String manufacturer, Plumbing[] plumbings) {
        PlumbingStorage filtered_plumbings = new PlumbingStorage();
        for (Plumbing plumbing : plumbings) {
            if (plumbing.getManufacturer().equalsIgnoreCase(manufacturer))
            {
                filtered_plumbings.add(plumbing);
            }
        }
        return filtered_plumbings;
    }

    public static PlumbingStorage getByKindCostLessThan(String kind, double max_price, Plumbing[] plumbings) {
        PlumbingStorage filtered_plumbings = new PlumbingStorage();
        for (Plumbing plumbing : plumbings) {
            if (plumbing.getKind().equalsIgnoreCase(kind) && plumbing.getPrice() < max_price)
            {
                filtered_plumbings.add(plumbing);
            }
        }
        return filtered_plumbings;
    }

}
