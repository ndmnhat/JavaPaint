import java.awt.event.MouseAdapter;

/**
 * Các công cụ vẽ. Ví dụ: Công cụ vẽ đường thẳng, hình chữ nhật, hình vuông, ...
 */
public abstract class DrawingTool extends MouseAdapter {
    protected IDrawingManager manager;
    
    /**
     * Hàm này dùng để set lớp DrawingManager cụ thể chịu trách nhiệm cho
     * DrawingTool này.
     * 
     * @param manager DrawingManager cụ thể chịu trách nhiệm cho DrawingTool này.
     */
    public void setManager(IDrawingManager manager) {
        this.manager = manager;
    };
}
