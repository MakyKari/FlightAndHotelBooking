import java.sql.*;
import java.util.Scanner;

public class Main {
    public static class Global {
        public static String connectionUrl = "jdbc:postgresql://localhost:5432/FlightsAndHotels";
        public static Scanner scanner = new Scanner(System.in);
    }
    public static void main(String[] args) {
        Connection con = null;
        Statement stmt = null;

        try{
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(Global.connectionUrl,"postgres","123456789");
            stmt = con.createStatement();

            Client client = new Client();


            System.out.println("Hello, welcome back! Do you have account?" +
                                "\nPlease enter Yes or No");
            String answer = Global.scanner.next();

            if(answer.equals("NO") || answer.equals("nO") || answer.equals("No") || answer.equals("no")){
                System.out.println("Not a problem, let's create your account:" + "\n What is your first name?");
                addClient(client);
            }
            else if(answer.equals("YES") || answer.equals("yes") || answer.equals("Yes") || answer.equals("YeS")){
                System.out.println("Please write down your\nTelephone number and Password");
                String telephoneNumber = Global.scanner.next();
                String password = Global.scanner.next();
                client = findClient(telephoneNumber, password);
            }

            System.out.println("Nice to meet you " + client.getSecondName() + " " + client.getFirstName() + "!");
        }
        catch (Exception e){
            System.out.println(e + "Connection Error!");
        }
        finally {
            try {
                assert stmt != null;
                stmt.close();
                con.close();
            }
            catch (SQLException throwable){
                throwable.printStackTrace();
            }
        }
    }

    public static void addClient(Client client){
        Scanner scanner = new Scanner(System.in);

        String sql = " insert into clients (firstName, secondName, telephoneNumber, age, password)"
                + " values (?, ?, ?, ?, ?)";

        try(Connection con = DriverManager.getConnection(Global.connectionUrl,"postgres","123456789");
            PreparedStatement preparedStmt = con.prepareStatement(sql)) {

            String firstName, secondName, telephoneNumber, password;
            int age;

            firstName = scanner.next();
            System.out.println("What is your second name?");
            secondName = scanner.next();
            System.out.println("Please, share with us your telephone number");
            telephoneNumber = scanner.next();
            System.out.println("What is your age?");
            age = scanner.nextInt();
            System.out.println("Set your password");
            password = scanner.next();

            preparedStmt.setString(1, firstName);
            preparedStmt.setString(2, secondName);
            preparedStmt.setString(3, telephoneNumber);
            preparedStmt.setInt(4, age);
            preparedStmt.setString(5, password);
            preparedStmt.execute();

            client.setFirstName(firstName);
            client.setSecondName(secondName);
            client.setTelephoneNumber(telephoneNumber);
            client.setAge(age);
            client.setPassword(password);
        }
        catch (Exception e){
            System.out.println(e + "Connection Error!");
        }
    }
    public static Client findClient(String telephoneNumber, String password){
        Client client = new Client();
        String sql= "select id,firstname, secondname, telephonenumber, age, password "
                + "from clients "
                + "where telephonenumber = ?";

        try(Connection con = DriverManager.getConnection(Global.connectionUrl,"postgres","123456789");
        PreparedStatement preparedStmt = con.prepareStatement(sql)){

            preparedStmt.setString(1,telephoneNumber);
            ResultSet rs = preparedStmt.executeQuery();

            while(rs.next()){
                while(!rs.getString("password").equals(password)){
                    System.out.println("Wrong password! Please try again");
                    password = Global.scanner.next();
                }

                client = new Client(rs.getInt("id"),rs.getString("firstname"), rs.getString("secondname"),
                            rs.getInt("age"),rs.getString("telephonenumber") , rs.getString("password"));

            }
        }
        catch (Exception e){
            System.out.println(e + "Connection Error!");
        }

        return client;
    }
}