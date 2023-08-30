package salesMangement;

import salesMangement.exceptions.AmountException;
import salesMangement.exceptions.TicketNotFoundException;
import salesMangement.readThread.ReadThread;
import salesMangement.readThread.ReadThreadListener;
import schedulesManagement.data.Schedules;
import schedulesManagement.data.TicketsPreis;
import salesMangement.exceptions.StatusExceptions;
import salesMangement.enume.*;
import salesMangement.data.Ticket;
import tcpThread.TCPStream;
import tcpThread.TCPStreamCreatedListener;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author alxer
 */
public class TicketAutomatImpl implements TicketAutomat, TCPStreamCreatedListener, ReadThreadListener {

    private Cities startOrt;
    private Cities zielOrt;
    private TicketsEnum ticket;
    private double preis = 0;

    private Status status = Status.START;
    private double sum = 0;

    private InputStream isTCP;
    private InputStream isTickets;
    private InputStream isSchedule;
    private OutputStream osTickets;

    private final ArrayList<Double> transactionsList = new ArrayList<>();
    private final ArrayList<Ticket> tickets = new ArrayList<>();
    private final ArrayList<Schedules> schedule = new ArrayList<>();

    public TicketAutomatImpl() throws IOException {

        // Datei (Verkaufte Tickets) einlesen und in die Liste speichern beim Starten
        try {
            this.isTickets = new FileInputStream("F:\\JAVA\\IntelliJ-Workspace\\TicketAutomat\\transactions.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.readInt();

        // file zum Schreiben der verkauften Tickets öffnen
        try {
            this.osTickets = new FileOutputStream("F:\\JAVA\\IntelliJ-Workspace\\TicketAutomat\\transactions.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.saveTransaction();

        double sum = 0 ;
        for(double p : this.transactionsList) {
            sum = sum + p;
        }
        System.out.print("Ticketsverkauf Summe bis zu diesem Zeitpunkt: "+sum+"€\n");

        // Fahrpläne importieren
        this.importSchedules();
    }

    @Override
    public Cities startOrt(Cities city) {
        this.startOrt = city;
        return this.startOrt;
    }

    @Override
    public Cities zielOrt(Cities city) {
        this.zielOrt = city;
        this.status = Status.TICKET;
        return this.zielOrt;
    }

    @Override
    public double ticketTyp(TicketsEnum ticket) throws StatusExceptions, TicketNotFoundException {
        if (this.status.equals(Status.TICKET)) {
            this.ticket = ticket;

            // preis bercehnen
            this.getPreis();

            // Status ändern und weiter zu Zahlen
            this.status = Status.ZAHLEN;
            return this.preis;
        }
        else {
            if (this.zielOrt == null) {
                throw new StatusExceptions("Ziel Ort fehlt !\n");
            }
            throw new StatusExceptions("Start Ort fehlt !\n");
        }
    }

    @Override
    public double zahlen(double preis) throws StatusExceptions, AmountException {
        if (this.status.equals(Status.ZAHLEN)) {
            System.out.print(this.preis+"€ sind zu zahlen .. \n");

            this.sum = sum + preis;

            if (this.sum >= this.preis) {

                double rueckgeld = this.sum - this.preis;
                return rueckgeld;
            } else {
                throw new AmountException("noch "+(this.preis - this.sum)+" zu zahlen\n");
            }
        } else {
            if (this.status.equals(Status.TICKET)) {
                throw new StatusExceptions("Tickettyp auswählen !\n");
            } else {
                throw new StatusExceptions("Orte vollständig auswählen !\n");
            }
        }
    }

    @Override
    public Ticket ticketPrint() {
        Ticket newTicket = new Ticket(this.startOrt,this.zielOrt,this.preis,this.ticket);
        this.tickets.add(newTicket);
        System.out.print("+----------------------------------------------\n");
        System.out.print("+ "+this.ticket+"\n");
        System.out.print("+ von : "+this.startOrt+" -> nach : "+this.zielOrt+"\n");
        System.out.print("+ preis : "+this.preis+"€\n");
        System.out.print("+ Verkaufsdatum : "+this.tickets.get(this.tickets.size()-1).getDate()+"\n");
        System.out.print("+----------------------------------------------\n");

        // verkauf speichern
        this.saveNewTransaction();

        // aufräumen
        this.cancel();

        return newTicket;
    }

    @Override
    public void cancel() {
        this.status = Status.START;
        this.startOrt = null;
        this.zielOrt = null;
        this.ticket = null;
        this.preis = 0;
        this.sum = 0;
    }

    // Obj mit den entsprechenden Städten finden, das gewünschte Ticket in der Ticketsliste der Städte finden und dann deren Preis speichern
    private double getPreis() throws TicketNotFoundException{
        for(Schedules f : this.schedule) {
            if(f.getStadt1().equals(this.startOrt) && f.getStadt2().equals(this.zielOrt) || f.getStadt2().equals(this.startOrt) && f.getStadt1().equals(this.zielOrt)) {
                for (TicketsPreis t : f.getTickets()) {
                    if(this.ticket.equals(t.getTicktTyp())) {
                        this.preis = t.getPreis();
                        return t.getPreis();
                    }
                }
            }
        }
        throw new TicketNotFoundException();
    }

    //==================================================================
    // Data exchange area :
    //==================================================================

    private void saveTransaction() {
        DataOutputStream dos = new DataOutputStream(this.osTickets);
        try {
            for(double p : this.transactionsList) {
                dos.writeDouble(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveNewTransaction() {
        DataOutputStream dos = new DataOutputStream(this.osTickets);
        try {
            dos.writeDouble(this.preis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readInt() throws IOException {
        DataInputStream dis = new DataInputStream(this.isTickets);
        while (dis.available()>0) {
            try {
                this.transactionsList.add(dis.readDouble());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void importSchedules() throws IOException {

        try {
            this.isSchedule = new FileInputStream("F:\\JAVA\\IntelliJ-Workspace\\TicketAutomat\\fahrplaene_list.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        DataInputStream disSchedule = new DataInputStream(this.isSchedule);
        Cities stadt1;
        Cities stadt2;
        TicketsEnum ticketTyp;
        double preis;

        while (disSchedule.available()>0) {
            ArrayList<TicketsPreis> tickets = new ArrayList<>();

            // Städte-Teil einlesen :
            int startCityInt = disSchedule.readInt();
            stadt1 = this.getCityInEnum(startCityInt);

            int distCityInt = disSchedule.readInt();
            stadt2 = this.getCityInEnum(distCityInt);

            // Tickets-Teil einlesen :
            int ticketTyp1Int = disSchedule.readInt();
            ticketTyp = this.getTicketInEnm(ticketTyp1Int);
            preis = disSchedule.readDouble();
            tickets.add(new TicketsPreis(ticketTyp,preis));

            int ticketTyp2Int = disSchedule.readInt();
            ticketTyp = this.getTicketInEnm(ticketTyp2Int);
            preis = disSchedule.readDouble();
            tickets.add(new TicketsPreis(ticketTyp,preis));

            int ticketTyp3Int = disSchedule.readInt();
            ticketTyp = this.getTicketInEnm(ticketTyp3Int);
            preis = disSchedule.readDouble();
            tickets.add(new TicketsPreis(ticketTyp,preis));

            // Fahrpläne eintragen
            this.schedule.add(new Schedules(stadt1,stadt2,tickets));
        }
    }

    private Cities getCityInEnum(int c) {
        switch (c) {
            case 0 -> {
                return Cities.BERLIN;
            }
            case 1 -> {
                return Cities.HAMBURG;
            }
            case 2 -> {
                return Cities.KOELN;
            }
            case 3 -> {
                return Cities.FRANKFURT;
            }
            case 4 -> {
                return Cities.MUENCHEN;
            }
        }

        return null;
    }

    private TicketsEnum getTicketInEnm(int t) {
        switch (t) {
            case 0 -> {
                return TicketsEnum.EINZEL_TICKET;
            }
            case 1 -> {
                return TicketsEnum.WOCHEN_TICKET;
            }
            case 2 -> {
                return TicketsEnum.TAGESTICKET;
            }
        }
        return null;
    }

    //==================================================================
    // TCP-Connection area :
    //==================================================================

    @Override
    public void streamCreated(TCPStream channel) {
        try {
            this.isTCP = channel.getInputStream();
            // readThread TCP starten
            ReadThread readThread = new ReadThread(this,this.isTCP);
            readThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //==================================================================
    // Read thread area :
    //==================================================================

    @Override
    public void updated() throws IOException {
        System.out.print("System update ..\n");
        this.importSchedules();
        System.out.print("Updated successfully !\n");
    }

    @Override
    public void verbindungSchliessen() {
        System.out.print("Verbindung abgebaut");
        this.isTCP = null;
    }
}