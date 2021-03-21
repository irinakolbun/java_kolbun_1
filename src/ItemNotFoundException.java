public class ItemNotFoundException extends RuntimeException {
    ItemNotFoundException() {
        super("Не знайдено жодного виробу");
    }

    ItemNotFoundException(String message) {
        super(message);
    }
}
