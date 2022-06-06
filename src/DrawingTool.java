package src;

import java.awt.event.MouseAdapter;

/**
 * Các công cụ vẽ. Ví dụ: Công cụ vẽ đường thẳng, hình chữ nhật, hình vuông, ...
 */
public abstract class DrawingTool extends MouseAdapter {
    protected IDrawingManager manager;
    /**
     * Hàm này dùng để set lớp src.DrawingManager cụ thể chịu trách nhiệm cho
     * src.DrawingTool này.
     * 
     * @param manager src.DrawingManager cụ thể chịu trách nhiệm cho src.DrawingTool này.
     */
    public void setManager(IDrawingManager manager) {
        this.manager = manager;
    };

    abstract public String getToolName();
}
