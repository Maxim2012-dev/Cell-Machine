package game

import java.awt.{Graphics2D, Image}

abstract class Game_Button {

  val image: Image
  val screenX: Int
  val screenY: Int

  def draw(g: Graphics2D): Unit
  
}
