package salesMangement;

import salesMangement.enume.Cities;
import salesMangement.enume.TicketsEnum;
import salesMangement.exceptions.AmountException;
import salesMangement.exceptions.StatusExceptions;
import console.Console;
import salesMangement.exceptions.TicketNotFoundException;
import tcpThread.TCPStream;

import java.io.IOException;

public class TicketAutomatUI {

    public static void auotmatLoop(TicketAutomatImpl ticketAutomat, Console console) {
        while (true) {
            for(int i = 0; i < Cities.values().length ; i++) {
                System.out.print(i+". "+Cities.values()[i]+"\n");
            }
            ticketAutomat.startOrt(console.readCity("Start Stadt : "));
            for(int i = 0; i < Cities.values().length ; i++) {
                System.out.print(i+". "+Cities.values()[i]+"\n");
            }
            ticketAutomat.zielOrt(console.readCity("Ziel Stadt : "));
            for (int i = 0; i < TicketsEnum.values().length; i++) {
                System.out.print(i +". " +TicketsEnum.values()[i] + "\n");
            }
            try {
                ticketAutomat.ticketTyp(console.readTicket("Ticket Typ eingeben : "));
                boolean amountNotCompletely = true;
                double rest = 0;
                while (amountNotCompletely) {
                    try {
                        rest = ticketAutomat.zahlen(console.readDouble("Geld einzahlen : "));
                        amountNotCompletely = false;
                    } catch (AmountException ignored) {
                    }
                }
                System.out.print("Rückgeld : " + rest+"\n");
            } catch (StatusExceptions | TicketNotFoundException ignored) {
            }
        }
    }

    public static void main(String[] args) throws IOException {
        TicketAutomatImpl ticketAutomat = new TicketAutomatImpl();
        Console console = new Console();

        // TCP connection :
        int port = 2462;
        boolean asServer = false;
        String name = "TCPStream";
        TCPStream tcpStream = new TCPStream(port, asServer, name, ticketAutomat);
        // TCP start :
        tcpStream.start();

        // Automat start
        auotmatLoop(ticketAutomat, console);

    }
}
