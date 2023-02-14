package com.company.repositories;

import com.company.data.interfaces.IDB;
import com.company.entities.Client;
import com.company.repositories.interfaces.IClientRepository;

import java.sql.*;
import java.util.Scanner;

public class ClientRepository implements IClientRepository {
    private final IDB db;

    public ClientRepository(IDB db) {
        this.db = db;
    }

    @Override
    public void addClient(Client client) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = " insert into clients (firstName, secondName, telephoneNumber, age, password)" + " values (?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(sql);

            preparedStmt.setString(1, client.getFirstName());
            preparedStmt.setString(2, client.getSecondName());
            preparedStmt.setString(3, client.getTelephoneNumber());
            preparedStmt.setInt(4, client.getAge());
            preparedStmt.setString(5, client.getPassword());
            preparedStmt.execute();
        } catch (Exception e) {
            System.out.println(e + "Connection Error!");
        }
    }

    @Override
    public Client findClient(String telephoneNumber, String password) {
        Scanner scanner = new Scanner(System.in);
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "select id,firstname, secondname, telephonenumber, age, password " + "from clients " + "where telephonenumber = ?";
            PreparedStatement preparedStmt = con.prepareStatement(sql);

            preparedStmt.setString(1, telephoneNumber);
            ResultSet rs = preparedStmt.executeQuery();

            while (rs.next()) {
                while (!rs.getString("password").equals(password)) {
                    System.out.println("Wrong password! Please try again");
                    password = scanner.next();
                }

                return new Client(rs.getString("firstname"), rs.getString("secondname"),
                        rs.getInt("age"), rs.getString("telephonenumber"), rs.getString("password"));
            }
        } catch (Exception e) {
            System.out.println(e + "Connection Error!");
        }
        return null;
    }
}
