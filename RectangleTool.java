import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.event.MouseEvent;

public class RectangleTool extends DrawingTool {
    private boolean isDragging = false;
    private int startX1, startY1;
    private Rectangle previewShape;
    public void mouseDragged(MouseEvent e) {
        System.out.println("Mouse dragged");
        if (!isDragging) {
            isDragging = true;
            startX1 = e.getX();
            startY1 = e.getY();
            previewShape = new Rectangle(startX1, startY1, e.getX(), e.getY(), Color.BLACK, Color.BLACK, false,
                    new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        }
        manager.clearPreviewShapes();
        previewShape.setX2(e.getX());
        previewShape.setY2(e.getY());
        manager.addPreviewShape(previewShape);
        manager.draw();
    }

    public void mouseReleased(MouseEvent e) {
        manager.addShape(previewShape);
        manager.clearPreviewShapes();
        manager.draw();
        isDragging = false;
    }
}
