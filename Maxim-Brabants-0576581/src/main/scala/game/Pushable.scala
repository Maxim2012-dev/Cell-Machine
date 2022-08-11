package game

import java.awt.{Color, Graphics2D, Rectangle}
import game.Cell

trait Pushable() {
  var direction: String
  def draw(g: Graphics2D): Unit
  def move(direction: String, cell: Cell): (Int, Int) =
    if direction == "up" then cell.y -= 1 
    else if direction == "right" then cell.x += 1
    else if direction == "down" then cell.y += 1
    else if direction == "left" then cell.x -= 1
    (cell.x, cell.y)
}