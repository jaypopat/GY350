// The Ostrich class extends the Bird class
public class Ostrich extends Bird {

    // Two boolean variables to describe the characteristics of an Ostrich
    boolean isTall;
    boolean thinLongLegs;

    // Constructor for the Ostrich class
    public Ostrich() {
        // Call the constructor of the superclass
        super();
        // Set the isTall and thinLongLegs properties to true
        isTall = true;
        flies = false;
        thinLongLegs = true;
    }

    // Overridden method that checks if the current object is equal to the object passed as a parameter
    @Override
    public boolean equals(Object o) {
        // Check if the current object is the same as the object passed as a parameter
        if (this == o) return true;
        // Check if the object passed as a parameter is an instance of the Ostrich class
        if (!(o instanceof Ostrich ostrich)) return false;
        // Check if the current object and the Ostrich object have the same values for isTall and thinLongLegs
        return isTall == ostrich.isTall && thinLongLegs == ostrich.thinLongLegs;
    }

    // Overridden method that returns a String representation of the Ostrich object
    @Override
    public String toString() {
        // Return a String representation of the Ostrich object, indicating whether it is tall and has thin long legs
        return "Ostrich\nisTall=" + (isTall ? "True" : "False") + "\n thinLongLegs=" + (thinLongLegs ? "True" : "False") + "\n" + super.toString();
    }
}
