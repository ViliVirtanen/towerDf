package Game

import java.io.{BufferedReader, FileNotFoundException, FileReader, IOException}
import scala.collection.mutable.Buffer

/** World class is the map for the game. It keeps track of objects and
 *  updates them. World is a 2D Buffer of gameObjects.*/
class World(file: String) {


  /** Map of the game. 2D buffer of all the objects in the game currently.
  *  Map can be changed and it is updated constantly. */
  var map: Buffer[Buffer[GameObject]]       = Buffer()



  /** Buffers for keeping different objects that are currently in the game.
   *  This helps with updating the GUI and deleting objects.*/
  var currentObjects: Buffer[GameObject] = Buffer()
  var currentEnemies: Buffer[Enemy]      = Buffer()
  var currentTowers : Buffer[Tower]      = Buffer()
  var currentProj: Buffer[Projectile]    = Buffer()



  /** Create map method creates the starting state/map of the game.
   *  It reads the layout from the given file and adds the correct
   *  object to the map variable. If there is no file or the file
   *  is corrupted. this method throws an exeption. */
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



  /** Update method calls every object currently in game and updates their state.*/
  def update() =
    if (currentObjects.nonEmpty) {
        currentObjects.foreach(_.update())
    }



  /** AddEnemy, addTower and addProjectile methods can be used for adding new
   *  gameObjects to the map. It places the given object to the correct location
   *  and adds it to the objects own buffer.*/
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


  def addProjectile(p: Projectile) = {
     map(p.location._1)(p.location._2) = p
      currentProj += p
      currentObjects += p
  }


}
