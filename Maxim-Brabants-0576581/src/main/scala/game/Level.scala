package game

import mygame.GridPanel
import game.PushCell
import game.Pushable
import game.Rotatable
import game.Unrotatable
import game.ImmovableCell

import collection.JavaConverters._
import java.util
import Array.*

// In dit spel maken we één level met alle functionaliteit aanwezig
class Level(grid: GridPanel) {

  // game matrix van type 'Cell'
  val gameMatrix: GameMatrix[Cell] = new GameMatrix[Cell](grid, grid.getRows, grid.getColumns)
  var cell_selected: Boolean = false
  var selectedCell: Cell = new EmptyCell(grid)

  val cells: List[Cell] = grid.getCells.asScala.toList
  for (current_cell <- cells) {
    gameMatrix.initializeNewCell(current_cell)
  }

  for (i <- 0 until gameMatrix.getRows )
    for ( j <- 0 until gameMatrix.getColumns )
      print(" " + gameMatrix.getCellOnPos(i, j))
    println()

  def determineAction(xPos: Int, yPos: Int): Unit =
    if cell_selected then
      replaceCell(xPos, yPos)
      cell_selected = false
    else
      selectCell(xPos, yPos)
      cell_selected = true

  def replaceCell(xPos: Int, yPos: Int): Unit =
    val (col, row): (Int, Int) = ImageDrawer.determineCellInMatrix(xPos, yPos, grid)
    if gameMatrix.getCellOnPos(col, row).isSelected then
      selectedCell = new EmptyCell(grid)
      println("Already selected this cell!")
    else if !gameMatrix.isCellOfType(col, row, EmptyCell) then
      selectedCell = new EmptyCell(grid)
      println("Cell can't be placed here!")
    else
      gameMatrix.moveCell()
        
    println("col: " + col + " row: " + row)

  def selectCell(xPos: Int, yPos: Int): Unit =
    val (col, row): (Int, Int) = ImageDrawer.determineCellInMatrix(xPos, yPos, grid)
    selectedCell = gameMatrix.getCellOnPos(col, row)
    selectedCell.isSelected = true


  // alle duwcellen 1 iteratie volgens hun richting laten bewegen
  def movePushCells(): Unit =
    for (i <- 0 until gameMatrix.getRows )
      for ( j <- 0 until gameMatrix.getColumns )
        if gameMatrix.isPushCell(j, i) && j < grid.getColumns then
          var emptyCell: EmptyCell = new EmptyCell(grid)
          emptyCell.x = j
          emptyCell.y = i
          gameMatrix.moveCell(j, i, emptyCell)
          //grid.removeCell(gameMatrix(i)(j))
          grid.addCells(List(emptyCell).asJava)
          /*gameMatrix(i)(j).asInstanceOf[Pushable].move(gameMatrix(i)(j).direction, gameMatrix(i)(j))
          gameMatrix(i)(j+1) = gameMatrix(i)(j)
          grid.removeCell(gameMatrix(i)(j+1))
          grid.addCells(List(gameMatrix(i)(j+1)).asJava)
          gameMatrix(i)(j) = emptyCell*/

  // Mondelinge toelichting van ontbrekende functionaliteit:
  // Als een pushcell dan een andere cel raakt die beweegbaar is, dan moet de moveprocedure opgeroepen worden met de cel die geraakt wordt.
  // Als een pushcell een draaicel raakt, dan moet de richting (direction) van de pushcell worden aangepast

}
