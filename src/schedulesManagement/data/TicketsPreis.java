package schedulesManagement.data;

import salesMangement.enume.TicketsEnum;

public class TicketsPreis {
    private TicketsEnum ticktTyp;
    private double preis;

    public TicketsPreis(TicketsEnum ticktTyp, double preis) {
        this.ticktTyp = ticktTyp;
        this.preis = preis;
    }

    public TicketsEnum getTicktTyp() {
        return ticktTyp;
    }

    public double getPreis() {
        return preis;
    }

    public void setTicktTyp(TicketsEnum ticktTyp) {
        this.ticktTyp = ticktTyp;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    @Override
    public String toString() {
        return "Tickets (" +
                ticktTyp +
                ", preis=" + preis +"€"+
                " )\n";
    }
}
