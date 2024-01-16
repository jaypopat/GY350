public class Trip {
    // Unique identifier for the trip
    private int ID;

    // Number of available seats on the trip
    private int seats;

    // Starting location of the trip
    private String startingLocation;

    // Destination of the trip
    private String destination;

    // Date of departure
    private String dateOfDeparture;

    // Time of departure
    private String timeOfDeparture;

    // Date of arrival
    private String dateOfArrival;

    // Time of arrival
    private String timeOfArrival;

    // Fare for the trip
    private float fare;

    // constructor initialising the field variables with the values when instantiating trip object
    public Trip(int ID, String startingLocation, String destination, String dateOfDeparture, String timeOfDeparture, String dateOfArrival, String timeOfArrival, float fare) {
        this.ID = ID;
        this.seats = 60;
        this.startingLocation = startingLocation;
        this.destination = destination;
        this.dateOfDeparture = dateOfDeparture;
        this.timeOfDeparture = timeOfDeparture;
        this.dateOfArrival = dateOfArrival;
        this.timeOfArrival = timeOfArrival;
        this.fare = fare;
    }

    // Getter for the ID
    public int getID() {
        return ID;
    }

    // Getter for the number of available seats
    public int getSeats() {
        return seats;
    }

    // Method to adjust the number of available seats after booking
    public void adjustSeatsAvailable(int seatsBooked) {
        this.seats -= seatsBooked;
    }

    // Getter for the starting location
    public String getStartingLocation() {
        return startingLocation;
    }

    // Getter for the destination
    public String getDestination() {
        return destination;
    }

    // Getter for the date of departure
    public String getDateOfDeparture() {
        return dateOfDeparture;
    }

    // Getter for the time of departure
    public String getTimeOfDeparture() {
        return timeOfDeparture;
    }

    // Getter for the date of arrival
    public String getDateOfArrival() {
        return dateOfArrival;
    }

    // Getter for the time of arrival
    public String getTimeOfArrival() {
        return timeOfArrival;
    }

    // Getter for the fare
    public float getFare() {
        return fare;
    }

    // Overridden toString method to provide a string representation of the Trip object
    @Override
    public String toString() {
        return "Trip{" + "ID=" + ID + ", seats=" + seats + ", startingLocation='" + startingLocation + '\'' + ", destination='" + destination + '\'' + ", dateOfDeparture='" + dateOfDeparture + '\'' + ", timeOfDeparture='" + timeOfDeparture + '\'' + ", dateOfArrival='" + dateOfArrival + '\'' + ", timeOfArrival='" + timeOfArrival + '\'' + ", fare=" + fare + '}';
    }
}
