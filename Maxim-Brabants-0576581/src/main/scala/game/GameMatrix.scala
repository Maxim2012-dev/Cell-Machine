package game

import mygame.GridPanel

import scala.Array.ofDim

// representatie van het speelveld
class GameMatrix (grid: GridPanel, rows: Int, cols: Int){

  private var gameMatrix = ofDim[Cell](rows, cols)

  for (i <- 0 until rows) {
    for (j <- 0 until cols) {
      gameMatrix(i)(j) = getCorrespondingCell(j, i);
    }
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
    
  def getPosX(x: Int, y: Int) =  
    gameMatrix(y)(x).x
  def getPosY(x: Int, y: Int) = 
    gameMatrix(y)(x).y
  
  def isPushCell(x: Int, y: Int): Boolean =
    gameMatrix(y)(x) match {
      case _: PushCell => true
      case _ => false
    }
    
  
  def getRows() = rows
  def getColumns() = cols

}
