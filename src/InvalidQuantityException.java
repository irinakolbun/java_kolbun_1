import java.util.InputMismatchException;

public class InvalidQuantityException extends InputMismatchException {
    InvalidQuantityException() {
        super("Введіть число більше нуля");
    }

    InvalidQuantityException(String message) {
        super(message);
    }
}
