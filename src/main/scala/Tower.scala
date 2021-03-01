
//implement different towers

 abstract class Tower(location: (Int, Int)) extends GameObject(location){
  val price:       Int
  val range:       Int
  val attackSpeed: Int
  val damage:      Int

 }

class normalTower(location: (Int, Int)) extends Tower(location) {
  val range       = 1
  val attackSpeed = 1
  val price       = 2
  val damage      = 1
  var canPass     = true
 // and more
}