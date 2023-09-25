public class TestTrafficLight {

    public static void main(String[] args) {
        TrafficLights test = new TrafficLights();
        // initialize a TrafficLights object 

        // performing the same set of methods on the object for the number of iterations of the for loop(5)
        for (int i = 1; i <= 5; i++) {
            System.out.println("----Run " + i + "----");
            test.go();
            test.printState();
            System.out.print("\n");

            test.prepareToStop();
            test.printState();
            System.out.print("\n");

            test.stop();
            test.printState();
            System.out.println("\n");
        }
    }
}