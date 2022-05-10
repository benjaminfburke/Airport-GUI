public class BoardingPass {

    String airline;
    String gate;
    Passenger passenger;

    public BoardingPass(String airline, Passenger passenger) {
        this.airline = airline;
        this.passenger = passenger;
        Gate g = new Gate();
        gate = g.createGate();

    }

    public String printBoarding(String airline, Passenger passenger) {
        return "------------------------------------------------------------------\n" +
                "BOARDING PASS FOR FLIGHT 18000 WITH " + airline + "\n" +
                "PASSENGER FIRST NAME: " + passenger.getFirstName().toUpperCase() + "\n" +
                "PASSENGER LAST NAME: " + passenger.getLastName().toUpperCase() + "\n" +
                "PASSENGER AGE: " + passenger.getAge() + "\n" +
                "You can now begin boarding at gate " + gate + "\n" +
                "------------------------------------------------------------------";
    }

    public String getAirline() {
        return airline;
    }
    public String getGate() {
        return gate;
    }

    public Passenger getPassenger() {
        return passenger;
    }
}
