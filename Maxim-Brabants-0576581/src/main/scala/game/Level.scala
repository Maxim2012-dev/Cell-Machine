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

// In dit spel maken we één level met alle funcitonaliteit aanwezig
class Level(grid: GridPanel) {

  val pushCell: PushCell = new PushCell(grid)
  val rotatableCell: Rotatable = new Rotatable(grid)
  val unrotatableCell: Unrotatable = new Unrotatable(grid)
  val immovableCell: ImmovableCell = new ImmovableCell(grid)
  val generatorCell: Generator = new Generator(grid)
  val enemyCell: Enemy = new Enemy(grid)

  val matrixRows: Int = grid.getRows
  val matrixCols: Int = grid.getColumns

  // representatie van het speelveld
  var gameMatrix = ofDim[Cell](grid.getRows, grid.getColumns)
  for (i <- 0 to matrixRows-1) {
    for ( j <- 0 to matrixCols-1) {
      gameMatrix(i)(j) = getCorrespondingCell(j, i);
    }
  }

  for (i <- 0 to matrixRows-1) {
    for ( j <- 0 to matrixCols-1) {
      print(" " + gameMatrix(i)(j));
    }
    println();
  }

  // geeft de cel in 'cells' terug die overeenkomt met de x en y
  // anders geeft deze een EmptyCell terug
  def getCorrespondingCell(x: Int, y: Int): Cell =
    val cells: List[Cell] = grid.getCells.asScala.toList
    for (n <- cells) {
      if n.x == x && n.y == y then
        return n
    }
    var emptyCell: Cell = new EmptyCell(grid)
    emptyCell.x = x
    emptyCell.y = y
    grid.addCells(List(emptyCell).asJava)
    emptyCell

  // alle duwcellen 1 iteratie volgens hun richting laten bewegen
  def movePushCells(): Unit = {
    for (i <- 0 to matrixRows-1) {
      for ( j <- 0 to matrixCols-1) {
        if gameMatrix(i)(j).isInstanceOf[PushCell] &&
        j < grid.getRows then
          var emptyCell: EmptyCell = new EmptyCell(grid)
          emptyCell.x = gameMatrix(i)(j).x
          emptyCell.y = gameMatrix(i)(j).y
          //grid.removeCell(gameMatrix(i)(j))
          grid.addCells(List(emptyCell).asJava)
          gameMatrix(i)(j).asInstanceOf[Pushable].move(gameMatrix(i)(j).direction, gameMatrix(i)(j))
          gameMatrix(i)(j+1) = gameMatrix(i)(j)
          grid.removeCell(gameMatrix(i)(j+1))
          grid.addCells(List(gameMatrix(i)(j+1)).asJava)
          gameMatrix(i)(j) = emptyCell
      }
    }
  }

  // Mondelinge toelichting van ontbrekende functionaliteit:
  // Als een pushcell dan een andere cel raakt die beweegbaar is, dan moet de moveprocedure opgeroepen worden met de cel die geraakt wordt.
  // Als een pushcell een draaicel raakt, dan moet de richting (direction) van de pushcell worden aangepast

}
