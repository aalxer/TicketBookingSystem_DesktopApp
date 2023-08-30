package salesMangement.exceptions;

public class StatusExceptions extends Exception {
    public StatusExceptions(String message) {
        String error = StatusExceptions.ConsoleColors.RED+message;
        System.out.print(error);
    }

    public class ConsoleColors {
        public static final String RED = "\033[0;31m";
    }
}
