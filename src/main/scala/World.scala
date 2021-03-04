import scala.collection.mutable.Buffer
import java.io.FileReader
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.IOException

class World(file: String) {

 // map is a 2d Buffer containing Objects.
 // map can be changed by adding towers etc.

  var map: Buffer[Buffer[GameObject]]       = Buffer()
  // im not sure if I even need current objects
  var currentObjects: Buffer[GameObject] = Buffer()

 //createMap method will read the layout of the map from file and makes a 2d buffer
 // makes objects and adds them to the right places
 def createMap() = {
   var counter1 = 0

   try {
     val fileIn = new FileReader(file)
     val lineIn = new BufferedReader(fileIn)

     try {
      var oneLine = lineIn.readLine()

      while ({oneLine = lineIn.readLine(); oneLine != null}) {
        var counter2 = 0
        var holder: Buffer[GameObject] = Buffer()

         if (oneLine.startsWith("#") || oneLine.startsWith("R") || oneLine.startsWith("O")) {
         for (a <- oneLine) {
           a match {
             case '#' => holder   += new Ground(counter1,counter2)
                         counter2 += 1
             case 'R' => holder   += new Road(counter1,counter2)
                         counter2 += 1
             case 'O' => holder   += new Obstacle(counter1,counter2)
                         counter2 += 1
             case  _  => throw new Exception
           }
         }
         map += holder
         counter1 += 1
         }
      }
     } finally {
       fileIn.close()
        lineIn.close()
     }
   } catch {
      case notFound: FileNotFoundException => throw new FileNotFoundException("File not found")
      case e: IOException                  => throw new IOException("Reading failed")
      case a: Exception                    => throw new Exception("Map file is corrupted")
    }
 }

 // update all objects and map
 def update() = map.foreach(_.foreach(_.update()))

 // add objects
 def addObject(gobject: GameObject) = {
      map(gobject.location._1)(gobject.location._2) = gobject
      currentObjects += gobject
 }
}
