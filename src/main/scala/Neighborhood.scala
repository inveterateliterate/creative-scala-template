import doodle.core._
import doodle.core.Image._
import doodle.syntax._
import doodle.jvm.Java2DFrame._
import doodle.backend.StandardInterpreter._

object Neighborhood {
  // House

    val roof = triangle(40,26).fillColor(Color.brown)
    val body = rectangle(40,40).fillColor(Color.red)
    val door = rectangle(6,20).fillColor(Color.black)
    val filler = rectangle(40,20).fillColor(Color.red)

    val structure = filler above door on body

    val house = roof above structure

  // Tree

    val leaves = circle(25).fillColor(Color.green)
    val trunk = rectangle(6, 17).fillColor(Color.brown)

    val tree = leaves above trunk

  // Road

    val yellow = rectangle(26, 4).fillColor(Color.yellow)
    val black = rectangle(14, 4).fillColor(Color.black)

    val topRoadSegment = yellow beside black
    val bottomRoadSegment = rectangle(40,6).fillColor(Color.black)

    val roadSegment = topRoadSegment above bottomRoadSegment

    val road = roadSegment beside roadSegment beside roadSegment


  // All together

    val houseAndTree = house beside tree

    val houseOnRoad = houseAndTree above road

    val image = (houseOnRoad beside houseOnRoad beside houseOnRoad).lineWidth(0)
}
