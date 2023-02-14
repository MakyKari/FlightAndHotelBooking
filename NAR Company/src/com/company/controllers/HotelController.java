package com.company.controllers;

import com.company.entities.Hotel;
import com.company.repositories.interfaces.IHotelRepository;

import java.util.List;

public class HotelController {
    private final IHotelRepository repo;

    public HotelController(IHotelRepository repo) {
        this.repo = repo;
    }

    public List<Hotel> displayHotels(String location) {
        return repo.displayHotels(location);
    }

    public Hotel getHotel(String name) {
        return repo.getHotel(name);
    }
}