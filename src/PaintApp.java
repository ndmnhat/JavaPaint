package src;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class PaintApp extends JFrame {
    private DrawingManager drawingManager;

    public PaintApp() {
        // constance
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int TOOL_BOX_WIDTH = 250;
        int SCREEN_HEIGHT = (int)screenSize.getHeight();
        int SCREEN_WIDTH = (int)screenSize.getWidth();
        // init
        drawingManager = new DrawingManager();
        ToolBox toolBox = new ToolBox(drawingManager);

        // toolBox
        toolBox.setPreferredSize(new Dimension(TOOL_BOX_WIDTH,SCREEN_HEIGHT));

        // drawing area
        DrawingArea drawingArea = drawingManager.getDrawingArea();
        drawingArea.setPreferredSize(new Dimension(SCREEN_WIDTH - 340, SCREEN_HEIGHT));


        // frame
        add(toolBox);
        add(drawingArea);
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setTitle("src.PaintApp");
        setLayout(new FlowLayout(FlowLayout.LEFT));
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });
    }

    public static void main(String args[]) {
        new PaintApp();
    }

}