import java.util.ArrayList;
import java.util.List;

public class Booking {
    private List<Flight> flights;

    public Booking() {
        this.flights = new ArrayList<>();
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

//    public void addNewPassenger(){
//
//    }

    public void chooseFlight(Passenger passenger, Flight flight) {
        flight.addPassengers(passenger);
        passenger.setFlightId(flight);

    }
}
