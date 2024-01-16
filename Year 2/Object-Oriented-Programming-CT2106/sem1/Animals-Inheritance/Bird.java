import java.util.Objects;

public abstract class Bird extends Animal {
    //instance variables (fields)
    boolean hasFeathers;
    boolean hasWings;
    boolean flies;

    /**
     * Constructor for objects of class Bird
     */
    public Bird() {
        super(); //calls the constructor of its superclass  - Animal
//        breathes = true;
//        hasSkin = true;
//        colour = "grey";

        colour = "black"; //overrides the value of colour inherited from Animal
        hasFeathers = true; //all the subclasses of Bird inherit this property and value
        hasWings = true; //all the subclasses of Bird inherit this property and value
        flies = true; //all the subclasses of Bird inherit this property and value
    }

    /**
     * move method overrides the move method
     * inherited from superclass Animal
     */
    @Override // good programming practice to use @Override to denote overridden methods
    public void move(int distance) {
        // prints out a different message depending on the value of the boolean field - flies (whether the bird flies or not)
        if (flies) {
            System.out.println("I fly " + distance + " metres");
            return;
        }
        System.out.println("I am a bird but cannot fly. I walk " + distance + " meters");
    }

    @Override
    public void eat() {
        // Print a message indicating that the bird eats seeds
        System.out.println("I eat seeds");
    }

    /**
     * sing method that all birds have
     */
    public void sing() {
        // Print a message indicating the bird's song
        System.out.println("tra la la");
    }

// Removed getter methods as they are already declared as public fields at the top and don't require getters

    @Override
    public boolean equals(Object o) {
        // Check if the current object is the same as the object passed as a parameter
        if (this == o) return true;
        // Check if the object passed as a parameter is an instance of the Bird class
        if (!(o instanceof Bird bird)) return false;
        // Call the equals method of the superclass
        if (!super.equals(o)) return false;
        // Check if the current object and the Bird object have the same values for hasFeathers, hasWings, and flies
        return hasFeathers == bird.hasFeathers && hasWings == bird.hasWings && flies == bird.flies;
    }

    @Override
    public String toString() {
        // Return a String representation of the Bird object, indicating whether it has feathers, wings, and flies
        return "hasFeathers=" + (hasFeathers ? "True" : "False") + "\nhasWings=" + (hasWings ? "True" : "False") + "\nflies=" + (flies ? "True" : "False") + '\n' + super.toString();
    }

}
