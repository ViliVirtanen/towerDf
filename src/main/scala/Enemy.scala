

abstract class Enemy(var loc: (Int, Int)) extends GameObject(loc) {
  var health : Int
  var canPass : Boolean


}

class EasyEnemy(loc: (Int, Int)) extends Enemy(loc) {
  var canPass   = false
  var dead      = false
  var health    = 1


}