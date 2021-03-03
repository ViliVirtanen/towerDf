import scala.collection.mutable.Buffer


class World(file: String) {

 // map is a 2d Array containing chars. Every char has different meaning
 // G = Ground, T = Tower, R = Road, E = Enemy
 // map can be changed by adding towers etc.
 // maybe change map to array(array(GameObject)) so location and so on is easier to find???
 var map: Array[Array[Char]] = Array(Array('G','G','G','G','G','G','G','G','G','G'),
                                     Array('G','G','G','G','G','G','G','G','G','G'),
                                     Array('G','G','G','G','R','G','G','G','G','G'),
                                     Array('G','G','G','G','G','G','G','G','G','R'),
                                     Array('G','G','G','G','G','G','G','G','G','G'),
                                     Array('G','G','G','G','G','G','G','G','G','G'),
                                     Array('T','G','G','G','G','G','G','G','G','G'),
                                     Array('G','G','G','G','G','G','G','G','G','G'),
                                     Array('G','G','G','G','G','G','G','G','G','G'),
                                     Array('G','G','G','G','G','G','G','G','G','G'),
                                     )

 var currentObjects: Buffer[GameObject] = Buffer()


 //createMap method will read the layout of the map from file and change the map to look like it
 def createMap = ???

 // update all objects and map
 def update() = currentObjects.foreach(_.update())

 // add objects
 def addObject(gobject: GameObject) = {
      map(gobject.location._1)(gobject.location._2) = gobject.id
      currentObjects += gobject
 }


}
