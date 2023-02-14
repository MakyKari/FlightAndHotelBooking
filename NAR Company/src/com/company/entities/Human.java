package com.company.entities;

public abstract class Human {
    private String firstName;
    private String secondName;
    private int age;
    private int id;

    public Human(){}
    public Human(String firstName, String secondName, int age){
        setFirstName(firstName);
        setSecondName(secondName);
        setAge(age);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String surname) {
        this.secondName = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}