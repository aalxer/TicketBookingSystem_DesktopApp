package schedulesManagement;

public class TicketExistsException extends Exception{
    public TicketExistsException(String message) {
        String error = TicketExistsException.ConsoleColors.RED+message;
        System.out.print(error);
    }

    public class ConsoleColors {
        public static final String RED = "\033[0;31m";
    }
}
