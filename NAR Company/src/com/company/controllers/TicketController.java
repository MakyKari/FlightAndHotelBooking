package com.company.controllers;

import com.company.entities.Ticket;
import com.company.repositories.interfaces.ITicketRepository;

import java.sql.Time;
import java.util.List;

public class TicketController {
    private final ITicketRepository repo;

    public TicketController(ITicketRepository repo) {
        this.repo = repo;
    }

    public void addTicket(String name, String surname, String airplane, String owner, String  departurePoint, String destination, Time departureTime, Time arrivalTime) {
        Ticket ticket = new Ticket(name, surname, airplane, owner, destination, departurePoint, departureTime, arrivalTime);
        repo.addTicket(ticket);
    }

    public List<Ticket> findTicket(String name, String surname) {
        return repo.findTicket(name, surname);
    }

    public void deleteTicket(String name, String surname, String airplane) {
        repo.deleteTicket(name, surname, airplane);
    }
}
