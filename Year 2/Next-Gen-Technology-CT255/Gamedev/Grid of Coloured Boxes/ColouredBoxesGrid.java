import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class ColouredBoxesGrid extends JFrame {

    // setting value of the constants
    private static final Dimension WindowSize = new Dimension(610, 600);
    private static final int SQUARE_LEN = 40;
    private static final int margin_between_squares = 10;


    ColouredBoxesGrid() {
        this.setTitle("Pacman, or something...");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Center the screen
        // Get the size of the default screen
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        // Calculate the x-coordinate for the window's top left corner so that the window is centered horizontally
        int x = screenSize.width / 2 - WindowSize.width / 2;

        // Calculate the y-coordinate for the window's top left corner so that the window is centered vertically
        int y = screenSize.height / 2 - WindowSize.height / 2;

        // Set the bounds of the window, placing it in the center of the screen
        setBounds(x, y, WindowSize.width, WindowSize.height);

        // Make the window visible
        setVisible(true);
    }

    public void paint(Graphics _g) {
        Graphics2D g2D = (Graphics2D) _g;
        Rectangle rectangle = this.getBounds();

        int h = maxSquares(rectangle.getHeight());
        //calculating the number of squares that can fit vertically in the bounding rectangle
        int w = maxSquares(rectangle.getWidth());
        //calculating the number of squares that can fit horizontally in the bounding rectangle

        // Loop through the width of the grid
        for (int x = 0; x < w; x++) {
            // Loop through the height of the grid
            for (int y = 0; y < h; y++) {
                // Generate random RGB values for the color
                int r = randColor();
                int g = randColor();
                int b = randColor();

                // Create a new color using the randomly generated RGB values
                Color mycolor = new Color(r, g, b);

                // Set the current color of the graphics object to the newly created color
                g2D.setColor(mycolor);

                Rectangle2D rect = getRectangle2D(x, y);
                // Calls getRectangle2D method with parameters x and y, and assigns the resulting Rectangle2D object to rect

                // Draw the outline of the square
                g2D.draw(rect);
                // Fill the square with the current color
                g2D.fill(rect);

            }
        }

    }

    private static Rectangle2D getRectangle2D(int x, int y) {
        // Calculate the X position of the top left corner of the square
        int topLeftCornerX = margin_between_squares + (x * (SQUARE_LEN + margin_between_squares));
        // Calculate the Y position of the top left corner of the square
        int topLeftCornerY = y * (margin_between_squares + SQUARE_LEN);
        // Create a new square at the calculated position with the calculated dimensions
        return new Rectangle2D.Double(topLeftCornerX, topLeftCornerY, SQUARE_LEN, SQUARE_LEN);
    }

    private static int randColor() {
        return (int) (Math.random() * 255);
    } // utility function to return random r/g/b values

    private static int maxSquares(double length) {
        return (int) length / (SQUARE_LEN + margin_between_squares);
    } //returns max squares which can fit in length given

    public static void main(String[] args) {
        new ColouredBoxesGrid(); // launches the jframe gui
    }
}
