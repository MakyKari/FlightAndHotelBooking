package com.company.entities;

public class Hotel {
    private String name;
    private String location;
    private int starCount;
    private boolean isBreakfastIncluded;
    private String optionalServices;

    public Hotel(){}

    public Hotel(String name, String location, int starCount, boolean isBreakfastIncluded, String optionalServices) {
        setName(name);
        setLocation(location);
        setStarCount(starCount);
        setBreakfastIncluded(isBreakfastIncluded);
        setOptionalServices(optionalServices);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getStarCount() {
        return starCount;
    }

    public void setStarCount(int starCount) {
        this.starCount = starCount;
    }

    public boolean isBreakfastIncluded() {
        return isBreakfastIncluded;
    }

    public void setBreakfastIncluded(boolean breakfastIncluded) {
        isBreakfastIncluded = breakfastIncluded;
    }

    public String getOptionalServices() {
        return optionalServices;
    }

    public void setOptionalServices(String optionalServices) {
        this.optionalServices = optionalServices;
    }

    @Override
    public String toString() {
        String answer = name + " in the city " + location + ". With " + starCount + " stars, ";
        if(isBreakfastIncluded) answer += "with breakfast ";
        else answer += "without breakfast ";

        return answer;
    }
}
