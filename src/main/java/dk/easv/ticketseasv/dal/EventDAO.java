package dk.easv.ticketseasv.dal;

import dk.easv.ticketseasv.be.Event;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class EventDAO
{
    ConnectionManager conMan = new ConnectionManager();

    public int addEvent(String name, String description, LocalTime start, LocalTime end, LocalDate Date, String location)
    {
        try(Connection con = conMan.getConnection())
        {

        }
        catch (SQLException e)
        {
            System.err.println("Error adding event: " + e.getMessage());
            return 0;
        }
        return 0;
    }

    public void editEvent(Event event)
    {
        try(Connection con = conMan.getConnection())
        {

        }
        catch (SQLException e)
        {
            System.err.println("Error editing event: " + e.getMessage());
        }
    }
}
