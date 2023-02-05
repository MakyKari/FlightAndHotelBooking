import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String connectionUrl = "jdbc:postgresql://localhost:5432/FlightsAndHotels";
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;

        try{
            Class.forName("org.postgresql.Driver");

            con = DriverManager.getConnection(connectionUrl,"postgres","123456789");

            stmt = con.createStatement();

            rs = stmt.executeQuery("select * from airplanes");

            while(rs.next()){
                System.out.println(rs.getTime("departureTime"));
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        finally {
            try {
                assert rs != null;
                rs.close();
                stmt.close();
                con.close();
            }
            catch (SQLException throwable){
                throwable.printStackTrace();
            }
        }
    }
}