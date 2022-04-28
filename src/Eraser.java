package src;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Eraser extends DrawingTool{
    private Rectangle eraser;

    public void mouseDragged(MouseEvent e) {
        manager.addPreviewShape(eraser);
        manager.draw();
    }
}
