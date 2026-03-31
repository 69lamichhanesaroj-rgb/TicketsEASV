package dk.easv.ticketseasv.bll;

import dk.easv.ticketseasv.be.*;
import dk.easv.ticketseasv.dal.DAOManager;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventLogic {

    private final DAOManager dao = new DAOManager();

    public EventLogic()
    {}

    public int createEvent(String name, String description, LocalTime start, LocalTime end, LocalDate Date, String location)
    {
        return dao.getEventDAO().addEvent(name, description, start, end, Date, location);
    }

    public void editEvent(Event event)
    {
        dao.getEventDAO().editEvent(event);
    }
}
