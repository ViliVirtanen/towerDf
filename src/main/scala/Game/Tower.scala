package Game

import scalafx.scene.paint.Color

import scala.collection.mutable.Buffer
//implement different towers

 abstract class Tower(location: (Int, Int), world: World) extends GameObject(location){
  val price:       Int
  val range:       Int
  val damage:      Int
  val id = 'T'
  def update() = {
    var inRange: Buffer[Enemy] = Buffer()
   try {
    for (i <- world.currentEnemies) {
      val xd       = i.loc._1 - this.location._1
      val xy       = i.loc._2 - this.location._2
      val distance = math.sqrt(xd*xd + xy*xy)
      if (distance < range) {
        inRange += i
        throw new Exception     // Breaks the loop maybe???
      }
    }
   } catch {
     case e: Exception =>
   }
    // shoot at inrange.head
  }

  // creates a new projectile?
  def shoot(target: Enemy)  = ()


 }

class normalTower(location: (Int, Int), world: World) extends Tower(location, world) {
  val range       = 15
  val price       = 2
  val damage      = 1
  val color       = Color.Blue
}