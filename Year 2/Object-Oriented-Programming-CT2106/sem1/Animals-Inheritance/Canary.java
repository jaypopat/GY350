import java.util.Objects;

public class Canary extends Bird {

    String name; // the name of this Canary
    
    public Canary(String name) {
        super(); // call the constructor of the superclass Bird
        colour = "yellow"; // this overrides the value inherited from Bird
        this.name=name;

    }

    /**
     * Sing method overrides the sing method
     * inherited from superclass Bird
     */
    @Override // good programming practice to use @Override to denote overridden methods
    public void sing() {
        System.out.println("tweet tweet tweet");
    }
    // print a message overriding the bird's sing method to denote the canary's singing sound - tweet


    //toString method returns a String representation of the bird
    @Override
    public String toString() {
        return "Canary\n" + "name " + name + '\n' + super.toString();
    }


    @Override
    public boolean equals(Object o) {
        // Check if the current object is the same as the object passed as a parameter
        if (this == o) return true;
        // Check if the object passed as a parameter is an instance of the Canary class
        if (!(o instanceof Canary canary)) return false;
        // Call the equals method of the superclass
        if (!super.equals(o)) return false;
        // Check if the current object and the Canary object have the same values for name
        return Objects.equals(name, canary.name);
    }
}
