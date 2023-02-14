package com.company.repositories.interfaces;

import com.company.entities.Airplane;

import java.util.List;

public interface IFlightRepository {
    List<Airplane> displayFlights(String departurePoint, String destination);
    List<Airplane> displayFlights();
    Airplane getFlight(String serialnumber);
}