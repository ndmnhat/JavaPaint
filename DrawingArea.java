import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class DrawingArea extends JPanel implements Drawable {
    private Manager manager;
    private BufferedImage canvas;

    public DrawingArea() {
        setVisible(true);
        setDoubleBuffered(true);
        //Graphics2D g = (Graphics2D) getGraphics();
        // if (g == null) {

        // repaint();
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    protected void paintComponent(Graphics g) {
        System.out.println("paint");
        super.paintComponent(g);

        if (canvas == null) {
            canvas = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
            System.out.println(canvas);
            Graphics2D g2d = canvas.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setPaint(Color.WHITE);
            g2d.fillRect(0, 0, 800, 600);
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

    @Override
    public void drawLine(Graphics2D g2d, PencilLine line) {
        g2d.setColor(line.getColor());
        g2d.setStroke(line.getStroke());
        for (Path path : line.getPaths()) {
            g2d.drawLine(path.getX1(), path.getY1(), path.getX2(), path.getY2());
        }
    }

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
}
