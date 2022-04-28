package src;

import java.awt.*;

/**
 *  Interface cho các lớp có thể vẽ shape
 */
public interface IDrawable {

    public void drawLine(Graphics2D g2d, PencilLine line);

    public void drawRect(Graphics2D g2d, Rectangle rect);

    void drawOval(Graphics2D g2g, OvalLine ovalLine);
}
