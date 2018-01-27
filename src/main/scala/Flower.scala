import doodle.core._
import doodle.core.Image._
import doodle.syntax._
import doodle.jvm.Java2DFrame._
import doodle.backend.StandardInterpreter._

object Flower {
  // EXAMPLES

  def parametricCircle(angle: Angle): Point = Point.cartesian(angle.cos * 200, angle.sin * 200)

  // def parametricCircle(angle: Angle): Point = Point.polar(200, angle)

  def sample(start: Angle, samples: Int): Image = {
    // Angle.one is one complete turn, i.e., 360 degrees
    val step = Angle.one / samples
    val dot = triangle(10, 10)
    def loop(count: Int): Image = {
      val angle = step * count
      count match {
        case 0 => Image.empty
        case n =>
          dot.at(parametricCircle(angle).toVec) on loop(n - 1)
      }
    }

    loop(samples)
  }

  // Parametric equation for rose with k = 7
  def polarRose(angle: Angle) = Point.polar((angle * 7).cos * 200, angle)

  def cartesianRose(angle: Angle) = Point.cartesian((angle * 7).cos * angle.cos, (angle * 7).cos * angle.sin)

}
