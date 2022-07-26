package game

import java.awt.Image
import javax.imageio.ImageIO

object ImageLoader {
  def loadImage(path: String): Image =
    ImageIO.read(ImageLoader.getClass.getResourceAsStream("/" + path))

}
