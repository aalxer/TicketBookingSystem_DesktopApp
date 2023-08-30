import schedulesManagement.SchedulesManagement;
import schedulesManagement.SchedulesManagementImpl;
import schedulesManagement.MemoryEmptyException;
import schedulesManagement.TicketExistsException;
import schedulesManagement.data.Schedules;
import schedulesManagement.data.TicketsPreis;
import org.junit.Test;
import salesMangement.enume.Cities;
import salesMangement.enume.TicketsEnum;

import java.io.IOException;
import java.util.ArrayList;

public class FahrplanMangmentTest {

    @Test
    public void test() throws IOException, MemoryEmptyException, TicketExistsException {
        SchedulesManagement fahrpreise = new SchedulesManagementImpl();
        ArrayList<TicketsPreis> tickets = new ArrayList<>();
        tickets.add(new TicketsPreis(TicketsEnum.EINZEL_TICKET,24.9));
        tickets.add(new TicketsPreis(TicketsEnum.WOCHEN_TICKET,66));
        tickets.add(new TicketsPreis(TicketsEnum.TAGESTICKET,89.9));

        ArrayList<TicketsPreis> tickets2 = new ArrayList<>();
        tickets2.add(new TicketsPreis(TicketsEnum.EINZEL_TICKET,14.9));
        tickets2.add(new TicketsPreis(TicketsEnum.WOCHEN_TICKET,46));
        tickets2.add(new TicketsPreis(TicketsEnum.TAGESTICKET,77.9));

        ArrayList<TicketsPreis> tickets3 = new ArrayList<>();
        tickets3.add(new TicketsPreis(TicketsEnum.EINZEL_TICKET,4.9));
        tickets3.add(new TicketsPreis(TicketsEnum.WOCHEN_TICKET,6));
        tickets3.add(new TicketsPreis(TicketsEnum.TAGESTICKET,7.9));

        //fahrpreise.add(new Fahrpreis(Cities.BERLIN,Cities.HAMBURG,tickets));
        //fahrpreise.add(new Fahrpreis(Cities.BERLIN,Cities.MUENCHEN,tickets2));
        fahrpreise.add(new Schedules(Cities.BERLIN,Cities.KOELN,tickets3));

        fahrpreise.printAll();
    }
}
