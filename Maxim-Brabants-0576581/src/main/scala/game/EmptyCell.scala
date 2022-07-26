package game

import mygame.GridPanel

import java.awt.{Graphics2D, Image}

class EmptyCell(grid: GridPanel, xPos: Int, yPos: Int) extends Cell {

  val image: Image = ImageLoader.loadImage("empty-cell.png")
  var x: Int = xPos
  var y: Int = yPos
  var direction: String = "none"

  override def draw(g: Graphics2D): Unit =
    val (screenX: Int, screenY: Int) = ImageDrawer.determineCellOnScreen(x, y, grid)
    ImageDrawer.drawImage(image, screenX, screenY, g, grid)

}
