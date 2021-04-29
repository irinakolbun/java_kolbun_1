import java.util.Scanner;

class PlumbingView {        // View
    Scanner in;
    String menu = "\n Будь ласка, виберіть опцію меню."
            + "\n1 Вивести усі доступні товари."
            + "\n2 Отримати список сантехніки заданої фірми."
            + "\n3 Отримати список сантехніки заданого виду і менше вказаної ціни."
            + "\n4 Додати товар."
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

    String modelPrompt() {
        System.out.println("Введіть модель товару: ");
        return in.nextLine();
    }

    double pricePrompt() {
        System.out.println("Введіть ціну товару: ");
        double res = in.nextDouble();
        scannerSkip();
        return res;
    }

    int quantityPrompt() {
        System.out.println("Введіть кількість товару: ");
        int res = in.nextInt();
        return res;
    }

    String namePrompt() {
        System.out.println("Введіть назву товару: ");
        return in.nextLine();
    }

    void printException(Exception ex) {
        System.out.println(ex.getMessage());
    }

    void printInvalidInput() {
        System.out.printf("\"%s\" - невірний формат вводу!%n", in.nextLine());
    }

}
