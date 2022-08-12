package mygame;

import game.Cell;
import game.ImageDrawer;
import game.ImageLoader;

import javax.swing.*;
import java.awt.*;
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
    private ArrayList<game.GameButton> buttons;

    public Graphics2D getmGraphics() {
        return mGraphics;
    }

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

    public boolean markedCell = false;
    public int xScreen;
    public int yScreen;
    public Image markedImage = ImageLoader.loadImage("red-border.png");


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
        buttons = new ArrayList<>();
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
    public void markCell(int col, int row) {
        markedCell = true;
        xScreen = ImageDrawer.getXOnScreen(col, this);
        yScreen = ImageDrawer.getYOnScreen(row, this);
    }

    public void unmarkCell() {
        markedCell = false;
        xScreen = 0;
        yScreen = 0;
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        mGraphics = (Graphics2D) g.create();
        this.repaintCells();
        mGraphics.dispose();
    }

    /**
     * Hertekent het volledig venster gegeven de cellen
     */
    public void repaintCells() {
        mGraphics.clearRect(0, 0, windowWidth, windowHeight);
        this.drawGrid();
        for (int i = 0; i < cells.size(); i++) {
            cells.get(i).draw(mGraphics);
        }
        if (markedCell) {
            mGraphics.drawImage(markedImage, xScreen, yScreen, cellWidth, cellHeight, null);
        }
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).draw(mGraphics);
        }
        this.repaint();
    }

    /**
     * Stel de nieuwe cellen in
     */
    public void addCells(Iterable<Cell> cells) {
        cells.forEach(this.cells::add);
    }
    public void addButtons(Iterable<game.GameButton> buttons) {
        buttons.forEach(this.buttons::add);
    }

}
