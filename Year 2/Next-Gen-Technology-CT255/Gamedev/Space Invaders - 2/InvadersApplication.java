import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

public class InvadersApplication extends JFrame implements Runnable, KeyListener {
    private static InvadersApplication instance;
    private final BufferStrategy strategy;
    // member data
    private static boolean isGraphicsInitialised = false;
    private static final Dimension WindowSize = new Dimension(800, 600);
    private static final int NUMALIENS = 30;
    public static final Alien[] AliensArray = new Alien[NUMALIENS];
    private final Spaceship PlayerShip;
    // Flags to track if the left and right keys are pressed
    private boolean left;
    private boolean right;
    private final Image shipImage = new ImageIcon("src/images/player_ship.png").getImage();
    private final Image alienImage = new ImageIcon("src/images/alien_ship_1.png").getImage();

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


    public void updateAliens() {
        boolean edgeReached = false;
        for (int i = 0; i < NUMALIENS; i++) {
            if (AliensArray[i].move()) {
                edgeReached = true;
                break; // Exit the loop if any alien hits the edge
            }
        }
        if (edgeReached) {
            Alien.changeDirection(); // Change direction and move down
        }

        // Check if any alien is within 30 units of the PlayerShip's y position
        for (int i = 0; i < NUMALIENS; i++) {
            if (Math.abs(AliensArray[i].y - PlayerShip.y) <= 30) {
//                Object[] options = {"Exit", "Restart"};
//                int choice = JOptionPane.showOptionDialog(null, "You Lost", "Game Over", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
//
//                if (choice == 0) {
                    System.exit(0);
//                } else if (choice == 1) {
//                    resetGame();
//                }
            }
        }
    }
    // resetting caused the alien movement to be buggy - need to figure this out

//    private void resetGame() {
//        // Reset the player's position
//        PlayerShip.setPosition(300,530);
//
//        for (int i = 0; i < NUMALIENS; i++) {
//            // Reset each alien's position to its initial position
//            AliensArray[i].initPosition(i);
//        }
//
//
//        // Start a new game loop
//        Thread t = new Thread(this);
//        t.start();
//    }


    public InvadersApplication() {
    //Display the window, centred on the screen
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int x = screenSize.width / 2 - WindowSize.width / 2;
        int y = screenSize.height / 2 - WindowSize.height / 2;
        this.setTitle("Space Invaders! .. (sort of)");
        this.setLocation(x, y);
        this.setPreferredSize(WindowSize);
        this.pack();
        this.setVisible(true);
        this.addKeyListener(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.createBufferStrategy(2);
        strategy = getBufferStrategy();


        for (int i = 0; i < NUMALIENS; i++) {

            AliensArray[i] = new Alien(alienImage, WindowSize.width);
            AliensArray[i].initPosition(i);
        }

        PlayerShip = new Spaceship(shipImage, WindowSize.width);
        PlayerShip.setPosition(300, 530);
        Thread t = new Thread(this);
        t.start();
        addKeyListener(this);
        isGraphicsInitialised = true; // it’s now safe to paint the images
    }

    // thread's entry point
    public void run() {
        while (true) {
            try {
                Thread.sleep(20); // Control the loop speed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            updateAliens();
            PlayerShip.move();
            this.repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (KeyEvent.VK_RIGHT == e.getKeyCode()) right = true;
        if (KeyEvent.VK_LEFT == e.getKeyCode()) left = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (KeyEvent.VK_RIGHT == e.getKeyCode()) right = false;
        if (KeyEvent.VK_LEFT == e.getKeyCode()) left = false;
    }

    public void keyTyped(KeyEvent e) {
    }

    public void paint(Graphics g) {
        if (strategy == null) {
            return; // BufferStrategy not yet created, skip this paint call
        }
        g = strategy.getDrawGraphics();
        if (isGraphicsInitialised) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, WindowSize.width, WindowSize.height);
            for (int i = 0; i < NUMALIENS; i++)
                AliensArray[i].paint(g);
            PlayerShip.paint(g);
        }
        strategy.show();
    }

    // application entry point
    public static void main(String[] args) {
        new InvadersApplication();
    }
}