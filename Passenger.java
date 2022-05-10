public class Passenger {

    private String firstName;
    private String lastName;
    private String age;
    private Gate gate;

    public Passenger(String firstName, String lastName, String age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        gate = new Gate();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAge() {
        return age;
    }

}
