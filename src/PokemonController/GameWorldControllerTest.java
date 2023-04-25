package PokemonController;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class GameWorldControllerTest {
    @FXML
    private Pane centerPane;

    @FXML
    private ImageView kantoMapImageView;

    @FXML
    public void initialize() {
        kantoMapImageView.fitWidthProperty().bind(centerPane.widthProperty());
        kantoMapImageView.fitHeightProperty().bind(centerPane.heightProperty());
    }
}
