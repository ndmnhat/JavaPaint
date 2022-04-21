import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;

public class PaintApp extends JFrame {
    private DrawingManager drawingManager;

    public PaintApp() {
        drawingManager = new DrawingManager();
        add(drawingManager.getDrawingArea());

        JComboBox<String> combobox = new JComboBox<>(new String[] { "Pencil", "Rectangle" });
        combobox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawingManager.setTool(combobox.getSelectedIndex());
            }
        });
        add(combobox);
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(800, 600));
        setVisible(true);
        setTitle("PaintApp");
        setLayout(new FlowLayout());
        setVisible(true);
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