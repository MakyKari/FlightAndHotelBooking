import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.*;
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Map<String, List<String>> hotels = readHotels();
        List<String> optionals = readOptionals();
        Map<String, String> ownerLocations = readOwnerLocations();
        try {
            fillDB(hotels, optionals, ownerLocations);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    static void fillDB(Map<String, List<String>> hotels, List<String> optionals, Map<String, String> ownerLocations) throws SQLException {
        // Create one big INSERT SQL query
        String query = "INSERT INTO hotels (name, location, starcount, isbreakfastincluded, optionalservices) VALUES ";
        // Concatenate all the values
        // Add data randomly from the maps
        // Just create query and do not execute it
        // Go through all locations and hotels in them
        for(List<String> hotelsInLocation : hotels.values()){
            // Go through all hotels in the location
            for(String hotelName : hotelsInLocation){
                // Add hotel name
                query += "('" + hotelName+"'";
                // Add location of the hotel
                // Search for the key of the hotel in the map
                query += ", '" + hotels.entrySet().stream().filter(entry -> entry.getValue().contains(hotelName)).findFirst().get().getKey() + "'";
                // Add star count
                query += ", '" + (int)(Math.random()*5+1)+"'";
                // Add isBreakfastIncluded
                query += ", '" + (Math.random() > 0.5)+"'";
                // Add random amount of optional services from the map
                {
                    // Create random amount of optionals
                    int amount = (int)(Math.random()*optionals.size());
                    // Create random list of optionals
                    List<String> randomOptionals = new ArrayList<>();
                    for(int i=0; i<amount; i++){
                        randomOptionals.add(optionals.get((int)(Math.random()*optionals.size())));
                    }
                    // Add optionals to the query

                    StringBuilder ans = new StringBuilder(randomOptionals.toString());
                    ans.deleteCharAt(0);
                    ans.deleteCharAt(ans.length() - 1);

                    query += ", '" + ans.toString() + "'";
                }
                // Add closing bracket
                query += "),\n";
            }
        }

        String airwaysQuery = "INSERT INTO airplanes (name, owner, destination, departurepoint, departuretime, arrivaltime) VALUES ";
        // Concatenate all the values
        // Add data randomly from the maps
        // Just create query and do not execute it
        // Create 30 airways with random data
        for(int i=0; i<500; i++){
            // Add name
            {
                // Create airway name by randomizing two letters adding '-', then adding random number from 100 to 999
                String name = "";
                for(int j=0; j<2; j++){
                    name += (char)((int)(Math.random()*26)+65);
                }
                name += "-";
                name += (int)(Math.random()*900+100);
                airwaysQuery += "('" + name+"'";
            }
            // Add from keyset of owner locations
            airwaysQuery += ", '" + ownerLocations.keySet().toArray()[(int)(Math.random()*ownerLocations.keySet().size())] + "'";
            // Add destination
            // Get random key from the map and add it to the query
            airwaysQuery += ", '" + hotels.keySet().toArray()[(int)(Math.random()*hotels.keySet().size())] + "'";
            // Add departure point
            airwaysQuery += ", '" + ownerLocations.values().toArray()[(int)(Math.random()*ownerLocations.keySet().size())] + "'";
            // Add departure time
            airwaysQuery += ", '" + (int)(Math.random()*24) + ":" + (int)(Math.random()*60)+ "'";
            // Add arrival time
            airwaysQuery += ", '" + (int)(Math.random()*24) + ":" + (int)(Math.random()*60)+ "'";
            // Add closing bracket
            airwaysQuery += "),\n";
        }
        System.out.println(query);
        System.out.println("\n\n\n\n\n");
        System.out.println(airwaysQuery);
    }
    static Map<String, List<String>> readHotels() throws FileNotFoundException {
        Map<String, List<String>> hotels = new HashMap<>();
        File file = new File("cities_hotels.txt");
        Scanner scanner = new Scanner(file);
        String hotelName = null;
        int i=0;
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            if(line.length()==0) continue;
            if(line.contains(":")){
                hotelName = line.substring(0, line.indexOf(":"));
                hotels.put(hotelName, new ArrayList<>());
            }
            else {
                hotels.get(hotelName).add(line);
            }
        }
        return hotels;
    }
    static List<String> readOptionals() throws FileNotFoundException {
        List<String> optionals = new ArrayList<>();
        File file = new File("optional.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.length() == 0) continue;
            optionals.add(line);
        }
        return optionals;
    }
    static Map<String, String> readOwnerLocations() throws FileNotFoundException {
        Map<String, String> ownerLocations = new HashMap<>();
        File file = new File("owner_location.txt");
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            if(line.length()==0) continue;
            String[] parts = line.split(" : ");
            ownerLocations.put(parts[0], parts[1]);
        }
        return ownerLocations;
    }
}
