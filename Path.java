/**
 * Các đường nhỏ trong 1 đường lớn được vẽ theo trỏ chuột
 */
public class Path {
    private int x1, y1, x2, y2;

    public Path() {
    }

    public Path(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    
    /** 
     * Get x1
     * @return int x1
     */
    public int getX1() {
        return this.x1;
    }

    
    /** 
     * Set x1
     * @param x1
     */
    public void setX1(int x1) {
        this.x1 = x1;
    }

    
    /** 
     * Get y1
     * @return int y1
     */
    public int getY1() {
        return this.y1;
    }

    
    /** 
     * Set y1
     * @param y1
     */
    public void setY1(int y1) {
        this.y1 = y1;
    }

    
    /** 
     * Get x2
     * @return int x2
     */
    public int getX2() {
        return this.x2;
    }

    
    /** 
     * Set x2
     * @param x2
     */
    public void setX2(int x2) {
        this.x2 = x2;
    }

    
    /** 
     * Get y2
     * @return int y2
     */
    public int getY2() {
        return this.y2;
    }

    
    /** 
     * Set y2
     * @param y2
     */
    public void setY2(int y2) {
        this.y2 = y2;
    }
}
