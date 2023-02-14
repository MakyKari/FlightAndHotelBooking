package com.company.repositories;

import com.company.data.interfaces.IDB;
import com.company.entities.Airplane;
import com.company.repositories.interfaces.IFlightRepository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FlightRepository implements IFlightRepository{
    private final IDB db;

    public FlightRepository(IDB db) {
        this.db = db;
    }


    @Override
    public List<Airplane> displayFlights(String departurePoint, String destination) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT * FROM airplanes where departurepoint = ? and destination = ?";
            PreparedStatement preparedStmt = con.prepareStatement(sql);

            preparedStmt.setString(1, departurePoint);
            preparedStmt.setString(2, destination);

            ResultSet rs = preparedStmt.executeQuery();
            List<Airplane> flights = new LinkedList<>();
            while (rs.next()) {
                Airplane airplane = new Airplane(rs.getString("name"), rs.getString("owner"), rs.getString("departurepoint"),
                        rs.getString("destination"), rs.getTime("departuretime"), rs.getTime("arrivaltime"));

                flights.add(airplane);
            }

            return flights;
        } catch (Exception e) {
            System.out.println(e + "Connection Error!");
        }
        return null;
    }

    @Override
    public List<Airplane> displayFlights() {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT * FROM airplanes";
            PreparedStatement preparedStmt = con.prepareStatement(sql);

            ResultSet rs = preparedStmt.executeQuery();
            List<Airplane> flights = new LinkedList<>();
            while (rs.next()) {
                Airplane airplane = new Airplane(rs.getString("name"), rs.getString("owner"), rs.getString("destination"),
                        rs.getString("departurepoint"), rs.getTime("departuretime"), rs.getTime("arrivaltime"));

                flights.add(airplane);
            }

            return flights;
        } catch (Exception e) {
            System.out.println(e + "Connection Error!");
        }
        return null;
    }

    @Override
    public Airplane getFlight(String serialnumber) {
        Scanner scanner = new Scanner(System.in);
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT * FROM airplanes WHERE name = ?";
            PreparedStatement preparedStmt = con.prepareStatement(sql);

            preparedStmt.setString(1, serialnumber);

            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {
                while (!rs.getString("name").equals(serialnumber)) {
                    System.out.println("Sorry, you entered incorrect serial number, try again!\n");
                    serialnumber = scanner.next();
                }

                return new Airplane(rs.getString("name"), rs.getString("owner"), rs.getString("departurepoint"),
                        rs.getString("destination"), rs.getTime("departuretime"), rs.getTime("arrivaltime"));
            }
        } catch (Exception e) {
            System.out.println(e + "Connection Error!");
        }
        return null;
    }
}
