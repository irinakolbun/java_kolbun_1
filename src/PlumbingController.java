import org.apache.log4j.Logger;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.InputMismatchException;

class PlumbingController {      // Controller
    private PlumbingView view;
    private PlumbingStorage storage;
    private FileIO io;
    final static Logger logger = Logger.getLogger(PlumbingController.class);

    public PlumbingController() {
        this.storage = new PlumbingStorage();
        this.io = new FileIO();
        try {
            storage = io.readPlumbingsFromFile("storage.json");
        } catch (IOException e) {
            logger.warn("Failed to read plumbings from file storage.json, falling back to static items list");
            // fallback to static items list
            storage.getModelsFromDatabase();
        }
        this.view = new PlumbingView();
    }

    public void run()
    {
        int option;
        do {
            do {
                option = view.menuPrompt();
            } while(option < 0 || option > 5);
            logger.info("Option " + String.valueOf(option) + " selected by user");
            switch(option) {
                case 1:
                    System.out.print(storage);
                    logger.info("All items printed successfully");
                    break;
                case 2:
                    String manufacturer = view.manufacturerPrompt();
                    PlumbingStorage search;
                    try {
                        search = getByManufacturer(manufacturer, storage.getModels());
                        System.out.print(search);
                        logger.info("Search results printed");
                        logger.debug("\n" + search);
                        if (view.saveToFilePrompt()) {
                            Timestamp ts = new Timestamp(System.currentTimeMillis());
                            try {
                                io.writePlumbingsToFile("search_man_" + ts.toString() + ".json", search);
                                logger.info("Search results written to file successfully");
                            } catch (IOException ex) {
                                logger.error("Failed to write plumbings to file search_man_" + ts.toString() + ".json");
                                view.printException(ex);
                            }
                        }
                    } catch (ItemNotFoundException ex) {
                        logger.debug("No items found");
                        view.printException(ex);
                    }

                    break;
                case 3:
                    String kind = view.kindPrompt();
                    double price;

                    while (true) {
                        try {
                            price = view.maxPricePrompt();
                            price = validatePrice(price);
                            logger.debug("User entered: " + price + " price");
                            break;
                        } catch (InvalidPriceException ex)
                        {
                            view.printException(ex);
                            logger.debug("Invalid data format");
                        } catch (InputMismatchException ex) {
                            view.printInvalidInput();
                        }
                    }

                    try {
                        search = getByKindCostLessThan(kind, price, storage.getModels());
                        System.out.print(search);
                        if (view.saveToFilePrompt()) {
                            Timestamp ts = new Timestamp(System.currentTimeMillis());
                            try {
                                io.writePlumbingsToFile("search_kindcost_" + ts.toString() + ".json", search);
                            } catch (IOException ex) {
                                logger.error("Failed to write plumbings to file search_kindcost_" + ts.toString() + ".json");
                                view.printException(ex);
                            }
                        }
                    } catch (ItemNotFoundException ex) {
                        view.printException(ex);
                    }
                    break;
                case 4:
                    String new_kind = view.kindPrompt();
                    String new_manufacturer = view.manufacturerPrompt();
                    String new_name = view.namePrompt();
                    String new_model = view.modelPrompt();
                    double new_price;
                    while (true) {
                        try {
                            new_price = view.pricePrompt();
                            new_price = validatePrice(new_price);
                            break;
                        } catch (InvalidPriceException ex)
                        {
                            view.printException(ex);
                        } catch (InputMismatchException ex) {
                            view.printInvalidInput();
                        }
                    }

                    int new_quantity;

                    while (true) {
                        try {
                            new_quantity = view.quantityPrompt();
                            new_quantity = validateQuantity(new_quantity);
                            break;
                        } catch (InvalidQuantityException ex)
                        {
                            view.printException(ex);
                        } catch (InputMismatchException ex) {
                            view.printInvalidInput();
                        }
                    }

                    Plumbing new_plumbing = new Plumbing(storage.getNextId(),
                            new_kind, new_manufacturer, new_name, new_quantity, new_price, new_model);
                    storage.add(new_plumbing);
                    break;
                default:
                    break;
            }
        } while (option != 0);
        try {
            io.writePlumbingsToFile("storage.json", storage);
        } catch (IOException ex) {
            logger.error("Failed to write plumbings to file storage.json");
            view.printException(ex);
        }
        logger.info("Exiting program");
    }

    public static PlumbingStorage getByManufacturer(String manufacturer, Plumbing[] plumbings) {
        PlumbingStorage filtered_plumbings = new PlumbingStorage();
        for (Plumbing plumbing : plumbings) {
            if (plumbing.getManufacturer().equalsIgnoreCase(manufacturer))
            {
                filtered_plumbings.add(plumbing);
            }
        }
        if (filtered_plumbings.len() == 0)
            throw new ItemNotFoundException();
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
        if (filtered_plumbings.len() == 0)
            throw new ItemNotFoundException();
        return filtered_plumbings;
    }

    public static double validatePrice(double price) {
        if (price <= 0)
            throw new InvalidPriceException();
        return price;
    }

    public static int validateQuantity(int quantity) {
        if (quantity < 0)
            throw new InvalidPriceException();
        return quantity;
    }

}
