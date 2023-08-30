package schedulesManagement.data;

import salesMangement.enume.Cities;

import java.util.ArrayList;

public class Schedules {
    private Cities stadt1;
    private Cities stadt2;
    private ArrayList<TicketsPreis> tickets;

    public Schedules(Cities stadt1, Cities stadt2, ArrayList<TicketsPreis> tickets) {
        this.stadt1 = stadt1;
        this.stadt2 = stadt2;
        this.tickets = tickets;
    }

    public Cities getStadt1() {
        return stadt1;
    }

    public Cities getStadt2() {
        return stadt2;
    }

    public ArrayList<TicketsPreis> getTickets() {
        return tickets;
    }

    public void setStadt1(Cities stadt1) {
        this.stadt1 = stadt1;
    }

    public void setStadt2(Cities stadt2) {
        this.stadt2 = stadt2;
    }

    public void setTickets(ArrayList<TicketsPreis> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "Fahrplan [" +
                " von: " + stadt1 +
                ", nach: " + stadt2 + "\n"+
                tickets +
                " ]\n" + "---------------------------------------------------------\n";
    }
}
