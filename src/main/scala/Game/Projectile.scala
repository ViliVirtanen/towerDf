package Game

import scalafx.scene.paint.Color._


class Projectile(loc: (Int,Int), world: World, target: Enemy, damage: Int) extends GameObject(loc) {
  val color = Black
  val id: Char = 'p'
  // moves towards targeted enemy
  def update(): Unit = ()
}
