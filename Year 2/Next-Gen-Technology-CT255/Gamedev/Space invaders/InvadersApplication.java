import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Iterator;

public class InvadersApplication extends JFrame implements Runnable, KeyListener {

    // member data
    private static boolean isInitialised = false;
    private static final ArrayList<Bullet> bullets = new ArrayList<>();

    private static final Dimension WindowSize = new Dimension(800, 600);
    private final BufferStrategy strategy;
    private final Graphics offscreenGraphics;
    private static final int NUMALIENS = 30;
    private int score;
    private int highestScore = 0;
    private int aliensAlive = NUMALIENS;
    private final Alien[] AliensArray = new Alien[NUMALIENS];
    private final Spaceship PlayerShip;
    private final Image bulletImage;
    private final Image alienImage;
    private final Image shipImage;
    private boolean isGameOver = false;

    // constructor
    public InvadersApplication() {
        //Display the window, centred on the screen
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int x = screenSize.width / 2 - WindowSize.width / 2;
        int y = screenSize.height / 2 - WindowSize.height / 2;
        setBounds(x, y, WindowSize.width, WindowSize.height);
        setVisible(true);
        this.setTitle("Space Invaders!");

        // load image from disk
        alienImage = new ImageIcon("src/images/alien_ship_1.png").getImage();
        shipImage = new ImageIcon("src/images/player_ship.png").getImage();
        bulletImage = new ImageIcon("src/images/bullet.png").getImage();

        // create and initialise some aliens, passing them each the image we have loaded
        resetAliensPosition();
        Alien.setFleetXSpeed(2);

        // create and initialise the player's spaceship

        PlayerShip = new Spaceship(shipImage);
        PlayerShip.setPosition(300, 530);

        // tell all sprites the window width
        Sprite2D.setWinWidth(WindowSize.width);

        // create and start our animation thread
        Thread t = new Thread(this);
        t.start();

        // send keyboard events arriving into this JFrame back to its own event handlers
        addKeyListener(this);

        // initialise double-buffering
        createBufferStrategy(2);
        strategy = getBufferStrategy();
        offscreenGraphics = strategy.getDrawGraphics();

        isInitialised = true;
    }

    private void resetAliensPosition() {
        for (int i = 0; i < NUMALIENS; i++) {
            AliensArray[i] = new Alien(alienImage);
            double xx = (i % 5) * 80 + 70;
            double yy = (i / 5) * 40 + 50; // integer division!
            AliensArray[i].setPosition(xx, yy);
        }
        Alien.setFleetXSpeed(2);

    }

    private void startNewWave() {
        // Reset aliens' positions
        resetAliensPosition();
        PlayerShip.setPosition(300, 530);

        // Increase the speed of the aliens
        double currSpeed = Alien.xSpeed;
        double newSpeed = currSpeed + 3;
        Alien.setFleetXSpeed(newSpeed);

        // Reset the aliensAlive counter
        aliensAlive = NUMALIENS;
    }

    // thread's entry point
    public void run() {
        while (true) {

            // 1: sleep for 1/50 sec
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
            }
            // Check if all aliens are killed
            if (aliensAlive == 0) {
                startNewWave();
            }

            // 2: animate game objects
            boolean alienDirectionReversalNeeded = false;
            for (int i = 0; i < NUMALIENS; i++) {
                if (AliensArray[i].move()) alienDirectionReversalNeeded = true;
            }
            if (alienDirectionReversalNeeded) {
                Alien.reverseDirection();
                for (int i = 0; i < NUMALIENS; i++) {
                    AliensArray[i].jumpDownwards();
                    if ((AliensArray[i].y + alienImage.getHeight(null) >= PlayerShip.y) && AliensArray[i].isAlive) {
                        this.isGameOver = true;
                        System.out.println("game ended");
                        if (this.score > this.highestScore) {
                            this.highestScore = this.score;
                        }
                    }
                }
            }

            PlayerShip.move();
            Iterator<Bullet> iterator = bullets.iterator();
            while (iterator.hasNext()) {

                Bullet b = iterator.next();
                boolean flag = b.move();
                if (!flag) {
                    iterator.remove();
                    continue;
                }

                for (int i = 0; i < NUMALIENS; i++) {
                    double widthBullet = b.x;
                    double heightBullet = b.y;

                    if (AliensArray[i].checkCollision((int) widthBullet, (int) heightBullet, bulletImage.getWidth(null), bulletImage.getHeight(null))) {
                        iterator.remove();
                        score += 50;
                        aliensAlive--;
                        if (score > highestScore) {
                            highestScore = score;
                        }
                        break;
                    }
                }
            }
            // 3: force an application repaint
            this.repaint();
        }
    }

    // Three Keyboard Event-Handler functions
    @Override
    public void keyPressed(KeyEvent e) {
        if (!isGameOver) {

            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                PlayerShip.setXSpeed(-4);
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                PlayerShip.setXSpeed(4);
            } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                shootBullet();
            } else if (e.getKeyCode() == KeyEvent.VK_ALT) { // stats
                System.out.println("Bullets count: " + bullets.size());
                System.out.println(score);
            }
        } else {
            startNewGame();
        }
    }

    private void startNewGame() {
        isGameOver = false;
        score = 0;
        aliensAlive = NUMALIENS;
        resetAliensPosition();
        PlayerShip.setPosition(300, 530);
        bullets.clear();
    }

    private void shootBullet() {
        Bullet b = new Bullet(bulletImage);
        // Center the bullet on the spaceship
        b.setPosition(PlayerShip.x + 54 / 2, PlayerShip.y);
        bullets.add(b); // Add the bullet to the bullets list
        System.out.println("Bullet shot! Bullets count: " + bullets.size()); // Debug print
        repaint();
    }


    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) PlayerShip.setXSpeed(0);
    }

    public void keyTyped(KeyEvent e) {}

    // application's paint method
    public void paint(Graphics g) {
        if (!isInitialised) return;

        g = offscreenGraphics;

        // Clear the canvas with a big black rectangle
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WindowSize.width, WindowSize.height);


        if (!isGameOver) {

            // Draw the highest score text at the top right corner of the screen
            g.setColor(Color.WHITE); // Set the text color to white
            g.setFont(new Font("Arial", Font.BOLD, 20)); // Set the font properties
            String highestScoreText = "Highest Score: " + highestScore; // Create the highest score text
            g.drawString(highestScoreText, WindowSize.width - 200, 30); // Draw the highest score text

            // Draw the current score text at the top left corner of the screen
            String currScore = "Score: " + score; // Create the current score text
            g.drawString(currScore, 10, 30); // Draw the current score text at (x=10, y=30)

            // Redraw all game objects
            for (int i = 0; i < NUMALIENS; i++)
                AliensArray[i].paint(g);

            PlayerShip.paint(g);

            for (Bullet bullet : bullets) {
                bullet.paint(g);
            }
        } else {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 24)); // Set the font to make the text larger and more readable
            String gameOver = "GAME OVER";
            g.drawString(gameOver, WindowSize.width / 2 - 100, WindowSize.height / 2 - 100);
            String pressAnyKey = "Press any key to play";
            g.drawString(pressAnyKey, WindowSize.width / 2 - 150, WindowSize.height / 2);
            String moveInstructions = "Arrow keys to move, Space to fire";
            g.drawString(moveInstructions, WindowSize.width / 2 - 200, WindowSize.height / 2 + 50);
        }
        // Flip the buffers offscreen<-->onscreen
        strategy.show();
    }
    // application entry point
    public static void main(String[] args) {
        new InvadersApplication();
    }

}

