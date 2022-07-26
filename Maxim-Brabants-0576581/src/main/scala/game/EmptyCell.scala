package game

import mygame.GridPanel

import java.awt.Graphics2D

class EmptyCell(grid: GridPanel) extends Cell {

  val image = ImageLoader.loadImage("Background.png")
  var x: Int = 0
  var y: Int = 0
  var direction: String = "none"

  override def draw(g: Graphics2D): Unit =
    val (screenX: Int, screenY: Int) = ImageDrawer.determineCell(x, y, grid)
    ImageDrawer.drawImage(image, screenX, screenY, g, grid)

}
