package salesMangement.data;

import salesMangement.enume.*;
import java.util.Date;

public class Ticket {
    private Cities startOrt;
    private Cities zielOrt;
    private double preis;
    private TicketsEnum ticketTyp;
    private Date date;

    public Ticket(Cities startOrt, Cities zielOrt, double preis, TicketsEnum ticketTyp) {
        this.startOrt = startOrt;
        this.zielOrt = zielOrt;
        this.preis = preis;
        this.ticketTyp = ticketTyp;
        this.date = new Date();
    }

    public Cities getStartOrt() {
        return startOrt;
    }

    public Cities getZielOrt() {
        return zielOrt;
    }

    public double getPreis() {
        return preis;
    }

    public TicketsEnum getTicketTyp() {
        return ticketTyp;
    }

    public Date getDate() {
        return date;
    }

    public void setStartOrt(Cities startOrt) {
        this.startOrt = startOrt;
    }

    public void setZielOrt(Cities zielOrt) {
        this.zielOrt = zielOrt;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public void setTicketTyp(TicketsEnum ticketTyp) {
        this.ticketTyp = ticketTyp;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Ticket [ " +
                "startOrt=" + startOrt +
                ", zielOrt=" + zielOrt +
                ", preis=" + preis +
                ", ticketTyp=" + ticketTyp +
                ", date=" + date +
                " ]";
    }
}
