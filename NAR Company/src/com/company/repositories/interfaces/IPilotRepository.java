package com.company.repositories.interfaces;
import com.company.entities.Pilot;

public interface IPilotRepository {
    void addPilot(String firstName, String secondName, int age, String telephoneNumber, int experience, boolean ill);
}
