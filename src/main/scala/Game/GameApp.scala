package Game
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.event.ActionEvent
import scalafx.geometry.Pos
import scalafx.scene.Scene
import scalafx.scene.control.{Button, Label, TextInputDialog}
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
  var health = new Label("Health " + player.hp.toString)
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
    val dialog = new TextInputDialog(defaultValue = "19,53") {
      initOwner(stage)
      title = "Place a tower"
      headerText = "Enter a location for your tower as:\n number,number" +
                   " \n first y and then x \n0,0 is top left corner"
      contentText = "Please enter a location:"
    }
     val result = dialog.showAndWait()
    result match {
       case Some(loc) =>  if ((loc.split(",")(1).toInt <100 && loc.split(",")(1).toInt >= 0) &&
                              (loc.split(",")(0).toInt <100 && loc.split(",")(0).toInt >= 0)   ) {

                          grid.add(Circle(5, Blue), loc.split(",")(1).toInt,loc.split(",")(0).toInt ,4,4)
                         world.addTower( new normalTower((loc.split(",")(0).toInt,loc.split(",")(1).toInt),world))
       }

       case None       => println("Dialog was canceled.")
   }
  }


  // testing how enemy moves
  var a =  new EasyEnemy((9,99),world,game)
   world.addEnemy(a)
  var b =  new EasyEnemy((9,97),world,game)
   world.addEnemy(b)
  var c =  new EasyEnemy((9,95),world,game)
   world.addEnemy(c)


  // every 7 secs new enemy spawns. still have to figure out how to close this
  val t = new java.util.Timer()
val task = new java.util.TimerTask {
  def run() = {
    var c =  new EasyEnemy((9,95),world,game)
    world.addEnemy(c)

  }
}
t.schedule(task, 1000L, 1000L)


  def animate = () => {
    // moves all enemies
    for (o <- world.currentEnemies) {
      if ( o.loc != (98,98)) {
       grid.add(Circle(3, o.color), o.loc._2,o.loc._1)
      }
       health.setText("health :" + player.hp.toString)
      if (o.pastLocations.length > 1) {
        grid.add( Rectangle(7, 7, Brown),
        o.pastLocations(o.pastLocations.length -2)._2,
        o.pastLocations(o.pastLocations.length -2)._1 )
      }
    }

    for (o <- world.currentProj) {
       if ( o.loc != (98,98) && o.loc._2>=0 && o.loc._1 >=0) {
         grid.add(Circle(2, o.color), o.loc._2,o.loc._1)
       }
       if (o.lastLocs.length > 2) {
         val last = o.lastLocs(o.lastLocs.length -2)

         grid.add( Rectangle(7, 7, world.map(last._1)(last._2).color),
                   last._2, last._1 )
       }
    }

    // stupid and easy way of making tower shoot
   // for (o <- world.currentTowers) {
   //    if (o.test && o.target.isDefined) {
   //     grid.add(Circle(2,Black),o.target.get.loc._2,o.target.get.loc._1)
   //   }
   //}

    // this is very stupid way around a bug :D
    try {
      world.update()
    } catch {
      case e: NullPointerException =>
    }


    if (game.gameLost) {
      val lost = new Label("you lost!")

       lost.setPrefSize(110,100)
       stack.children += lost
        ()
    }
  }


//  val ticker = new Ticker(animate)
 // ticker.start()

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



// Animator
import javafx.animation.AnimationTimer

class Ticker(function:() =>  Unit) extends AnimationTimer {
    override def handle(now: Long): Unit = {function()}
}