package game

import java.awt.{Color, Graphics2D, Rectangle}
import mygame.GridPanel
import game.Pushable

class ImmovableCell(grid: GridPanel) extends Cell, Pushable {

  val image = ImageLoader.loadImage("immobile.png");
  var x: Int = 5
  var y: Int = 2
  var direction: String = "none"

  override def draw(g: Graphics2D): Unit =
    val (screenX: Int, screenY: Int) = ImageDrawer.determineCellOnScreen(x, y, grid)
    ImageDrawer.drawImage(image, screenX, screenY, g, grid)

}
