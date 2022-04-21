import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

public class DrawingManager implements Manager {
    private int currentTool;
    private ArrayList<DrawingTool> tools;
    private DrawingArea drawingArea;
    
    private Stack<Shape> shapes;
    private Stack<Shape> previewShapes;

    public DrawingManager() {
        drawingArea = new DrawingArea();
        drawingArea.setPreferredSize(new Dimension(640, 480));
        drawingArea.setManager(this);

        Pencil pencil = new Pencil();
        pencil.setManager(this);

        RectangleTool rectTool = new RectangleTool();
        rectTool.setManager(this);

        currentTool = 0;
        tools = new ArrayList<DrawingTool>();
        tools.add(pencil);
        tools.add(rectTool);
        setTool(0);

        shapes = new Stack<Shape>();
        previewShapes = new Stack<Shape>();
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
        drawingArea.removeMouseListener(tools.get(currentTool));
        drawingArea.removeMouseMotionListener(tools.get(currentTool));

        currentTool = index;
        drawingArea.addMouseListener(tools.get(index));
        drawingArea.addMouseMotionListener(tools.get(index));
    }

    public DrawingArea getDrawingArea() {
        return drawingArea;
    }

    @Override
    public Stack<Shape> getShapes() {
        return shapes;
    }

    @Override
    public void addShape(Shape shape) {
        shapes.push(shape);
    }

    @Override
    public void addShapes(Collection<Shape> shapes) {
        this.shapes.addAll(shapes);

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
