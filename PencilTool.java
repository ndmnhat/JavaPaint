import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class PencilTool extends DrawingTool {
    private int prevX1, prevY1;
    private boolean isDragging = false;
    private PencilLine previewShape;
    public void mouseDragged(MouseEvent e) {
        System.out.println("Mouse dragged");
        if (!isDragging) {
            isDragging = true;
            manager.clearPreviewShapes();
            previewShape = new PencilLine(Color.BLACK, Color.BLACK,
                    new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            manager.addPreviewShape(previewShape);
            prevX1 = e.getX();
            prevY1 = e.getY();
        }
        previewShape.addPath(new Path(prevX1, prevY1, e.getX(), e.getY()));
        prevX1 = e.getX();
        prevY1 = e.getY();
        manager.draw();
    }

    public void mouseReleased(MouseEvent e) {
        manager.addShape(previewShape);
        manager.clearPreviewShapes();
        manager.draw();
        isDragging = false;
    }
}
