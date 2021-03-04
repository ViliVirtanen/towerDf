import scalafx.application.JFXApp
import scalafx.geometry.Pos
import scalafx.scene.Scene
import scalafx.scene.control.Button
import scalafx.scene.layout.{AnchorPane, ColumnConstraints, GridPane, RowConstraints, StackPane}
import scalafx.scene.paint.Color._
import scalafx.scene.shape.{Circle, Rectangle}

object GameApp extends JFXApp {

                        // have to use absolute path for some reason
  val world  = new World("C:\\Users\\virta\\IdeaProjects\\Tower Defence\\src\\main\\scala\\map1.txt")
  val player = new Player(100,100)
  val game   = new Game(world, player)
  world.createMap()

  stage = new JFXApp.PrimaryStage {
    title.value = "Tower Defence"
    width = 900
    height = 750
  }

// proper layout for GUI
  val stack   = new StackPane()
  val anchor  = new AnchorPane()
  val button1 = new Button("Normal Tower")
  val button2 = new Button("Other Tower")
// positioning the grid to right place
  val grid    = new GridPane()              //grid for map
  val rc      = new RowConstraints()
  val cc      = new ColumnConstraints()
      grid.setAlignment(Pos.TopRight)
      rc.setPrefHeight(70)
      cc.setPrefWidth(70)
  //adding the constraints to the grid
  for (i <- 0 to 9) {
      grid.getColumnConstraints.add(cc)
      grid.getRowConstraints.add(rc)
  }

 // making visible grid.
  try {
    for (i <- 0 to 9) {
     for (j <- 0 to 9) {

       world.map(i)(j) match {
         case g: Ground   =>  grid.add(Rectangle(70,70,Green),j,i)
         case g: Road     =>  grid.add(Rectangle(70,70,Brown),j,i)
         case g: Obstacle =>  grid.add(Rectangle(70,70,Green),j,i)
                              grid.add(Circle(25,g.color),j,i)
         case g: Tower    =>  grid.add(Rectangle(70,70,Green),j,i)
                              grid.add(Circle(50,g.color),j,i)
         case _           =>  throw new Exception
       }
     }
    }
  } catch {
     case exception: Exception =>
       val e = new Exception("Wrong type of element in map")
       throw e
  }

// Setting up the buttons
  anchor.children = List(button1,button2)
  AnchorPane.setTopAnchor(button1,30)
  AnchorPane.setTopAnchor(button2,60)

  stack.children  = List(grid,anchor)
  val root        = stack
  val scene       = new Scene(root)  //Scene acts as a container for the scene graph

  stage.scene = scene

}
