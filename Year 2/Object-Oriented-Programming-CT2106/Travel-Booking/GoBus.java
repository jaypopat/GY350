public class GoBus extends BusVendor {
    public GoBus() {
        super(); // calls the constructor of the superclass BusVendor - hence can use the fields and methods in the BusVendor Class which is used to manage the booking operations // display gobus trips

        // adds to the trips arraylist initialised in the BusVendor Class.
        trips.add(new Trip(1, "New York", "Los Angeles", "2023-11-10", "09:00", "2023-11-10", "17:00", 500.0f));
        trips.add(new Trip(2, "Chicago", "Houston", "2023-11-11", "10:00", "2023-11-11", "18:00", 400.0f));
        trips.add(new Trip(3, "Boston", "Philadelphia", "2023-11-12", "11:00", "2023-11-12", "19:00", 300.0f));
        trips.add(new Trip(4, "San Francisco", "Seattle", "2023-11-13", "12:00", "2023-11-13", "20:00", 200.0f));
        trips.add(new Trip(5, "Austin", "Dallas", "2023-11-14", "13:00", "2023-11-14", "21:00", 100.0f));


    }
}
