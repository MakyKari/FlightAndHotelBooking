package com.company.entities;

public class Client extends Human {
    private String telephoneNumber;
    private String password;
    private boolean isAdmin;
    public Client(){}
    public Client(String firstName, String secondName, int age, String telephoneNumber, String password, boolean isAdmin){
        super(firstName,secondName,age);
        setTelephoneNumber(telephoneNumber);
        setPassword(password);
        this.isAdmin = isAdmin;
    }

    public Client(String firstName, String secondName, int age, String telephoneNumber, String password){
        super(firstName,secondName,age);
        setTelephoneNumber(telephoneNumber);
        setPassword(password);
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isAdmin(){return isAdmin;}
}