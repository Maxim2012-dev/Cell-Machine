package game

import mygame.GridPanel

import java.awt.{Color, Graphics2D, Image}

object ImageDrawer {
  def determineCell(x: Int, y: Int, grid: GridPanel): (Int, Int) =
    ((x * grid.cellWidth) + grid.getPadding,
      (y * grid.cellHeight) + grid.getPadding)

  def drawImage(image: Image, x: Int, y: Int, mGraphics: Graphics2D, grid: GridPanel) =
    mGraphics.drawImage(image, x, y, grid.cellWidth, grid.cellHeight, Color.GRAY, null)
}
