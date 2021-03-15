import Game._

import org.scalatest.funsuite.AnyFunSuite

// unit testing
class SetSuite extends AnyFunSuite {
  val world = new World("src/main/scala/map1.txt")
  world.createMap()
  val game = new Game(world, new Player(100,100))
  val a = new EasyEnemy((0,0),world,game)

  test("destroy should change enemy loc to 9898") {
   a.destroy()
    assert(a.loc == (98,98) )
  }
}
