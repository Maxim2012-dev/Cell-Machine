package game

import mygame.GridPanel

import java.awt.Graphics2D

class StepBtn(grid: GridPanel) extends Cell{

  val image = ImageLoader.loadImage("step.png")
  var x = grid.cellWidth + 10
  var y = grid.windowHeight - grid.cellHeight
  var direction: String = "none"
  
  override def draw(g: Graphics2D): Unit =
    grid.drawRectangle(x, y)
    ImageDrawer.drawImage(ImageLoader.loadImage("UIMask.png"), x, y, g, grid)
    ImageDrawer.drawImage(image, x, y, g, grid)

}
