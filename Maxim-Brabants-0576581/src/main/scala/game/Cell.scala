package game

import java.awt.{Graphics2D, Image}

abstract class Cell {
  
  val image: Image
  var x: Int
  var y: Int
  var isSelected: Boolean = false
  var direction: String

  def draw(g: Graphics2D): Unit

}
