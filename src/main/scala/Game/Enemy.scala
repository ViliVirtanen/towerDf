package Game
import scalafx.scene.paint.Color

import scala.collection.mutable


abstract class Enemy(var loc: (Int, Int), world: World, game: Game) extends GameObject(loc) {
  var health : Int
  val id   = 'E'
  val damage : Int

  var pastLocations = mutable.Buffer[(Int,Int)](loc)
  // location 98,98 is "cemetary"
  def destroy() = {
     world.map(loc._1)(loc._2) = new Route(loc._1,loc._2)
     world.currentObjects.remove(world.currentObjects.indexOf(this))
     world.currentEnemies.remove(world.currentEnemies.indexOf(this))
     this.loc = (98,98)


  }

  // checks if some adjecent tile is predefined route and moves there.
  def update() = {
    if ( world.map(loc._1 - 1)(loc._2).id == 'g' ) {
        this.destroy()
        game.player.hp = game.player.hp - damage


    } else if (  loc._1 > 0 && loc._2 > 0 &&
                 world.map(loc._1)(loc._2 - 1).id == 'r' &&
                 !pastLocations.contains((loc._1,loc._2 - 1))) {

        loc                            = (loc._1,loc._2-1)                // updates location
        world.map(loc._1)(loc._2)      = this                               // adds this enemy to map
        world.map(loc._1)(loc._2 + 1)  = new Route((loc._1,loc._2 + 1))   // adds a route tile back when moves
        pastLocations                 += loc

    } else if (  loc._1 > 0 && loc._2 > 0 &&
                 world.map(loc._1 - 1)(loc._2).id == 'r' &&
                 !pastLocations.contains((loc._1-1,loc._2))) {

        loc                          = (loc._1-1,loc._2)
        world.map(loc._1)(loc._2)    = this
        world.map(loc._1+1)(loc._2)  = new Route((loc._1 + 1,loc._2 ))
        pastLocations               += loc

    } else if (  loc._1 > 0 && loc._2 > 0 &&
                 world.map(loc._1 + 1)(loc._2).id == 'r' &&
                 !pastLocations.contains((loc._1+1,loc._2))) {

        loc                          = (loc._1+1,loc._2)
        world.map(loc._1)(loc._2)    = this
        world.map(loc._1-1)(loc._2)  = new Route((loc._1 - 1,loc._2 ))
        pastLocations               += loc
    }
  }

}

class EasyEnemy(loc: (Int, Int), world: World, game: Game) extends Enemy(loc, world,game) {
  var health        = 200
  val damage        = 4
  val color         = Color.Yellow


}