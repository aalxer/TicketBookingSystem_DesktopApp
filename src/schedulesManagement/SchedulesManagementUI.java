package schedulesManagement;

import schedulesManagement.data.Schedules;
import schedulesManagement.data.TicketsPreis;
import console.Console;
import salesMangement.enume.Cities;
import salesMangement.enume.TicketsEnum;
import tcpThread.TCPStream;

import java.io.IOException;
import java.util.ArrayList;

public class SchedulesManagementUI {
    public static void main(String[] args) throws IOException, MemoryEmptyException, TicketExistsException {

        SchedulesManagementImpl schedulesManagement = new SchedulesManagementImpl();
        Console console = new Console();

        // TCP connection :
        int port = 2462;
        boolean asServer = true;
        String name = "TCPStream";
        TCPStream tcpStream = new TCPStream(port,asServer,name,schedulesManagement);
        // TCP start :
        tcpStream.start();

        while (true) {
            System.out.print("1. Aktuelle Fahrpläne zeigen\n");
            System.out.print("2. Fahrplan hinzufügen\n");
            System.out.print("3. Fahrplan löschen \n");
            int input = console.readInteger("Option wählen :\n");

            switch (input) {
                case 1 -> {
                    print(schedulesManagement);
                }
                case 2 -> {
                    add(schedulesManagement, console);
                }
                case 3 -> {
                    remove(schedulesManagement, console);
                }
            }
        }
    }

    private static void print(SchedulesManagement fahrpreise) throws MemoryEmptyException {

        fahrpreise.printAll();
    }

    private static void add(SchedulesManagement fahrpreise, Console console) throws IOException, TicketExistsException {
        for(int i = 0; i < Cities.values().length ; i++) {
            System.out.print(i+". "+Cities.values()[i]+"\n");
        }
        Cities stadt1 = console.readCity("Start Stadt auswählen : ");

        for(int i = 0; i < Cities.values().length ; i++) {
            System.out.print(i+". "+Cities.values()[i]+"\n");
        }
        Cities stadt2 = console.readCity("Ziel Stadt auswählen : ");

        ArrayList<TicketsPreis> ticketsList = new ArrayList<>();

        for(int i = 0; i < TicketsEnum.values().length ; i++) {
            for (int ii = 0; ii < TicketsEnum.values().length; ii++) {
                System.out.print(ii + ". " + TicketsEnum.values()[ii] + "\n");
            }
            TicketsEnum ticketTyp = console.readTicket("Ticket auswählen :");
            double preis = console.readDouble("Preis eingeben : ");

            ticketsList.add(new TicketsPreis(ticketTyp,preis));
        }

        fahrpreise.add(new Schedules(stadt1,stadt2,ticketsList));
    }

    private static void remove(SchedulesManagement fahrpreise, Console console) throws MemoryEmptyException, IOException {

        int input = console.readInteger("Fahrplansnummer zum Löschen eingeben : ");
        fahrpreise.delete(input);
    }
}
