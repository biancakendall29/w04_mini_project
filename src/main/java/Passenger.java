public class Passenger {
    private String name;
    private String contactInfo;
    private int id;
    protected int flightId;

    public Passenger(String name, String contactInfo, int id) {
        this.name = name;
        this.contactInfo = contactInfo;
        this.id = id;
        this.flightId = 0;
    }

    public void setFlightId(Flight flight){
        this.flightId = flight.getFlightId();
    }

    public int getFlightId() {
        return flightId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "name='" + name + '\'' +
                ", contactInfo=" + contactInfo +
                ", id=" + id +
                ", flightId=" + flightId +
                '}';
    }
}
