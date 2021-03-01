


// location type is not permanent
// First charachter is from 0 to 9 and tells whitch row
// and second number is from 0 to 9 and tells whitch column
abstract class GameObject(val location: (Int, Int)) {
  var canPass: Boolean


}


class Road(location: (Int, Int)) extends GameObject(location) {

  var canPass: Boolean = true

}

class Ground(location: (Int, Int)) extends GameObject(location) {
  var canPass: Boolean = true
}

class Obstacle(location: (Int, Int)) extends GameObject(location){
  var canPass: Boolean = true
}