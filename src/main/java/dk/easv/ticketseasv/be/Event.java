package dk.easv.ticketseasv.be;

public class Event
{
    private int id;
    private String name;
    private String date;
    private String startTime;
    private String endTime;
    private String location;
    private String description;
    // Full constructor — used when loading from DB (ID already exists)
    public Event(String name, String description, String startTime, String endTime, String date, String location, int id)
    {
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.location = location;
        this.id = id;
    }

//     No-ID constructor — used when creating a new event before DB assigns an ID
    public Event(String name, String description, String startTime, String endTime, String date,  String location)
    {
        this (name, description, date, startTime, endTime, location, 0);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getStarTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }

    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString()
    {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}