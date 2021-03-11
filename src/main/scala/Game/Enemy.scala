package Game
import scalafx.scene.paint.Color

import scala.collection.mutable


abstract class Enemy(loc: (Int, Int), world: World, game: Game) extends GameObject(loc) {
  var health : Int
  val id = 'E'
}

class EasyEnemy(var loca: (Int, Int), world: World, game: Game) extends Enemy(loca, world,game) {
  var health        = 1
  val damage        = 4
  val color         = Color.Yellow
  var dead          = health == 0

  var pastLocations = mutable.Buffer[(Int,Int)](loca)

  // location 98,98 is "cemetary"
  def destroy() = {
     world.map(loca._1)(loca._2) = new Route(loca._1,loca._2)
     this.loca = (98,98)
  }

  // checks if some adjecent tile is predefined route and moves there.
  def update() = {
    if (world.map(loca._1)(loca._2 - 1).id == 'g' ||
        world.map(loca._1 - 1)(loca._2).id == 'g' ||
        world.map(loca._1 + 1)(loca._2).id == 'g'   ) {

        game.player.hp = game.player.hp - damage
        this.destroy()

    } else if (  loca._1 > 0 && loca._2 > 0 &&
                 world.map(loca._1)(loca._2 - 1).id == 'r' &&
                 !pastLocations.contains((loca._1,loca._2 - 1))) {

        loca                            = (loca._1,loca._2-1)                // updates location
        world.map(loca._1)(loca._2)     = this                               // adds this enemy to map
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