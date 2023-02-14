package com.company.repositories.interfaces;

import com.company.entities.Hotel;


import java.util.List;

public interface IHotelRepository {
    List<Hotel> displayHotels(String location);
    Hotel getHotel(String name);
}
