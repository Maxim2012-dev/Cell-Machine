package game

import java.awt.Graphics2D
import java.awt.Color
import javax.imageio.ImageIO
import java.io.File
import mygame.GridPanel
import game.Pushable

class PushCell(grid: GridPanel) extends Cell, Pushable {
  
  val image = ImageLoader.loadImage("mover2.png")
  var x: Int = 1
  var y: Int = 2
  var direction: String = "right"

  override def draw(g: Graphics2D): Unit =
    val (screenX: Int, screenY: Int) = ImageDrawer.determineCell(x, y, grid)
    ImageDrawer.drawImage(image, screenX, screenY, g, grid) 
  
}
