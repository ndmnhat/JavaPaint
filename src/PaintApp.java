package src;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class PaintApp extends JFrame {
    private DrawingManager drawingManager;
    private ToolBox toolBox;
    private JScrollPane drawingArea;
    private MenuBar menuBar;
    public PaintApp() {
        // constance
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int TOOL_BOX_WIDTH = 250;
        int SCREEN_HEIGHT = (int)screenSize.getHeight();
        int SCREEN_WIDTH = (int)screenSize.getWidth();
        // init
        drawingManager = new DrawingManager();
        toolBox = new ToolBox(drawingManager);

        // toolBox
        toolBox.setPreferredSize(new Dimension(TOOL_BOX_WIDTH,SCREEN_HEIGHT));
        drawingArea = drawingManager.getScrollDrawingArea();
        drawingArea.setPreferredSize(new Dimension(SCREEN_WIDTH - 340, SCREEN_HEIGHT));

        // menu bar
        menuBar = new MenuBar(drawingManager);

        // frame
        add(toolBox);
        add(drawingArea);
        setJMenuBar(menuBar);

        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setTitle("JavaPaint");
        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });
        addMouseWheelListener(mouseWheelEvent -> {
            if (mouseWheelEvent.isControlDown()) {
                int scrolled = mouseWheelEvent.getUnitsToScroll();
                drawingManager.zoom(-scrolled*0.05);
            }
        });
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PaintApp();
            }
        });
    }
}