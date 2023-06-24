package PokemonController;

import PokemonGame.World;
import SwitchScenes.SwitchScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import java.io.IOException;

public class HomeScreenController {

    private World world;
    //private MediaPlayer mediaPlayer;
    SwitchScene newScene = new SwitchScene();


    @FXML
    private AnchorPane anchorPaneBackground;

    @FXML
    private Button btnStartGame;

    @FXML
    private Button btnContinuGame1;

    @FXML
    private Label fighterz;

    @FXML
    private Label hosts;

    @FXML
    private ImageView GifBackgroundPikachu;

    @FXML
    private ImageView PokeBallBackground;

    @FXML
    void btnStartGame(ActionEvent event) {
        // Stage currentStage zoekt & vindt de actieve stage
        // Dit wordt dan gebruikt als parameter in de method openNewScene
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        try {
            newScene.stopSceneThemeSong();
            newScene.openNewScene("Intro", currentStage,"Welcome to the world of Pokémon!");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        newScene.startSceneThemeSong("HomescreenTheme");
    }
}