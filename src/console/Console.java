package console;

import salesMangement.enume.Cities;
import salesMangement.enume.TicketsEnum;

import java.util.Scanner;

public class Console {

    private final Scanner input = new Scanner(System.in);

    public int readInteger(String text) {
        try {
            System.out.print(ConsoleColors.BLACK+text);
            return Integer.parseInt(input.nextLine());
        } catch (Exception e) {
            System.out.print(ConsoleColors.BLACK+"Falsche Eingabe ! \n");
            return readInteger(text);
        }
    }

    public double readDouble(String text) {
        try {
            System.out.print(ConsoleColors.BLACK+text);
            return Double.parseDouble(input.nextLine());
        } catch (Exception e) {
            System.out.print(ConsoleColors.BLACK+"Falsche Eingabe ! \n");
            return readDouble(text);
        }
    }

    public String readString(String text) {
        try {
            System.out.print(ConsoleColors.BLACK+text);
            return input.nextLine();
        } catch (Exception e){
            System.out.print(ConsoleColors.BLACK+"Falsche Eingabe !");
            return readString(text);
        }
    }

    public Cities readCity(String text) {
        try {
            System.out.print(ConsoleColors.BLACK+text);
            int number = Integer.parseInt(input.nextLine());
            for(int i=0 ; i < Cities.values().length ; i++) {
                if (i==number) {
                    return Cities.values()[i];
                }
            }
        } catch (Exception e) {
            System.out.print(ConsoleColors.BLACK+"Falsche Eingabe ! \n");
            return readCity(text);
        }
        return null;
    }

    public TicketsEnum readTicket(String text) {
        try {
            System.out.print(ConsoleColors.BLACK+text);
            int number = Integer.parseInt(input.nextLine());
            for(int i = 0; i < TicketsEnum.values().length ; i++) {
                if (i==number) {
                    return TicketsEnum.values()[i];
                }
            }
        } catch (Exception e) {
            System.out.print(ConsoleColors.BLACK+"Falsche Eingabe ! \n");
            return readTicket(text);
        }
        return null;
    }

    public static class ConsoleColors {

        public static final String BLACK = "\033[0;30m";
        public static final String GREEN = "\033[0;32m";
        public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";
        public static final String YELLOW_UNDERLINED = "\033[4;33m";
        public static final String RED = "\033[0;31m";
    }
}
