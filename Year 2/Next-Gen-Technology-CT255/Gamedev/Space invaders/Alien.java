import javax.swing.*;
import java.awt.*;

public class Alien extends Sprite2D {
    static double xSpeed=0;
    boolean isAlive = true;
    private int framesDrawn =  0; // Make framesDrawn a field of the class
    private static final Image image2 = new ImageIcon("src/images/alien_ship_2.png").getImage();


    public Alien(Image i) {
        super(i); // invoke constructor on superclass Sprite2D
    }

    // public interface
    public boolean move() {
        x+=xSpeed;

        // direction reversal needed?
        return x <= 0 || x >= winWidth - myImage.getWidth(null);
    }
    @Override
    public void paint(Graphics g) {
        framesDrawn++;
        if(isAlive) {
            framesDrawn++;
            if (framesDrawn %  100 <  50) {
                g.drawImage(myImage, (int)x, (int)y, null);
            } else {
                g.drawImage(image2, (int)x, (int)y, null);
            }
        }

    }


    public static void setFleetXSpeed(double dx) {
        xSpeed=dx;
    }

    public boolean checkCollision(int xx, int yy, int width, int height) {
        if (!isAlive) return false;

        // collision detection logic
        boolean xOverlap = (xx < x + myImage.getWidth(null) && xx + width > x);
        boolean yOverlap = (yy < y + myImage.getHeight(null) && yy + height > y);

        if (xOverlap && yOverlap) {
            this.isAlive = false;
            System.out.println("hit target");
            return true;
        }
        return false;
    }


    public static void reverseDirection() {
        xSpeed=-xSpeed;
    }

    public void jumpDownwards() {
        y+=20;
    }
}

