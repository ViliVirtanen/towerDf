import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.control.Button
import scalafx.scene.layout.GridPane
import scalafx.scene.shape.Rectangle

object GameApp extends JFXApp {

  stage = new JFXApp.PrimaryStage {
    title.value = "asdasd"
    width = 600
    height = 450
  }

 val asd = new Button("asd")
  val grid = new GridPane()   //grid for map

  grid.gridLinesVisible = true
  grid.add(asd,1,1)


  val root = grid
  val scene = new Scene(root)  //Scene acts as a container for the scene graph

  stage.scene = scene

}
