import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Booking {
    private List<Flight> flights;
    private Scanner scanner;

    public Booking() {
        this.flights = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void addFlight(Flight flight){
        flights.add(flight);
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void cancelFlight(Flight flight) {
        flights.remove(flight);
    }

    public void addNewPassenger(){
        System.out.println("Provide a passenger name: ");
        String passengerName = scanner.nextLine();
        System.out.println("Provide a phone number: ");
        int phoneNumber = scanner.nextInt();
        System.out.println("Provide a booking ID number: ");
        int idNumber = scanner.nextInt();

        Passenger passenger = new Passenger(passengerName, phoneNumber, idNumber);
    }

    public void chooseFlight(Passenger passenger, Flight flight) {
        System.out.println("Which flight (destination) would you like to choose? ");
        String dest = scanner.nextLine();
        Flight flightDest = flights
                .stream()
                .filter(fl -> fl.getDestination().equals(dest)).map();
        flight.addPassengers(passenger);
        passenger.setFlightId(flight);

    }
}
