public class Booking {
    // The trip associated with this booking
    private Trip myTrip;

    // The number of passengers for this booking
    private int passengers;

    // The cost of this booking
    private float cost;

    // Constructor for the Booking class
    public Booking(Trip myTrip, int passengers) {
        this.myTrip = myTrip;
        this.passengers = passengers;
    }

    // Getter for the trip associated with this booking
    public Trip getMyTrip() {
        return myTrip;
    }

    // Getter for the number of passengers for this booking
    public int getSeats() {
        return passengers;
    }

    // Calculates and returns the cost of this booking
    public float getCost() {
        cost = myTrip.getFare();
        return cost;
    }

    // Overridden toString method to provide a string representation of the Booking object
    @Override
    public String toString() {
        return "Booking{" + "myTrip=" + myTrip + ", seats=" + passengers + ", cost=" + cost + '}';
    }
}
