package game

import mygame.GridPanel

import java.awt.{Graphics2D, Image}

class PlayBtn(grid: GridPanel) extends GameButton{

  val image: Image = ImageLoader.loadImage("play.png")
  val screenX = 0
  val screenY: Int = grid.windowHeight - grid.cellHeight
  
  override def draw(g: Graphics2D): Unit =
    grid.drawRectangle(screenX, screenY)
    ImageDrawer.drawImage(ImageLoader.loadImage("UIMask.png"), screenX, screenY, g, grid)
    ImageDrawer.drawImage(image, screenX, screenY, g, grid)
  
}
