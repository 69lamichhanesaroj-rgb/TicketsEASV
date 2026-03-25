package dk.easv.ticketseasv.be;

public class Ticket {
    private int id;
    private String barcode;
    private int eventId;

    public Ticket(int id, String barcode, int eventId) {
        this.id = id;
        this.barcode = barcode;
        this.eventId = eventId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
