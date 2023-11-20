public class BusEireann extends BusVendor {
    public BusEireann() {
        super(); // calls the constructor of the superclass BusVendor - hence can use the fields and methods in the BusVendor Class which is used to manage the booking operations // display BusEireann trips

        // adds to the trips arraylist initialised in the BusVendor Class.
        trips.add(new Trip(6, "New York", "Chicago", "2023-11-15", "09:00", "2023-11-15", "17:00", 500.0f));
        trips.add(new Trip(4, "Los Angeles", "San Francisco", "2023-11-16", "10:00", "2023-11-16", "18:00", 400.0f));
        trips.add(new Trip(8, "Houston", "Austin", "2023-11-17", "11:00", "2023-11-17", "19:00", 300.0f));
        trips.add(new Trip(9, "Philadelphia", "Boston", "2023-11-18", "12:00", "2023-11-18", "20:00", 200.0f));
        trips.add(new Trip(10, "Seattle", "San Diego", "2023-11-19", "13:00", "2023-11-19", "21:00", 100.0f));
    }
}
