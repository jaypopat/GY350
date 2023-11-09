public class Trout extends Fish {
    // boolean variables to describe the characteristics of a Trout
    boolean hasSpikes;
    boolean isEdible;

    // Constructor for the Trout class
    Trout() {
        // Call the constructor of the superclass
        super();
        // Set the colour, isEdible, and hasSpikes properties
        colour = "brown";
        isEdible = true;
        hasSpikes = true;
    }

    // Overridden method that prints a message indicating the trout moves upriver to lay eggs
    @Override
    public void move(int distance) {
        System.out.println("I swim " + distance + " meters upriver to lay eggs");
    }

    // Overridden method that prints a message indicating the trout eats small fish
    @Override
    public void eat() {
        System.out.println("I eat small fish");
    }

    // Overridden method that returns a String representation of the Trout object
    @Override
    public String toString() {
        // Return a String representation of the Trout object, indicating whether it has spikes and is edible
        return "Trout\n" + "has spikes=" + (hasSpikes ? "True" : "False") + "\nisEdible=" + (isEdible ? "True" : "False") + "\n" + super.toString();
    }

    // Overridden method that checks if the current object is equal to the object passed as a parameter
    @Override
    public boolean equals(Object o) {
        // Check if the current object is the same as the object passed as a parameter
        if (this == o) return true;
        // Check if the object passed as a parameter is an instance of the Trout class
        if (!(o instanceof Trout trout)) return false;
        // Call the equals method of the superclass
        if (!super.equals(o)) return false;
        // Check if the current object and the Trout object have the same values for hasSpikes and isEdible
        return hasSpikes == trout.hasSpikes && isEdible == trout.isEdible;
    }
}
