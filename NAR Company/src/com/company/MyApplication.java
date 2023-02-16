package com.company;

import com.company.controllers.*;
import com.company.entities.Airplane;
import com.company.entities.Client;
import com.company.entities.Hotel;
import com.company.entities.Ticket;

import java.sql.Time;
import java.util.List;
import java.util.Scanner;

public class MyApplication {
    private final ClientController clientController;
    private final FlightController flightController;
    private final HotelController hotelController;
    private final TicketController ticketController;
    private final PilotController pilotController;
    private final Scanner scanner;

    public MyApplication(ClientController clientController, PilotController pilotController, FlightController flightController, HotelController hotelController, TicketController ticketController) {
        this.clientController = clientController;
        this.flightController = flightController;
        this.hotelController = hotelController;
        this.ticketController = ticketController;
        this.pilotController = pilotController;
        scanner = new Scanner(System.in);
    }

    public void start() {
        try {
            System.out.println("I welcome you to the AITUTRAVEL website!\nWe are pleased to provide you with the best service for finding cheap flights and hotels around the world!\n");
            System.out.println("Do you have account?" + "\nPlease enter Yes or No");
            String answer = scanner.next();

            Client client = new Client();

            checkYesOrNo(answer);

            if (answer.equalsIgnoreCase("no")) {
                client = addClient();
                System.out.println("Nice to meet you " + client.getSecondName() + " " + client.getFirstName() + "!" + "\n");
            } else if (answer.equalsIgnoreCase("yes")) {
                client = findClient();
                System.out.println("Nice to meet you!");

                if(client.isAdmin()){
                    System.out.println(client.getSecondName() + " " + client.getFirstName() + "," + " have a good working day!");
                    while(true) {
                        System.out.println("Do you have new flights? ");
                        answer = scanner.next();

                        checkYesOrNo(answer);

                        if (answer.equalsIgnoreCase("no")) {
                            System.out.println("Ok, maybe you have new pilots?\n");
                            answer = scanner.next();
                            checkYesOrNo(answer);

                            if (answer.equalsIgnoreCase("no")) {
                                System.out.println("Maybe you want to leave?");
                                answer = scanner.next();
                                checkYesOrNo(answer);
                                if (answer.equalsIgnoreCase("yes")) {
                                    System.out.println("Have a nice evening!");
                                    return;
                                }
                            }
                            else if (answer.equalsIgnoreCase("yes")) {
                                while(true) {
                                    addPilot();
                                    System.out.println("Pilot added!\nDo you want add another pilot?");
                                    answer = scanner.next();
                                    checkYesOrNo(answer);
                                    if(answer.equalsIgnoreCase("no")) break;
                                }
                            }
                        }
                        else if (answer.equalsIgnoreCase("yes")) {
                            while(true) {
                                addFlight();
                                System.out.println("Flight added!\nDo you want add another flight?");
                                answer = scanner.next();
                                checkYesOrNo(answer);
                                if(answer.equalsIgnoreCase("no")) break;
                            }
                        }
                    }
                }

                while(true) {
                    System.out.println("\n" + "1) Display/show me my tickets" + "\n" + "2) Please remove/cancel my tickets" + "\n" + "3) Purchase tickets for another flight" + "\n");
                    int respond = scanner.nextInt();
                    if (respond == 1) {
                        findTicket(client.getFirstName(), client.getSecondName());
                    } else if (respond == 2) {
                        System.out.println("Please enter it's serial number");
                        answer = scanner.next();
                        deleteTicket(client.getFirstName(), client.getSecondName(), answer);
                    } else if (respond == 3) {
                        break;
                    } else {
                        System.out.println("Please input a number");
                    }
                }
            }

            System.out.println("You can see all our flights: \n");
            displayFlights();

            System.out.println("\nChoose any flight that you want\nPlease write departure point and destination\n");
            displayFlightWithPara();

            System.out.println("\nWhich flight do you want to take?\nPlease enter it's serial number\n");

            Airplane airplane = getFlight();

            System.out.println("It is a nice choice! Welcome to the board to " + airplane.getAirplane() + " " + airplane.getDeparturePoint() + " --> " + airplane.getDestination() + "\n");

            System.out.println("Now let's choose hotel!\n");

            displayHotels(airplane.getDestination());

            System.out.println("\nWhich one of them do you want?\nPlease enter the name of hotel\n");

            Hotel hotel = getHotel();
            System.out.println(client.getFirstName() + ", i am sure, that you made the right choice! " + hotel.getName() + " is a nice hotel!\n");

            System.out.println("Let's make overall conclusion: \n" + "Your telephone number: " + client.getTelephoneNumber() + "\n" +
                    "You booked flight: " + airplane.toString() + "\n" + "You booked hotel: " + hotel.toString() + "\n");

            while(true) {
                System.out.println("Are you sure, that you want to take this travel? Yes/No \n");
                answer = scanner.next();
                checkYesOrNo(answer);
                if (answer.equalsIgnoreCase("yes")) {
                    addTicket(client.getFirstName(), client.getSecondName(), airplane.getAirplane(), airplane.getOwner(),
                            airplane.getDeparturePoint(), airplane.getDestination(), airplane.getDepartureTime(), airplane.getArrivalTime());

                    System.out.println(client.getSecondName() + " " + client.getFirstName() + ", have a nice trip!\n");
                    break;
                }
                else if (answer.equalsIgnoreCase("no")) {
                    while(true) {
                        System.out.println("Ok, what do you want to change?\n");
                        answer = scanner.next();
                        if (answer.equalsIgnoreCase("airplane")) {
                            System.out.println("You can see all our flights: \n");
                            displayFlights();
                            System.out.println("\nChoose any flight that you want\nPlease write departure point and destination");
                            displayFlightWithPara();
                            System.out.println("\nWhich flight do you want to take?\nPlease enter it's serial number");
                            airplane = getFlight();
                            break;
                        } else if (answer.equalsIgnoreCase("hotel")) {
                            displayHotels(airplane.getDestination());

                            System.out.println("\nWhich one of them do you want?\nPlease enter the name of hotel\n");

                            hotel = getHotel();

                            System.out.println(client.getFirstName() + ", i am sure, that you made the right choice! " + hotel.getName() + " is a nice hotel");
                            break;
                        } else {
                            System.out.println("Sorry unknown command, try again!");
                            answer = scanner.next();
                        }
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(e + "Connection Error!");
        }
    }

    public Client addClient() {
        System.out.println("Not a problem, let's create your account:" + "\nWhat is your first name?");
        String firstName = scanner.next();
        System.out.println("What is your second name?");
        String secondName = scanner.next();
        System.out.println("Please, share with us your telephone number");
        String telephoneNumber = scanner.next();
        System.out.println("What is your age?");
        int age = scanner.nextInt();
        System.out.println("Set your password");
        String password = scanner.next();

        return clientController.addClient(firstName, secondName, age, telephoneNumber, password);
    }

    public Client findClient() {
        System.out.println("Please write down your\nTelephone number and Password");
        String telephoneNumber = scanner.next();
        String password = scanner.next();
        return clientController.findClient(telephoneNumber, password);
    }

    public void displayFlights() {
        List<Airplane> response = flightController.displayFlights();
        printData1(response);
    }

    public void displayFlightWithPara() {
        String departurePoint = scanner.next();
        String destination = scanner.next();

        List<Airplane> response = flightController.displayFlights(departurePoint, destination);
        printData1(response);
    }

    public Airplane getFlight() {
        String serialnumber = scanner.next();
        return flightController.getFlight(serialnumber);
    }


    public static void printData1(List<Airplane> airplanes) {
        for (Airplane airplane : airplanes) {
            System.out.println("* " + airplane.toString());
        }
    }

    public void displayHotels(String location) {
        List<Hotel> response = hotelController.displayHotels(location);
        printData2(response);
    }

    public Hotel getHotel() {
        String name = scanner.next();
        return hotelController.getHotel(name);
    }

    public static void printData2(List<Hotel> hotels) {
        for (Hotel hotel : hotels) {
            System.out.println("* Hotel: " + hotel.toString());
        }
    }

    public void addTicket(String name, String surname, String airplane, String owner, String  departurePoint, String destination, Time departureTime, Time arrivalTime) {
        ticketController.addTicket(name, surname, airplane, owner, departurePoint, destination, departureTime, arrivalTime);
    }

    public void findTicket(String name, String surname) {
        List<Ticket> response = ticketController.findTicket(name, surname);
        printData3(response);
    }

    public static void printData3(List<Ticket> tickets) {
        if (tickets.isEmpty()) {
            System.out.println("You still haven't bought a ticket");
        }
        else {
            for (Ticket ticket : tickets) {
                System.out.println(ticket.toString());
            }
        }
    }

    public void deleteTicket(String name, String surname, String airplane) {
        ticketController.deleteTicket(name, surname, airplane);
    }

    public void addFlight(){
        System.out.println("What is serial number of airplane?\n");
        String name = scanner.next();
        System.out.println("Who is the owner?");
        String owner = scanner.next();
        System.out.println("What is departure point?");
        String departurepoint = scanner.next();
        System.out.println("What is destiantion point?");
        String destiantionpoint = scanner.next();
        System.out.println("What is departure time?");
        Time departuretime = Time.valueOf(scanner.next());
        System.out.println("What is arrival time?");
        Time arrivaltime = Time.valueOf(scanner.next());

        flightController.addFlight(name, owner, departurepoint, destiantionpoint, departuretime, arrivaltime);
    }

    public void addPilot(){
        System.out.println("What is pilot's first name?");
        String firstName = scanner.next();
        System.out.println("What is pilot's second name?");
        String secondName = scanner.next();
        System.out.println("What is pilot's telephone number?");
        String telephoneNumber = scanner.next();
        System.out.println("What is pilot's age?");
        int age = scanner.nextInt();
        System.out.println("What is pilot's experience in years?");
        int experience = scanner.nextInt();
        System.out.println("Does pilot have any diseases? true/false");
        boolean ill = scanner.nextBoolean();

        pilotController.addPilot(firstName,secondName,age,telephoneNumber, experience, ill);
    }
    public String checkYesOrNo(String ans){
        while(!(ans.equalsIgnoreCase("yes") || ans.equalsIgnoreCase("no"))){
            System.out.println("Sorry, it is an unknown answer, please enter only (YES) or (NO)\n");
            ans = scanner.next();
        }
        return ans;
    }
}
