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
        JPanel toolBox = new JPanel();
        ColorPicker colorPicker = new ColorPicker(drawingManager);
        JComboBox<String> comboBox = new JComboBox<>(new String[] { "Pencil", "src.Rectangle" });
        ImageIcon undoIcon = new ImageIcon("./images/undo.png");
        ImageIcon redoIcon = new ImageIcon("./images/redo.png");
        JButton undo = new JButton(undoIcon);
        JButton redo = new JButton(redoIcon);

        // undo btn
        undo.setBackground(Color.white);
        undo.setPreferredSize(new Dimension(32,32));
        undo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                drawingManager.undo();
            }
        });
        undo.setEnabled(drawingManager.getShapesCount() > 0);

        // redo btn
        redo.setBackground(Color.white);
        redo.setPreferredSize(new Dimension(32,32));
        redo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                drawingManager.redo();
            }
        });
        redo.setEnabled(drawingManager.getUndoCount() > 0);

        //combo box
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawingManager.setTool(comboBox.getSelectedIndex());
            }
        });

        // drawing manager
        drawingManager.addEventListener(new DrawingManagerEventAdapter() {
            public void undoShapesChanged(int newSize) {
                redo.setEnabled(newSize > 0);
            }
            
            public void shapesChanged(int newSize) {
                undo.setEnabled(newSize > 0);
            }
        });

        // tool box
        toolBox.add(comboBox);
        toolBox.add(undo);
        toolBox.add(redo);
        toolBox.add(colorPicker);
        toolBox.setBackground(Color.white);
        toolBox.setPreferredSize(new Dimension(TOOL_BOX_WIDTH,SCREEN_HEIGHT));
        toolBox.setLayout(new FlowLayout(FlowLayout.LEADING));



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