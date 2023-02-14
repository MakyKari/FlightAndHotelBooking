package com.company;

import com.company.controllers.ClientController;
import com.company.controllers.FlightController;
import com.company.controllers.HotelController;
import com.company.entities.Airplane;
import com.company.entities.Client;
import com.company.entities.Hotel;

import java.util.List;
import java.util.Scanner;

public class MyApplication {
    private final ClientController clientController;
    private final FlightController flightController;
    private final HotelController hotelController;
    private final Scanner scanner;

    public MyApplication(ClientController clientController, FlightController flightController, HotelController hotelController) {
        this.clientController = clientController;
        this.flightController = flightController;
        this.hotelController = hotelController;
        scanner = new Scanner(System.in);
    }

    public void start() {
        try {
            System.out.println("I welcome you to the AITUTRAVEL website!\nWe are pleased to provide you with the best service for finding cheap flights and hotels around the world!\n");
            System.out.println("Do you have account?" + "\nPlease enter Yes or No");
            String answer = scanner.next();

            Client client = new Client();

            while(!(answer.equalsIgnoreCase("no") || answer.equalsIgnoreCase("yes"))){
                System.out.println("Sorry, it is an unknown answer, please enter only (YES) or (NO)\n");
                answer = scanner.next();
            }

            if (answer.equalsIgnoreCase("no")) {
                client = addClient();
            } else if (answer.equalsIgnoreCase("yes")) {
                client = findClient();
            }
            System.out.println("Nice to meet you " + client.getSecondName() + " " + client.getFirstName() + "!" + "\n");

            System.out.println("You can see all our flights: \n");
            displayFlights();

            System.out.println("\nChoose any flight that you want\nPlease write departure point and destination\n");
            displayFlightWithPara();

            System.out.println("\nWhich flight do you want to take?\nPlease enter it's serial number\n");

            Airplane airplane = getFlight();

            System.out.println("It is a nice choice! Welcome to the board to " + airplane.getName() + " " + airplane.getDeparturePoint() + " --> " + airplane.getDestination() + "\n");

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
                if (answer.equalsIgnoreCase("yes")) {
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
}
