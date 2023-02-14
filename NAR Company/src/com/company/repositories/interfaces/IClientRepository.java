package com.company.repositories.interfaces;

import com.company.entities.Client;

public interface IClientRepository {
    void addClient(Client client);
    Client findClient(String telephoneNumber, String password);
}
