package dk.easv.ticketseasv.dal;

public class DAOManager
{
        private final CoordinatorDAO coordinatorDAO;
        private final EventDAO eventDAO;
        private final TicketDAO ticketDAO;
        private final UsersDAO usersDAO;

        public DAOManager()
        {
            coordinatorDAO = new CoordinatorDAO();
            eventDAO = new EventDAO();
            ticketDAO = new TicketDAO();
            usersDAO = new UsersDAO();
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

        public UsersDAO getUsersDAO()
        {
            return usersDAO;
        }
}
