package src;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.util.Stack;

/** 
 * Interface cho src.DrawingManager
 */
public interface IDrawingManager {
    public void addEventListener(IDrawingManagerEventListener listener);
    public void draw();

    public Graphics2D getDrawingGraphics();

    public void addShape(Shape shape);

    public Stack<Shape> getShapes();

    public void addPreviewShape(Shape shape);

    public Stack<Shape> getPreviewShapes();

    public void clearPreviewShapes();

    public Color getCurrentColor();

    public void setCurrentColor(Color currentColor);

    public Color getCurrentFillColor();

    public void setCurrentFillColor(Color currentFillColor);

    public int getCurrentThickness();

    public void setCurrentThickness(int currentThickness);

    public void loadFile(File file);

    public void saveFileAs(File file);

    public void newFile();
    
    public void undo();

    public void redo();

    public void zoom(double addScale);

    public double getScale();

    public int getScaled(double value);

    public void setTool(String key);

    public int getUndoCount();

    public int getShapesCount();

    public boolean getIsFilled();
    public void setIsFilled(boolean value);
}
