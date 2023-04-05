package PokemonController;

import PokemonGame.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

// Deze method in de klasse OpenNewScreen laat ons toe om bij een actie (bv. klikken op een button) een nieuw scherm (fxml) te openen
// & het actieve scherm te sluiten (zodat die niet op de achtergrond is)
// 2 parameters nodig:
// fxmlFilename is de nieuwe fxml die geopend moet worden
// currentStage is het actieve scherm wat dan moet worden gesloten

//dit is een test van daniel

public class OpenNewScreen {
    public void openNewScreen(String fxmlFilename, Stage currentStage) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fmxl/" + fxmlFilename + ".fxml"));
        stage.setTitle("New Scene");
        stage.setScene(new Scene(root, 300, 275));
        stage.show();
        currentStage.close();
    }
}
