import scalafx.scene.paint.Color


// location type is not permanent
// First charachter is from 0 to 9 and tells whitch row
// and second number is from 0 to 9 and tells whitch column
abstract class GameObject(val location: (Int, Int)) {
  var canPass: Boolean
  var color: Color
  val id: Char



}


class Road(location: (Int, Int)) extends GameObject(location) {
  var color = Color.Brown
  var canPass: Boolean = true
  val id = 'R'

}

class Ground(location: (Int, Int)) extends GameObject(location) {
  var canPass: Boolean = true
  var color = Color.Green
  val id = 'G'

}

class Obstacle(location: (Int, Int)) extends GameObject(location){
  var color = Color.Grey
  var canPass: Boolean = true
  val id = 'O'

}