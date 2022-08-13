package game

import java.awt.{Color, Graphics2D, Rectangle}
import game.Cell

import scala.reflect.ClassTag

trait Pushable() {
  var isMoved: Boolean = false
  var direction: String

  def draw(g: Graphics2D): Unit

  // checken op het raken van een andere duwbare cel
  def move(cell: Cell, gameMatrix: GameMatrix[Cell]): (Int, Int) =
    if cell.direction == "up" then
      checkAdjacentCell(gameMatrix, cell, cell.x, cell.y - 1)
      cell.y -= 1
    else if cell.direction == "right" then
      checkAdjacentCell(gameMatrix, cell, cell.x + 1, cell.y)
      cell.x += 1
    else if cell.direction == "down" then
      checkAdjacentCell(gameMatrix, cell, cell.x, cell.y + 1)
      cell.y += 1
    else if cell.direction == "left" then
      checkAdjacentCell(gameMatrix, cell, cell.x - 1, cell.y)
      cell.x -= 1
    (cell.x, cell.y)

  def checkAdjacentCell(gameMatrix: GameMatrix[Cell], cell: Cell, adj_cellX: Int, adj_cellY: Int): Unit =
    gameMatrix.getCellOnPos(adj_cellY, adj_cellX) match
      case pushable: Pushable =>
        pushable.move(pushable, gameMatrix)  // recursief move oproepen op naburige cel (duwbare cel)
        gameMatrix.moveCell(cell, adj_cellX, adj_cellY)
      case _ =>

  def toggleMoved(): Unit =
    if isMoved then isMoved = false
    else isMoved = true
}