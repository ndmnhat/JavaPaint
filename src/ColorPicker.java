package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ColorPicker extends JButton {

    private JDialog dialog;
    private Container container;
    private JButton pickColor;
    private JButton cancel;
    private JColorChooser colorChooser;
    public ColorPicker(IDrawingManager drawingManager) {
        dialog = new JDialog();
        pickColor = new JButton("Pick");
        cancel = new JButton("Cancel");

        dialog.setVisible(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dialog.setVisible(true);
            }
        });

        this.setPreferredSize(new Dimension(30, 30));
        this.setBackground(Color.BLACK);

        dialog.setBounds(200, 200, 660, 400);
        container = dialog.getContentPane();
        container.setLayout(new FlowLayout());

        colorChooser = new JColorChooser();
        pickColor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ColorPicker.this.setBackground(colorChooser.getColor());
                dialog.setVisible(false);
            }
        });

        cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dialog.setVisible(false);
            }
        });

        container.add(colorChooser);
        container.add(cancel);
        container.add(pickColor);
    }

    public Color getChooserColor() {
        return colorChooser.getColor();
    }

    public void addPickColorListener(MouseListener listener) {
        pickColor.addMouseListener(listener);
    }
}



