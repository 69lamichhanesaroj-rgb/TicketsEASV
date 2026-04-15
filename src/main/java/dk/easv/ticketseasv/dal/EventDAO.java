package dk.easv.ticketseasv.dal;

import dk.easv.ticketseasv.be.Event;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDAO
{
    private final ConnectionManager conMan = new ConnectionManager();

    public int addEvent(Event event)
    {
        String sql = "INSERT INTO Events (name, description, start_time, end_time, date, Location) " + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = conMan.getConnection()) {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO Events (name, description, start_time, end_time, date, location) VALUES (?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, event.getName());
            stmt.setString(2, event.getDescription());
            stmt.setString(3, event.getStarTime());
            stmt.setString(4, event.getEndTime());
            stmt.setString(5, event.getDate());
            stmt.setString(6, event.getLocation());

            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys())
            {
                if (keys.next())
                {
                    return keys.getInt(1);
                }
            }
        }
        catch (SQLException e)
        {
            System.err.println("Error adding event: " + e.getMessage());
            return 0;
        }
        return 0;
    }

    public boolean editEvent(Event event)
    {
        try(Connection con = conMan.getConnection())
        {
            PreparedStatement stmt = con.prepareStatement("UPDATE Events SET name=?, date=?, start_time=?, end_time=?, " + "location=?, description=? WHERE id=?");

            stmt.setString(1, event.getName());
            stmt.setString(2, event.getDescription());
            stmt.setString(3, event.getStarTime());
            stmt.setString(4, event.getEndTime());
            stmt.setString(5, event.getDate());
            stmt.setString(6, event.getLocation());
            stmt.setInt(7, event.getId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        }
        catch (SQLException e)
        {
            System.err.println("Error editing event: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteEvent(Event event)
    {
        String sql = "DELETE FROM Events WHERE id=?";

        try (Connection con = conMan.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql))
        {

            stmt.setInt(1, event.getId());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        }
        catch (SQLException e)
        {
            System.err.println("Error deleting event: " + e.getMessage());
            return false;
        }
    }

    public List<Event> getAllEvents()
    {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM Events";

        try (Connection con = conMan.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Event event = new Event(
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("start_time"),
                        rs.getString("end_time"),
                        rs.getString("date"),
                        rs.getString("location"),
                        rs.getInt("id"));
                events.add(event);
            }

        }
        catch (SQLException e)
        {
            System.err.println("Error fetching events: " + e.getMessage());
        }

        return events;
    }
}
