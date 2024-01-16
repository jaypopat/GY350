import java.util.Objects;

// This class is used to test the functionality of the Animal class and its subclasses.
public class AnimalTest {
    public static void main(String[] args) {
        // Create an instance of the AnimalTest class
        AnimalTest test = new AnimalTest();
        // Call the test1 method to test the toString method
        test.test1();
        // Call the test2 method to test the equals method
        test.test2();
    }

    // Constructor for the AnimalTest class, prints student information
      AnimalTest() {
        System.out.println("Inheritance Assignment\nJay Popat - 22346566");
        System.out.println("-----------");
    }public void test1() { 
        // Instantiate an array of Animal objects
        Animal[] animals = new Animal[4];
        // Create various animals and assign them to the array
        animals[0] = new Canary("Bluey");
        animals[1] = new Ostrich();
        animals[2] = new Shark();
        animals[3] = new Trout();

        // Loop through each animal in the array
        for (Animal animal : animals) {
            // Print the animal's toString representation
            System.out.println(animal);
            // Call the move method with an argument of 22
            animal.move(22);
            // Call the eat method
            animal.eat();
            // Print a line of dashes
            System.out.println("----------");
            // Print a blank line
            System.out.println();
        }
    }

    public void test2() { // This method is designed to test the equals method
    // Create an array of Animal objects
    Animal[] animals = new Animal[8];

    // Create various animals and assign them to the array
    animals[0] = new Canary("Birdie");
    animals[1] = new Canary("Birdie");
    animals[2] = new Canary("Joey");
    animals[3] = new Ostrich();
    animals[4] = new Ostrich();
    animals[5] = new Trout();
    animals[6] = new Trout();
    animals[7] = new Shark();

    // Print the name of the second animal in the array
    System.out.println(animals[1].name);

    // Loop through each animal in the array
    for (int i = 0; i <= 7; i++) {
        // Loop through each animal in the array again
        for (int j = 1; j <= 7; j++) {
            // Call the equals method and store the result
            boolean compareArrays = animals[i].equals(animals[j]);
            // If both animals are instances of the Canary class
            if (animals[i] instanceof Canary && animals[j] instanceof Canary) {
                // Cast the animals to the Canary class and get their names
                String name1 = ((Canary) animals[i]).name;
                String name2 = ((Canary) animals[j]).name;
                // Print the class name, the names of the animals, and the result of the comparison
                System.out.println(animals[i].getClass().getName() + " with name: " + name1 + " compared with " + animals[j].getClass().getName() + " with name: " + name2 + "==> result: " + compareArrays);

            } else {
                // Print the class name of both animals and the result of the comparison
                System.out.println(animals[i].getClass().getName() + " compared with " + animals[j].getClass().getName() + " result: " + compareArrays);
            }
        }
        // Print a blank line for separation
        System.out.println();
    }
    // Create an instance of the Ostrich class and call its move method
    Ostrich test = new Ostrich();
    test.move(10);
    }
}
