import java.awt.Graphics2D;
import java.util.Collection;
import java.util.Stack;

public interface Manager {
    public void draw();

    public Graphics2D getDrawingGraphics();

    public void addShape(Shape shape);
    public void addShapes(Collection<Shape> shapes);

    public Stack<Shape> getShapes();

    public void addPreviewShape(Shape shape);

    public Stack<Shape> getPreviewShapes();

    public void clearPreviewShapes();
}
