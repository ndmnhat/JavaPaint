package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Lớp này là khu vực để vẽ các shape lên màn hình. 
 */
public class DrawingArea extends JPanel implements IDrawable {
    private IDrawingManager manager;
    private BufferedImage canvas;
    private int canvasWidth = 2000, canvasHeight = 1000;
    private double scale;
    
    public DrawingArea() {
        setVisible(true);
        setDoubleBuffered(true);
        setScale(1);
        setLayout(new BorderLayout());
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        if (scale >= 0.1 && scale < 5) {
            this.scale = scale;   
        }
    }

    /** 
     * Hàm này dùng để set lớp src.DrawingManager cụ thể chịu trách nhiệm cho src.DrawingArea này.
     * @param manager src.DrawingManager cụ thể chịu trách nhiệm cho src.DrawingArea này.
     */
    public void setManager(IDrawingManager manager) {
        this.manager = manager;
    }

    public void loadImage(File file) {
        try {
            canvas = ImageIO.read(file);
            canvasWidth = canvas.getWidth();
            canvasHeight = canvas.getHeight();
        } catch (IOException e) {

        }
    }
    
    public void saveImageAs(File file) {
        BufferedImage bImg = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_ARGB);
        double tempScale = getScale();
        setScale(1);
        Graphics2D cg = bImg.createGraphics();
        paintComponent(cg);
        setScale(tempScale);
        try {
            if (ImageIO.write(bImg, "png", file)) {
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
            canvasWidth = canvas.getWidth();
            canvasHeight = canvas.getHeight();
            Graphics2D g2d = canvas.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setPaint(Color.WHITE);
            g2d.fillRect(0, 0, 2000, 1000);
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.scale(scale, scale);
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
        int X1 = rect.getX1();
        int Y1 = rect.getY1();
        int X2 = rect.getX2();
        int Y2 = rect.getY2();

        int startX = X1 < X2 ? X1 : X2;
        int startY = Y1 < Y2 ? Y1 : Y2;

        int width = Math.abs(X2 - X1);
        int height = Math.abs(Y2 - Y1);

        if (rect.getIsFilled()) {
            g2d.setColor(rect.getFillColor());
            g2d.fillRect(startX, startY, width, height);
        }
        g2d.setColor(rect.getColor());
        g2d.setStroke(rect.getStroke());
        g2d.drawRect(startX, startY, width, height);
    }

    @Override
    public void drawOval(Graphics2D g2d, OvalLine ovalLine) {
        int X1 = ovalLine.getX1();
        int Y1 = ovalLine.getY1();
        int X2 = ovalLine.getX2();
        int Y2 = ovalLine.getY2();

        int startX = X1 < X2 ? X1 : X2;
        int startY = Y1 < Y2 ? Y1 : Y2;

        int width = Math.abs(X2 - X1);
        int height = Math.abs(Y2 - Y1);

        if (ovalLine.getIsFilled()) {
            g2d.setColor(ovalLine.getFillColor());
            g2d.fillOval(startX, startY, width, height);
        }
        g2d.setColor(ovalLine.getColor());
        g2d.setStroke(ovalLine.getStroke());
        g2d.drawOval(startX, startY, width, height);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(new Dimension(Utility.round(canvasWidth * scale), Utility.round(canvasHeight * scale)));
    }

    public void newImage() {
        canvas = null;
    }
}
