import Game.{normalTower, _}
import org.scalatest.funsuite.AnyFunSuite


// unit testing
class SetSuite extends AnyFunSuite {
  val world = new World("src/main/scala/map1.txt")
  world.createMap()
  val game = new Game(world, new Player(100,100))
  val a = new EasyEnemy((1,1),world,game)
  world.addEnemy(a)
  val b = new normalTower((3,1),world)
  world.addTower(b)

  test("should destroy enemy and still work") {

  }
}
