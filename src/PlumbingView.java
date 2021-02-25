import java.util.Scanner;

class PlumbingView {        // View
    Scanner in;
    String menu = "\n Будь ласка, виберіть опцію меню."
            + "\n1 Вивести усі доступні товари."
            + "\n2 Отримати список сантехніки заданої фірми."
            + "\n3 Отримати список сантехніки заданого виду і менше вказаної ціни."
            + "\n0 Вийти з програми. \n";

    PlumbingView() {
        in = new Scanner(System.in);
    }

    void scannerSkip()
    {
        in.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
    }

    int menuPrompt() {
        System.out.println(menu);
        int res = in.nextInt();
        scannerSkip();
        return res;
    }

    String manufacturerPrompt() {
        System.out.println("Введіть назву фірми: ");
        return in.nextLine();
    }

    String kindPrompt() {
        System.out.println("Введіть вид товару: ");
        return in.nextLine();
    }

    double maxPricePrompt() {
        System.out.println("Введіть максимальну ціну: ");
        double res = in.nextDouble();
        scannerSkip();
        return res;
    }

    public void printPlumbing(Plumbing plumbing){
        System.out.printf("%-50s   %-7s   %15s %18s %16s %n",
                plumbing.getName().substring(0, Math.min(plumbing.getName().length(), 50)),
                plumbing.getPrice(), plumbing.getModel(), plumbing.getKind(), plumbing.getManufacturer());
    }

    public void printPlumbings(Plumbing[] plumbings) {
        PlumbingView view = new PlumbingView();
        System.out.println("-".repeat(119) + "-");
        System.out.println("Name" + " ".repeat(48) + " Price " + " ".repeat(13) + " Model " + " ".repeat(10) + " Kind " + " ".repeat(10) + " Manufacturer |");
        System.out.println("-".repeat(119) + "-");
        for (Plumbing plumbing : plumbings) {
            printPlumbing(plumbing);
        }
        System.out.println();
    }
}
