import java.awt.event.MouseAdapter;

public abstract class DrawingTool extends MouseAdapter {
    protected Manager manager;
    public void setManager(Manager manager) {
        this.manager = manager;
    };
}
