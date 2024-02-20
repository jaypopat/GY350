import javax.swing.*;
import java.awt.*;

// Main application class extending JFrame and implementing Runnable for multithreading
public class MovingSquaresApplication extends JFrame implements Runnable {

    // Defining the window size
    public static final Dimension WindowSize = new Dimension(600, 600);

    // Number of game objects
    private static final int NUMGAMEOBJECTS = 30;

    // Array to hold game objects
    private final GameObject[] GameObjectsArray = new GameObject[NUMGAMEOBJECTS];

    // Constructor for MovingSquaresApplication
    public MovingSquaresApplication() {

        // Setting the title of the window
        this.setTitle("Moving Squares innit");

        // Setting the default close operation
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Centering the window
        Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int x = screensize.width / 2 - WindowSize.width / 2;
        int y = screensize.height / 2 - WindowSize.height / 2;
        this.setLocation(x, y);

        // Setting the window size
        this.setSize(WindowSize);

        // Making the window visible
        this.setVisible(true);

        // Initializing game objects
        for (int i = 0; i < GameObjectsArray.length; i++) {
            GameObjectsArray[i] = new GameObject();
        }

        // Starting a new thread
        Thread t = new Thread(this);
        t.start();
    }

    // Overriding the run method from Runnable
    @Override
    public void run() {
        // Infinite loop to keep the animation running
        while (true) {
            try {
                // Pausing the thread for 40 milliseconds
                Thread.sleep(40);
            } catch (InterruptedException e) {
                // If the thread is interrupted, wrap the exception in a RuntimeException and throw it
                throw new RuntimeException(e);
            }

            // Iterating over all the GameObjects in the array and calling the move method on each object
            for (GameObject gameObject : GameObjectsArray) {
                gameObject.move();
            }

            // Removing all components from the JFrame
            this.removeAll();

            // Revalidating the layout of the JFrame
            this.revalidate();

            // Repainting the JFrame
            this.repaint();
        }
    }

    // Overriding the paint method to clear and redraw
    public void paint(Graphics g) {
        g.fillRect(0, 0, 600, 600);
        for (GameObject gameObject : GameObjectsArray) {
            gameObject.paint(g);
        }
    }

    // Main method to start the application
    public static void main(String[] args) {
        new MovingSquaresApplication();
    }
}
