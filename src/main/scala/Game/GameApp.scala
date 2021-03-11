package Game
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.event.ActionEvent
import scalafx.geometry.Pos
import scalafx.scene.Scene
import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout._
import scalafx.scene.paint.Color._
import scalafx.scene.shape.{Circle, Rectangle}

object GameApp extends JFXApp {

  val world = new World("src/main/scala/map1.txt")
  val player = new Player(100, 100)
  val game = new Game(world, player)
  world.createMap()

  stage = new JFXApp.PrimaryStage {
    title.value = "Tower Defence"
    width = 900
    height = 760
  }

  // proper layout for GUI
  val stack = new StackPane()
  val anchor = new AnchorPane()
  val button1 = new Button("Normal Tower")
  val button2 = new Button("Other Tower")
  val towerT = new Label("Buy Towers")
  val health = new Label("Health " + player.hp.toString)
  // positioning the grid to right place
  val grid = new GridPane()          //grid for map

  val rc = new RowConstraints()
  val cc = new ColumnConstraints()
  grid.setAlignment(Pos.TopRight)
  rc.setPrefHeight(7)
  cc.setPrefWidth(7)
  grid.getColumnConstraints.add(cc)  //adding the constraints to the grid
  grid.getRowConstraints.add(rc)


    try {
      for (i <- 0 to 99) {
        for (j <- 0 to 99) {

          world.map(i)(j) match {
            case g: Ground     => grid.add(Rectangle(7, 7, Green), j, i)
            case g: Road       => grid.add(Rectangle(7, 7, Brown), j, i)
            case g: Route      => grid.add(Rectangle(7, 7, Brown), j, i)
            case g: Obstacle   => grid.add(Rectangle(7, 7, Green), j, i)
                                  grid.add(Circle(2, g.color), j, i)
            case g: Tower      => grid.add(Rectangle(7, 7, Green), j, i)
                                  grid.add(Circle(25, g.color), j, i, 7, 7)
            case g: Enemy      => grid.add(Rectangle(7, 7, Green), j, i)
                                  grid.add(Circle(3, g.color), j, i)
            case _             => throw new Exception
          }
        }
      }
    } catch {
      case exception: Exception =>
        val e = new Exception("Wrong type of element in map")
        throw e
    }



  // Testing button mechanics
  button1.onAction  = (event: ActionEvent) =>  {
     grid.add(Circle(25, Blue), 1, 2,10,10)
}
  // testing how enemy moves
  var a =  new EasyEnemy((9,99),world)
   world.addObject(a)

  def animate = () => {
    grid.add(Circle(3, a.color), a.loca._2,a.loca._1)
    world.update()
  }

  val ticker = new Ticker(animate)
  ticker.start()

  // Setting up the buttons
  anchor.children = List(button1, button2, towerT, health)
  AnchorPane.setTopAnchor(button1, 30)
  AnchorPane.setTopAnchor(button2, 60)
  AnchorPane.setTopAnchor(towerT, 10)
  AnchorPane.setTopAnchor(health, 200)


  stack.children = List(grid, anchor)


  val root = stack
  val scene = new Scene(root) //Scene acts as a container for the scene graph
  stage.scene = scene

}



import javafx.animation.AnimationTimer


class Ticker(function:() =>  Unit) extends AnimationTimer {


    override def handle(now: Long): Unit = {function()}

}