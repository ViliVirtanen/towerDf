


// location type is not permanent
class GameObject(val location: (Int,Int)) {

}


class Road(location: (Int,Int)) extends GameObject(location)

class Ground(location: (Int,Int)) extends GameObject(location)

class Obstacle(location: (Int,Int)) extends GameObject(location)