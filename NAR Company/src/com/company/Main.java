package com.company;

import com.company.controllers.ClientController;
import com.company.controllers.FlightController;
import com.company.controllers.HotelController;
import com.company.controllers.PilotController;
import com.company.data.interfaces.IDB;
import com.company.data.PostgresDB;
import com.company.repositories.ClientRepository;
import com.company.repositories.FlightRepository;
import com.company.repositories.HotelRepository;
import com.company.repositories.PilotRepository;
import com.company.repositories.interfaces.IClientRepository;
import com.company.repositories.interfaces.IFlightRepository;
import com.company.repositories.interfaces.IHotelRepository;
import com.company.repositories.interfaces.IPilotRepository;


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
        MyApplication app = new MyApplication(clientController, pilotController, flightController, hotelController);
        app.start();
    }
}

