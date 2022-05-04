package src;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Stack;

/**
 * Đường được vẽ theo trỏ chuột
 */
public class PencilLine extends Shape {
    private Stack<Path> paths;
    
    public PencilLine(Color color, Color fillColor, BasicStroke stroke) {
        this.color = color;
        this.fillColor = fillColor;
        this.stroke = stroke;
        this.paths = new Stack<Path>();
    }
    
    
    /** 
     * Lấy các đường nhỏ trong 1 đường lớn được vẽ theo trỏ chuột
     * @return Stack<src.Path>
     */
    public Stack<Path> getPaths() {
        return this.paths;
    }

    
    /** 
     * Thêm 1 đường nhỏ vào đường lớn được vẽ theo trỏ chuột
     * @param path Đường nhỏ cần thêm.
     */
    public void addPath(Path path) {
        paths.add(path);
    }

    
    /** 
     * Gọi đến hàm drawLine được định nghĩa bởi lớp hiện thực src.IDrawable
     * @param area
     * @param g2d
     */
    @Override
    public void draw(IDrawable area, Graphics2D g2d) {
        area.drawLine(g2d, this);
    }
}
