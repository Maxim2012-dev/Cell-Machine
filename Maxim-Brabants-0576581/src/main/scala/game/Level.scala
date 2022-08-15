package game

import mygame.GridPanel
import game.PushCell
import game.Pushable
import game.RotateCell
import game.UnrotatableCell
import game.ImmovableCell

import collection.JavaConverters.*
import java.util
import Array.*
import scala.collection.mutable.ListBuffer

// In dit spel maken we één level met alle functionaliteit aanwezig
class Level(grid: GridPanel) {

  // cellen die gebruikt worden voor dit level
  grid.addCells(new PushCell(grid, 2,1))
  grid.addCells(new EnemyCell(grid, 6,2))
  grid.addCells(new GeneratorCell(grid, 4,1))
  grid.addCells(new ImmovableCell(grid, 5,1))
  grid.addCells(new ImmovableCell(grid, 5,2))
  grid.addCells(new ImmovableCell(grid, 5,3))
  grid.addCells(new RotateCell(grid, 1,3))
  grid.addCells(new UnrotatableCell(grid, 3,4))

  // game matrix van type 'Cell'
  val gameMatrix: GameMatrix[Cell] = new GameMatrix[Cell](grid, grid.getRows, grid.getColumns)
  var cell_selected: Boolean = false
  var selectedCell: Cell = new EmptyCell(grid, 0, 0)    // dummy waarden (default waarde)
  var toggledCells = new ListBuffer[PushCell]
  var number_of_enemies: Int = 1

  val cells: List[Cell] = grid.getCells.asScala.toList
  for (current_cell <- cells) {
    gameMatrix.initializeNewCell(current_cell)
  }
  gameMatrix.addRemainingCells()


  def determineAction(xPosScreen: Int, yPosScreen: Int): Unit =
    if cell_selected then
      replaceCell(xPosScreen, yPosScreen)
      cell_selected = false
    else
      selectCell(xPosScreen, yPosScreen)


  def replaceCell(xPos: Int, yPos: Int): Unit =
    val (col, row): (Int, Int) = ImageDrawer.determineCellInMatrix(xPos, yPos, grid)
    if gameMatrix.getCellOnPos(col, row).isSelected then
      selectedCell = new EmptyCell(grid, 0, 0)    // dummy waarden
      println("Already selected this cell!")
    else gameMatrix.getCellOnPos(col, row) match
      case _: EmptyCell => gameMatrix.moveCell(selectedCell, col, row)
      case _ => selectedCell = new EmptyCell(grid, 0, 0)    // dummy waarden
        println("Cell can't be placed here!")
    grid.unmarkCell()


  def selectCell(xPos: Int, yPos: Int): Unit =
    val (col, row): (Int, Int) = ImageDrawer.determineCellInMatrix(xPos, yPos, grid)
    gameMatrix.getCellOnPos(col, row) match
      case _: EmptyCell => println("Can't select an empty cell...")
      case _ =>
        selectedCell = gameMatrix.getCellOnPos(col, row)
        selectedCell.isSelected = true
        cell_selected = true
        grid.markCell(col, row)
 

  // alle duwcellen 1 iteratie volgens hun richting laten bewegen
  def movePushCells(): Unit =
    for ( i <- 0 until gameMatrix.getRows )
      for ( j <- 0 until gameMatrix.getColumns )
        gameMatrix.getCellOnPos(j, i) match
          // Is het een push cell?
          case pushCell: PushCell =>
            if !pushCell.isMoved then
              println("moving push cell")
              val (newX, newY) = pushCell.move(pushCell, gameMatrix)
              gameMatrix.moveCell(pushCell, newX, newY)
              pushCell.toggleMoved()
              toggledCells.append(pushCell)
          case _ => print("")
      for (n <- toggledCells) n.toggleMoved()
      toggledCells.clear()

}
