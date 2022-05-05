import java.util.ArrayList;
import java.util.List;

public class Flight {
    private String destination;
    private int flightId;
    private List<Passenger> passengers;

    public Flight(String destination, int flightId) {
        this.destination = destination;
        this.flightId = flightId;
        this.passengers = new ArrayList<>();
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void addPassengers(Passenger passenger) {
        passengers.add(passenger);
    }

    public void removePassengers(Passenger passenger){
        passengers.remove(passenger);
    }

    @Override
    public String toString() {
        return "\n Flight{" +
                "destination='" + destination + '\'' +
                ", flightId=" + flightId +
                ", passengers=" + passengers +
                "}";
    }
}
