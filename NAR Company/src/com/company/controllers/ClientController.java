package com.company.controllers;

import com.company.entities.Client;
import com.company.repositories.interfaces.IClientRepository;

public class ClientController {
    private final IClientRepository repo;

    public ClientController(IClientRepository repo) {
        this.repo = repo;
    }

    public Client addClient(String firstName, String secondName, int age, String telephoneNumber, String password) {
        return repo.addClient(firstName, secondName, age, telephoneNumber, password);
    }

    public Client findClient(String telephoneNumber, String password) {
        return repo.findClient(telephoneNumber, password);
    }
}
