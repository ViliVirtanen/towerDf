package Game
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.event.ActionEvent
import scalafx.geometry.Pos
import scalafx.scene.Scene
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control.{Alert, Button, Label, TextInputDialog}
import scalafx.scene.layout._
import scalafx.scene.paint.Color._
import scalafx.scene.shape.{Circle, Rectangle}

object GameApp extends JFXApp {

  val world = new World("src/main/scala/map1.txt")
  val player = new Player(100, 100)
  val game = new Game(world, player)
  val gener = new WaveGenerator("src/main/scala/waves1.txt",world,game)
      world.createMap()

  stage = new JFXApp.PrimaryStage {
    title.value = "Tower Defence"
    width = 900
    height = 760
  }

  /** proper layout for GUI. It has a grid for the map itself and
      Buttons on the side. This Sets the tiles in grid to a certain
      size.
   **/

  val stack = new StackPane()
  val anchor = new AnchorPane()
  val button1 = new Button("Normal Tower(50c)")
  val button2 = new Button("Range Tower(70c)")
  val button3 = new Button("Damage Tower(70c)")
  val towerT = new Label("Buy Towers:")
  val health = new Label("Health :" + player.hp.toString)
  val coins = new Label("")
  // positioning the grid to right place
  val grid = new GridPane()          //grid for map
  val rc = new RowConstraints()
  val cc = new ColumnConstraints()
  grid.setAlignment(Pos.TopRight)
  rc.setPrefHeight(7)
  cc.setPrefWidth(7)
  grid.getColumnConstraints.add(cc)  //adding the constraints to the grid
  grid.getRowConstraints.add(rc)


  /** This loop paints the map to the GUI based on information from world class.
      If The map contains wrong type of elements, this throws an exception.
   **/

    try {
      for (i <- 0 to 99) {
        for (j <- 0 to 99) {

          world.map(i)(j) match {
            case g: Ground     => grid.add(Rectangle(7, 7, Green), j, i)
            case g: Road       => grid.add(Rectangle(7, 7, Brown), j, i)
            case g: Route      => grid.add(Rectangle(7, 7, Brown), j, i)
            case g: Obstacle   => grid.add(Rectangle(7, 7, Green), j, i)
                                  grid.add(Circle(2, g.color), j, i)

            case _             => throw new Exception
          }
        }
      }
    } catch {
      case exception: Exception =>
        val e = new Exception("Wrong type of element in map")
        throw e
    }



  /** Different buttons spawn different towers
   *  n is normalTower, r is rangeTower, d is damageTower */

  button1.onAction  = (event: ActionEvent) =>  buttonAction('n')
  button2.onAction  = (event: ActionEvent) =>  buttonAction('r')
  button3.onAction  = (event: ActionEvent) =>  buttonAction('d')



  // every 7 secs new enemy spawns.
  val t = new java.util.Timer()
  var tick = 0
  var tock = 0    // :DD
  var change = false
  val task = new java.util.TimerTask {
  def run() = {

    if (gener.waves.nonEmpty && gener.waves.length > tick) {

        if (gener.waves(tick).enemies.length > tock) {
          world.addEnemy(gener.waves(tick).enemies(tock))
          tock += 1
        } else {
           tick += 1
           tock = 0
        }

    } else {
         var c =  new EasyEnemy((9,95),world,game)
         world.addEnemy(c)
    }
  }
  }

  t.schedule(task, 1000L, 3000L)
  override def stopApp(): Unit = {t.cancel()}




def buttonAction(towerType: Char) = {
  val dialog = new TextInputDialog(defaultValue = "19,53") {
     initOwner(stage)
         title = "Place a tower"
    headerText = "Enter a location for your tower as:\n number,number" +
                   " \n first y and then x \n0,0 is top left corner."
   contentText = "Please enter a location:"
    }


  /** Helper function for error situations.
   *  Creates a popup window with error message. */
  def error(text: String) = {
    new Alert(AlertType.Error) {
      initOwner(stage)
      title       = "Error Dialog"
      headerText  = text
      contentText = "Ooops, there was an error!"
    }.showAndWait()
  }


    val result = dialog.showAndWait()

    result match {

       case Some(loc) =>  if ( loc.contains(",") &&
                             ( loc.split(",")(1).toInt <100 && loc.split(",")(1).toInt >= 0) &&
                             ( loc.split(",")(0).toInt <100 && loc.split(",")(0).toInt >= 0)  ) {


                            if (towerType == 'n' ) {

                             if (game.buyTower( new normalTower((loc.split(",")(0).toInt,
                                 loc.split(",")(1).toInt),world, game)) == "Success"    ) {

                                 grid.add(Circle(5, Blue), loc.split(",")(1).toInt,loc.split(",")(0).toInt ,4,4)
                             }   else error("Not enough coins")



                            } else if (towerType == 'r') {

                               if (game.buyTower( new rangeTower((loc.split(",")(0).toInt,
                                   loc.split(",")(1).toInt),world, game)) == "Success"    ) {

                                   grid.add(Circle(5, AliceBlue), loc.split(",")(1).toInt,loc.split(",")(0).toInt ,4,4)
                               }   else error("Not enough coins")



                            } else if (towerType == 'd') {

                               if (game.buyTower( new damageTower((loc.split(",")(0).toInt,
                                   loc.split(",")(1).toInt),world, game)) == "Success"    ) {

                                   grid.add(Circle(5, Aquamarine), loc.split(",")(1).toInt,loc.split(",")(0).toInt ,4,4)
                               }   else error("Not enough coins")
                            }


                          } else {
                            error("Wrong location. try again.")
                          }

       case None       =>
   }
}





  def animate = () => {

    coins.setText("Coins :" + player.coins.toString + "c")
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
         grid.add(Circle(1, o.color), o.loc._2,o.loc._1)
       }

       if (o.lastLocs.length > 1) {
         val last = o.lastLocs(o.lastLocs.length -2)
         grid.add( Rectangle(7, 7, world.map(last._1)(last._2).color),
                   last._2, last._1 )
       }
    }


    try {
      world.update()
    } catch {
      case e: NullPointerException =>
    }

    if (player.hp < 1 && !done) {
        end()
    }
  }



 val ticker = new Ticker(animate)
     ticker.start()


  /** If player dies a popup shows up on screen.
   *  done makes sure that this end() is called only once. */
  var done = false
  def end() = {
    done = true
    new Alert(AlertType.Information) {
      initOwner(stage)
      title = "Game over"
      headerText = "You lost."
    }.show()
      stopApp()
  }

  // Setting up the buttons
  anchor.children = List(button1, button2, button3, towerT, health,coins)
  AnchorPane.setTopAnchor(button1, 30)
  AnchorPane.setTopAnchor(button2, 60)
  AnchorPane.setTopAnchor(button3, 90)
  AnchorPane.setTopAnchor(towerT, 10)
  AnchorPane.setTopAnchor(health, 200)
  AnchorPane.setTopAnchor(coins,215)



  stack.children = List(grid, anchor)
  val root = stack
  val scene = new Scene(root) //Scene acts as a container for the scene graph
  stage.scene = scene

}






// Animator
import javafx.animation.AnimationTimer

class Ticker(function:() =>  Unit) extends AnimationTimer {

  private var lastUpdate = 0L
    override def handle(now: Long): Unit = {
      if (now - lastUpdate >= 250_000_000) {
          function()
        lastUpdate = now
      }

    }

}