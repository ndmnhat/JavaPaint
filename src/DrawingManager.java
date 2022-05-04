package src;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JScrollPane;

public class DrawingManager implements IDrawingManager {
    private ArrayList<IDrawingManagerEventListener> eventListeners;
    private int currentToolIndex;
    private ArrayList<DrawingTool> tools;
    private DrawingArea drawingArea;
    private JScrollPane scrollPane;
    
    private Stack<Shape> shapes;
    private Stack<Shape> previewShapes;
    private Stack<Shape> undoShapes;

    private Color currentColor;
    private Color currentFillColor;
    private int currentThickness;
    private boolean isFilled;

    public DrawingManager() {
        eventListeners = new ArrayList<IDrawingManagerEventListener>();

        drawingArea = new DrawingArea();
        drawingArea.setManager(this);

        scrollPane = new JScrollPane(drawingArea);
        
        PencilTool pencil = new PencilTool();
        pencil.setManager(this);
        RectangleTool rectTool = new RectangleTool();
        rectTool.setManager(this);
        OvalLineTool ovalLineTool = new OvalLineTool();
        ovalLineTool.setManager(this);

        currentToolIndex = 0;
        tools = new ArrayList<DrawingTool>();
        tools.add(pencil);
        tools.add(rectTool);
        tools.add(ovalLineTool);
        setTool(0);

        shapes = new Stack<Shape>();
        previewShapes = new Stack<Shape>();
        undoShapes = new Stack<Shape>();

        setCurrentColor(Color.BLACK);
        setCurrentFillColor(Color.WHITE);
        setIsFilled(false);
    }

    public boolean getIsFilled() {
        return isFilled;
    }

    public void setIsFilled(boolean isFilled) {
        this.isFilled = isFilled;
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
        notifyFillColorChanged(currentFillColor);
    }
    public Color getCurrentColor() {
        return currentColor;
    }
    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
        tools.get(currentToolIndex).setColor(this.currentColor);
        notifyColorChanged(currentColor);

    }
    @Override
    public void draw() {
        drawingArea.repaint();
        revalidateViewport();
    }

    public void revalidateViewport() {
        scrollPane.getViewport().revalidate();
        scrollPane.getViewport().repaint();
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

    public JScrollPane getScrollDrawingArea() {
        return scrollPane;
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

    @Override
    public void addEventListener(IDrawingManagerEventListener listener) {
        eventListeners.add(listener);
    }

    private void notifyColorChanged(Color newColor) {
        for (IDrawingManagerEventListener listener : eventListeners) {
            listener.colorChanged(newColor);
        }
    }
    
    private void notifyFillColorChanged(Color newColor) {
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

    @Override
    public void loadFile(File file) {
        drawingArea.loadImage(file);
        clearPreviewShapes();
        shapes.clear();
        undoShapes.clear();
        notifyUndoShapesChanged(undoShapes.size());
        notifyShapesChanged(shapes.size());
        draw();
    }

    @Override
    public void saveFileAs(File file) {
        drawingArea.saveImageAs(file);
    }

    @Override
    public void zoom(double addScale) {
        drawingArea.setScale(drawingArea.getScale() + addScale);
        draw();
    }

    @Override
    public double getScale() {
        return drawingArea.getScale();
    }

    @Override
    public int getScaled(double value) {
        return Utility.round(value / drawingArea.getScale());
    }

    @Override
    public void newFile() {
        drawingArea.newImage();
        clearPreviewShapes();
        shapes.clear();
        undoShapes.clear();
        notifyUndoShapesChanged(undoShapes.size());
        notifyShapesChanged(shapes.size());
        draw();
    }
    
}
