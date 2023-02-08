import java.sql.Time;

public class Airplane {
    private String name;
    private String owner;
    private String departurePoint;
    private String destination;
    private Time departureTime;
    private Time arrivalTime;

    public Airplane(){}
    public Airplane(String name, String owner, String  departurePoint, String destination, Time departureTime, Time arrivalTime) {
        this.name = name;
        this.owner = owner;
        this.destination = destination;
        this.departurePoint = departurePoint;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDeparturePoint() {
        return departurePoint;
    }

    public void setDeparturePoint(String departurePoint) {
        this.departurePoint = departurePoint;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return name + " , who's owner is " + owner + "\nFrom " + departurePoint + " to " + destination + "\nAt " + departureTime + " to " + arrivalTime + "\n";
    }
}
