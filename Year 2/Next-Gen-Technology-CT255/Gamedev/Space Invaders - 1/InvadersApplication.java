// Import required libraries
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

// Declare the InvadersApplication class which extends JFrame and implements Runnable and KeyListener interfaces
public class InvadersApplication extends JFrame implements Runnable, KeyListener {
    // Singleton instance of InvadersApplication
    private static InvadersApplication instance;
    // Size of the game window
    private static final Dimension WindowSize = new Dimension(1000, 600);
    // Total number of aliens
    private final int totalAliens = 30;
    // Array to hold all the aliens
    private final Sprite2D[] AliensArray = new Sprite2D[totalAliens];
    // The player's ship
    private final Sprite2D PlayerShip;
    // Flag to indicate whether the game is initialized
    private boolean isInitialised = false;
    // Flags to track if the left and right keys are pressed
    private boolean left;
    private boolean right;
    // Random object for generating random numbers
    Random rand = new Random();

    // Getter methods for left and right flags
    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    // Singleton pattern to ensure only one instance of InvadersApplication exists
    public static InvadersApplication getInstance() {
        if (instance == null) {
            instance = new InvadersApplication();
        }
        return instance;
    }

    // Constructor for InvadersApplication
    public InvadersApplication() {
        // Add this class as a key listener
        addKeyListener(this);
        // Set the title of the window
        this.setTitle("Space Invaders");
        // Set the operation that will happen when the window is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Center the window on the screen
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(screenSize.width / 2 - WindowSize.width / 2, screenSize.height / 2 - WindowSize.height / 2, WindowSize.width, WindowSize.height);
        // Make the window visible
        setVisible(true);
        // Load the alien and player images
        ImageIcon alien = new ImageIcon("src/images/alien_ship_1.png");
        Image alienImage = alien.getImage();
        ImageIcon player = new ImageIcon("src/images/player_ship.png");
        Image playerImage = player.getImage();

        // Initialize the aliens and player
        for (int i = 0; i < totalAliens; i++) {
            AliensArray[i] = new Sprite2D(alienImage, rand.nextInt(getWidth() + 300), rand.nextInt(getHeight() - 300));
        }
        PlayerShip = new Sprite2D(playerImage, (double) screenSize.width / 2 - (double) WindowSize.width / 2, getHeight() - 100);
        isInitialised = true;

        // Start the game thread
        Thread t = new Thread(this);
        t.start();
    }

    // Implementation of the Runnable interface
    @Override
    public void run() {
        while (true) {
            // Sleep for 1/50 sec
            try {
                Thread.sleep(90);
            } catch (InterruptedException ignored) {
            }
            // Animate game objects
            for (int i = 0; i < totalAliens; i++) {
                AliensArray[i].moveEnemy();
            }
            PlayerShip.movePlayer(); // Move the player
            // Force an application repaint
            this.repaint();
        }
    }

    // Override the paint method to draw game objects
    public void paint(Graphics g) {
        if (!isInitialised) return;
        // Clear the canvas with a big white rectangle
        int winWidth = getWidth();
        int winHeight = getHeight();
        g.fillRect(0, 0, winWidth, winHeight);
        // Redraw all game objects
        for (int i = 0; i < totalAliens; i++)
            AliensArray[i].paint(g);
        PlayerShip.paint(g);
    }

    // Main method to start the game
    public static void main(String[] args) {
        InvadersApplication.getInstance();
    }

    // Not needed - only for console logs
    public void keyTyped(KeyEvent e) {}

    // Override the keyPressed method to handle pressed keys
    @Override
    public void keyPressed(KeyEvent e) {
        if (KeyEvent.VK_RIGHT == e.getKeyCode()) right = true;
        if (KeyEvent.VK_LEFT == e.getKeyCode()) left = true;
    }

    // Override the keyReleased method to handle released keys
    @Override
    public void keyReleased(KeyEvent e) {
        if (KeyEvent.VK_RIGHT == e.getKeyCode()) right = false;
        if (KeyEvent.VK_LEFT == e.getKeyCode()) left = false;
    }
}
