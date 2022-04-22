import java.awt.Graphics2D;
import java.util.Collection;
import java.util.Stack;

/** 
 * Interface cho DrawingManager
 */
public interface IDrawingManager {
    public void draw();

    public Graphics2D getDrawingGraphics();

    public void addShape(Shape shape);

    public Stack<Shape> getShapes();

    public void addPreviewShape(Shape shape);

    public Stack<Shape> getPreviewShapes();

    public void clearPreviewShapes();
}
