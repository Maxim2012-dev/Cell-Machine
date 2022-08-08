package game

import mygame.GridPanel

import java.awt.{Color, Graphics2D, Image}

object ImageDrawer {
  def determineCellOnScreen(col: Int, row: Int, grid: GridPanel): (Int, Int) =
    ((col * grid.cellWidth) + grid.getPadding,
      (row * grid.cellHeight) + grid.getPadding)

  def determineCellInMatrix(x: Int, y: Int, grid: GridPanel): (Int, Int) =
    ((x / grid.cellWidth) - 1, (y / grid.cellHeight) - 1)

  def drawImage(image: Image, x: Int, y: Int, mGraphics: Graphics2D, grid: GridPanel) =
    mGraphics.drawImage(image, x, y, grid.cellWidth, grid.cellHeight, Color.GRAY, null)
}
