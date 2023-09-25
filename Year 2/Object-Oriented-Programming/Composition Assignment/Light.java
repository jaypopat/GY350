public class Light {
    // variables which will be used by TrafficLights class
    public static String GREEN = "GREEN"; 
    public static String AMBER = "AMBER"; 
    public static String RED = "RED";

    private String color; 
    private boolean turnedOn = false; 

    /**
     * Initializes a Light object with the given color.
     */
    public Light(String color) {
        this.color = color;
    }

    /**
     * Gets the color of the light.
     */
    public String getColor() {
        return color;
    }

    /**
     * Sets the color of the light.
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Checks if the light is turned on.
     * true if the light is turned on, false otherwise
     */
    public boolean isTurnedOn() {
        return turnedOn;
    }

    /**
     * Turns on the light.
     */
    public void turnOn() {
        this.turnedOn = true;
    }

    /**
     * Turns off the light.
     */
    public void turnOff() {
        this.turnedOn = false;
    }

    /**
     * Prints the state of the light.
     */
    public void printState() {
        if (turnedOn) {
            System.out.println(color);
        } else {
            System.out.println("=OFF=");
        }
    }
}
