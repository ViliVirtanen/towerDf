import scalafx.scene.paint.Color


abstract class Enemy(var loc: (Int, Int)) extends GameObject(loc) {
  var health : Int
  var canPass : Boolean
  val id = 'E'
  def update() = ???


}

class EasyEnemy(loc: (Int, Int)) extends Enemy(loc) {
  var canPass   = false
  var dead      = false
  var health    = 1
  var color     = Color.Red



}