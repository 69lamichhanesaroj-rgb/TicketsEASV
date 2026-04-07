package dk.easv.ticketseasv.be;

public class Event {
    private int id;
    private String name;
    private String moreInfo; // karaoke starts this time
    private String date;
    private String starTime;
    private String endTime;
    private String location;
    private String description; //About this event
    private String when;
    private String imagePath;



    //Constructor
    public Event(int id, String name, String moreInfo, String date, String starTime,String endTime,String location,String description) {
        this.id = id;
        this.name = name;
        this.moreInfo = moreInfo;
        this.date = date;
        this.starTime = starTime;
        this.endTime = endTime;
        this.location = location;
        this.description = description;
    }

    public Event(int id, String name, String moreInfo, String when, String starTime,String endTime,String location,String description, String imagePath) {
        this.id = id;
        this.name = name;
        this.moreInfo = moreInfo;
        this.when = when;
        this.starTime = starTime;
        this.endTime = endTime;
        this.location = location;
        this.description = description;
        this.imagePath = imagePath;
    }

    public Event (String name, String when, String imagePath){
        this.name = name;
        this.when = when;
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStarTime() {
        return starTime;
    }

    public void setStarTime(String starTime) {
        this.starTime = starTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWhen() {
        return when;
    }
    public void setWhen(String when) {
        this.when = when;
    }
    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}