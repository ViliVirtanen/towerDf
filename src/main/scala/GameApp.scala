import javafx.geometry.Insets
import scalafx.application.JFXApp
import scalafx.geometry.Pos
import scalafx.scene.Scene
import scalafx.scene.layout.{ColumnConstraints, GridPane, RowConstraints}
import scalafx.scene.paint.Color._
import scalafx.scene.shape.{Circle, Line, Rectangle, TriangleMesh}

object GameApp extends JFXApp {

  val world  = new World("")
  val player = new Player(100,100)
  val game   = new Game(world, player)

  stage = new JFXApp.PrimaryStage {
    title.value = "asdasd"
    width = 800
    height = 700
  }

// positioning the grid to right place
  val grid = new GridPane()   //grid for map
  val rc   = new RowConstraints()
  val cc   = new ColumnConstraints()
      grid.setAlignment(Pos.TopRight)
      grid.setPadding(new Insets(10,10,10,10))
      rc.setPrefHeight(50)
      cc.setPrefWidth(50)

  //adding the constraints to the grid
  for (i <- 1 to 10) {
    grid.getColumnConstraints.add(cc)
    grid.getRowConstraints.add(rc)
  }

 // making visible grid.
  try {
    for (i <- 0 to 9) {
     for (j <- 0 to 9) {

       world.map(i)(j) match {
         case g: Ground =>    grid.add(Rectangle(50,50,Green),j,i)
         case g: Road  =>     grid.add(Rectangle(50,50,Brown),j,i)
         case g: Obstacle =>  grid.add(Rectangle(50,50,Green),j,i)
                              grid.add(Circle(15,g.color),j,i)
         case g: Tower =>     grid.add(Rectangle(50,50,Green),j,i)
                              grid.add(Circle(20,g.color),j,i)

         case _ =>      throw new Exception
       }

     }
    }
  } catch {
     case exception: Exception =>
       val e = new Exception("Wrong type of element in map")
       throw e
  }


  val root = grid
  val scene = new Scene(root)  //Scene acts as a container for the scene graph

  stage.scene = scene

}
