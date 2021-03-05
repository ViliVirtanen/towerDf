package Game

import scalafx.scene.paint.Color


// location type is not permanent
// First charachter is from 0 to 9 and tells whitch row
// and second number is from 0 to 9 and tells whitch column
abstract class GameObject(val location: (Int, Int)) {
  var canPass: Boolean
  var color: Color
  val id: Char
  def update()

}

class Road(location: (Int, Int)) extends GameObject(location) {
  var color = Color.Brown
  var canPass: Boolean = false
  var isEmpty = true
  val id = 'R'

  def update() = ???
}

class Ground(location: (Int, Int)) extends GameObject(location) {
  var canPass: Boolean = false
  var color = Color.Green
  val id = 'G'
  def update() = ???
}

class Obstacle(location: (Int, Int)) extends GameObject(location){
  var color = Color.Grey
  var canPass: Boolean = false
  val id = 'O'
  def update() = ???
}

// this is the predefined route of the enemy
class Route(location: (Int, Int)) extends GameObject(location) {
  var canPass: Boolean = true
  var color = Color.Brown
  val id = 'r'
  def update() = ???
}