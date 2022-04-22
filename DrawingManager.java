import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

public class DrawingManager implements IDrawingManager {
    private ArrayList<IDrawingManagerEventListener> eventListeners;
    private int currentToolIndex;
    private ArrayList<DrawingTool> tools;
    private DrawingArea drawingArea;
    
    private Stack<Shape> shapes;
    private Stack<Shape> previewShapes;
    private Stack<Shape> undoShapes;

    private Color currentColor;
    private Color currentFillColor;
    private int currentThickness;
    
    public DrawingManager() {
        drawingArea = new DrawingArea();
        drawingArea.setPreferredSize(new Dimension(640, 480));
        drawingArea.setManager(this);

        PencilTool pencil = new PencilTool();
        pencil.setManager(this);

        RectangleTool rectTool = new RectangleTool();
        rectTool.setManager(this);

        currentToolIndex = 0;
        tools = new ArrayList<DrawingTool>();
        tools.add(pencil);
        tools.add(rectTool);
        setTool(0);

        shapes = new Stack<Shape>();
        previewShapes = new Stack<Shape>();
        undoShapes = new Stack<Shape>();
    }
    public int getCurrentThickness() {
        return currentThickness;
    }
    public void setCurrentThickness(int currentThickness) {
        this.currentThickness = currentThickness;
        notifyThicknessChanged(currentThickness);
    }
    public Color getCurrentFillColor() {
        return currentFillColor;
    }
    public void setCurrentFillColor(Color currentFillColor) {
        this.currentFillColor = currentFillColor;
        notifyfillColorChanged(currentFillColor);
    }
    public Color getCurrentColor() {
        return currentColor;
    }
    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
        notifyColorChanged(currentColor);

    }
    @Override
    public void draw() {
        drawingArea.repaint();
    }

    @Override
    public Graphics2D getDrawingGraphics() {
        return (Graphics2D) drawingArea.getGraphics();
    }
    
    public void setTool(int index) {
        drawingArea.removeMouseListener(tools.get(currentToolIndex));
        drawingArea.removeMouseMotionListener(tools.get(currentToolIndex));

        currentToolIndex = index;
        drawingArea.addMouseListener(tools.get(index));
        drawingArea.addMouseMotionListener(tools.get(index));
    }

    public DrawingArea getDrawingArea() {
        return drawingArea;
    }

    public void undo() {
        if (shapes.size() > 0) {
            undoShapes.push(shapes.pop());
            notifyUndoShapesChanged(undoShapes.size());
            notifyShapesChanged(shapes.size());
        }
        draw();
    }

    public void redo() {
        if (undoShapes.size() > 0) {
            shapes.push(undoShapes.pop());
            notifyUndoShapesChanged(undoShapes.size());
            notifyShapesChanged(shapes.size());
        }
        draw();
    }

    public int getUndoCount() {
        return undoShapes.size();
    }

    public int getShapesCount() {
        return shapes.size();
    }

    @Override
    public Stack<Shape> getShapes() {
        return shapes;
    }

    @Override
    public void addShape(Shape shape) {
        shapes.push(shape);
        undoShapes.clear();
        notifyUndoShapesChanged(undoShapes.size());
        notifyShapesChanged(shapes.size());
    }

    @Override
    public Stack<Shape> getPreviewShapes() {
        return previewShapes;
    }

    @Override
    public void addPreviewShape(Shape shape) {
        previewShapes.push(shape);
    }

    @Override
    public void clearPreviewShapes() {
        previewShapes.clear();
    }

    public void addEventListener(IDrawingManagerEventListener listener) {
        if (eventListeners == null) {
            eventListeners = new ArrayList<IDrawingManagerEventListener>();
        }
        eventListeners.add(listener);
    }

    private void notifyColorChanged(Color newColor) {
        for (IDrawingManagerEventListener listener : eventListeners) {
            listener.colorChanged(newColor);
        }
    }
    
    private void notifyfillColorChanged(Color newColor) {
        for (IDrawingManagerEventListener listener : eventListeners) {
            listener.fillColorChanged(newColor);
        }
    }

    private void notifyThicknessChanged(int newThickness) {
        for (IDrawingManagerEventListener listener : eventListeners) {
            listener.thicknessChanged(newThickness);
        }
    }

    private void notifyUndoShapesChanged(int newSize) {
        for (IDrawingManagerEventListener listener : eventListeners) {
            listener.undoShapesChanged(newSize);
        }
    }

    private void notifyShapesChanged(int newSize) {
        for (IDrawingManagerEventListener listener : eventListeners) {
            listener.shapesChanged(newSize);
        }
    }
}
