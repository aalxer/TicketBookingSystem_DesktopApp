package salesMangement;

import salesMangement.data.Ticket;
import salesMangement.exceptions.AmountException;
import salesMangement.exceptions.StatusExceptions;
import salesMangement.enume.*;
import salesMangement.exceptions.TicketNotFoundException;

public interface TicketAutomat {

    Cities startOrt(Cities city);
    Cities zielOrt(Cities city);
    double ticketTyp(TicketsEnum ticket) throws StatusExceptions, TicketNotFoundException;
    double zahlen(double preis) throws StatusExceptions, AmountException;
    Ticket ticketPrint();
    void cancel();
}
