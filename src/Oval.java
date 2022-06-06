package src;

import java.awt.*;

public class Oval extends Shape {
    public Oval(int x1, int y1, int x2, int y2, Color color, Color fillColor, boolean isFilled, BasicStroke stroke) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        this.fillColor = fillColor;
        this.isFilled = isFilled;
        this.stroke = stroke;
    }

    @Override
    public void draw(IDrawable area, Graphics2D g2d) {
        area.drawOval(g2d, this);
    }
}
