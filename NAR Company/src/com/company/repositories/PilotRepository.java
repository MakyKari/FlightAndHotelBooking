package com.company.repositories;

import com.company.data.interfaces.IDB;
import com.company.entities.Pilot;
import com.company.repositories.interfaces.IPilotRepository;

import java.sql.*;
import java.util.Scanner;

public class PilotRepository implements IPilotRepository{
    private final IDB db;

    public PilotRepository(IDB db) {
        this.db = db;
    }
    @Override
    public void addPilot(String firstName, String secondName, int age, String telephoneNumber, int experience, boolean ill) {
        Connection con = null;
        Scanner scanner = new Scanner(System.in);
        try {
            con = db.getConnection();
            String sql = " insert into pilots (firstName, secondName, telephoneNumber, age, experience, ill)" + " values (?, ?, ?, ?, ?, ?)";
            String sql2 = "select firstName, secondName, telephoneNumber, age, experience, ill from pilots where telephonenumber = ?";

            PreparedStatement preparedStatement2 = con.prepareStatement(sql2);
            preparedStatement2.setString(1,telephoneNumber);
            ResultSet rs = preparedStatement2.executeQuery();

            while(rs.isBeforeFirst()){
                System.out.println("Sorry, pilot with this telephone number is already exist\n Please enter new telephone number\n");
                telephoneNumber = scanner.next();

                preparedStatement2 = con.prepareStatement(sql2);
                preparedStatement2.setString(1,telephoneNumber);
                rs = preparedStatement2.executeQuery();
            }

            PreparedStatement preparedStmt = con.prepareStatement(sql);

            preparedStmt.setString(1, firstName);
            preparedStmt.setString(2, secondName);
            preparedStmt.setString(3, telephoneNumber);
            preparedStmt.setInt(4, age);
            preparedStmt.setInt(5, experience);
            preparedStmt.setBoolean(6, ill);
            preparedStmt.execute();

        } catch (Exception e) {
            System.out.println(e + "Connection Error!");
        }
    }
}
