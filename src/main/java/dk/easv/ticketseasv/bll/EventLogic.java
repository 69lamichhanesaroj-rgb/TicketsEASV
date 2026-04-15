package dk.easv.ticketseasv.bll;

import dk.easv.ticketseasv.be.*;
import dk.easv.ticketseasv.dal.DAOManager;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class EventLogic {

    private final DAOManager dao = new DAOManager();

    public EventLogic()
    {}

    public int createEvent(Event event) { return dao.getEventDAO().addEvent(event);
    }

    public void editEvent(Event event)
    {
        dao.getEventDAO().editEvent(event);
    }
    public Event getEventById(int id) {
        return dao.getEventDAO().getEventById(id);
    }

    public List<Event> getAllEvents() {
        return dao.getEventDAO().getAllEvents();
    }
}
