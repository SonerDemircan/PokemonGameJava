package PokemonController;

import PokemonGame.World;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

// Deze method laat ons toe om bij een actie (bv. klikken op een button) een nieuwe scene (fxml) te openen
// 3 parameters nodig:
// fxmlFilename is de nieuwe fxml wat geopend moet worden
// currentStage om te switchen tussen de scenes
// stageTitle (spreekt voor zich :D)
public class OpenNewScene {
    public void openNewScene(String fxmlFilename, Stage currentStage, String stageTitle) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/" + fxmlFilename + ".fxml"));

        Scene newScene = new Scene(root);
        currentStage.setMinWidth(1000);
        currentStage.setMinHeight(1000);
        currentStage.setScene(newScene);
        currentStage.setFullScreen(false);
        currentStage.setTitle(stageTitle);
        currentStage.show();
    }

    //om onze world object door te geven aan de volgende scene, zo kunnen we de objecten aangemaakt in deze controller aanspreken in andere controllers
    public void openNewSceneWithParam(String fxmlFilename, Stage currentStage, String stageTitle, World world) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + fxmlFilename + ".fxml"));
        BattleSceneController controller = new BattleSceneController(world);
        loader.setController(controller);
        Parent root = loader.load();


        Scene newScene = new Scene(root);
        currentStage.setMinWidth(1000);
        currentStage.setMinHeight(1000);
        currentStage.setScene(newScene);
        currentStage.setFullScreen(false);
        currentStage.setTitle(stageTitle);

        currentStage.show();


    }

}