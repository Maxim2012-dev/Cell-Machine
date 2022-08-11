package game

import java.awt.Graphics2D

trait Duplicatable {
  var direction: String
  def draw(g: Graphics2D): Unit
  def duplicate(): Unit
}
