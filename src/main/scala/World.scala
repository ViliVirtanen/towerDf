import scala.collection.mutable.Buffer


class World(file: String) {

 // map is a 2d Array containing chars. Every char has different meaning
 // G = Ground, T = Tower, R = Road, E = Enemy
 // map can be changed by adding towers etc.
 var map: Array[Array[Char]] = Array(Array('G','G','G','G','G','G','G','G','G','G'),
                                     Array('G','G','G','G','G','G','G','G','G','G'),
                                     Array('G','G','G','G','G','G','G','G','G','G'),
                                     Array('G','G','G','G','G','G','G','G','G','G'),
                                     Array('G','G','G','G','G','G','G','G','G','G'),
                                     Array('G','G','G','G','G','G','G','G','G','G'),
                                     Array('G','G','G','G','G','G','G','G','G','G'),
                                     Array('G','G','G','G','G','G','G','G','G','G'),
                                     Array('G','G','G','G','G','G','G','G','G','G'),
                                     Array('G','G','G','G','G','G','G','G','G','G'),
                                     )

 var currentObjects: Buffer[GameObject] = ???


 //createMap method will read the layout of the map from file and change the map to look like it
 def createMap = ???

 // update all objects and map
 def update() = ???


}
