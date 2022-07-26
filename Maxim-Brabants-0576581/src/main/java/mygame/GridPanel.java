package mygame;

import game.Cell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.sql.Array;
import java.util.ArrayList;

/**
 * GridPanel is de grafische representatie van een grid,
 * je zal ook nog een logische representatie hiervoor moeten maken.
 *
 * Het voorziet twee methodes die nuttig zijn:
 * - repaint: zorgt ervoor dat het scherm wordt leeggemaakt en dat alles wordt getekend
 * - setCells: geef een lijst van cellen die getekend moeten worden
 */
public class GridPanel extends JPanel {
    /**
     * De lijst van cellen (intern)
     */
    private ArrayList<Cell> cells;

    /**
     * Een referentie naar de renderer waarmee we interessante figuren kunnen tekenen
     */
    private Graphics2D mGraphics;

    /**
     * De breedte van 1 cell
     */
    public int cellWidth;

    /**
     * De hoogte van 1 cell
     */
    public int cellHeight;

    /**
     * De breedte van het venster
     */
    public int windowWidth;

    /**
     * De hoogte van het venster
     */
    public int windowHeight;

    /**
     * Het aantal rijen
    */
    private int rows;

    /**
     * Het aantal kolommen
     */
    private int columns;

    /**
     * De padding (witruimte langst alle kanten) van het grid
     */
    private int padding;

    /**
     * Vraag de padding op
     */
    public int getPadding() {
        return padding;
    }
    public int getRows() {
        return rows;
    }
    public int getColumns() {
        return columns;
    }
    public ArrayList<Cell> getCells() {
        return cells;
    }

    private SimpleMouseListener pressListener = new EmptyMouseListener();
    private SimpleMouseListener moveListener = new EmptyMouseListener();
    private SimpleMouseListener releaseListener = new EmptyMouseListener();

    /**
     * Stel een muis klik listener in
     */
    public void setPressListener(SimpleMouseListener clickListener) {
        this.pressListener = clickListener;
    }

    public void setMoveListener(SimpleMouseListener moveListener) {
        this.moveListener = moveListener;
    }

    public void setReleaseListener(SimpleMouseListener releaseListener) {
        this.releaseListener = releaseListener;
    }

    /**
     * Stel de padding in
     */
    public void setPadding(int padding) {
        this.padding = padding;
        cellWidth  = (windowWidth  - padding*2) / columns;
        cellHeight = (windowHeight - padding*2) / rows;
    }

    public GridPanel(int width, int height, int rows, int columns) {
        super();
        cellWidth  = (width  - padding*2) / columns;
        cellHeight = (height - padding*2) / rows;
        windowWidth = width;
        windowHeight = height;
        this.rows = rows;
        this.columns = columns;
        cells = new ArrayList<>();
    }

    private void drawGrid() {
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                Rectangle rect = new Rectangle(padding + i*cellWidth, padding + j*cellHeight, cellWidth, cellHeight);
                mGraphics.draw(rect);
            }
        }
    }
    
    public void drawRectangle(int screenX, int screenY) {
        Rectangle rect = new Rectangle(screenX, screenY, cellWidth, cellHeight);
        mGraphics.draw(rect);
    }
    
    public void removeCell(Cell cell) {
        cells.remove(cell);
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        mGraphics = (Graphics2D) g.create();
        this.repaintCells();
        mGraphics.dispose();
    }

    /**
     * Hertekend het volledig venster gegeven de cellen
     */
    public void repaintCells() {
        mGraphics.clearRect(0, 0, windowWidth, windowHeight);
        this.drawGrid();
        for (int i = 0; i < cells.size(); i++) {
            cells.get(i).draw(mGraphics);
        }

        this.repaint();
    }

    /**
     * Stel de nieuwe cellen in
     */
    public void addCells(Iterable<Cell> cells) {
        cells.forEach(this.cells::add);
    }

}
