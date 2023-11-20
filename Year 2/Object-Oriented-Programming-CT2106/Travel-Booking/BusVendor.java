import java.util.ArrayList;

public abstract class BusVendor {
    ArrayList<Trip> trips = new ArrayList<Trip>();
    // declared a trips arraylist which holds type Trip - this is initialised using hardcoded values - each bus-vendor class populates this arraylist for their own object

    ArrayList<Booking> bookings = new ArrayList<Booking>();
    // new valid bookings are stored in this arraylist
    // no use case at the moment but can be utilised in future for decisions made by vendor - (analytics?)


    //iterates over each trip in the arraylist and if the id matches it returns the corresponding trip object else returns null
    public Trip getTrip(int ID) {
        for (Trip trip : trips) {
            if (trip.getID() == ID) {
                return trip;
            }
        }
        return null;
    }

    //method to display all the trips for each bus vendor
    public String displayAllTrips() {
        // declares an empty string which is appended to with each trips data
        String displayString = "";
        for (Trip trip : trips) {
            // each trip is iterated over and the details such as id seats,locations,dates and prices are added to the string
            displayString += String.format("\n\nTrip ID: %-5d\n" + "Seats: %-5d\n" + "Starting Location: %-15s\n" + "Destination: %-15s\n" + "Date of Departure: %-10s\n" + "Time of Departure: %-10s\n" + "Date of Arrival: %-10s\n" + "Time of Arrival: %-10s\n" + "Fare: %.2f\n", trip.getID(), trip.getSeats(), trip.getStartingLocation(), trip.getDestination(), trip.getDateOfDeparture(), trip.getTimeOfDeparture(), trip.getDateOfArrival(), trip.getTimeOfArrival(), trip.getFare());
        }
        return displayString;
        // the string is returned to be used by each bus-vendor
    }

    // Method to check if a booking is successful
    public boolean bookingSuccessful(Booking booking) {
        // Check if the trip in the booking exists
        if (!trips.contains(booking.getMyTrip())) {
            System.out.println("Trip doesnt exist");
            return false;
        }
        // Check if there are enough seats available for the booking // if there are adjusts the seat count and returns true
        if (booking.getMyTrip().getSeats() > booking.getSeats() && booking.getSeats() > 0) {
            // Adjust the number of seats available for the trip
            booking.getMyTrip().adjustSeatsAvailable(booking.getSeats());
            bookings.add(booking); // add the booking to bookings arraylist
            return true;
        } else {
            // Print an error message if there are not enough seats available // and returns false
            System.out.println("You cannot book " + booking.getSeats() + " seats! Bus only has the capacity for " + booking.getMyTrip().getSeats() + " seats");
            return false;
        }
    }
}
