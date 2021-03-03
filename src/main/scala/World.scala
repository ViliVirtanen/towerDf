import scala.collection.mutable.Buffer


class World(file: String) {

 // map is a 2d Array containing Objects.
 // map can be changed by adding towers etc.
 // THIS IS FOR TEST PURPOSES ONLY. I will implement file reading method soon
var map: Array[Array[GameObject]]       = Array(Array(new Ground(0,0),new Ground(0,1),new Ground(0,2),new Ground(0,3),new Ground(0,4),new Ground(0,5),new Ground(0,6),new Ground(0,7),new Ground(0,8),new Ground(0,9)),
                                                Array(new Ground(1,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0)),
                                                Array(new Ground(2,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0)),
                                                Array(new Ground(3,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0)),
                                                Array(new Road(4,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0)),
                                                Array(new normalTower(5,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0)),
                                                Array(new Obstacle(6,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0)),
                                                Array(new Ground(7,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0)),
                                                Array(new Ground(8,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0)),
                                                Array(new Ground(9,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0),new Ground(0,0)),
                                               )


 var currentObjects: Buffer[GameObject] = Buffer()


 //createMap method will read the layout of the map from file and change the map to look like it
 // makes objects and adds them to the right places??
 def createMap = ???

 // update all objects and map
 def update() = currentObjects.foreach(_.update())

 // add objects
 def addObject(gobject: GameObject) = {
      map(gobject.location._1)(gobject.location._2) = gobject
      currentObjects += gobject
 }

 var mapd: Array[Array[GameObject]] = Array(Array(new Ground(0,0),new Ground(0,0),new Ground(0,0)),
                                            Array(new Ground(0,0),new Ground(0,0),new Ground(0,0)),
                                            Array(new Ground(0,0),new Ground(0,0),new Ground(0,0)),
                                     )

}
