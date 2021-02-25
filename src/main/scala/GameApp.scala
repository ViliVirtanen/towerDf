import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.layout.Pane

object GameApp extends JFXApp {

  stage = new JFXApp.PrimaryStage {
    title.value = "asdasd"
    width = 600
    height = 450
  }

  val root = new Pane          //Simple pane component
  val scene = new Scene(root)  //Scene acts as a container for the scene graph
  stage.scene = scene

}
