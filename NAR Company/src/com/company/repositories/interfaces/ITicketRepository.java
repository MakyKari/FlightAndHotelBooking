package com.company.repositories.interfaces;

import com.company.entities.Ticket;

import java.util.List;

public interface ITicketRepository {
    void addTicket(Ticket ticket);
    List<Ticket> findTicket(String name, String surname);
    void deleteTicket(String name, String surname, String airplane);
}
