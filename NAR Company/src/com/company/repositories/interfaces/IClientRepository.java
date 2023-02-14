package com.company.repositories.interfaces;

import com.company.entities.Client;

public interface IClientRepository {
    Client addClient(String firstName, String secondName, int age, String telephoneNumber, String password);
    Client findClient(String telephoneNumber, String password);
}
