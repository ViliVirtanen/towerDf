package Game

import scalafx.scene.paint.Color


// location type is not permanent
// First charachter is from 0 to 9 and tells whitch row
// and second number is from 0 to 9 and tells whitch column
abstract class GameObject(var location: (Int, Int)) {
  var color: Color
  val id: Char
  def update()

}

class Road(location: (Int, Int)) extends GameObject(location) {
  var color = Color.Brown
  var isEmpty = true
  val id = 'R'

  def update() = ()
}

class Ground(location: (Int, Int)) extends GameObject(location) {
  var color = Color.Green
  val id = 'G'
  def update() = ()
}

class Obstacle(location: (Int, Int)) extends GameObject(location){
  var color = Color.Grey
  val id = 'O'
  def update() = ()
}

// this is the predefined route of the enemy
class Route(location: (Int, Int)) extends GameObject(location) {
  var color = Color.Brown
  val id = 'r'
  def update() = ()
}