import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

public class PaintApp extends JFrame {
    private DrawingManager drawingManager;

    public PaintApp() {
        drawingManager = new DrawingManager();

        JComboBox<String> combobox = new JComboBox<>(new String[] { "Pencil", "Rectangle" });
        
        JButton btn = new JButton("Undo");
        btn.setEnabled(drawingManager.getShapesCount() > 0);

        JButton btn2 = new JButton("Redo");
        btn2.setEnabled(drawingManager.getUndoCount() > 0);

        combobox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawingManager.setTool(combobox.getSelectedIndex());
            }
        });

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                drawingManager.undo();
            }
        });
        btn2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                drawingManager.redo();
            }
        });

        drawingManager.addEventListener(new DrawingManagerEventAdapter() {
            public void undoShapesChanged(int newSize) {
                btn2.setEnabled(newSize > 0);
            }
            
            public void shapesChanged(int newSize) {
                btn.setEnabled(newSize > 0);
            }
        });

        add(drawingManager.getDrawingArea());
        add(combobox);
        add(btn2);        
        add(btn);

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