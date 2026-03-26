package dk.easv.ticketseasv.dal;

public class DAOManager
{
        private final CoordinatorDAO coordinatorDAO;
        private final EventDAO eventDAO;
        private final TicketDAO ticketDAO;

        public DAOManager()
        {
            coordinatorDAO = new CoordinatorDAO();
            eventDAO = new EventDAO();
            ticketDAO = new TicketDAO();
        }

        public CoordinatorDAO getCoordinatorDAO()
        {
            return coordinatorDAO;
        }

        public EventDAO getEventDAO()
        {
            return eventDAO;
        }

        public TicketDAO getTicketDAO()
        {
            return ticketDAO;
        }
}
