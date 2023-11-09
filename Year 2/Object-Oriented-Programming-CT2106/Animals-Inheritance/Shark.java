// The Shark class extends the Fish class
public class Shark extends Fish {
    // A boolean variable to describe if a shark is dangerous
    boolean isDangerous;

    // Constructor for the Shark class
    Shark() {
        // Call the constructor of the superclass
        super();
        // Set the isDangerous property to true
        isDangerous = true;
    }

    // Method that prints a message indicating the shark has bitten something
    void bite() {
        System.out.println("You have been bitten by a shark");
    }

    // Overridden method that prints a message indicating the shark is a carnivore
    @Override
    public void eat() {
        System.out.println("Im a carnivore - i eat anything that swims");
    }

    // Overridden method that returns a String representation of the Shark object
    @Override
    public String toString() {
        // Return a String representation of the Shark object, indicating whether it is dangerous
        return "Shark\n" + "is dangerous: " + (isDangerous ? "True" : "False") + "\n" + super.toString();
    }

    // Overridden method that checks if the current object is equal to the object passed as a parameter
    @Override
    public boolean equals(Object o) {
        // Check if the current object is the same as the object passed as a parameter
        if (this == o) return true;
        // Check if the object passed as a parameter is an instance of the Shark class
        if (!(o instanceof Shark shark)) return false;
        // Call the equals method of the superclass
        if (!super.equals(o)) return false;
        // Check if the current object and the Shark object have the same value for isDangerous
        return isDangerous == shark.isDangerous;
    }
}
