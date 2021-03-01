import javafx.geometry.Insets
import scalafx.application.JFXApp
import scalafx.geometry.Pos
import scalafx.scene.Scene
import scalafx.scene.layout.{ColumnConstraints, GridPane, RowConstraints}
import scalafx.scene.paint.Color._
import scalafx.scene.shape.Rectangle

object GameApp extends JFXApp {

  stage = new JFXApp.PrimaryStage {
    title.value = "asdasd"
    width = 800
    height = 700
  }

// positioning the grid to right place
  val grid = new GridPane()   //grid for map
  val rc   = new RowConstraints()
  val cc   = new ColumnConstraints()
      grid.gridLinesVisible = true
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
  for (i <- 1 to 9) {
    for (j <- 1 to 9) {
      grid.add(Rectangle(30,30,White),i,j)
    }
  }

  val root = grid
  val scene = new Scene(root)  //Scene acts as a container for the scene graph

  stage.scene = scene

}
