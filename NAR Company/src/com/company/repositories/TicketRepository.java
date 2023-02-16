package com.company.repositories;

import com.company.data.interfaces.IDB;
import com.company.entities.Ticket;
import com.company.repositories.interfaces.ITicketRepository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class TicketRepository implements ITicketRepository {
    private final IDB db;

    public TicketRepository(IDB db) {
        this.db = db;
    }

    @Override
    public void addTicket(Ticket ticket) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "insert into tickets (name, surname, airplane, owner, departurepoint, destination, departuretime, arrivaltime)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(sql);

            preparedStmt.setString(1, ticket.getName());
            preparedStmt.setString(2, ticket.getSurname());
            preparedStmt.setString(3, ticket.getAirplane());
            preparedStmt.setString(4, ticket.getOwner());
            preparedStmt.setString(5, ticket.getDestination());
            preparedStmt.setString(6, ticket.getDeparturePoint());
            preparedStmt.setTime(7, ticket.getDepartureTime());
            preparedStmt.setTime(8, ticket.getArrivalTime());
            preparedStmt.execute();
        } catch (Exception e) {
            System.out.println(e + "Connection Error!");
        }
    }

    @Override
    public List<Ticket> findTicket(String name, String surname) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT * FROM tickets where name = ? and surname = ?";
            PreparedStatement preparedStmt = con.prepareStatement(sql);

            preparedStmt.setString(1, name);
            preparedStmt.setString(2, surname);

            ResultSet rs = preparedStmt.executeQuery();
            List<Ticket> tickets = new LinkedList<>();

            while (rs.next()) {
                Ticket ticket = new Ticket(rs.getString("name"), rs.getString("surname"), rs.getString("airplane"),
                        rs.getString("owner"), rs.getString("destination"), rs.getString("departurePoint"),
                        rs.getTime("departureTime"), rs.getTime("arrivalTime"));

                tickets.add(ticket);
            }

            return tickets;
        } catch (Exception e) {
            System.out.println(e + "Connection Error!");
        }
        return null;
    }

    @Override
    public void deleteTicket(String name, String surname, String airplane) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "DELETE FROM tickets where name = ? and surname = ? and airplane = ?";
            PreparedStatement preparedStmt = con.prepareStatement(sql);

            preparedStmt.setString(1, name);
            preparedStmt.setString(2, surname);
            preparedStmt.setString(3, airplane);
            preparedStmt.execute();
        } catch (Exception e) {
            System.out.println(e + "Connection Error!");
        }
    }
}
