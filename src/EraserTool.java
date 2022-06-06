package src;
import java.awt.BasicStroke;
import java.awt.event.MouseEvent;

public class EraserTool extends DrawingTool{
    private int prevX1, prevY1;
    private boolean isDragging = false;
    private PencilLine previewShape;

    /**
     * Xử lý khi người dùng kéo chuột
     * 
     * @param e MouseEvent
     */
    public void mouseDragged(MouseEvent e) {
        if (!isDragging) {
            isDragging = true;
            manager.clearPreviewShapes();
            previewShape = new PencilLine(manager.getCurrentFillColor(), manager.getCurrentFillColor(),
                    new BasicStroke(manager.getCurrentThickness(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            manager.addPreviewShape(previewShape);
            prevX1 = manager.getScaled(e.getX());
            prevY1 = manager.getScaled(e.getY());
        }
        int X = manager.getScaled(e.getX());
        int Y = manager.getScaled(e.getY());
        previewShape.addPath(new Path(prevX1, prevY1, X, Y));
        prevX1 = manager.getScaled(e.getX());
        prevY1 = manager.getScaled(e.getY());
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

    @Override
    public String getToolName() {
        return "Eraser";
    }
}
