import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Stack;

public class PencilLine extends Shape {
    private Stack<Path> paths;
    
    public PencilLine(Color color, Color fillColor, BasicStroke stroke) {
        this.color = color;
        this.fillColor = fillColor;
        this.stroke = stroke;
        this.paths = new Stack<Path>();
    }
    
    public Stack<Path> getPaths() {
        return this.paths;
    }

    public void addPath(Path path) {
        paths.add(path);
    }

    @Override
    public void draw(Drawable area, Graphics2D g2d) {
        area.drawLine(g2d, this);
    }
}
