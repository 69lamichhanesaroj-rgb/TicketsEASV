package dk.easv.ticketseasv.dal;

import dk.easv.ticketseasv.be.Event;

import java.sql.*;

public class EventDAO
{
    ConnectionManager conMan = new ConnectionManager();

    public int addEvent(String name, String description, String start_time, String end_time, String date, String location)
    {
        try(Connection con = conMan.getConnection())
        {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO Events (name, description, start_time, end_time, date, location) VALUES (?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setString(3, start_time);
            stmt.setString(4, end_time);
            stmt.setString(5, date);
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
            PreparedStatement stmt = con.prepareStatement("UPDATE Events SET name=?, description=?, start_time=?, end_time=?, date=?, location=? WHERE id=?");

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
