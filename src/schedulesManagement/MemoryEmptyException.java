package schedulesManagement;

public class MemoryEmptyException extends Exception{
    public MemoryEmptyException(String message) {
        String error = MemoryEmptyException.ConsoleColors.RED+message;
        System.out.print(error);
    }

    public class ConsoleColors {
        public static final String RED = "\033[0;31m";
    }
}
