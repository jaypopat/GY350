public class Main {

    public static void main(String[] args) {

        // creates objects of each type of class - Bus Eireann CityLink and GoBus
        BusVendor be = new BusEireann();
        BusVendor cl = new CityLink();
        BusVendor gb = new GoBus();
        // the objects will be added as parameters to the displayToConsole function which will simulate a booking trip process
        // tripID and passenger size of trip for each vendor will be provided

        System.out.println("\n\n---------------Bus Eireann-------------------");
        displayToConsole(be, 4, 2); // gives no error - booking successful
        System.out.println("-------------------------------------------------");

        System.out.println("-------------------City Link---------------------");
        displayToConsole(cl, 7, 70); // gives error for booking  ->  Trip ID is valid but seats booked is more than capacity
        System.out.println("-------------------------------------------------");

        System.out.println("-------------------Go Bus------------------------");
        displayToConsole(gb, 65, 10); // gives error for booking -->  ID 65 is not a valid Trip ID
        System.out.println("-------------------------------------------------");

    }

    public static void displayToConsole(BusVendor busVendor, int tripID, int passengers) {
        System.out.println(busVendor.displayAllTrips());
        // calls the displayAllTrips on the object which iterates over each trip element of the trips
        // arraylist for the vendor and displays a readable output of the details of the bus trip

        Trip selectedTrip = busVendor.getTrip(tripID);
        // tripID passed in as a parameter is used to specify the trip chosen by the end-user
        // to be contained in the selectedTrip variable

        Booking booking = new Booking(selectedTrip, passengers);
        // instantiates a booking object with the trip selected by the ID (by end-user) and passenger number - (number of seats user is booking)

        boolean success = busVendor.bookingSuccessful(booking);
        // represents if parameters given by end user are valid - if they are a booking successful message is printed else error message
        // is printed along with a booking failed message on line 53


        if (success) {
            // prints details such as the start/end location with the ID and the cost associated with the booking
            System.out.println("\nBooking successful");
            System.out.println("----------------------------");
            System.out.println("Number of passengers: " + booking.getSeats());
            System.out.println("Trip details: |" + booking.getMyTrip().getStartingLocation() + "| To |" + booking.getMyTrip().getDestination() + "]");
            System.out.println("Trip ID: " + booking.getMyTrip().getID());
            System.out.println("Total Cost: €" + booking.getCost());
            System.out.println("----------------------------\n\n\n\n\n");
        } else {
            System.out.println("Booking Failed!\n\n\n");
        }
        System.out.println("Showing the list of trips....");
        System.out.println(busVendor.displayAllTrips());
        // displays all the trips again after the booking process was undergone. If successful the trips are shown with the updated seat count (number of seats left)
    }
}