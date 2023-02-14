package com.company.controllers;

import com.company.entities.Airplane;
import com.company.repositories.interfaces.IFlightRepository;

import java.sql.Time;
import java.util.List;

public class FlightController {
    private final IFlightRepository repo;

    public FlightController(IFlightRepository repo) {
        this.repo = repo;
    }


    public List<Airplane> displayFlights() {
        return repo.displayFlights();
    }

    public List<Airplane> displayFlights(String departurePoint, String destination) {
        return repo.displayFlights(departurePoint, destination);
    }

    public Airplane getFlight(String serialnumber) {
        return repo.getFlight(serialnumber);
    }
    public void addFlight(String name, String owner, String departurepoint, String destiantionpoint, Time departuretime, Time arrivaltime){
        repo.addFlight(name, owner, departurepoint, destiantionpoint, departuretime, arrivaltime);
    }
}
