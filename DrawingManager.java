import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

public class DrawingManager implements Manager {
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
    }
    public int getCurrentThickness() {
        return currentThickness;
    }
    public void setCurrentThickness(int currentThickness) {
        this.currentThickness = currentThickness;
    }
    public Color getCurrentFillColor() {
        return currentFillColor;
    }
    public void setCurrentFillColor(Color currentFillColor) {
        this.currentFillColor = currentFillColor;
    }
    public Color getCurrentColor() {
        return currentColor;
    }
    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
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
        }
        draw();
    }

    public void redo() {
        if (undoShapes.size() > 0) {
            shapes.push(undoShapes.pop());
        }
        draw();
    }

    public int getUndoCount() {
        return undoShapes.size();
    }

    @Override
    public Stack<Shape> getShapes() {
        return shapes;
    }

    @Override
    public void addShape(Shape shape) {
        shapes.push(shape);
        undoShapes.clear();
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
}
