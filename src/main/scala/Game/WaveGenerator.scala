package Game
import java.io.{BufferedReader, FileNotFoundException, FileReader, IOException}
import scala.collection.mutable

/** Waves are written in a different text file.
 *  One row is one wave and one file is one "game"
 *  and one enemy spawns every 7 secs.
 *  If there is no file or waves end, Game just spawns
 *  one normal enemy every 7 secs.
 *  e = easyEnemy, n = normalEnemy, h = hardEnemy. */

class WaveGenerator(file : String) {
var waves = mutable.Buffer[Wave]()

  val fileIn = new FileReader(file)
  val lineIn = new BufferedReader(fileIn)
}


class Wave {
  val enemies = Vector[Enemy]()

}