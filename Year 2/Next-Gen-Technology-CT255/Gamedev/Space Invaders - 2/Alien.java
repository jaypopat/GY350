import java.awt.*;

public class Alien extends Sprite2D {
    public static int direction = 1; // 1 for right, -1 for left

    public Alien(Image i, int winWidth) {
        super(i, winWidth);
    }

    public boolean move() {
        this.x += this.xSpeed * direction;
        return isAtEdge(winWidth);
    }

    public boolean isAtEdge(int windowWidth) {
        return x <= 0 || x >= windowWidth - myImage.getWidth(null);
    }

    public void moveDown() {
        y += 10;
    }
    public static void changeDirection() {
        direction *= -1;
        for (Alien alien : InvadersApplication.AliensArray) {
            alien.moveDown();
        }
    }
    // Inside your Alien class
    public void initPosition(int i) {
        // Number of aliens per row
        int aliensPerRow =  10;
        int row = i / aliensPerRow; // Calculate the current row
        int col = i % aliensPerRow; // Calculate the current column
        // Calculate the x position with added horizontal margin
        int spacingAlien =  70;
        int horizontalMargin =  50;
        int xPosition = col * spacingAlien + horizontalMargin;
        // Calculate the y position with added vertical margin
        int verticalMarginAlien =  50;
        int yPosition = row * spacingAlien + verticalMarginAlien;

        this.setPosition(xPosition, yPosition);
    }

}
