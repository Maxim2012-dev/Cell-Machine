package game

import java.awt.{Color, Graphics2D, Image, Rectangle}
import mygame.GridPanel
import game.Pushable

class GeneratorCell(grid: GridPanel, xPos: Int, yPos: Int) extends Cell {
  
  val image: Image = ImageLoader.loadImage("generator.png");
  var x: Int = xPos
  var y: Int = yPos
  var direction: String = "right"

  override def draw(g: Graphics2D): Unit =
    val (screenX: Int, screenY: Int) = ImageDrawer.determineCellOnScreen(x, y, grid)
    ImageDrawer.drawImage(image, screenX, screenY, g, grid)
}
