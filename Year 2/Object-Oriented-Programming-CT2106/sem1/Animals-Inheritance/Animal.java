import java.util.Objects;

public abstract class Animal {
    // Four variables to describe the characteristics of an Animal
    boolean hasSkin;
    boolean breathes;
    String colour;
    String name;

    // Constructor for the Animal class
    Animal() {
        // Set the breathes and hasSkin properties to true, and colour to "grey"
        breathes = true;
        hasSkin = true;
        colour = "grey";
    }

    // Method that prints a message indicating the animal moves a certain distance
    public void move(int distance) {
        System.out.println("I move " + distance + " metres");
    }

    // Method that prints a message indicating the animal eats
    public void eat() {
        System.out.println("I eat");
    }

    // Overridden method that checks if the current object is equal to the object passed as a parameter
    @Override
    public boolean equals(Object o) {
        // Check if the current object is the same as the object passed as a parameter
        if (this == o) return true;
        // Check if the object passed as a parameter is an instance of the Animal class
        if (!(o instanceof Animal animal)) return false;
        // Check if the current object and the Animal object have the same values for hasSkin, breathes, and colour
        return hasSkin == animal.hasSkin && breathes == animal.breathes && Objects.equals(colour, animal.colour);
    }

    // Method that returns a String representation of the Animal object
    public String toString() {
        // Return a String representation of the Animal object, indicating its colour, whether it breathes, and whether it has skin
        return "colour: " + colour + "\n" + "breathes: " + (breathes ? "True" : "False") + "\n" + "has skin: " + (hasSkin ? "True" : "False") + "\n";
    }
}
