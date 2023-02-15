package com.company.entities;

import java.sql.Time;

public class Ticket extends Airplane {
    private String name;
    private String surname;

    public Ticket() {
        super();
    }

    public Ticket(String name, String surname, String airplane, String owner, String destination, String departurepoint, Time departuretime, Time arrivaltime) {
        super(airplane, owner, departurepoint, destination, departuretime, arrivaltime);
        setName(name);
        setSurname(surname);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return name + " " + surname + ", your ticket: " + super.toString();
    }
}
