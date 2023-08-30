package salesMangement.exceptions;

public class BookedUpException extends Exception {
    public BookedUpException(String message) {
        String error = BookedUpException.ConsoleColors.RED+message;
        System.out.print(error);
    }

    public class ConsoleColors {
        public static final String RED = "\033[0;31m";
    }
}
