import salesMangement.exceptions.AmountException;
import salesMangement.exceptions.StatusExceptions;
import org.junit.Assert;
import org.junit.Test;
import salesMangement.enume.*;
import salesMangement.TicketAutomat;
import salesMangement.TicketAutomatImpl;
import salesMangement.exceptions.TicketNotFoundException;

import java.io.IOException;

public class Test_TicketAutomat {
    @Test
    public void test_Strat_Ziel_Ort() throws IOException {
        TicketAutomat testObj = new TicketAutomatImpl();

        Cities startReturn = testObj.startOrt(Cities.BERLIN);
        Assert.assertEquals(Cities.BERLIN, startReturn);

        Cities zielReturn = testObj.zielOrt(Cities.HAMBURG);
        Assert.assertEquals(Cities.HAMBURG, zielReturn);
    }

    @Test
    public void test_Tickettyp() throws StatusExceptions, IOException, TicketNotFoundException {
        TicketAutomat testObj = new TicketAutomatImpl();

        Cities startReturn = testObj.startOrt(Cities.BERLIN);
        Cities zielReturn = testObj.zielOrt(Cities.HAMBURG);

        double ticketReturn = testObj.ticketTyp(TicketsEnum.EINZEL_TICKET);
        Assert.assertEquals(TicketsEnum.EINZEL_TICKET, ticketReturn);
    }

    @Test
    public void test_zahlen() throws StatusExceptions, IOException, AmountException, TicketNotFoundException {
        TicketAutomat testObj = new TicketAutomatImpl();

        Cities startReturn = testObj.startOrt(Cities.BERLIN);
        Cities zielReturn = testObj.zielOrt(Cities.HAMBURG);
        double ticketReturn = testObj.ticketTyp(TicketsEnum.EINZEL_TICKET);
        // zahlen returned Rückgeld
        double zahlen = testObj.zahlen(50.5);

        Assert.assertEquals(20, zahlen);
    }

    @Test
    public void test_zahlen2() throws StatusExceptions, IOException, AmountException, TicketNotFoundException {
        TicketAutomat testObj = new TicketAutomatImpl();

        Cities startReturn = testObj.startOrt(Cities.BERLIN);
        Cities zielReturn = testObj.zielOrt(Cities.HAMBURG);
        double ticketReturn = testObj.ticketTyp(TicketsEnum.EINZEL_TICKET);
        // zahlen returned Rückgeld
        double zahlen = testObj.zahlen(20.5);
        double zahlen2 = testObj.zahlen(5);
        //double zahlen3 = testObj.zahlen(5);

        System.out.print(testObj.zahlen(5));
    }

    @Test (expected = StatusExceptions.class)
    public void test_Tickettyp_falschZustand() throws StatusExceptions, IOException, TicketNotFoundException {
        TicketAutomat testObj = new TicketAutomatImpl();

        Cities startReturn = testObj.startOrt(Cities.BERLIN);
        double ticketReturn = testObj.ticketTyp(TicketsEnum.EINZEL_TICKET);
        Cities zielReturn = testObj.zielOrt(Cities.HAMBURG);

    }

    @Test (expected = StatusExceptions.class)
    public void test_zahlen_falschZustand() throws StatusExceptions, IOException, AmountException, TicketNotFoundException {
        TicketAutomat testObj = new TicketAutomatImpl();

        Cities startReturn = testObj.startOrt(Cities.BERLIN);
        Cities zielReturn = testObj.zielOrt(Cities.HAMBURG);
        // zahlen returned Rückgeld
        double zahlen = testObj.zahlen(50.5);
        double ticketReturn = testObj.ticketTyp(TicketsEnum.EINZEL_TICKET);

    }

    @Test
    public void testAutomat() throws StatusExceptions, IOException, AmountException, TicketNotFoundException {
        TicketAutomat testObj = new TicketAutomatImpl();

        testObj.startOrt(Cities.BERLIN);
        testObj.zielOrt(Cities.HAMBURG);
        testObj.ticketTyp(TicketsEnum.WOCHEN_TICKET);
        double rest = testObj.zahlen(200);
        System.out.print("Rückgeld: "+rest);

    }
}