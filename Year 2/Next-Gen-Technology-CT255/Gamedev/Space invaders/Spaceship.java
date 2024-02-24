import java.awt.*;

public class Spaceship extends Sprite2D {
    private double xSpeed = 0;
    private final Image image;

    public Spaceship(Image i) {
        super(i); // invoke constructor on superclass Sprite2D
        this.image = i; // Store the passed image
    }

    public void setXSpeed(double dx) {
        xSpeed = dx;
    }

    public void move() {
        // apply current movement
        x += xSpeed;

        // stop movement at screen edge?
        if (x <= 0) {
            x = 0;
            xSpeed = 0;
        } else if (x >= winWidth - getImageWidth(image)) {
            x = winWidth - getImageWidth(image);
            xSpeed = 0;
        }
    }

    private int getImageWidth(Image image) {
        int width = image.getWidth(null);
        if (width == -1) {
            width = 100; // // Default width if actual width is not available
        }
        return width;
    }

}
