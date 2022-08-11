package game

import mygame.GridPanel
import game.PushCell
import game.Pushable
import game.RotateCell
import game.UnrotatableCell
import game.ImmovableCell

import collection.JavaConverters._
import java.util
import Array.*

// In dit spel maken we één level met alle functionaliteit aanwezig
class Level(grid: GridPanel) {

  // game matrix van type 'Cell'
  val gameMatrix: GameMatrix[Cell] = new GameMatrix[Cell](grid, grid.getRows, grid.getColumns)
  var cell_selected: Boolean = false
  var selectedCell: Cell = new EmptyCell(grid, 0, 0)    // dummy waarden

  val cells: List[Cell] = grid.getCells.asScala.toList
  for (current_cell <- cells) {
    gameMatrix.initializeNewCell(current_cell)
  }
  gameMatrix.addRemainingCells()

  /*for (i <- 0 until gameMatrix.getRows)
    for (j <- 0 until gameMatrix.getColumns)
      print(" " + gameMatrix.getCellOnPos(j, i) + " :: ")
    println()*/

  def determineAction(xPosScreen: Int, yPosScreen: Int): Unit =
    if cell_selected then
      replaceCell(xPosScreen, yPosScreen)
      cell_selected = false
    else
      selectCell(xPosScreen, yPosScreen)
      cell_selected = true

  def replaceCell(xPos: Int, yPos: Int): Unit =
    val (col, row): (Int, Int) = ImageDrawer.determineCellInMatrix(xPos, yPos, grid)
    if gameMatrix.getCellOnPos(col, row).isSelected then
      selectedCell = new EmptyCell(grid, 0, 0)    // dummy waarden
      println("Already selected this cell!")
    else if !gameMatrix.isCellOfType(col, row, new EmptyCell(grid, 0, 0)) then
      selectedCell = new EmptyCell(grid, 0, 0)    // dummy waarden
      println("Cell can't be placed here!")
    else
      gameMatrix.moveCell(selectedCell, col, row)
    grid.unmarkCell()  
    println("col: " + col + " row: " + row)

  def selectCell(xPos: Int, yPos: Int): Unit =
    val (col, row): (Int, Int) = ImageDrawer.determineCellInMatrix(xPos, yPos, grid)
    println("X-screen: " + xPos + " X-idx: " + col)
    println("Y-screen: " + yPos + " Y-idx: " + row)
    selectedCell = gameMatrix.getCellOnPos(col, row)
    selectedCell.isSelected = true
    grid.markCell(xPos, yPos)
 

  // alle duwcellen 1 iteratie volgens hun richting laten bewegen
  def movePushCells(): Unit =
    for (i <- 0 until gameMatrix.getRows )
      for ( j <- 0 until gameMatrix.getColumns )
        gameMatrix.getCellOnPos(j, i) match
          // Is het een push cell?
          case pushCell: PushCell =>
            val (newX, newY) = pushCell.move(pushCell.direction, pushCell)
            gameMatrix.moveCell(pushCell, j, i)

  // Mondelinge toelichting van ontbrekende functionaliteit:
  // Als een pushcell dan een andere cel raakt die beweegbaar is, dan moet de moveprocedure opgeroepen worden met de cel die geraakt wordt.
  // Als een pushcell een draaicel raakt, dan moet de richting (direction) van de pushcell worden aangepast

}
