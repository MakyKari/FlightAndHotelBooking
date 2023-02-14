package com.company.controllers;

import com.company.entities.Client;
import com.company.repositories.interfaces.IClientRepository;

public class ClientController {
    private final IClientRepository repo;

    public ClientController(IClientRepository repo) {
        this.repo = repo;
    }

    public Client addClient(String firstName, String secondName, int age, String telephoneNumber, String password) {
        Client client = new Client(firstName, secondName, age, telephoneNumber, password);
        repo.addClient(client);
        return client;
    }

    public Client findClient(String telephoneNumber, String password) {
        return repo.findClient(telephoneNumber, password);
    }
}
