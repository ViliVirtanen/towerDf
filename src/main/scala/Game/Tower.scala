package Game

import scalafx.scene.paint.Color

import scala.collection.mutable.Buffer
//implement different towers

 abstract class Tower(location: (Int, Int), world: World) extends GameObject(location){
  val price:       Int
  val range:       Int
  val damage:      Int
  val id = 'T'
   var test = false
   var target: Option[Enemy]= None
  def update() = {
    var inRange: Buffer[Enemy] = Buffer()
     test = false
    target = None
    for (i <- world.currentEnemies) {
      val xd       = i.loc._1 - this.location._1
      val xy       = i.loc._2 - this.location._2
      val distance = math.sqrt(xd*xd + xy*xy)
      if (distance < range.toDouble) {
        inRange += i
      }
    }

     if ( inRange.nonEmpty) {
       shoot(inRange.head)
     }
  }

  // creates a new projectile?

   def shoot(target: Enemy)  = {
     target.health -= this.damage
     if (target.health < 1) {
       target.destroy()
       world.currentProj.filter(_.target.loc == (98,98)).foreach(_.destroy())
     }

     test = true
     this.target = Option(target)
     world.addProjectile(new Projectile(location,world,target,damage))    // does not work porperly when enemy dies and projectile is flying still

   }




 }

class normalTower(location: (Int, Int), world: World) extends Tower(location, world) {
  val range       = 20
  val price       = 2
  val damage      = 2
  val color       = Color.Blue
}