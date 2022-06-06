package src;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class OvalTool extends DrawingTool {
    private boolean isDragging = false;
    private int startX1, startY1;
    private Oval previewShape;
    public void mouseDragged(MouseEvent e) {

        if (!isDragging) {
            isDragging = true;
            startX1 = manager.getScaled(e.getX());
            startY1 = manager.getScaled(e.getY());
            previewShape = new Oval(startX1, startY1, startX1, startY1, manager.getCurrentColor(), manager.getCurrentFillColor(), manager.getIsFilled(),
                    new BasicStroke(manager.getCurrentThickness(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        }

        manager.clearPreviewShapes();
        previewShape.setX2(manager.getScaled(e.getX()));
        previewShape.setY2(manager.getScaled(e.getY()));

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

    @Override
    public String getToolName() {
        return "Oval";
    }
}
