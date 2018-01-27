import doodle.core._
import doodle.core.Image._
import doodle.syntax._
import doodle.jvm.Java2DFrame._
import doodle.backend.StandardInterpreter._

object Recursion {
  def boxes(count: Int): Image = {
    val aBox = Image.rectangle(20, 20).fillColor(Color.royalBlue)
    def loop(count: Int): Image =
      count match {
        case 0 => Image.empty
        case n => aBox beside loop(n-1)
      }

    loop(count)
  }

  def cross(count: Int): Image = {
  // val aCross = unit above (unit beside unit beside unit) above unit
    val circ = circle(12)
    count match {
      case 0 => circ
      case n => circ above (circ beside cross(n-1) beside circ) above circ
    }
  }

  def chessboard(count: Int): Image = {
    val redSquare = rectangle(20,20).fillColor(Color.red)
    val blackSquare = rectangle(20,20).fillColor(Color.black)

    val boardSegment = (redSquare beside blackSquare) above (blackSquare beside redSquare)

    def loop(count: Int): Image = {
      count match {
        case 0 => boardSegment
        case n => {
          val prev = loop(n-1)
          (prev beside prev) above (prev beside prev)
        }
      }
    }

    loop(count)
  }

  def sierpinski(count: Int): Image = {
    count match {
      case 0 => triangle(10,10).lineColor(Color.magenta).lineWidth(1.4)
      case n => {
        val prevFractal = sierpinski(n-1)
        prevFractal above (prevFractal beside prevFractal)
      }
    }
  }

  def gradientBoxes(count: Int, spinFactor: Int): Image = {
    count match {
      case 0 => Image.empty
      case n => rectangle(40, 40).fillColor(Color.royalBlue.spin(spinFactor.degrees)).lineColor(Color.royalBlue.spin((spinFactor + 20).degrees)).lineWidth(2.0) beside gradientBoxes(n-1, spinFactor + 10)
    }
  }

  // Concentric Circles

  def concentricCircles(count: Int, size: Int): Image = {
    count match {
      case 0 => Image.empty
      case n => circle(size).lineColor(Color.royalBlue).lineWidth(2.4) on concentricCircles(n-1, size + 5)
    }
  }

  def concentricAlpha(count: Int, size: Int, transparency: Double): Image = {
    count match {
      case 0 => Image.empty
      case n => circle(size).lineColor(Color.royalBlue.alpha(transparency.normalized)).lineWidth(2.4) on concentricAlpha(n-1, size + 5, transparency - .05)
    }
  }

  def concentricChamelon(count: Int, size: Int, hue: Int): Image = {
    count match {
      case 0 => Image.empty
      case n => circle(size).lineColor(Color.blue.spin(hue.degrees)).lineWidth(2.4) on concentricChamelon(n-1, size + 5, hue + 10)
    }
  }

  // ALTERNATIVE SOLUTION

  def baseCircle(size: Int, color: Color): Image = circle(size).lineColor(color)

  def fadeCircles(n: Int, size: Int, color: Color): Image =
    n match {
      case 0 => Image.empty
      case n => baseCircle(size, color) on fadeCircles(n-1, size+7, color.fadeOutBy(0.05.normalized))
    }

  def gradientCircles(n: Int, size: Int, color: Color): Image =
    n match {
      case 0 => Image.empty
      case n => baseCircle(size, color) on gradientCircles(n-1, size+7, color.spin(15.degrees))
    }
}
