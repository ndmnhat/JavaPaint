package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MenuBar extends JMenuBar {
    private IDrawingManager manager;
    private JMenu fileMenu, editMenu;
    private JMenuItem newFile, loadFile, saveFile, saveFileAs, undo, redo, zoomIn, zoomOut;
    private File file;

    public MenuBar(IDrawingManager manager) {
        this.manager = manager;

        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");

        newFile = new JMenuItem("New");
        newFile.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        newFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.newFile();
            }

        });

        loadFile = new JMenuItem("Open...");        
        loadFile.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        loadFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLoadFile();
            }
            
        });

        saveFile = new JMenuItem("Save");
        saveFile.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        saveFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSaveFile();
            }
        });

        saveFileAs = new JMenuItem("Save as...");
        saveFileAs.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        saveFileAs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSaveFileAs();
            }
        });

        undo = new JMenuItem("Undo");
        undo.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.undo();
            }
        });

        redo = new JMenuItem("Redo");
        redo.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Z, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        redo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.redo();
            }
        });

        zoomIn = new JMenuItem("Zoom in");
        zoomIn.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_EQUALS, ActionEvent.CTRL_MASK));
        zoomIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.zoom(0.05);
            }
        });

        zoomOut = new JMenuItem("Zoom out");
        zoomOut.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_MINUS, ActionEvent.CTRL_MASK));
        zoomOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.zoom(-0.05);
            }
        });

        manager.addEventListener(new DrawingManagerEventAdapter() {
            public void undoShapesChanged(int newSize) {
                redo.setEnabled(newSize > 0);
            }

            public void shapesChanged(int newSize) {
                undo.setEnabled(newSize > 0);
            }
        });

        fileMenu.add(newFile);
        fileMenu.add(loadFile);
        fileMenu.add(new JSeparator());
        fileMenu.add(saveFile);
        fileMenu.add(saveFileAs);

        editMenu.add(undo);
        editMenu.add(redo);
        editMenu.add(new JSeparator());
        editMenu.add(zoomIn);
        editMenu.add(zoomOut);

        add(fileMenu);
        add(editMenu);
    }

    private void handleLoadFile() {
        JFileChooser dialog = new JFileChooser();
        dialog.setMultiSelectionEnabled(false);
        FileFilter imageFilter = new FileNameExtensionFilter(
                "Image files", ImageIO.getReaderFileSuffixes());
        dialog.setFileFilter(imageFilter);
        int returnVal = dialog.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = dialog.getSelectedFile();
            manager.loadFile(file);
        }
    }

    private void handleSaveFile() {
        if (file != null) {
            manager.saveFileAs(file);
        } else {
            handleSaveFileAs();
        }
    }

    private void handleSaveFileAs() {
        JFileChooser dialog = new JFileChooser();
        dialog.setMultiSelectionEnabled(false);
        dialog.setSelectedFile(new File("myImage.png"));
        FileFilter imageFilter = new FileNameExtensionFilter(
                "Image files", ImageIO.getReaderFileSuffixes());
        dialog.setFileFilter(imageFilter);
        int returnVal = dialog.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = dialog.getSelectedFile();
            manager.saveFileAs(file);
        }
    }
}
