package src;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class ToolBox extends JPanel {
    private ColorPicker colorPicker, fillColorPicker;
    private JButton pencil;
    private JButton eraser;
    private JButton rectangle;
    private JButton oval;
    private JButton undo;
    private JButton redo;
    private JToggleButton fill;
    private JSlider thicknessSlider;

    public ToolBox(IDrawingManager drawingManager) {
        colorPicker = new ColorPicker(drawingManager);
        fillColorPicker = new ColorPicker(drawingManager);

        //load image icon
        ImageIcon pencilIcon = new ImageIcon("./images/pencil.png");
        ImageIcon eraserIcon = new ImageIcon("./images/eraser.png");
        ImageIcon rectangleIcon = new ImageIcon("./images/rectangular.png");
        ImageIcon ovalIcon = new ImageIcon("./images/oval.png");
        ImageIcon undoIcon = new ImageIcon("./images/undo.png");
        ImageIcon redoIcon = new ImageIcon("./images/redo.png");
        ImageIcon fillIcon = new ImageIcon("./images/fill.png");
        ImageIcon noFillIcon = new ImageIcon("./images/noFill.png");

        pencil = new JButton(pencilIcon);
        eraser = new JButton(eraserIcon);
        rectangle = new JButton(rectangleIcon);
        oval = new JButton(ovalIcon);
        undo = new JButton(undoIcon);
        redo = new JButton(redoIcon);
        fill = new JToggleButton(noFillIcon, false);
        thicknessSlider = new JSlider(1, 20, drawingManager.getCurrentThickness());

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

        //drawing tool
        HashMap<String, JButton> toolButtons = new HashMap<String, JButton>();
        toolButtons.put("Pencil", pencil);
        toolButtons.put("Eraser", eraser);
        toolButtons.put("Rectangle", rectangle);
        toolButtons.put("Oval", oval);

        toolButtons.forEach((name, button) -> {
            button.setBackground(Color.WHITE);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    drawingManager.setTool(name);
                }
            });
        });
        pencil.setBackground(Color.LIGHT_GRAY);

        // slider
        Box thicknessBox = Box.createVerticalBox();
        JLabel thicknessLabel = new JLabel("Thickness");

        thicknessSlider.setMinorTickSpacing(1);
        thicknessSlider.setMajorTickSpacing(3);
        thicknessSlider.setPaintTicks(true);
        thicknessSlider.setPaintLabels(true);
        thicknessSlider.setPaintTrack(true);
        thicknessSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                drawingManager.setCurrentThickness(thicknessSlider.getValue());
            }
        });

        thicknessBox.add(thicknessLabel);
        thicknessBox.add(thicknessSlider);

        // color picker
        colorPicker.setBackground(drawingManager.getCurrentColor());
        colorPicker.addPickColorListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                drawingManager.setCurrentColor(colorPicker.getChooserColor());
            }
        });

        // fill color picker
        fillColorPicker.setBackground(drawingManager.getCurrentFillColor());
        fillColorPicker.addPickColorListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                drawingManager.setCurrentFillColor(fillColorPicker.getChooserColor());
            }
        });

        // fill
        fill.setBackground(Color.WHITE);
        fill.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    drawingManager.setIsFilled(true);
                    fill.setIcon(fillIcon);
                }
                else {
                    drawingManager.setIsFilled(false);
                    fill.setIcon(noFillIcon);
                }
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

            public void toolChanged(String newTool) {
                toolButtons.forEach((name, button) -> {
                    if (name.equals(newTool)) {
                        button.setBackground(Color.LIGHT_GRAY);
                    } else {
                        button.setBackground(Color.WHITE);
                    }
                });
            }
        });

        add(pencil);
        add(eraser);
        add(rectangle);
        add(oval);
        add(undo);
        add(redo);
        add(colorPicker);
        add(fillColorPicker);
        add(fill);
        add(thicknessBox);
        setBackground(Color.white);
        setLayout(new GridLayout(5, 2,10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));
    }
}
