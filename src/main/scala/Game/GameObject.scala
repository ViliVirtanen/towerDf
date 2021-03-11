package Game

import scalafx.scene.paint.Color


// location type is not permanent
// First charachter is from 0 to 9 and tells whitch row
// and second number is from 0 to 9 and tells whitch column
abstract class GameObject(var location: (Int, Int)) {
  val color: Color
  val id: Char
  def update()

}

class Road(location: (Int, Int)) extends GameObject(location) {
  val color = Color.Brown
  val id = 'R'

  def update() = ()
}

class Ground(location: (Int, Int)) extends GameObject(location) {
  val color = Color.Green
  val id = 'G'
  def update() = ()
}

class Obstacle(location: (Int, Int)) extends GameObject(location){
  val color = Color.Grey
  val id = 'O'
  def update() = ()
}

// this is the predefined route of the enemy
class Route(location: (Int, Int)) extends GameObject(location) {
  val color = Color.Brown
  val id = 'r'
  def update() = ()
}

class Goal(location: (Int, Int)) extends Route(location) {
  override val id = 'g'
}
class Start(location: (Int, Int)) extends Route(location) {
  override val id = 's'
}