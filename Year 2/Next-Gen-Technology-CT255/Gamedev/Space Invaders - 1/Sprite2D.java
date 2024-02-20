import java.awt.*;

// Declare the Sprite2D class
public class Sprite2D {
    // Declare the x and y coordinates of the sprite
    private double x, y;
    // Declare the image of the sprite
    private final Image myImage;

    // Constructor for Sprite2D
    public Sprite2D(Image myImage, double x, double y) {
        // Assign the passed parameters to the corresponding instance variables
        this.myImage = myImage;
        this.x = x;
        this.y = y;
    }

    // Method to move the enemy sprite
    public void moveEnemy() {
        // Change the x and y coordinates of the sprite
        x += 5 * (Math.random() - Math.random());
        y += 5 * (Math.random() - Math.random());
    }

    // Method to move the player sprite
    public void movePlayer() {
        // Define the speed of the player sprite
        double xSpeed = 5;
        // Check if the left key is pressed
        if (InvadersApplication.getInstance().isLeft()) x -= xSpeed;
        // Check if the right key is pressed
        if (InvadersApplication.getInstance().isRight()) x += xSpeed;
    }

    // Method to paint the sprite
    public void paint(Graphics g) {
        // Draw the sprite at its current coordinates
        g.drawImage(myImage, (int) x, (int) y, 40, 40, null);
    }
}
