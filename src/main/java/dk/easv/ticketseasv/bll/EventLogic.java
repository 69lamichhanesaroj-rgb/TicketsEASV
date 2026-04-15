package dk.easv.ticketseasv.bll;

import dk.easv.ticketseasv.be.*;
import dk.easv.ticketseasv.dal.DAOManager;

import java.util.List;

public class EventLogic {

    private final DAOManager dao = new DAOManager();

    public int createEvent(Event event) {
        return dao.getEventDAO().addEvent(event);
    }

    public boolean editEvent(Event event) {
        return dao.getEventDAO().editEvent(event);
    }

    public boolean deleteEvent(Event event) {
        return dao.getEventDAO().deleteEvent(event);
    }
}
