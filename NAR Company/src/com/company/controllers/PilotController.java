package com.company.controllers;

import com.company.entities.Pilot;
import com.company.repositories.interfaces.IPilotRepository;
public class PilotController {
    private final IPilotRepository repo;
    public PilotController(IPilotRepository repo) {
        this.repo = repo;
    }
    public void addPilot(String firstName, String secondName, int age, String telephoneNumber, int experience, boolean ill){
        repo.addPilot(firstName, secondName, age, telephoneNumber, experience, ill);
    }
}
