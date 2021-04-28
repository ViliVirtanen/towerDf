package Game

import scalafx.scene.paint.Color


/** Gameobjects have a location that can change and the location is
 *  defined as: first number is the row (y-axis) and the second number
 *  is the column (x-axis). In game there is many different kinds of
 *  gameObjects with different kind of functionality and use.*/
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


/** Gray rock like object just for GUI.*/
class Obstacle(location: (Int, Int)) extends GameObject(location){
  val color = Color.Grey
  val id = 'O'
  def update() = ()
}


/** Route class is the real predefined road that enemies move.
 *  It is almost like Road but enemies can only move along Route.*/
class Route(location: (Int, Int)) extends GameObject(location) {
  val color = Color.Brown
  val id = 'r'
  def update() = ()
}


/** Goal and Start classes helps the enemy to track if its at its destination.*/
class Goal(location: (Int, Int)) extends Route(location) {
  override val id = 'g'
}
class Start(location: (Int, Int)) extends Route(location) {
  override val id = 's'
}