package game

import java.awt.{Color, Graphics2D, Image}
import javax.imageio.ImageIO
import java.io.File
import mygame.GridPanel
import game.Pushable

class PushCell(grid: GridPanel, xPos: Int, yPos: Int) extends Cell, Pushable {
  
  val image: Image = ImageLoader.loadImage("mover2.png")
  var x: Int = xPos
  var y: Int = yPos
  var direction: String = "right"

  override def draw(g: Graphics2D): Unit =
    val (screenX: Int, screenY: Int) = ImageDrawer.determineCellOnScreen(x, y, grid)
    ImageDrawer.drawImage(image, screenX, screenY, g, grid) 
  
}
