import java.awt.*;

public class Spaceship extends Sprite2D {
    public Spaceship(Image i, int winWidth) {
        super(i, winWidth);
    }

    public void move() {
        // Define the speed of the player sprite
        double xSpeed = 5;
        // Check if the left/right key is pressed - if yes adjust speed accordingly (+/-)
        if (InvadersApplication.getInstance().isLeft()) x -= xSpeed;
        if (InvadersApplication.getInstance().isRight()) x += xSpeed;
    }
}
