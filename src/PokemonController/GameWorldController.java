package PokemonController;

import PokemonGame.World;
import SwitchScenes.SwitchScene;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameWorldController implements Initializable {

    @FXML
    private Button btnExitGame;

    @FXML
    private GridPane gridPane;
    private World world;
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        world = new World(gridPane, new SwitchScene(), stage);
        gridPane.setOnKeyPressed(this::handleKeyPressed);
        gridPane.setOnKeyReleased(this::handleKeyReleased);
        gridPane.requestFocus();
    }

    private void handleKeyPressed(KeyEvent event) {
        KeyCode keyCode = event.getCode();
        switch (keyCode) {
            case UP:
            case DOWN:
            case LEFT:
            case RIGHT:
                world.handleKeyPressed(event);
                break;
            default:
                break;
        }
    }

    private void handleKeyReleased(KeyEvent event) {
        KeyCode keyCode = event.getCode();
        switch (keyCode) {
            case UP:
            case DOWN:
            case LEFT:
            case RIGHT:
                world.handleKeyReleased(event);
                openBattleScene(event);
                break;
        }
    }

    private void openBattleScene(Event event) {
        if(world.checkWildPokemonEncounter()) {

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            SwitchScene newScene = new SwitchScene();

            try {
                newScene.openNewSceneWithParam("BattleScene", currentStage,"Gameworld",world);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}