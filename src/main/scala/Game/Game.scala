package Game

import scalafx.scene.paint.Color.Brown


class Game(val world: World, val player: Player) {

  var gameLost = player.hp == 0
  def buyTower(o: Tower) = {
    // TOWERS CANT BE ADDED ON TOP OF ROAD
    if (o.price <= player.coins && world.map(o.location._1)(o.location._2).color != Brown) {
      world.addTower(o)
      player.coins = player.coins - o.price
      "Success"
    } else {
      "Insufficient funds"
   }
 }

  // updates the state of the game moving enemies etc.
  def update() = world.update()

}
