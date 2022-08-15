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
    gameMatrix(cell.y)(cell.x) = cell

  def getCellOnPos(x: Int, y: Int): Cell =
    gameMatrix(y)(x)

  // verplaatsen van een cel in de matrix (positie cel aanpassen + matrix updaten)
  def moveCell[T <: Cell](cell: T, x: Int, y: Int): Unit =
    gameMatrix(cell.y)(cell.x) = new EmptyCell(grid, cell.x, cell.y)
    gameMatrix(y)(x) = cell
    cell.x = x
    cell.y = y
    cell.isSelected = false

  def removeCell(x: Int, y: Int): Unit =
    grid.removeCell(gameMatrix(y)(x))
    gameMatrix(y)(x) = new EmptyCell(grid, x, y)
    
  def printMatrix(): Unit =
    for (i <- 0 until getRows)
      for (j <- 0 until getColumns)
        print(gameMatrix(i)(j))
      println()  
  
  def getRows: Int = rows
  def getColumns: Int = cols

}
