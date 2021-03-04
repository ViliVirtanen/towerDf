import scalafx.scene.paint.Color
//implement different towers

 abstract class Tower(location: (Int, Int), world: World) extends GameObject(location){
  val price:       Int
  val range:       Int
  val attackSpeed: Int
  val damage:      Int
   val id = 'T'
   def update() = ???

   // creates a new projectile?
   def shoot()  = ???


 }

class normalTower(location: (Int, Int), val world: World) extends Tower(location, world) {
  val range       = 1
  val attackSpeed = 1
  val price       = 2
  val damage      = 1
  var canPass     = true
  var color       = Color.Blue




 // and more
}