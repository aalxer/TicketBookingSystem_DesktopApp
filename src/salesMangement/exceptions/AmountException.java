package salesMangement.exceptions;

public class AmountException extends Exception{
    public AmountException(String message) {
        String error = BookedUpException.ConsoleColors.RED+message;
        System.out.print(error);
    }

    public class ConsoleColors {
        public static final String RED = "\033[0;31m";
    }
}
