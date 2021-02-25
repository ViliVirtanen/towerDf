
//implement different towers

 class Tower(location: (Int,Int)) extends GameObject(location){

 }

class normalTower(location: (Int,Int)) extends Tower(location) {
 val range       = 2
 val attackSpeed = 2
 // and more
}