import java.util.InputMismatchException;

public class InvalidPriceException extends InputMismatchException {
    InvalidPriceException() {
        super("Введіть число більше нуля");
    }

    InvalidPriceException(String message) {
        super(message);
    }
}
