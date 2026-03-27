package dk.easv.ticketseasv.be;

public class Ticket {
    private int id;
    private String ticketOwner;
    private String barcode;
    private int eventId;
    private int ticketTypeId;
    private double ticketPrice;

    public Ticket(int id, String ticketOwner, String barcode, int eventId, int ticketTypeId, double ticketPrice) {
        this.id = id;
        this.ticketOwner = ticketOwner;
        this.barcode = barcode;
        this.eventId = eventId;
        this.ticketTypeId = ticketTypeId;
        this.ticketPrice = ticketPrice;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTicketOwner() {
        return ticketOwner;
    }

    public void setTicketOwner(String ticketOwner) {
        this.ticketOwner = ticketOwner;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getTicketTypeId() {
        return ticketTypeId;
    }

    public void setTicketTypeId(int ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}
