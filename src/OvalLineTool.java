package src;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class OvalLineTool extends DrawingTool {
    private boolean isDragging = false;
    private int startX1, startY1;
    private OvalLine previewShape;
    public void mouseDragged(MouseEvent e) {

        if (!isDragging) {
            isDragging = true;
            startX1 = e.getX();
            startY1 = e.getY();
            previewShape = new OvalLine(startX1, startY1, e.getX(), e.getY(), this.color, this.color, false,
                    new BasicStroke(this.strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        }

        manager.clearPreviewShapes();
        previewShape.setX2(e.getX());
        previewShape.setY2(e.getY());

        // handle logic if SHIFT btn is pressed => draw square
        if (Keyboard.isKeyPressed(KeyEvent.VK_SHIFT)) {
            int x1 = previewShape.getX1();
            int x2 = previewShape.getX2();
            int squareEdge = Math.abs(previewShape.getY2() - previewShape.getY1());

            if (x1 < x2) {
                previewShape.setX2(x1 + squareEdge);
            } else {
                previewShape.setX2(x1 - squareEdge);
            }
        }
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