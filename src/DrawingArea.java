package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * Lớp này là khu vực để vẽ các shape lên màn hình. 
 */
public class DrawingArea extends JPanel implements IDrawable {
    private IDrawingManager manager;
    private BufferedImage canvas;

    public DrawingArea() {
        setVisible(true);
        setDoubleBuffered(true);
    }

    
    /** 
     * Hàm này dùng để set lớp src.DrawingManager cụ thể chịu trách nhiệm cho src.DrawingArea này.
     * @param manager src.DrawingManager cụ thể chịu trách nhiệm cho src.DrawingArea này.
     */
    public void setManager(IDrawingManager manager) {
        this.manager = manager;
    }

    
    /** 
     * Hàm này thực hiện việc vẽ được thực hiện như thế nào.
     * Đầu tiên là vẽ ảnh nền, rồi đến các shape đã vẽ, rồi đến preview shape.
     * @param g Graphics được vẽ lên.
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (canvas == null) {
            canvas = new BufferedImage(2000, 1000, BufferedImage.TYPE_INT_ARGB);
            System.out.println(canvas);
            Graphics2D g2d = canvas.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setPaint(Color.WHITE);
            g2d.fillRect(0, 0, 2000, 1000);
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(canvas, 0, 0, null);

        for (Shape shape : manager.getShapes()) {
            shape.draw(this, g2);
        }

        for (Shape shape : manager.getPreviewShapes()) {
            shape.draw(this, g2);
        }
    }

    
    /** 
     * Hàm vẽ đường theo trỏ chuột
     * @param g2d Graphics2D được vẽ lên.
     * @param line Line cần vẽ vào g2d.
     */
    @Override
    public void drawLine(Graphics2D g2d, PencilLine line) {
        g2d.setColor(line.getColor());
        g2d.setStroke(line.getStroke());
        for (Path path : line.getPaths()) {
            g2d.drawLine(path.getX1(), path.getY1(), path.getX2(), path.getY2());
        }
    }

    
    /** 
     * Hàm vẽ hình chữ nhật
     * @param g2d Graphics2D được vẽ lên.
     * @param rect Hình chữ nhật cần vẽ vào g2d.
     */
    @Override
    public void drawRect(Graphics2D g2d, Rectangle rect) {
        g2d.setColor(rect.getColor());
        g2d.setStroke(rect.getStroke());
        
        int startX = rect.getX1() < rect.getX2() ? rect.getX1() : rect.getX2();
        int startY = rect.getY1() < rect.getY2() ? rect.getY1() : rect.getY2();

        int width = Math.abs(rect.getX2() - rect.getX1());
        int height = Math.abs(rect.getY2() - rect.getY1());
        
        g2d.drawRect(startX, startY, width, height);
    }

    @Override
    public void drawOval(Graphics2D g2d, OvalLine ovalLine) {
        g2d.setColor(ovalLine.getColor());
        g2d.setStroke(ovalLine.getStroke());

        int startX = ovalLine.getX1() < ovalLine.getX2() ? ovalLine.getX1() : ovalLine.getX2();
        int startY = ovalLine.getY1() < ovalLine.getY2() ? ovalLine.getY1() : ovalLine.getY2();

        int width = Math.abs(ovalLine.getX2() - ovalLine.getX1());
        int height = Math.abs(ovalLine.getY2() - ovalLine.getY1());

        g2d.drawOval(startX, startY, width, height);
    }
}
