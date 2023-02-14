package com.company.repositories.interfaces;

import com.company.entities.Airplane;

import java.sql.Time;
import java.util.List;

public interface IFlightRepository {
    List<Airplane> displayFlights(String departurePoint, String destination);
    List<Airplane> displayFlights();
    Airplane getFlight(String serialnumber);

    void addFlight(String name, String owner, String departurepoint, String destiantionpoint, Time departuretime, Time arrivaltime);
}