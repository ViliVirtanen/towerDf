package Game

import java.io.{BufferedReader, FileNotFoundException, FileReader, IOException}
import scala.collection.mutable.Buffer

class World(file: String) {

 // map is a 2d Buffer containing Objects.
 // map can be changed by adding towers etc.

  var map: Buffer[Buffer[GameObject]]       = Buffer()

  var currentObjects: Buffer[GameObject] = Buffer()
  var currentEnemies: Buffer[Enemy]      = Buffer()
  var currentTowers : Buffer[Tower]      = Buffer()

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

         if (oneLine.startsWith("#") ||
             oneLine.startsWith("R") ||
             oneLine.startsWith("O") ||
             oneLine.startsWith("r") ||
             oneLine.startsWith("s") ||
             oneLine.startsWith("g")   ) {

         for (a <- oneLine) {
           a match {
             case '#' => holder   += new Ground(counter1,counter2)
                         counter2 += 1
             case 'R' => holder   += new Road(counter1,counter2)
                         counter2 += 1
             case 'O' => holder   += new Obstacle(counter1,counter2)
                         counter2 += 1
             case 'r' => holder   += new Route(counter1,counter2)
                         counter2 += 1
             case 's' => holder   += new Start(counter1,counter2)
                         counter2 += 1
             case 'g' => holder   += new Goal(counter1,counter2)
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
 def update() =
   if (currentObjects.nonEmpty) {
       currentObjects.foreach(_.update())
   }

 // add objects
 def addEnemy(e: Enemy) = {
      map(e.loc._1)(e.loc._2) = e
      currentEnemies += e
      currentObjects += e
 }
  def addTower(t: Tower) = {
     map(t.location._1)(t.location._2) = t
      currentTowers += t
      currentObjects += t
  }
}
