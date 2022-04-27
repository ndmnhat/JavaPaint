package src;

import java.awt.Graphics2D;

/**
 *  Interface cho các lớp có thể vẽ shape
 */
public interface IDrawable {
    public void drawLine(Graphics2D g2d, PencilLine line);

    public void drawRect(Graphics2D g2d, Rectangle rect);
}
