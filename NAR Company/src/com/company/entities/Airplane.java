package com.company.entities;

import java.sql.Time;

public class Airplane {
    private String airplane;
    private String owner;
    private String departurePoint;
    private String destination;
    private Time departureTime;
    private Time arrivalTime;

    public Airplane(){}
    public Airplane(String airplane, String owner, String  departurePoint, String destination, Time departureTime, Time arrivalTime) {
        setAirplane(airplane);
        setOwner(owner);
        setDestination(destination);
        setDeparturePoint(departurePoint);
        setDepartureTime(departureTime);
        setArrivalTime(arrivalTime);
    }

    public String getAirplane() {
        return airplane;
    }

    public void setAirplane(String name) {
        this.airplane = name;
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
        return airplane + ", who's owner is " + owner + ". From " + departurePoint + " to " + destination + ". At " + departureTime + " to " + arrivalTime;
    }
}
