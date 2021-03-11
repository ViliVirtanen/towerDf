package Game
import scalafx.scene.paint.Color

import scala.collection.mutable


abstract class Enemy(loc: (Int, Int), world: World) extends GameObject(loc) {
  var health : Int
  val id = 'E'
}

class EasyEnemy(var loca: (Int, Int), world: World) extends Enemy(loca, world) {
  var health        = 1
  var color         = Color.Yellow
  var dead          = health == 0
  var pastLocations = mutable.Buffer[(Int,Int)]()

  // checks if some adjecent tile is predefined route and moves there.
  def update() = {
    if (  loca._1 > 0 && loca._2 > 0 &&
          world.map(loca._1)(loca._2 - 1).id == 'r' &&
          !pastLocations.contains((loca._1,loca._2 - 1))) {

       loca                            = (loca._1,loca._2-1)
       world.map(loca._1)(loca._2)     = this
       world.map(loca._1)(loca._2 + 1) = new Route((loca._1,loca._2 + 1))   // adds a route tile back when moves
       pastLocations                  += loca

    } else if (  loca._1 > 0 && loca._2 > 0 &&
                 world.map(loca._1 - 1)(loca._2).id == 'r' &&
                 !pastLocations.contains((loca._1-1,loca._2))) {

       loca                          = (loca._1-1,loca._2)
       world.map(loca._1)(loca._2)   = this
       world.map(loca._1+1)(loca._2) = new Route((loca._1 + 1,loca._2 ))
       pastLocations                += loca

    } else if (  loca._1 > 0 && loca._2 > 0 &&
                 world.map(loca._1 + 1)(loca._2).id == 'r' &&
                 !pastLocations.contains((loca._1+1,loca._2))) {

       loca                          = (loca._1+1,loca._2)
       world.map(loca._1)(loca._2)   = this
       world.map(loca._1-1)(loca._2) = new Route((loca._1 - 1,loca._2 ))
       pastLocations                += loca
    }
  }

}