import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Main extends JFrame implements KeyListener, MouseListener, Runnable {

    private static final Dimension WindowSize = new Dimension(800, 800);
    private final BufferStrategy strategy;
    private final boolean[][][] gameState = new boolean[40][40][2];
    private int currentBuffer = 0; //  0 for front buffer,  1 for back buffer
    private final int cellWidth = 20; // width of each cell in pixels
    private final int cellHeight = 20; // height of each cell in pixels
    private boolean isPLaying = false;
    Random random = new Random(); // Creating a Random object


    Main() {
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int x = screenSize.width / 2 - WindowSize.width / 2;
        int y = screenSize.height / 2 - WindowSize.height / 2;
        this.setTitle("Conways Game of Life");
        this.setLocation(x, y);
        this.setSize(WindowSize);

        this.setVisible(true);
        this.addMouseListener(this);
        addKeyListener(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.createBufferStrategy(2);
        strategy = getBufferStrategy();
        Thread t = new Thread(this);
        t.start();
    }


    @Override // Override the mouseClicked method
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse clicked");

        int x = e.getX();
        int y = e.getY();

        int row = y / cellHeight; // calculating cell row based on mouse click
        int col = x / cellWidth; // calculating cell column based on mouse click

        gameState[row][col][currentBuffer] = !gameState[row][col][currentBuffer];
        repaint();


        System.out.println("Row: " + row);
        System.out.println("Col: " + col);

    }

    // Implement other MouseListener methods
    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }

            if (isPLaying) {
                updateGameState();
            }
    }
    }

    public void keyPressed(KeyEvent e) {


        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            randomiseGameState();
            System.out.println("randomised");
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            isPLaying = !isPLaying;
        }
    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent e) {
    }


    @Override
    public void paint(Graphics g) {

        g = strategy.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WindowSize.width, WindowSize.height);


//        g.setColor(Color.GREEN);
//        g.fillRect(190,  10,  100,  50); // Position and size of the Random button
//        g.setColor(Color.BLACK);
//        g.drawString("Random",  20,  30); // Position of the Start label
//
//        g.setColor(Color.GREEN);
//        g.fillRect(120,  70,  100,  50); // Position and size of the Random button
//        g.setColor(Color.BLACK);
//        g.drawString("Random",  20,  30); // Position of the Start label


        g.setColor(Color.WHITE);
        for (int row = 0; row < gameState.length; row++) {
            for (int col = 0; col < gameState[row].length; col++) {
                if (isLiveCell(row, col)) { // If the cell is true
                    g.fillRect(col * cellWidth, row * cellHeight, cellWidth, cellHeight);
                }
            }
        }

//        g.setColor(Color.GREEN);
//        g.fillRect(30,40,50,25);
//        g.fillRect(90,40,80,25);
//        g.setColor(Color.WHITE);
//        g.setFont(new Font("TimesRoman",Font.PLAIN,15));
//        g.drawString("Start",35,60);
//        g.drawString("Random",95,60);

        strategy.show();
    }

    public static void main(String[] args) {
        new Main();
    }

    public boolean isLiveCell(int row, int col) {
        return gameState[row][col][currentBuffer];
    }

    public void updateGameState() {
        logic();
        repaint();
    }

    public void randomiseGameState() {

        for (int row = 0; row < gameState.length; row++) {
            for (int col = 0; col < gameState[0].length; col++) {
                boolean randomBoolean = random.nextBoolean(); // Generating a random boolean
                gameState[row][col][currentBuffer] = randomBoolean;
            }
        }
        repaint();
    }

    public void logic() {
        for (int row = 0; row < gameState.length; row++) {
            for (int col = 0; col < gameState[0].length; col++) {
                int liveNeighbours = getLiveNeighbours(row, col);
                boolean isAlive = isLiveCell(row, col);

                // Apply game rules based on the current state of the cell and its neighbors
                if (isAlive) {
                    // If the cell is alive
                    switch (liveNeighbours) {
                        case 0:
                        case 1:
                            gameState[row][col][currentBuffer] = false; // dies by under-population
                            break;
                        case 2:
                        case 3:
                            gameState[row][col][currentBuffer] = true; // lives on to the next generation
                            break;
                        default:
                            if (liveNeighbours > 3) {
                                gameState[row][col][currentBuffer] = false; // dies by overcrowding
                            }
                            break;
                    }
                } else {
                    // If the cell is dead
                    if (liveNeighbours == 3) {
                        gameState[row][col][currentBuffer] = true; // lives as if by reproduction
                    }
                }
            }
        }

        // Switch buffers
        currentBuffer = 1 - currentBuffer;

        repaint();
    }



    public int getLiveNeighbours(int row, int col) {
        int neighbours = 0;
        // 0 being the current cell
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                // Check if the cell is not the current cell itself
                if (x != 0 || y != 0) {
                    int newRow = row + x;
                    int newCol = col + y;

                    // Check if the new position is within the bounds of the board
                    if (newRow >= 0 && newRow < gameState.length && newCol >= 0 && newCol < gameState[0].length) {
                        // If the cell is alive, increment the neighbours count
                        if (gameState[newRow][newCol][currentBuffer]) {
                            neighbours++;
                        }
                    }
                }
            }
        }
        return neighbours;
    }
}
