package game

import mygame.GridPanel

import java.awt.{Color, Graphics2D, Image}

object ImageDrawer {
  
  // POSITIE IN MATRIX ------> POSITIE OP HET SCHERM
  def determineCellOnScreen(col: Int, row: Int, grid: GridPanel): (Int, Int) =
    ((col * grid.cellWidth) + grid.getPadding,
      (row * grid.cellHeight) + grid.getPadding)

  def getXOnScreen(col: Int, grid: GridPanel): Int =
    (col * grid.cellWidth) + grid.getPadding

  def getYOnScreen(row: Int, grid: GridPanel): Int =
    (row * grid.cellHeight) + grid.getPadding
    
    
  // POSITIE OP HET SCHERM ------> POSITIE IN MATRIX
  def determineCellInMatrix(x: Int, y: Int, grid: GridPanel): (Int, Int) =
    ((x - grid.getPadding) / grid.cellWidth, (y - grid.getPadding) / grid.cellHeight)

  def getXIndex(x: Int, grid: GridPanel): Int =
    (x - grid.getPadding) / grid.cellWidth

  def getYIndex(y: Int, grid: GridPanel): Int =
    (y - grid.getPadding) / grid.cellHeight
    

  def drawImage(image: Image, x: Int, y: Int, mGraphics: Graphics2D, grid: GridPanel): Boolean =
    mGraphics.drawImage(image, x, y, grid.cellWidth, grid.cellHeight, null)
}
