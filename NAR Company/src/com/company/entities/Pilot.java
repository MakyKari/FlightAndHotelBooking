package com.company.entities;

public class Pilot extends Human {
    private String experience;
    private int id;
    private String ill;
    private String telephonenumber;

    public Pilot(String experience) {
        this.experience = experience;
    }

    public Pilot(String firstName, String secondName, int age, String experience) {
        super(firstName, secondName, age);
        this.experience = experience;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getIll() {
        return ill;
    }

    public void setIll(String ill) {
        this.ill = ill;
    }

    public String getTelephonenumber() {
        return telephonenumber;
    }

    public void setTelephonenumber(String telephonenumber) {
        this.telephonenumber = telephonenumber;
    }
}
