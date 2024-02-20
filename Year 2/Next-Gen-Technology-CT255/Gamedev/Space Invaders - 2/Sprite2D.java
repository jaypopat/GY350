import java.awt.*;

public class Sprite2D {
    // member data
    protected double x, y;
    protected double xSpeed = 2;
    protected Image myImage;
    public int winWidth;

    // constructor
    public Sprite2D(Image i, int winWidth) {
        this.myImage=i;
        this.winWidth = winWidth;
    }

    public void setPosition(double xx, double yy) {
        x = xx;
        y = yy;
    }

    public void paint(Graphics g) {
        g.drawImage(myImage, (int) x, (int) y, null);
    }
}