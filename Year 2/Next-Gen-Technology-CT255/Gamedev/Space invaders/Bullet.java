import java.awt.*;

public class Bullet extends Sprite2D {
    private static final int speed = 20;

    public Bullet(Image i) {
        super(i);
        System.out.println("Bullet created"); // Debug print
    }
    public boolean move() {
        this.y -= speed;

        return !(this.y < 0);
    }

}
