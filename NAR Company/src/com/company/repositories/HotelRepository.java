package com.company.repositories;

import com.company.data.interfaces.IDB;
import com.company.entities.Hotel;
import com.company.repositories.interfaces.IHotelRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class HotelRepository implements IHotelRepository {
    private final IDB db;

    public HotelRepository(IDB db) {
        this.db = db;
    }

    @Override
    public List<Hotel> displayHotels(String location) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT * FROM hotels where location = ?";
            PreparedStatement preparedStmt = con.prepareStatement(sql);

            preparedStmt.setString(1, location);
            ResultSet rs = preparedStmt.executeQuery();
            List<Hotel> hotels = new LinkedList<>();
            while (rs.next()) {
                Hotel hotel = new Hotel(rs.getString("name"), rs.getString("location"), rs.getInt("starcount"),
                rs.getBoolean("isbreakfastincluded"), rs.getString("optionalservices"));

                hotels.add(hotel);
            }

            return hotels;
        } catch (Exception e) {
            System.out.println(e + "Connection Error!");
        }
        return null;
    }

    @Override
    public Hotel getHotel(String name) {
        Scanner scanner = new Scanner(System.in);
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT * FROM hotels WHERE name = ?";
            PreparedStatement preparedStmt = con.prepareStatement(sql);

            preparedStmt.setString(1, name);

            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {
                while (!rs.getString("name").equals(name)) {
                    System.out.println("Sorry, you entered incorrect name, try again!\n");
                    name = scanner.next();
                }

                return new Hotel(rs.getString("name"), rs.getString("location"), rs.getInt("starcount"),
                        rs.getBoolean("isbreakfastincluded"), rs.getString("optionalservices"));
            }
        } catch (Exception e) {
            System.out.println(e + "Connection Error!");
        }
        return null;
    }
}
