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

  private var gameMatrix = ofDim[Cell](rows, cols)

  // lege plaatsen in matrix opvullen met lege cellen
  def addRemainingCells(): Unit =
    for (i <- 0 until getRows)
      for (j <- 0 until getColumns)
        if gameMatrix(i)(j) == null then gameMatrix(i)(j) = new EmptyCell(grid, j, i)

  def initializeNewCell(cell: T): Unit =
    val (colIdx, rowIdx) : (Int, Int) = ImageDrawer.determineCellInMatrix(cell.x, cell.y, grid)
    gameMatrix(rowIdx)(colIdx) = cell

  def getCellOnPos(x: Int, y: Int): Cell =
    gameMatrix(y)(x)

  // verplaatsen van een cel in de matrix (positie cel aanpassen + matrix updaten)
  def moveCell[T <: Cell](cell: T, x: Int, y: Int): Unit =
    gameMatrix(cell.y)(cell.x) = new EmptyCell(grid, 0, 0)
    gameMatrix(y)(x) = cell
    cell.x = x
    cell.y = y
    println("Moved cell...")

  
  def isCellOfType[T](x: Int, y: Int, cellType: T): Boolean =
    gameMatrix(y)(x) match {
      case _: T => true
      case _ => false
    }
    
  
  def getRows: Int = rows
  def getColumns: Int = cols

}
