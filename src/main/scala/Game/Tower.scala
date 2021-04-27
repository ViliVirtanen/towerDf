package Game
import scalafx.scene.paint.Color
import scala.collection.mutable.Buffer

/** Tower is a gameObject that has location, price, range and damage.
 *  Tower is placed to a world and it searches enemies in range and
 *  shoots them damaging or killing them.*/

 abstract class Tower(location: (Int, Int), world: World, game: Game) extends GameObject(location){
  val price:       Int
  val range:       Int
  val damage:      Int
  val id = 'T'



   /** Update method checks the distance of all enemies active currently
    *  in world. If distance is  lower than towers range, it adds the
    *  enemy to buffer. After its done method takes the first one of the
    *  enemies and calls the shoot method.*/
  def update() = {
    var inRange: Buffer[Enemy] = Buffer()

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


/** Shoot method takes a target and shoots it with the towers damage.
 *  If targets health is 0, target is destroyed and the projectiles
 *  targeting it are also destroyerd. Shoot also sends a new projectile
 *  targeting the enemy.*/
   def shoot(target: Enemy)  = {
     target.health -= this.damage
     if (target.health < 1) {
       target.destroy()
       world.currentProj.filter(_.target.loc == (98,98)).foreach(_.destroy())
       game.player.coins += 10
     }
     world.addProjectile(new Projectile(location,world,target,damage))
   }
 }



/** Different type of towers. Damage range and color changes depending of the type.*/

class normalTower(location: (Int, Int), world: World, game: Game) extends Tower(location, world, game) {
  val range       = 20
  val price       = 50
  val damage      = 1
  val color       = Color.Blue
}

class rangeTower(location: (Int, Int), world: World, game: Game) extends Tower(location, world, game) {
  val range       = 40
  val price       = 70
  val damage      = 2
  val color       = Color.AliceBlue
}

class damageTower(location: (Int, Int), world: World, game: Game) extends Tower(location, world, game) {
  val range       = 20
  val price       = 70
  val damage      = 5
  val color       = Color.Aquamarine
}