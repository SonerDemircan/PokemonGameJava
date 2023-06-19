package PokemonGame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

public class PlayerCharacter extends Character {
    private GridPane gridPane;
    private ImageView characterImageView;

    public PlayerCharacter(GridPane gridPane, String playerName, char playerGender) {
        super(playerName, playerGender);
        this.gridPane = gridPane;
    }

    // Keyboard controls om de speler te bewegen
    public void handleKeyPressed(KeyEvent event) {
        KeyCode keyCode = event.getCode();
        int moveRow = 0;
        int moveColumn = 0;

        switch (keyCode) {
            case UP:
                moveRow = -1;
                break;
            case DOWN:
                moveRow = 1;
                break;
            case LEFT:
                moveColumn = -1;
                break;
            case RIGHT:
                moveColumn = 1;
                break;
        }
        moveCharacter(moveRow, moveColumn);
    }

    // Method om de speler te bewegen
    private void moveCharacter(int rowMove, int columnMove) {
        int newRow = getCharRow() + rowMove;
        int newColumn = getCharColumn() + columnMove;

        if (inBounds(newRow, newColumn)) {

            // Speler wordt verwijderd op de huidige positie
            gridPane.getChildren().remove(characterImageView);

            setCharRow(newRow);
            setCharColumn(newColumn);

            // Op basis van de richting waar de speler uitgaat verandert de sprite
            String imagePath;
            if (columnMove > 0) {
                imagePath = "ImagesAndSprites/SpriteRight.png";
            } else if (columnMove < 0) {
                imagePath = "ImagesAndSprites/SpriteLeft.png";
            } else if (rowMove < 0) {
                imagePath = "ImagesAndSprites/SpriteBack.png";
            } else {
                imagePath = "ImagesAndSprites/SpriteFront.png";
            }

            // Sprite en positie van de speler wordt geüpdatet
            characterImageView.setImage(new Image(imagePath, 100, 100, false, false));
            GridPane.setColumnIndex(characterImageView, newColumn);
            GridPane.setRowIndex(characterImageView, newRow);

            // Speler wordt terug toegevoegd op de nieuwe positie
            gridPane.getChildren().add(characterImageView);
        }
    }

    private boolean inBounds(int row, int column) {
        return row >= 0 && row < gridPane.getRowConstraints().size() && column >= 0 && column < gridPane.getColumnConstraints().size();
    }

    public void setCharacterImageView(ImageView characterImageView) {
        this.characterImageView = characterImageView;
    }
}
