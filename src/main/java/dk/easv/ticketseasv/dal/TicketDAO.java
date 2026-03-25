package dk.easv.ticketseasv.dal;

import dk.easv.ticketseasv.be.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {
    ConnectionManager conMan = new ConnectionManager();

    public List<Ticket> getTickets() {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection con = conMan.getConnection()) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM dbo.Ticket");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("barcode");
                int eventid = rs.getInt("eventid");


                tickets.add(new Ticket(id, name, eventid));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving tickets: " + e.getMessage());
        }
        return tickets;


    }
}
