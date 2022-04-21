import java.awt.Graphics2D;

public interface Drawable {
    public void drawLine(Graphics2D g2d, PencilLine line);

    public void drawRect(Graphics2D g2d, Rectangle rect);
}
