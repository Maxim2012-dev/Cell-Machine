package game

import mygame.GridPanel

import scala.Array.ofDim
import scala.reflect.ClassTag

import scala.jdk.CollectionConverters.ListHasAsScala
import scala.jdk.CollectionConverters.CollectionHasAsScala
import scala.jdk.CollectionConverters.IterableHasAsScala
import scala.jdk.CollectionConverters.SeqHasAsJava
import scala.jdk.CollectionConverters.IterableHasAsJava


// representatie van het speelveld
class GameMatrix[T <: Cell : ClassTag](grid: GridPanel, rows: Int, cols: Int){

  private var gameMatrix = ofDim[T](rows, cols)

  def initializeNewCell[T <: Cell](cell: Cell) =
    gameMatrix(cell.y)(cell.x) = cell

  def getCellOnPos(x: Int, y: Int): Cell =
    gameMatrix(y)(x)

  // laat de cel op positie (x, y) bewegen en update de matrix
  def moveCell(x: Int, y: Int, emptyCell: EmptyCell) =
    gameMatrix(y)(x).move(gameMatrix(y)(x).direction, gameMatrix(y)(x))


  // geeft de cel in 'cells' terug die overeenkomt met de x en y
  // anders geeft deze een EmptyCell terug
  /*def getCorrespondingCell(x: Int, y: Int): T =

    var emptyCell = new EmptyCell(grid)
    emptyCell.x = x
    emptyCell.y = y
    grid.addCells(List(emptyCell).asJava)
    emptyCell*/
  
  def isPushCell(x: Int, y: Int): Boolean =
    gameMatrix(y)(x) match {
      case _: PushCell => true
      case _ => false
    }
    
  
  def getRows() = rows
  def getColumns() = cols

}
