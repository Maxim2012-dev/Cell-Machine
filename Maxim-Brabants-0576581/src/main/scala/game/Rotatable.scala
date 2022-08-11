package game

import java.awt.{Color, Graphics2D, Rectangle}

trait Rotatable {
  var direction: String
  def draw(g: Graphics2D): Unit
  def move(direction: String, cell: Cell): Unit =
    if direction == "up" then cell.direction = "right"
    else if direction == "right" then cell.direction = "down"
    else if direction == "down" then cell.direction = "left"
    else if direction == "left" then cell.direction = "up"
}
