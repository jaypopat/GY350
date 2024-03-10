import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.io.*;
import java.util.Random;

public class Main extends JFrame implements Runnable, MouseMotionListener, MouseListener {

    private static final Dimension WindowSize = new Dimension(800, 800);
    private final BufferStrategy strategy;
    public static final int size = 40;

    private boolean[][][] gameState = new boolean[size][size][2];
    private static int currentBuffer = 0; //  0 for front buffer,  1 for back buffer
    private final int cellWidth = 20; // width of each cell in pixels
    private final int cellHeight = 20; // height of each cell in pixels
    private boolean isPLaying = false;
    Random random = new Random(); // Creating a Random object
    private final String gameSaveFile = "gameState.txt";


    Main() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = screenSize.width / 2 - WindowSize.width / 2;
        int y = screenSize.height / 2 - WindowSize.height / 2;
        this.setTitle("Conways Game of Life");
        this.setLocation(x, y);
        this.setSize(WindowSize);
        this.setVisible(true);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.createBufferStrategy(2);
        strategy = getBufferStrategy();
        Thread t = new Thread(this);
        t.start();
    }

    public static void main(String[] args) {
        new Main();
    }


    private void toggleCellState(int row, int col) {
        if (row >= 0 && row < gameState.length && col >= 0 && col < gameState[0].length) {
            gameState[row][col][currentBuffer] = !gameState[row][col][currentBuffer]; // Toggle cell state
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        int row = y / cellHeight; // Calculate cell row based on mouse position during drag
        int col = x / cellWidth; // Calculate cell column based on mouse position during drag

        toggleCellState(row, col);
        repaint();

    }

    @Override // Override the mouseClicked method
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse clicked");

        int x = e.getX();
        int y = e.getY();

        int row = y / cellHeight; // calculating cell row based on mouse click
        int col = x / cellWidth; // calculating cell column based on mouse click

        // Check if "Random" button is clicked
        if (x >= 90 && x <= (90 + 80) && y >= 40 && y <= (40 + 25)) {
            randomiseGameState();
            System.out.println("Randomized");
        }

        // Check if "Start" button is clicked
        if (x >= 30 && x <= (30 + 50) && y >= 40 && y <= (40 + 25)) {
            isPLaying = !isPLaying;
            System.out.println("Playing: " + isPLaying);
        }
        // Check if "Load" button is clicked
        if (x >= 180 && x <= (180 + 80) && y >= 40 && y <= (40 + 25)) {
            loadGameState(gameSaveFile);
            System.out.println("Loaded");
        }

        // Check if "Save" button is clicked
        if (x >= 270 && x <= (270 + 80) && y >= 40 && y <= (40 + 25)) {
            encodeAndWriteGameState(gameState, gameSaveFile);
            System.out.println("Saved");
        } else {
            toggleCellState(row, col);
            repaint();

            System.out.println("Row: " + row);
            System.out.println("Col: " + col);
        }

    }

    public static void encodeAndWriteGameState(boolean[][][] gameState, String fileName) {
        String encodedState = "";


        for (int row = 0; row < gameState.length; row++) {
            for (int col = 0; col < gameState[row].length; col++) {
                boolean isAlive = gameState[row][col][currentBuffer];
                encodedState += isAlive ? '1' : '0';
            }
            encodedState += "\n";

        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(encodedState);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadGameState(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("File does not exist.");
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            for (int row = 0; row < size; row++) {
                String line = reader.readLine();
                if (line != null) {
                    for (int col = 0; col < size; col++) {
                        char cellState = line.charAt(col);
                        gameState[row][col][currentBuffer] = cellState == '1';
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("just here before switching");
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

    @Override
    public void paint(Graphics g) {

        if (strategy == null) {
            return; // Early return if strategy hasn't been initialized yet
        }

        g = strategy.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WindowSize.width, WindowSize.height);


        g.setColor(Color.WHITE);
        for (int row = 0; row < gameState.length; row++) {
            for (int col = 0; col < gameState[row].length; col++) {
                if (gameState[row][col][currentBuffer]) //if cell is live
                { // If the cell is true
                    g.fillRect(col * cellWidth, row * cellHeight, cellWidth, cellHeight);
                }
            }
        }

        g.setColor(Color.GREEN);
        g.fillRect(30, 40, 50, 25);
        g.fillRect(90, 40, 80, 25);

        g.fillRect(180, 40, 80, 25);
        g.fillRect(270, 40, 80, 25);

        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        g.drawString("Start", 35, 60);
        g.drawString("Random", 95, 60);
        g.drawString("Load", 200, 60);
        g.drawString("Save", 300, 60);


        strategy.show();
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


    public void updateGameState() {
        int nextBuffer = 1 - currentBuffer; // Determine the back buffer

        for (int row = 0; row < gameState.length; row++) {
            for (int col = 0; col < gameState[row].length; col++) {
                int liveNeighbours = getLiveNeighbours(row, col);
                boolean isAlive = gameState[row][col][currentBuffer]; // Read from the current buffer

                if (isAlive && (liveNeighbours < 2 || liveNeighbours > 3)) {
                    gameState[row][col][nextBuffer] = false; // Cell dies
                } else if (!isAlive && liveNeighbours == 3) {
                    gameState[row][col][nextBuffer] = true; // Cell becomes alive
                } else {
                    gameState[row][col][nextBuffer] = gameState[row][col][currentBuffer]; // Keeps current state
                }
            }
        }

        currentBuffer = nextBuffer; // Switch the buffers

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
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
