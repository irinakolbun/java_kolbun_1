import java.util.Arrays;

class PlumbingController {      // Controller
    private Plumbing[] models;
    private PlumbingView view;

    public PlumbingController(){
        this.models = getModelsFromDatabase();
        this.view = new PlumbingView();
    }

    public Plumbing[] getPlumbings() {
        return models;
    }

    public void run()
    {
        int option;
        do {
            do {
                option = view.menuPrompt();
            } while(option < 0 || option > 3); // This will make the menu repeat if option is higher than 6 or lowen than 0.

            switch(option) {
                case 1:
                    view.printPlumbings(getPlumbings());
                    break;
                case 2:
                    String manufacturer = view.manufacturerPrompt();
                    view.printPlumbings(getByManufacturer(manufacturer, getPlumbings()));
                    break;
                case 3:
                    String kind = view.kindPrompt();
                    double price = view.maxPricePrompt();
                    view.printPlumbings(getByKindCostLessThan(kind, price, getPlumbings()));
                    break;
                default:
                    break; //I always use this break, even when not needed.
            }
        } while (option != 0);
    }

    public static Plumbing[] getByManufacturer(String manufacturer, Plumbing[] plumbings) {
        Plumbing[] filtered_plumbings = new Plumbing[0];
        for (Plumbing plumbing : plumbings) {
            if (plumbing.getManufacturer().equalsIgnoreCase(manufacturer))
            {
                filtered_plumbings = Arrays.copyOf(filtered_plumbings, filtered_plumbings.length + 1);
                filtered_plumbings[filtered_plumbings.length - 1] = plumbing;
            }
        }
        return filtered_plumbings;
    }

    public static Plumbing[] getByKindCostLessThan(String kind, double max_price, Plumbing[] plumbings) {
        Plumbing[] filtered_plumbings = new Plumbing[0];
        for (Plumbing plumbing : plumbings) {
            if (plumbing.getKind().equalsIgnoreCase(kind) && plumbing.getPrice() < max_price)
            {
                filtered_plumbings = Arrays.copyOf(filtered_plumbings, filtered_plumbings.length + 1);
                filtered_plumbings[filtered_plumbings.length - 1] = plumbing;
            }
        }
        return filtered_plumbings;
    }

    private Plumbing[] getModelsFromDatabase()
    {
        Plumbing[] plumbings = new Plumbing[10];
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
        return plumbings;
    }

}
