import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BookingTest {
    Booking booking;
    Flight flight;
    Flight flight2;
    Passenger passenger;
    Passenger passenger1;

    @BeforeEach
    public void setUp() {
        booking = new Booking();
        flight = new Flight("London", 1);
        flight2 = new Flight("London", 2);
        passenger = new Passenger("John Doe", "1234567890", 1);
        passenger1 = new Passenger("Jane Doe", "0987654321", 2);

    }

    @Test
    public void addFlightToBooking() {
        booking.addFlight(flight);
        assertThat(booking.getFlights().size()).isEqualTo(1);
    }

    @Test
    public void addPassengerToFlight() {
        flight.addPassengers(passenger);
        assertThat(flight.getPassengers().size()).isEqualTo(1);
    }

    @Test
    public void checkPassengerFlightId() {
        passenger.setFlightId(flight);
        assertThat(passenger.getFlightId()).isEqualTo(flight.getFlightId());
    }

    @Test
    public void cancelPassenger() {
        booking.addFlight(flight);
        booking.addFlight(flight2);

        assertThat(booking.getFlights().size()).isEqualTo(2);
        booking.removeFlight(flight2);
        assertThat(booking.getFlights().size()).isEqualTo(1);
    }
}
