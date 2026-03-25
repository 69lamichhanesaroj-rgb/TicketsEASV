package dk.easv.ticketseasv.be;


public class Event {
    private int id;
    private String name;
    private double price;

    public Event(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
}