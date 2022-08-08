package game

import java.awt.{Graphics2D, Image}

// We laten de Cell-interface weg uit 'mygame' en maken er een abstracte klasse van
// zodat we hier de x en y kunnen onder brengen + de drawmethode voor elke cel
abstract class Cell {
  
  val image: Image
  var x: Int
  var y: Int
  var isSelected: Boolean = false
  var direction: String

  def draw(g: Graphics2D): Unit
  override def toString = s"Cell($image, $x, $y, $isSelected, $direction)"
}
