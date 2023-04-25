package PokemonController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeScreenController {

    //private Scene HomeScreenScene;

    //public void setHomeScreenScene(Scene scene) {
    //    this.HomeScreenScene = scene;
    //}

    @FXML
    private Button btnStartGame;

    @FXML
    private Label lblStartText;

    @FXML
    private ImageView imgPokeballs;

    @FXML
    void btnStartGame(ActionEvent event) {
        // Onderstaand heb ik moeten opzoeken, Stage currentStage zoekt & vindt de actieve stage
        // Dit wordt dan gebruikt als parameter in de method openNewScreen
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        OpenNewScene newScene = new OpenNewScene();

        // Werken met een try-catch (bv. als de fmxl file verkeerd is of niet bestaat)
        try {
            newScene.openNewScene("Intro", currentStage,"Welcome to the world of Pokémon!");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}