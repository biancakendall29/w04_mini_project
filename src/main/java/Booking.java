import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.console;
import static java.lang.System.exit;

public class Booking {
    private List<Flight> flights;
    private Scanner scanner;
    private List<Passenger> passengers;

    public Booking() {
        this.flights = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.passengers = new ArrayList<>();
    }

    public void start() {
        System.out.println("FLIGHT BOOKING");
        System.out.println("What would you like to do?");
        System.out.println("1 - Add a new flight");
        System.out.println("2 - Display all available flights");
        System.out.println("3 - Add a new passenger");
        System.out.println("4 - Book a passenger onto a flight");
        System.out.println("5 - Cancel a flight");
        System.out.println("6 - Remove a passenger from a flight");
        boolean loop = true;

        while (loop) {
            int option = Integer.parseInt(scanner.nextLine());
            if (option == 1) {
                System.out.println("Option 1 chosen");
                addNewFlight();
                loop = false;
            } else if (option == 2) {
                System.out.println("Option 2 chosen");
                displayAllFlights();
                loop = false;
            } else if (option == 3) {
                System.out.println("Option 3 chosen");
                addNewPassenger();
                loop = false;
            } else if (option == 4) {
                System.out.println("Option 4 chosen");
                bookPassenger();
                loop = false;
            } else if (option == 5) {
                System.out.println("Option 5 chosen");
                cancelFlight();
                loop = false;
            } else if (option == 6) {
                System.out.println("Option 6 chosen");
                removePassenger();
                loop = false;
            } else {
                System.out.println("Invalid input");

            }
        }
    }

    public void addNewFlight() {
        System.out.println('\n');
        System.out.println("Provide a flight destination: ");
        String flightDest1 = scanner.nextLine();
        String flightDest = flightDest1.toUpperCase();
        System.out.println("Providing a flight id to flight");
        Random rand = new Random();
        boolean randomLoop = false;
        int flightId = rand.nextInt(1, 101);

        while (randomLoop) {
            int finalFlightId = flightId;
            Flight dupFlightId = flights.stream()
                    .filter(flight -> flight.getFlightId() == finalFlightId)
                    .findAny().orElseThrow();
            if (flightId == dupFlightId.getFlightId()) {
                flightId = rand.nextInt(1, 101);
            } else {
                randomLoop = true;
            }
        }


        Flight flight = new Flight(flightDest, flightId);
        flights.add(flight);
        System.out.println(flight);

        returnToMenu();
    }

    public void displayAllFlights() {
        System.out.println('\n');
        System.out.println(flights);

        returnToMenu();

    }

    public void addNewPassenger() {
        System.out.println('\n');
        System.out.println("Provide a passenger name: ");
        String passengerName1 = scanner.nextLine();
        String passengerName = passengerName1.toUpperCase();
        System.out.println("Provide a phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.println("Providing a booking id to passenger");

        Random rand = new Random();
        boolean randomLoop = false;
        int idNumber = rand.nextInt(1, 101);

        while (randomLoop) {
            int finalIdNumber = idNumber;
            Passenger dupPassengerNumber = passengers.stream()
                    .filter(flight -> flight.getFlightId() == finalIdNumber)
                    .findAny().orElseThrow();
            if (idNumber == dupPassengerNumber.getId()) {
                idNumber = rand.nextInt(1, 101);
            } else {
                randomLoop = true;
            }
        }

        Passenger passenger = new Passenger(passengerName, phoneNumber, idNumber);
        passengers.add(passenger);
        System.out.println("Your details:");
        System.out.println(passenger);

        returnToMenu();

    }

    public void bookPassenger() {
        System.out.println('\n');
        System.out.println("Where would you like to go?");
        List<Flight> foundFlights;
        try {
            String foundFlight1 = scanner.nextLine();
            String foundFlight = foundFlight1.toUpperCase();

            foundFlights = flights.stream().parallel()
                    .filter(flight -> flight.getDestination().equals(foundFlight))
                    .collect(Collectors.toList());
            if (foundFlights.size() == 0) {
                System.out.println("There are no flights to that destination!");
                System.out.println("Add a flight to that destination");
                returnToMenu();
            } else if (foundFlights.size() != 0) {
                System.out.println(foundFlights);

                System.out.println("Flights found! Choose a flight id: ");
                int chosenFlightId = Integer.parseInt(scanner.nextLine());
                Flight chosenFlight = flights.stream()
                        .filter(flight -> flight.getFlightId() == chosenFlightId)
                        .findAny().orElseThrow();

                System.out.println(chosenFlight);
                System.out.println("Which passenger would you like to book onto the flight? Select a name.");
                System.out.println(passengers);

                String passName1 = scanner.nextLine();
                String passName = passName1.toUpperCase();


                Passenger chosenPassenger = passengers.stream()
                        .filter(passenger -> passenger.getName().equals(passName))
                        .findAny().orElseThrow();

                chosenFlight.addPassengers(chosenPassenger);
                chosenPassenger.setFlightId(chosenFlight);

                System.out.println("Your booking details: ");
                System.out.println(chosenFlight);

                returnToMenu();
            }
        } catch (Exception exception) {
            System.out.println("This input does not exist!");
            System.out.println("Check that this input matches the record.");
            bookPassenger();
        }
    }
//


    public void cancelFlight() {
        System.out.println('\n');
        System.out.println(flights);
        System.out.println("Which flight would you like to cancel?");
        System.out.println("Select a flight id");

        int cancelledFlightId = Integer.parseInt(scanner.nextLine());

        Flight cancelledFlight = flights.stream()
                .filter(flight -> flight.getFlightId() == cancelledFlightId)
                .findAny().orElseThrow();

        flights.remove(cancelledFlight);
        System.out.println("Flight removed!");

        System.out.println(flights);

        returnToMenu();

    }

    public void returnToMenu() {
        System.out.println('\n');
        System.out.println("Would you like to return to the main menu?");
        System.out.println("Y or N");
        String yOrN = scanner.nextLine();

        if (yOrN.equals("Y") || yOrN.equals("y")) {
            start();
        } else if (yOrN.equals("N") || yOrN.equals("n")) {
            System.exit(0);
        } else {
            returnToMenu();
        }
    }

    public void removePassenger() {
        System.out.println("Please choose the flight id of the passenger you would like to remove.");

        System.out.println(passengers);
        int choosingPassengerFlightId = Integer.parseInt(scanner.nextLine());
        Flight flightChosen = flights.stream()
                .filter(flight1 -> flight1.getFlightId() == choosingPassengerFlightId)
                .findAny().orElseThrow();

        System.out.println("Which passenger do you want to remove? Please select an passenger id");
        System.out.println(flightChosen.getPassengers());

        int passengerIdChosen = Integer.parseInt(scanner.nextLine());


        Passenger passengerChosen = passengers.stream()
                .filter(passenger -> passenger.getId() == passengerIdChosen)
                .findAny().orElseThrow();

        flightChosen.removePassengers(passengerChosen);
        System.out.println(passengerChosen + "removed from " + flightChosen);
//        passengerChosen.setFlightId(null);



    }

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void removeFlight(Flight flight) {
        flights.remove(flight);
    }


//    public void chooseFlight() {
//        System.out.println("Which flight (destination) would you like to choose? ");
//        String dest = scanner.next();
//        System.out.println("You have chosen " + dest);
//
//
//        Random rand = new Random();
//        int flightId = rand.nextInt(1, 11);
//
//        Flight flight = new Flight(dest, flightId);
//        flight.addPassengers(passenger);
//
//        flights.add(flight);
//
//        System.out.println(flight);

//        try {
//            flight = flights
//                    .stream()
//                    .filter(fl -> fl.getDestination().equals(dest))
//                    .findAny()
//                    .get();
//        } catch (NoSuchElementException exception) {
//            System.out.println("Flight doesn't exist. Creating new flight!");
//        }

//        passenger.setFlightId(flight);

}



