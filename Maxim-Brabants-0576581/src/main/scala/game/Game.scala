package game

import collection.JavaConverters.asJavaIterableConverter
import mygame.GUI
import game.Level

import java.awt.{Button, Graphics2D}
import java.awt.event.{MouseEvent, MouseListener}

object Game:
  @main def run(): Unit =
    val gui = GUI(800, 500, 5, 8, 80)
    val grid = gui.getGridPanel
    grid.addCells(List(PushCell(grid), Rotatable(grid), Unrotatable(grid), ImmovableCell(grid), Generator(grid), Enemy(grid)).asJava)
    grid.addButtons(List(PlayBtn(grid), StepBtn(grid)).asJava)
    val level = Level(grid)
    grid.repaint()
    // Mouse listener for buttons
    grid.addMouseListener(new MouseListener {
      override def mouseClicked(e: MouseEvent): Unit =
        if e.getX / grid.cellWidth == 0 && e.getY / grid.cellHeight == 6 then
          println("Play button pressed")
        else if e.getX / grid.cellWidth == 1 && e.getY / grid.cellHeight == 6 then
          level.movePushCells()
        else if e.getX / grid.cellWidth > 0 && e.getY / grid.cellHeight < 6 then  // Clicked on a cell
          println("Pressed within field")

      override def mousePressed(e: MouseEvent): Unit = ()

      override def mouseReleased(e: MouseEvent): Unit = ()

      override def mouseEntered(e: MouseEvent): Unit = ()

      override def mouseExited(e: MouseEvent): Unit = ()
    })
