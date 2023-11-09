public abstract class Fish extends Animal {
    // class fields
    boolean hasFins;
    boolean hasGills;

    @Override
    public void move(int distance) {
        System.out.println("I can swim " + distance + " meters");
        // overrides a move method - takes int - distance as a parameter and displays a message indicating the distance the fish can swim
    }

    @Override
    public void eat() {
        // Overridden method that prints a message indicating the fish eats everything
        System.out.println("Im an omnivore i eat everything");
    }

    Fish() {
        // Constructor for the Fish class
        // Calls the constructor of the superclass
        super();
        // Sets the hasFins and hasGills properties to true
        hasFins = true;
        hasGills = true;
    }

    @Override
    public String toString() {
        // Overridden method that returns a String representation of the Fish object
        // Indicating whether it has fins and gills
        return "hasFins " + (hasFins ? "True" : "False") + "\nhasGills=" + (hasGills ? "True" : "False") + "\n" + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        // Check if the current object is the same as the object passed as a parameter
        if (this == o) return true;
        // Check if the object passed as a parameter is an instance of the Fish class
        if (!(o instanceof Fish fish)) return false;
        // Check if the current object and the Bird object have the same values for hasFins, hasGills
        return hasFins == fish.hasFins && hasGills == fish.hasGills;
    }
}
