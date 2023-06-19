package PokemonGame;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class World implements Initializable {

    // Gridpane wordt geïnjecteerd in de wereld
    @FXML
    private GridPane gridPane;

    private PlayerCharacter playerCharacter;
    private int characterRow;
    private int characterColumn;
    private ImageView characterImageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Bounds van de gridpane
        int numRows = gridPane.getRowConstraints().size();
        int numColumns = gridPane.getColumnConstraints().size();

        // Gras, water etc. wordt aangemaakt in de gridpane
        for (int row = 0; row < numRows; row++) {
            for (int column = 0; column < numColumns; column++) {
                ImageView imageView;

                if (isTallgrass(row, column)) {
                    imageView = new ImageView(new Image("ImagesAndSprites/Tallgrass.png"));
                } else if (isPath(row, column)) {
                    imageView = new ImageView(new Image("ImagesAndSprites/Path.png"));
                } else if (isWater(row, column)) {
                    imageView = new ImageView(new Image("ImagesAndSprites/Water.png"));
                } else {
                    imageView = new ImageView(new Image("ImagesAndSprites/Grass.png"));
                }

                imageView.setFitWidth(110);
                imageView.setFitHeight(110);
                gridPane.add(imageView, column, row);
            }
        }

        // Speler wordt aangemaakt
        playerCharacter = new PlayerCharacter(gridPane,"Soner", 'M');

        // Startpositie van de speler
        characterRow = 9;
        characterColumn = 7;

        // Positie van de speler wordt geüpdatet op de gridpane
        playerCharacter.setCharRow(characterRow);
        playerCharacter.setCharColumn(characterColumn);

        characterImageView = createImageView("ImagesAndSprites/SpriteFront.png", true);
        characterImageView.setFitWidth(100);
        characterImageView.setFitHeight(100);
        gridPane.add(characterImageView, characterColumn, characterRow);
        playerCharacter.setCharacterImageView(characterImageView);

        gridPane.setOnKeyPressed(this::handleKeyPressed);
        gridPane.requestFocus();
    }

    private boolean isTallgrass(int row, int column) {
        return (row == 6 && column >= 1 && column <= 4) || (row >= 7 && row <= 8 && column >= 1 && column <= 4);
    }

    private boolean isPath(int row, int column) {
        return (row == 7 && column >= 5 && column <= 7) || (row == 3 && column >= 0) || (column == 7 && row >= 4);
    }

    private boolean isWater(int row, int column) {
        return (row == 0 && column >= 0 && column <= 4) || (row >= 1 && row <= 2 && column >= 0 && column <= 4);
    }

    private ImageView createImageView(String imagePath, boolean preserveRatio) {
        ImageView imageView = new ImageView(new Image(imagePath));
        imageView.setPreserveRatio(preserveRatio);
        return imageView;
    }

    private void handleKeyPressed(KeyEvent event) {
        KeyCode keyCode = event.getCode();

        switch (keyCode) {
            case UP:
            case DOWN:
            case LEFT:
            case RIGHT:
                playerCharacter.handleKeyPressed(event);
                break;
        }
    }
}
