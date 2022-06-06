package src;

import java.awt.Color;

public abstract class DrawingManagerEventAdapter implements IDrawingManagerEventListener{
    @Override
    public void colorChanged(Color newColor) {
    }

    @Override
    public void fillColorChanged(Color newColor) {
    }

    @Override
    public void thicknessChanged(int newThickness) {
    }

    @Override
    public void undoShapesChanged(int newSize) {
    }

    @Override
    public void shapesChanged(int newSize) {
    }

    @Override
    public void toolChanged(String newTool) {
    }
}
