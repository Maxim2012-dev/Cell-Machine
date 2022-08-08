package game

import java.awt.{Color, Graphics2D, Rectangle}
import mygame.GridPanel
import game.Pushable

class Rotatable(grid: GridPanel) extends Cell, Pushable {
  
  val image = ImageLoader.loadImage("spinner.png")
  var x: Int = 1
  var y: Int = 3
  var direction: String = "right"

  override def draw(g: Graphics2D): Unit =
    val (screenX: Int, screenY: Int) = ImageDrawer.determineCellOnScreen(x, y, grid)
    ImageDrawer.drawImage(image, screenX, screenY, g, grid)
}
