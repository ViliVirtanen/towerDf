


class Game(val world: World, val player: Player) {

  var gameLost = player.hp == 0

  def buyTower(o: Tower, cost: Int) = {
    if (cost <= player.coins) {
      world.addObject(o)
      player.coins = player.coins - cost
      "Success"
    } else {
      "Insufficient funds"
    }
  }

  // updates the state of the game moving enemies etc.
  def update() = world.update()

}
