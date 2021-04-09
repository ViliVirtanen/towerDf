package Game
import java.io.{BufferedReader, FileNotFoundException, FileReader, IOException}
import scala.collection.mutable



/** Waves are written in a different text file.
 *  One row is one wave and one file is one "game"
 *  and one enemy spawns every 7 secs.
 *  If there is no file or waves end, Game just spawns
 *  one normal enemy every 7 secs.
 *  e = easyEnemy, m = mediumEnemy, b = bossEnemy. */

class WaveGenerator(file : String, world: World, game: Game) {
   var waves = mutable.Buffer[Wave]()

   try {
       val fileIn = new FileReader(file)
       val lineIn = new BufferedReader(fileIn)

     try {
       var oneLine = lineIn.readLine()

       while ({oneLine = lineIn.readLine(); oneLine != null}) {

         if (oneLine.startsWith("e") ||
             oneLine.startsWith("m") ||
             oneLine.startsWith("b")  ) {

          var holder = new Wave

         for (a <- oneLine) {

           a match {
             case 'e' => holder.addEnemy(new EasyEnemy((9,99),world, game))

             case 'm' => holder.addEnemy(new mediumEnemy((9,99),world, game))

             case 'b' => holder.addEnemy(new bossEnemy((9,99),world, game))

             case  _  => throw new Exception
           }
         }
         waves += holder
         }
      }

     } finally {
       fileIn.close()
        lineIn.close()
     }

   } catch {
      case notFound: FileNotFoundException => throw new FileNotFoundException("File not found")
      case e: IOException                  => throw new IOException("Reading failed")
      case a: Exception                    => throw new Exception("Wave file is corrupted")
   }
}



  class Wave {
  var enemies = mutable.Buffer[Enemy]()
  def addEnemy(e: Enemy) = enemies += e
}