package game

import mygame.GridPanel
import game.Pushable

import java.awt.{Graphics2D, Image}

class EnemyCell(grid: GridPanel, xPos: Int, yPos: Int) extends Cell {

  val image: Image = ImageLoader.loadImage("enemy.png")
  var x: Int = xPos
  var y: Int = yPos
  var direction: String = "right"

  override def draw(g: Graphics2D): Unit =
    val (screenX: Int, screenY: Int) = ImageDrawer.determineCellOnScreen(x, y, grid)
    ImageDrawer.drawImage(image, screenX, screenY, g, grid)

}
