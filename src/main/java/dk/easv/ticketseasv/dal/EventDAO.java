package dk.easv.ticketseasv.dal;

import dk.easv.ticketseasv.be.Event;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDAO
{
    ConnectionManager conMan = new ConnectionManager();

    public int addEvent(Event event)
    {
        try(Connection con = conMan.getConnection())
        {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO Events (name, description, start_time, end_time, date, location) VALUES (?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
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

    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();

        try (Connection con = conMan.getConnection()) {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Events");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Event event = new Event(
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("date")
                );

                // do we need more or less stuff displayed on the homepage?
                // Feel free to change it if needed

                events.add(event);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching events: " + e.getMessage());
        }

        return events;
    }

    public void deleteEvent(Event event)
    {
        try (Connection con = conMan.getConnection())
        {
            PreparedStatement stmt = con.prepareStatement("DELETE Events WHERE id = ?");
            stmt.setInt(1, event.getId());
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.err.println("Error deleting event: " + e.getMessage());
        }
    }
}
