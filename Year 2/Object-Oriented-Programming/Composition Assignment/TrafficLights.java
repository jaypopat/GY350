public class TrafficLights {
    // Represents the green light
    private Light green; 

    // Represents the amber light
    private Light amber;

    // Represents the red light
    private Light red;

    /**
     Creates objects of the light class
     */
    public TrafficLights() {
        green = new Light(Light.GREEN);
        amber = new Light(Light.AMBER);
        red = new Light(Light.RED);
    }

    /**
     * Turns on the green light and turns off the red and amber lights.
     */
    public void go() {
        red.turnOff();
        amber.turnOff();
        green.turnOn();
    }

    /**
     * Turns on the amber light and turns off the green and red lights.
     */
    public void prepareToStop() {
        green.turnOff();
        amber.turnOn();
        red.turnOff();
    }

    /**
     * Turns on the red light and turns off the green and amber lights.
     */
    public void stop() {
        green.turnOff();
        amber.turnOff();
        red.turnOn();
    }

    /**
     * Prints the state of each light.
     */
    public void printState() {
        green.printState();
        amber.printState();
        red.printState();
    }
}
