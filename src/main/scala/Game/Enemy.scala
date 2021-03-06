package Game
import scalafx.scene.paint.Color

import scala.collection.mutable


abstract class Enemy(var loc: (Int, Int), world: World, game: Game) extends GameObject(loc) {
  var health : Int
  val id     = 'E'
  val damage : Int
  var pastLocations = mutable.Buffer[(Int,Int)](loc)


  // location 98,98 is "cemetary"
  def destroy() = {
     world.map(loc._1)(loc._2) = new Route(loc._1,loc._2)
     world.currentObjects.remove(world.currentObjects.indexOf(this))
     world.currentEnemies.remove(world.currentEnemies.indexOf(this))
     this.loc = (98,98)
  }



 /** Checks for predefined route from all the directions next to it.
  *  Moves to the correct direction and saves the location to pastlocations.
  *  It adds a route tile back to the place it just moved from.
  *  Also checks that it does not go to a location where it has already been.*/
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
  var health        = 20
  val damage        = 5
  val color         = Color.Yellow
}

class mediumEnemy(loc: (Int, Int), world: World, game: Game) extends Enemy(loc, world,game) {
  var health        = 40
  val damage        = 10
  val color         = Color.Orange
}

class bossEnemy(loc: (Int, Int), world: World, game: Game) extends Enemy(loc, world,game) {
  var health        = 100
  val damage        = 50
  val color         = Color.Violet
}