package dk.easv.ticketseasv.be;

public class EventType {
    private int id;
    private String name;
    private String moreInfo;

    public  EventType(int id, String name, String moreInfo)
    {
        this.id = id;
        this.name = name;
        this.moreInfo = moreInfo;
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

}
