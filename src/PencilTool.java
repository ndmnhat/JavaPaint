package src;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.event.MouseEvent;

/**
 * Công cụ vẽ theo trỏ chuột
 */
public class PencilTool extends DrawingTool {
    private int prevX1, prevY1;
    private boolean isDragging = false;
    private PencilLine previewShape;
    
    /** 
     * Xử lý khi người dùng kéo chuột
     * 
     * @param e MouseEvent
     */
    public void mouseDragged(MouseEvent e) {
        System.out.println("Mouse dragged" + isDragging);
        if (!isDragging) {
            isDragging = true;
            manager.clearPreviewShapes();
            previewShape = new PencilLine(color, color,
                    new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            manager.addPreviewShape(previewShape);
            prevX1 = e.getX();
            prevY1 = e.getY();
        }
        previewShape.addPath(new Path(prevX1, prevY1, e.getX(), e.getY()));
        prevX1 = e.getX();
        prevY1 = e.getY();
        manager.draw();
    }


    /**
     * Xử lý khi người dùng thả chuột
     * 
     * @param e MouseEvent
     */
    public void mouseReleased(MouseEvent e) {
        manager.addShape(previewShape);
        manager.clearPreviewShapes();
        manager.draw();
        isDragging = false;
    }
}
