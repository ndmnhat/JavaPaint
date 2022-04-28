package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ToolBox extends JPanel {
    private ColorPicker colorPicker;
    private JComboBox<String> comboBox;
    private ImageIcon undoIcon;
    private ImageIcon redoIcon;
    private JButton undo;
    private JButton redo;

    public ToolBox(DrawingManager drawingManager) {
        colorPicker = new ColorPicker(drawingManager);
        comboBox = new JComboBox<>(new String[] { "Pencil", "Rectangle", "Oval line" });
        undoIcon = new ImageIcon("./images/undo.png");
        redoIcon = new ImageIcon("./images/redo.png");
        undo = new JButton(undoIcon);
        redo = new JButton(redoIcon);

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

        add(comboBox);
        add(undo);
        add(redo);
        add(colorPicker);
        setBackground(Color.white);
        setLayout(new FlowLayout(FlowLayout.LEADING));
    }

    public ColorPicker getColorPicker() {
        return colorPicker;
    }

    public void setColorPicker(ColorPicker colorPicker) {
        this.colorPicker = colorPicker;
    }

    public JComboBox<String> getComboBox() {
        return comboBox;
    }

    public void setComboBox(JComboBox<String> comboBox) {
        this.comboBox = comboBox;
    }

    public ImageIcon getUndoIcon() {
        return undoIcon;
    }

    public void setUndoIcon(ImageIcon undoIcon) {
        this.undoIcon = undoIcon;
    }

    public ImageIcon getRedoIcon() {
        return redoIcon;
    }

    public void setRedoIcon(ImageIcon redoIcon) {
        this.redoIcon = redoIcon;
    }

    public JButton getUndo() {
        return undo;
    }

    public void setUndo(JButton undo) {
        this.undo = undo;
    }

    public JButton getRedo() {
        return redo;
    }

    public void setRedo(JButton redo) {
        this.redo = redo;
    }
}
