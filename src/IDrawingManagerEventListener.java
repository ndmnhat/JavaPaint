package src;

import java.awt.Color;

public interface IDrawingManagerEventListener {
    public void colorChanged(Color newColor);

    public void fillColorChanged(Color newColor);

    public void thicknessChanged(int newThickness);
    
    public void undoShapesChanged(int newSize);

    public void shapesChanged(int newSize);
}
