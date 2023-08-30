package schedulesManagement;

import schedulesManagement.data.Schedules;
import schedulesManagement.data.TicketsPreis;
import salesMangement.enume.Cities;
import salesMangement.enume.TicketsEnum;
import tcpThread.TCPStream;
import tcpThread.TCPStreamCreatedListener;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author alxer
 */
public class SchedulesManagementImpl implements SchedulesManagement, TCPStreamCreatedListener {

    private OutputStream osTCP;
    private OutputStream os;
    private InputStream is;
    private final ArrayList<Schedules> fahrplaene;

    // Const:
    private final int BERLIN = 0;
    private final int HAMBURG = 1;
    private final int KOELN = 2;
    private final int FRANKFURT = 3;
    private final int MUENCHEN = 4;

    private final int EINZEL_TICKET = 0;
    private final int WOCHEN_TICKET = 1;
    private final int TAGESTICKET = 2;

    public SchedulesManagementImpl() throws IOException {
        // hier wird die Datei importiert und in der Liste gespeichert
        this.fahrplaene = new ArrayList<>();

        // Datei wird geöffnet und die Data eingelesen
        try {
            this.is = new FileInputStream("F:\\JAVA\\IntelliJ-Workspace\\TicketAutomat\\fahrplaene_list.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        this.importList();

        // Datei wird neu geöffnet zum Speichern
        try {
            this.os = new FileOutputStream("F:\\JAVA\\IntelliJ-Workspace\\TicketAutomat\\fahrplaene_list.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // damit die file wieder die eingelesenen Daten, die er hatte, haben.
        for (Schedules f : this.fahrplaene) {
            this.exportSchedule(f);
        }
    }

    @Override
    public void add(Schedules ticket) throws IOException, TicketExistsException {
        if (this.getTicketByCities(ticket.getStadt1(),ticket.getStadt2()) != null) {
            throw new TicketExistsException("Ticket exsistiert schon");
        }

        this.fahrplaene.add(ticket);
        // Fahrplan in die Datei schreiben
        this.exportSchedule(ticket);
        // SaleClass durch tcp informieren
        this.notifyListener();
    }

    @Override
    public void delete(int index) throws MemoryEmptyException, IndexOutOfBoundsException, IOException {
        if (this.fahrplaene.size()==0) {
            throw new MemoryEmptyException("Es sind noch keine Fahrpläne im System");
        }
        if (this.fahrplaene.size() < index) {
            throw new IndexOutOfBoundsException();
        }

        // Löschen
        for(int i = 0; i < this.fahrplaene.size() ; i++) {
            if(index == (i+1)) {
                this.fahrplaene.remove(i);
            }
        }
        // in die Datei neu eintragen
        try {
            this.os = new FileOutputStream("D:\\JAVA\\IntelliJ-Workspace\\TicketAutomat\\fahrplaene_list.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (Schedules f : this.fahrplaene) {
            this.exportSchedule(f);
        }
        // SaleClass durch tcp informieren
        this.notifyListener();
    }

    @Override
    public void printAll() throws MemoryEmptyException{
        if (this.fahrplaene.size()==0) {
            throw new MemoryEmptyException("Es sind noch keine Fahrpläne im System");
        }

        for(Schedules f : this.fahrplaene) {
            System.out.print(f.toString());
        }
    }

    @Override
    public Schedules getTicketByIndex(int index) {
        for(int i = 0; i < this.fahrplaene.size() ; i++) {
            if(index == i+1) {
                return this.fahrplaene.get(i);
            }
        }
        return null;
    }

    @Override
    public Schedules getTicketByCities(Cities stadt1, Cities stadt2) {
        for (Schedules f : this.fahrplaene) {
            if (stadt1.equals(f.getStadt1()) && stadt2.equals(f.getStadt2()) || stadt1.equals(f.getStadt2()) && stadt2.equals(f.getStadt1())) {
                return f;
            }
        }
        return null;
    }

    //==================================================================
    // Data exchange area :
    //==================================================================

    private void importList() throws IOException {

        DataInputStream dis = new DataInputStream(this.is);
        Cities stadt1;
        Cities stadt2;
        TicketsEnum ticketTyp;
        double preis;

        while (dis.available()>0) {
            ArrayList<TicketsPreis> tickets = new ArrayList<>();

            // Städte-Teil einlesen :
            int startCityInt = dis.readInt();
            stadt1 = this.getCityInEnum(startCityInt);

            int distCityInt = dis.readInt();
            stadt2 = this.getCityInEnum(distCityInt);

            // Tickets-Teil einlesen :
            int ticketTyp1Int = dis.readInt();
            ticketTyp = this.getTicketInEnum(ticketTyp1Int);
            preis = dis.readDouble();
            tickets.add(new TicketsPreis(ticketTyp,preis));

            int ticketTyp2Int = dis.readInt();
            ticketTyp = this.getTicketInEnum(ticketTyp2Int);
            preis = dis.readDouble();
            tickets.add(new TicketsPreis(ticketTyp,preis));

            int ticketTyp3Int = dis.readInt();
            ticketTyp = this.getTicketInEnum(ticketTyp3Int);
            preis = dis.readDouble();
            tickets.add(new TicketsPreis(ticketTyp,preis));

            // Fahrpläne eintragen
            this.fahrplaene.add(new Schedules(stadt1,stadt2,tickets));
        }
    }

    private void exportSchedule(Schedules f) {
        // Data auf Stream schreiben zum Speichern
        DataOutputStream dos = new DataOutputStream(this.os);
        try {
            int stadt1 = this.getCityInInt(f.getStadt1());
            int stadt2 = this.getCityInInt(f.getStadt2());
            dos.writeInt(stadt1);
            dos.writeInt(stadt2);
            // Tickets schreiben
            for (TicketsPreis t : f.getTickets()) {
                int ticketTyp = this.getTicketInInt(t.getTicktTyp());
                dos.writeInt(ticketTyp);
                dos.writeDouble(t.getPreis());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getCityInInt(Cities c) {
        switch (c) {
            case BERLIN -> {
                return this.BERLIN;
            }
            case HAMBURG -> {
                return this.HAMBURG;
            }
            case KOELN -> {
                return this.KOELN;
            }
            case FRANKFURT -> {
                return this.FRANKFURT;
            }
            case MUENCHEN -> {
                return this.MUENCHEN;
            }
        }
        return 0;
    }

    private int getTicketInInt(TicketsEnum c) {
        switch (c) {
            case EINZEL_TICKET -> {
                return this.EINZEL_TICKET;
            }
            case WOCHEN_TICKET -> {
                return this.WOCHEN_TICKET;
            }
            case TAGESTICKET -> {
                return this.TAGESTICKET;
            }
        }
        return 0;
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

    private TicketsEnum getTicketInEnum(int t) {
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
            this.osTCP = channel.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void notifyListener() throws IOException {
        // verbindung checken
        if (this.osTCP!=null) {
            try {
                DataOutputStream dataTcp_os = new DataOutputStream(this.osTCP);
                // "true" schicken als Signal, dass etwas geändert wurde
                dataTcp_os.writeBoolean(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}