// Importing necessary libraries
import java.awt.*;

// GameObject class representing a square in the game
public class GameObject {
    // Variables to store the coordinates of the square
    private double x, y;

    // Maximum coordinate value
    private static final int maxCoordinate = 1300;

    // Variable to store the color of the square
    private Color c;

    // Constructor for GameObject
    public GameObject() {
        // Assigning random color and random coordinates to the square
        c = randColour();
        x = randCoordinate(maxCoordinate);
        y = randCoordinate(maxCoordinate);
    }

    // Method to generate random colors
    public static Color randColour() {
        int r = (int) (Math.random() * 256);
        int g = (int) (Math.random() * 256);
        int b = (int) (Math.random() * 256);
        return new Color(r, g, b);
    }

    // Method to generate random coordinates within the maximum limit
    public static double randCoordinate(int max) {
        return (Math.random() * max * MovingSquaresApplication.WindowSize.width / max);
    }

    // Method to move the square in a random direction
    public void move() {
        // Generate random directions
        float dx = (float) (Math.random() * 3 - 1);
        float dy = (float) (Math.random() * 3 - 1);

        // Check for collisions with the window's edges
        if ((x + dx >= MovingSquaresApplication.WindowSize.width - 50) || (x + dx <= 0)) {
            dx = -500;
        }
        if ((y + dy >= MovingSquaresApplication.WindowSize.height - 50) || (y + dy <= 0)) {
            dy = -500;
        }

        // Update the box's position
        x += dx;
        y += dy;
    }

    // Method to draw the square on the screen
    public void paint(Graphics g) {
        g.setColor(c);
        g.fillRect((int) x, (int) y, 50, 50);
    }
}
