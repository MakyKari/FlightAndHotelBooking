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
    public Client addClient(String firstName, String secondName, int age, String telephoneNumber, String password) {
        Connection con = null;
        Scanner scanner = new Scanner(System.in);
        try {
            con = db.getConnection();
            String sql = " insert into clients (firstName, secondName, telephoneNumber, age, password)" + " values (?, ?, ?, ?, ?)";
            String sql2 = "select id,firstname, secondname, telephonenumber, age, password from clients where telephonenumber = ?";

            PreparedStatement preparedStatement2 = con.prepareStatement(sql2);
            preparedStatement2.setString(1,telephoneNumber);
            ResultSet rs = preparedStatement2.executeQuery();

            if(rs.isBeforeFirst()){
                System.out.println("Sorry, clients with this telephone number is already exist\nDo you want to sign in by this telephone number? Yes/ No \n");
                String answer = scanner.next();
                while(!(answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("no"))){
                    System.out.println("Sorry, it is an unknown answer, please enter only (YES) or (NO)\n");
                    answer = scanner.next();
                }
                if(answer.equalsIgnoreCase("no")){
                    System.out.println("Soooo, enter new telephone number:)\n");
                    telephoneNumber = scanner.next();

                    preparedStatement2 = con.prepareStatement(sql2);
                    preparedStatement2.setString(1,telephoneNumber);
                    rs = preparedStatement2.executeQuery();
                }
                else if(answer.equalsIgnoreCase("yes")){
                    System.out.println("Please write down password to this telephone number\n");
                    return findClient(telephoneNumber, scanner.next());
                }
            }

            PreparedStatement preparedStmt = con.prepareStatement(sql);

            preparedStmt.setString(1, firstName);
            preparedStmt.setString(2, secondName);
            preparedStmt.setString(3, telephoneNumber);
            preparedStmt.setInt(4, age);
            preparedStmt.setString(5, password);
            preparedStmt.execute();

            return new Client(firstName, secondName,age, telephoneNumber, password);

        } catch (Exception e) {
            System.out.println(e + "Connection Error!");
        }
        return null;
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

            while(!rs.isBeforeFirst()){
                System.out.println("Sorry, this telephone number doesn't exist :_(\nPlease try again, enter your telephone number and password\n");
                preparedStmt.setString(1, scanner.next());
                password = scanner.next();
                rs = preparedStmt.executeQuery();
            }

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
