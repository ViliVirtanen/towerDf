import Game.{normalTower, _}
import org.scalatest.funsuite.AnyFunSuite

import scala.collection.mutable._


// unit testing
class SetSuite extends AnyFunSuite {
  val world = new World("src/main/scala/map1.txt")
  world.createMap()
  val game = new Game(world, new Player(100,100))
  val a = new EasyEnemy((1,18),world,game)
  world.addEnemy(a)
  val b = new normalTower((50,30),world,game)
  world.addTower(b)

  test("enemy should dissapear to 98, 98 ") {


    a.update()
    a.update()



    assert(world.currentObjects == Buffer(b))
  }
  test("waves should be added to wavegenerator") {
    val asd = new WaveGenerator("src/main/scala/waves1.txt",world,game)
    assert(asd.waves.length == 7)
  }
}
