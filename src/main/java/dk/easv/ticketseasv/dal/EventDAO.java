package dk.easv.ticketseasv.dal;

import dk.easv.ticketseasv.be.Event;

import java.sql.*;

public class EventDAO
{
    ConnectionManager conMan = new ConnectionManager();

    public int addEvent(String name, String description, String start, String end, String Date, String location)
    {
        try(Connection con = conMan.getConnection())
        {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO Events (name, description, start, end, date, location) VALUES (?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setString(3, start);
            stmt.setString(4, end);
            stmt.setString(5, Date);
            stmt.setString(6, location);
            stmt.executeUpdate();
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
            PreparedStatement stmt = con.prepareStatement("UPDATE Events SET name=?, description=?, start=?, end=?, date=?, location=? WHERE id=?");

            stmt.setString(1, event.getName());
            stmt.setString(2, event.getDescription());
            stmt.setString(3, event.getStarTime());
            stmt.setString(4, event.getEndTime());
            stmt.setString(5, event.getDate());
            stmt.setString(6, event.getLocation());
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.err.println("Error editing event: " + e.getMessage());
        }
    }
}
