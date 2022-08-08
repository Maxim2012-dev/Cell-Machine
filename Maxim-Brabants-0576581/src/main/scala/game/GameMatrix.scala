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

  def initializeNewCell(cell: T): Unit =
    val (colIdx, rowIdx) : (Int, Int) = ImageDrawer.determineCellInMatrix(cell.x, cell.y, grid)
    gameMatrix(rowIdx)(colIdx) = cell

  def getCellOnPos(x: Int, y: Int): T =
    gameMatrix(y)(x)

  // verplaatsen van een cel in de matrix (positie cel aanpassen + matrix updaten)
  def moveCell(cell: T, x: Int, y: Int): Unit =
    gameMatrix(cell.y)(cell.x) = new EmptyCell(grid)
    gameMatrix(y)(x) = cell
    cell.x = x
    cell.y = y
    println("Moved cell...")


  // geeft de cel in 'cells' terug die overeenkomt met de x en y
  // anders geeft deze een EmptyCell terug
  /*def getCorrespondingCell(x: Int, y: Int): T =

    var emptyCell = new EmptyCell(grid)
    emptyCell.x = x
    emptyCell.y = y
    grid.addCells(List(emptyCell).asJava)
    emptyCell*/
  
  def isCellOfType[T <: Cell](x: Int, y: Int, cellType: T): Boolean =
    gameMatrix(y)(x) match {
      case _: T => true
      case _ => false
    }
    
  
  def getRows: Int = rows
  def getColumns: Int = cols

}
