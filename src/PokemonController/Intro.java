package PokemonController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class Intro {
    private String [] introText = {"Hi! Sorry to keep you waiting! Welcome to the world of Pokémon! My name is Oak. But everyone calls me the Pokémon Professor. "
            ,"This is what we call a \"Pokémon.\" This world is widely inhabited by creatures known as Pokémon. We humans live alongside Pokémon, at times as friendly playmates, and at times as cooperative workmates. And sometimes, we band together and battle others like us."
            ,"But despite our closeness, we don't know everything about Pokémon. In fact, there are many, many secrets surrounding Pokémon. To unravel Pokémon mysteries, I've been undertaking research. That's what I do."
            //Deze tekst heb ik zelf bedacht :D
            ,""
            ,""
            ,"ddd"
            ,"ddd"};

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
    private ImageView pngPokeballs;

}
