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

        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(Global.connectionUrl, "postgres", "123456789");
            stmt = con.createStatement();

            Client client = new Client();
            Airplane airplane = new Airplane();
            Hotel hotel = new Hotel();

            System.out.println("I welcome you to the AITUTRAVEL website!\nWe are pleased to provide you with the best service for finding cheap flights and hotels around the world!\n");

            System.out.println("Do you have account?" +
                    "\nPlease enter Yes or No");
            String answer = Global.scanner.next();

            if (answer.equals("NO") || answer.equals("nO") || answer.equals("No") || answer.equals("no")) {
                System.out.println("Not a problem, let's create your account:" + "\nWhat is your first name?");
                addClient(client);
            } else if (answer.equals("YES") || answer.equals("yes") || answer.equals("Yes") || answer.equals("YeS")) {
                System.out.println("Please write down your\nTelephone number and Password");
                String telephoneNumber = Global.scanner.next();
                String password = Global.scanner.next();
                client = findClient(telephoneNumber, password);
            }

            System.out.println("Nice to meet you " + client.getSecondName() + " " + client.getFirstName() + "!" + "\n");
            System.out.println("You can see all our flights: \n");

            displayFlights();

            System.out.println("\nChoose any flight that you want\nPlease write departure point and destination");

            displayFlights(Global.scanner.next(), Global.scanner.next());

            System.out.println("Which flight do you want to take?\nPlease enter it's serial number");

            airplane = getFlight(Global.scanner.next());

            System.out.println("It is a nice choice! Welcome on the board to " + airplane.getName() + " " + airplane.getDeparturePoint() + " --> " + airplane.getDestination() + "\n" +
                    "Now let's choose hotel!\n");

            displayHotels(airplane.getDestination());

            System.out.println("Which one of them do you want?\nPlease enter the name of hotel\n");

            hotel = getHotel(Global.scanner.next());

            System.out.println(client.getFirstName() + " " + ", i am sure, that you made the right choice! " + hotel.getName() + " is a nice hotel");

            System.out.println("Let's make overall conclusion: \n" +
                                "Your telephone number: " + client.getTelephoneNumber() + "\n" +
                                "You booked flight: " + airplane.toString() + "\n"+
                                "You booked hotel: " + hotel.toString() + "\n");

            while(true) {
                System.out.println("Are you sure, that you want to take this travel? Yes/No \n");
                answer = Global.scanner.next();
                if (answer.equalsIgnoreCase("yes")) {
                    System.out.println(client.getSecondName() + " " + client.getFirstName() + " have a nice trip!\n");
                    break;
                }
                else if (answer.equalsIgnoreCase("no")) {
                    while(true) {
                        System.out.println("Ok, what you want to change?\n");
                        answer = Global.scanner.next();
                        if (answer.equalsIgnoreCase("airplane")) {
                            System.out.println("You can see all our flights: \n");
                            displayFlights();
                            System.out.println("\nChoose any flight that you want\nPlease write departure point and destination");
                            displayFlights(Global.scanner.next(), Global.scanner.next());
                            System.out.println("Which flight do you want to take?\nPlease enter it's serial number");
                            airplane = getFlight(Global.scanner.next());
                            break;
                        } else if (answer.equalsIgnoreCase("hotel")) {
                            displayHotels(airplane.getDestination());

                            System.out.println("Which one of them do you want?\nPlease enter the name of hotel\n");

                            hotel = getHotel(Global.scanner.next());

                            System.out.println(client.getFirstName() + " " + ", i am sure, that you made the right choice! " + hotel.getName() + " is a nice hotel");
                            break;
                        } else {
                            System.out.println("Sorry unknown command, try again!");
                            answer = Global.scanner.next();
                        }
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(e + "Connection Error!");
        } finally {
            try {
                assert stmt != null;
                stmt.close();
                con.close();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
    }

    public static void addClient(Client client) {
        String sql = " insert into clients (firstName, secondName, telephoneNumber, age, password)"
                + " values (?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(Global.connectionUrl, "postgres", "123456789");
             PreparedStatement preparedStmt = con.prepareStatement(sql)) {

            String firstName, secondName, telephoneNumber, password;
            int age;

            firstName = Global.scanner.next();
            System.out.println("What is your second name?");
            secondName = Global.scanner.next();
            System.out.println("Please, share with us your telephone number");
            telephoneNumber = Global.scanner.next();
            System.out.println("What is your age?");
            age = Global.scanner.nextInt();
            System.out.println("Set your password");
            password = Global.scanner.next();

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
        } catch (Exception e) {
            System.out.println(e + "Connection Error!");
        }
    }

    public static Client findClient(String telephoneNumber, String password) {
        Client client = new Client();
        String sql = "select id,firstname, secondname, telephonenumber, age, password "
                + "from clients "
                + "where telephonenumber = ?";

        try (Connection con = DriverManager.getConnection(Global.connectionUrl, "postgres", "123456789");
             PreparedStatement preparedStmt = con.prepareStatement(sql)) {

            preparedStmt.setString(1, telephoneNumber);
            ResultSet rs = preparedStmt.executeQuery();

            while (rs.next()) {
                while (!rs.getString("password").equals(password)) {
                    System.out.println("Wrong password! Please try again");
                    password = Global.scanner.next();
                }

                client = new Client(rs.getInt("id"), rs.getString("firstname"), rs.getString("secondname"),
                        rs.getInt("age"), rs.getString("telephonenumber"), rs.getString("password"));

            }
        } catch (Exception e) {
            System.out.println(e + "Connection Error!");
        }

        return client;
    }

    public static void displayFlights() {
        String sql = "select * from airplanes";
        try (Connection con = DriverManager.getConnection(Global.connectionUrl, "postgres", "123456789");
             PreparedStatement preparedStmt = con.prepareStatement(sql)) {
            ResultSet rs = preparedStmt.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("name") + " " + rs.getString("owner") + " " + rs.getString("departurepoint") + " --> " +
                        rs.getString("destination") + " " + rs.getTime("departuretime") + " " + rs.getTime("arrivaltime"));
            }
        } catch (Exception e) {
            System.out.println(e + "Connection Error!");
        }
    }

    public static void displayFlights(String departurePoint, String destination) {
        String sql = "select name, owner, destination, departurepoint, departuretime, arrivaltime " +
                "from airplanes " +
                "where departurepoint = ? and destination = ?";

        try (Connection con = DriverManager.getConnection(Global.connectionUrl, "postgres", "123456789");
             PreparedStatement preparedStmt = con.prepareStatement(sql)) {

            preparedStmt.setString(1, departurePoint);
            preparedStmt.setString(2, destination);

            ResultSet rs = preparedStmt.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("name") + " " + rs.getString("owner") + " " + rs.getString("departurepoint") + " --> " +
                        rs.getString("destination") + " " + rs.getTime("departuretime") + " " + rs.getTime("arrivaltime"));
            }
        } catch (Exception e) {
            System.out.println(e + "Connection Error!");
        }
    }

    public static Airplane getFlight(String serialnumber) {
        String sql = "select name, owner, destination, departurepoint, departuretime, arrivaltime " +
                "from airplanes " +
                "where name = ?";

        try (Connection con = DriverManager.getConnection(Global.connectionUrl, "postgres", "123456789");
             PreparedStatement preparedStmt = con.prepareStatement(sql)) {

            preparedStmt.setString(1, serialnumber);

            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {
                while (!rs.getString("name").equals(serialnumber)) {
                    System.out.println("Sorry, you entered incorrect serial number, try again!\n");
                    serialnumber = Global.scanner.next();
                }

                return new Airplane(rs.getString("name"), rs.getString("owner"), rs.getString("departurepoint"),
                        rs.getString("destination"), rs.getTime("departuretime"), rs.getTime("arrivaltime"));
            }
        } catch (Exception e) {
            System.out.println(e + "Connection Error!");
        }

        return new Airplane();
    }

    public static void displayHotels(String location) {
        String sql = "select name, location, starcount, isbreakfastincluded, optionalservices " +
                "from hotels " +
                "where location = ?";

        try (Connection con = DriverManager.getConnection(Global.connectionUrl, "postgres", "123456789");
             PreparedStatement preparedStmt = con.prepareStatement(sql)) {

            preparedStmt.setString(1, location);
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {
                System.out.println("Hotel: " + rs.getString("name") + "\nLocation: " + rs.getString("location") + " ");
                for (int i = 0; i < rs.getInt("starcount"); i++) {
                    System.out.print("★");
                }
                if (rs.getBoolean("isbreakfastincluded")) System.out.println("\nBreakfast included ");
                else System.out.println("\nBreakfast doesn't included");
                System.out.println(rs.getString("optionalservices") + "\n");
            }
        } catch (Exception e) {
            System.out.println(e + "Connection Error!");
        }
    }

    public static Hotel getHotel(String name) {
        String sql = "select name, location, starcount, isbreakfastincluded, optionalservices " +
                "from hotels " +
                "where name = ?";

        try (Connection con = DriverManager.getConnection(Global.connectionUrl, "postgres", "123456789");
             PreparedStatement preparedStmt = con.prepareStatement(sql)) {

            preparedStmt.setString(1, name);

            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {
                while (!rs.getString("name").equals(name)) {
                    System.out.println("Sorry, you entered incorrect name, try again!\n");
                    name = Global.scanner.next();
                }

                return new Hotel(rs.getString("name"), rs.getString("location"), rs.getInt("starcount"),
                        rs.getBoolean("isbreakfastincluded"), rs.getString("optionalservices"));
            }
        } catch (Exception e) {
            System.out.println(e + "Connection Error!");
        }

        return new Hotel();
    }
}