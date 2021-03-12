package Game




class Game(val world: World, val player: Player) {

  var gameLost = player.hp == 0
  def buyTower(o: Tower, cost: Int) = {
    // TOWERS CANT BE ADDED ON TOP OF ROAD
    if (cost <= player.coins) {
      world.addTower(o)
      player.coins = player.coins - cost
      "Success"
    } else {
      "Insufficient funds"
   }
 }

  // updates the state of the game moving enemies etc.
  def update() = world.update()

}
