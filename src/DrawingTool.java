package src;

import java.awt.*;
import java.awt.event.MouseAdapter;

/**
 * Các công cụ vẽ. Ví dụ: Công cụ vẽ đường thẳng, hình chữ nhật, hình vuông, ...
 */
public abstract class DrawingTool extends MouseAdapter {
    protected IDrawingManager manager;
    protected Color color = Color.BLACK;
    protected int strokeWidth = 5;
    /**
     * Hàm này dùng để set lớp src.DrawingManager cụ thể chịu trách nhiệm cho
     * src.DrawingTool này.
     * 
     * @param manager src.DrawingManager cụ thể chịu trách nhiệm cho src.DrawingTool này.
     */
    public void setManager(IDrawingManager manager) {
        this.manager = manager;
    };

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }
}
