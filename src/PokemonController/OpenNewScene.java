package PokemonController;

import PokemonGame.GameworldController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
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
        //currentStage.setFullScreen(true);
        currentStage.setTitle(stageTitle);
        currentStage.show();
    }

    public void openNewSceneKeyevent(String fxmlFilename, Stage currentStage, String stageTitle) throws IOException {
        //Parent root = FXMLLoader.load(getClass().getResource("/fxml/" + fxmlFilename + ".fxml"));

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/GameWorld.fxml"));
        Parent root = fxmlLoader.load();


        Scene newScene = new Scene(root);
        currentStage.setMinWidth(1000);
        currentStage.setMinHeight(1000);
        currentStage.setScene(newScene);
        //currentStage.setFullScreen(true);
        currentStage.setTitle(stageTitle);

        GameworldController controller = fxmlLoader.getController();

        newScene.setOnKeyPressed(e ->  {
            controller.keyboardMove(e);
        });



        currentStage.show();
    }
}