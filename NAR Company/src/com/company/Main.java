package com.company;

import com.company.controllers.*;
import com.company.data.interfaces.IDB;
import com.company.data.PostgresDB;
import com.company.repositories.*;
import com.company.repositories.interfaces.*;


public class Main {
    public static void main(String[] args) {
        IDB db = new PostgresDB();
        IPilotRepository  repoPilot = new PilotRepository(db);
        PilotController pilotController = new PilotController(repoPilot);
        IClientRepository repoClient = new ClientRepository(db);
        ClientController clientController = new ClientController(repoClient);
        IFlightRepository repoFlight = new FlightRepository(db);
        FlightController flightController = new FlightController(repoFlight);
        IHotelRepository repoHotel = new HotelRepository(db);
        HotelController hotelController = new HotelController(repoHotel);
        ITicketRepository repoTicket = new TicketRepository(db);
        TicketController ticketController = new TicketController(repoTicket);
        MyApplication app = new MyApplication(clientController, pilotController, flightController, hotelController, ticketController);
        app.start();
    }
}

