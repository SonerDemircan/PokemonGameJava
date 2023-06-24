package PokemonController;

import SwitchScenes.SwitchScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;

public class IntroController {
    private String [] introText = {"Hi! Sorry to keep you waiting! Welcome to the world of Pokémon! \nMy name is Oak. But everyone calls me the Pokémon Professor. "
            ,"This is what we call a \"Pokémon.\" This world is widely inhabited by creatures known as Pokémon. \nWe humans live alongside Pokémon, at times as friendly playmates, \nand at times as cooperative workmates. \nAnd sometimes, we band together and battle others like us."
            ,"But despite our closeness, we don't know everything about Pokémon. \nIn fact, there are many, many secrets surrounding Pokémon. To unravel Pokémon mysteries, \nI've been undertaking research. That's what I do."
            ,"Your very own Pokemon adventure is about to unfold!"
            ,"A world of dreams and adventures with pokemon awaits!"
            ,"Let's go!"};

    @FXML
    private Label lblIntroText;

    @FXML
    private ImageView gifPangoro;

    @FXML
    private Button btnPrevious;

    @FXML
    private Button btnNext;

    @FXML
    private Button btnContinue;

    private SwitchScene newScene = new SwitchScene();

    // Switchen tussen tekst
    // Index moet op 0 staan, anders komen we nooit in de String array
    private int currentIntroIndex = 0;

    // Als we in de intro scene komen moet er in de label al een start tekst staan
    @FXML
    public void initialize() {
        lblIntroText.setText(introText[currentIntroIndex]);
        newScene.startSceneThemeSong("IntroTheme");
    }

    private void updateIntroText() {
        lblIntroText.setText(introText[currentIntroIndex]);
        // Als Oak over Pokémon begint, zien we een pokémon
        if (currentIntroIndex > 0) {
            gifPangoro.setVisible(true);
        } else {
            gifPangoro.setVisible(false);
        }
        // Buttons previous & continue verdwijnen als de speler de volledige tekst heeft gelezen
        if (currentIntroIndex == 5) {
            btnNext.setVisible(false);
            btnPrevious.setVisible(false);
        }
    }

    // -1 om te starten met index 0
    // currentIntroIndex wordt verhoogd zodat we kunnen loopen door de String array
    @FXML
    public void btnNext() {
        if (currentIntroIndex < introText.length - 1) {
            currentIntroIndex++;
            updateIntroText();
        }
    }

    // Idem als btnNext, enkel wordt hier de currentIntroIndex verlaagd
    @FXML
    public void btnPrevious() {
        if (currentIntroIndex > 0) {
            currentIntroIndex--;
            updateIntroText();
        }
    }

    @FXML
    public void btnContinue(ActionEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            newScene.stopSceneThemeSong();
            newScene.openNewScene("World", currentStage,"Gameworld");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
