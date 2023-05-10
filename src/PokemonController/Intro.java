package PokemonController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;

public class Intro {
    private String [] introText = {"Hi! Sorry to keep you waiting! Welcome to the world of Pokémon! My name is Oak. But everyone calls me the Pokémon Professor. "
            ,"This is what we call a \"Pokémon.\" This world is widely inhabited by creatures known as Pokémon. We humans live alongside Pokémon, at times as friendly playmates, and at times as cooperative workmates. And sometimes, we band together and battle others like us."
            ,"But despite our closeness, we don't know everything about Pokémon. In fact, there are many, many secrets surrounding Pokémon. To unravel Pokémon mysteries, I've been undertaking research. That's what I do."
            ,"BLA BLA BLA"
            ,"NOG MEER BLA BLA"
            ,"NOG MEEEEEEEEEEER"
            ,"EINDE TEKST"};

    @FXML
    private Label lblIntroText;

    @FXML
    private ImageView gifHaxorus;

    @FXML
    private ImageView pngBag;

    @FXML
    private Button btnPrevious;

    @FXML
    private Button btnNext;

    @FXML
    private Button btnContinue;

    @FXML
    private ImageView pngPokeballs;

    @FXML
    public void btnContinue(ActionEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        OpenNewScene newScene = new OpenNewScene();

        try {
            newScene.openNewSceneKeyevent("GameWorld", currentStage,"Intro");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Switchen tussen tekst
    // Index moet op 0 staan, anders komen we nooit in de String array
    private int currentIntroIndex = 0;

    // Als we in de intro scene komen moet er in de label al een start tekst staan
    @FXML
    public void initialize() {
        lblIntroText.setText(introText[currentIntroIndex]);
    }

    private void updateIntroText() {
        //Initialize kunnen we hier gebruiken omdat deze altijd de tekst van de huidige index in de String array teruggeeft
        initialize();

        // Als Oak over Pokémon begint, zien we een pokémon: in dit geval Haxorus
        if (currentIntroIndex > 0) {
            gifHaxorus.setVisible(true);
        } else {
            gifHaxorus.setVisible(false);
        }

        // Button continue verschijnt pas als de speler de volledige tekst heeft gelezen
        if (currentIntroIndex == 6) {
            btnContinue.setVisible(true);
        } else {
            btnContinue.setVisible(false);
        }
    }

    // -1 om te starten met index 0
    // currentIntroIndex wordt verhoogd zodat we kunnen loopen door de String array
    // ActionEvent event is overbodig omdat we die nooit gebruiken, maar heb ik toch laten staan (als we bv. met de keycontrols gaan werken ofzo)
    @FXML
    public void btnNext(/*ActionEvent event*/) {
        if (currentIntroIndex < introText.length - 1) {
            currentIntroIndex++;
            updateIntroText();
        }
    }

    // Idem als btnNext, enkel wordt hier de currentIntroIndex verlaagd
    @FXML
    public void btnPrevious(/*ActionEvent event*/) {
        if (currentIntroIndex > 0) {
            currentIntroIndex--;
            updateIntroText();
        }
    }
}
