package PokemonController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeScreenController {

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

        // Instantie van class OpenNewScreen aanmaken zodat we de method kunnen gebruiken
        OpenNewScreen newScreen = new OpenNewScreen();

        // Werken met een try-catch (bv. als de fmxl file verkeerd is of niet bestaat)
        try {
            newScreen.openNewScreen("Intro",currentStage);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}