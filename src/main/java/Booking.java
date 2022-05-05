import java.util.*;
import java.util.stream.Collectors;

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
        boolean loop = true;

        while (loop) {
            int option = scanner.nextInt();
            if (option == 1) {
                System.out.println("Option 1 chosen");
                addNewFlight();
            } else if (option == 2) {
                System.out.println("Option 2 chosen");
                displayAllFlights();
            } else if (option == 3) {
                System.out.println("Option 3 chosen");
                addNewPassenger();
            } else if (option == 4) {
                System.out.println("Option 4 chosen");
                bookPassenger();
            } else if (option == 5) {
                System.out.println("Option 5 chosen");
//            cancelFlight();
            } else {
                System.out.println("Invalid input");
            }
        }
    }

    public void addNewFlight() {
        System.out.println("Provide a flight destination: ");
        String flightDest = scanner.next();
        System.out.println("Provide a flight id: ");
        int flightId = scanner.nextInt();

        Flight flight = new Flight(flightDest, flightId);
        flights.add(flight);
        System.out.println(flight);
    }

    public void displayAllFlights() {
        System.out.println(flights);
    }

    public void addNewPassenger() {
        System.out.println("Provide a passenger name: ");
        String passengerName = scanner.next();
        System.out.println("Provide a phone number: ");
        int phoneNumber = scanner.nextInt();
        System.out.println("Provide a booking ID number: ");
        int idNumber = scanner.nextInt();

        Passenger passenger = new Passenger(passengerName, phoneNumber, idNumber);
        passengers.add(passenger);
        System.out.println("Your details:");
        System.out.println(passenger);
    }

    public void bookPassenger(){
        System.out.println("What flight would you like to go on?");
        String foundFlight = scanner.next();

        List<Flight> foundFlights = flights.stream().parallel()
                .filter(flight -> flight.getDestination().equals(foundFlight))
                .collect(Collectors.toList());

        System.out.println(foundFlights);

        System.out.println("Flights found! Choose a flight id: ");
        int chosenFlightId = scanner.nextInt();

        Flight chosenFlight = flights.stream()
                .filter(flight -> flight.getFlightId() == chosenFlightId)
                .findAny().orElseThrow();

        System.out.println(chosenFlight);
        System.out.println("Which passenger would you like to book onto the flight?");
        System.out.println(passengers);

        String passName = scanner.next();

        Passenger chosenPassenger = passengers.stream()
                .filter(passenger -> passenger.getName().equals(passName))
                .findAny().orElseThrow();

        chosenFlight.addPassengers(chosenPassenger);
        chosenPassenger.setFlightId(chosenFlight);

        System.out.println("Your booking details: ");
        System.out.println(chosenFlight);

    }

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void cancelFlight(Flight flight) {
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



