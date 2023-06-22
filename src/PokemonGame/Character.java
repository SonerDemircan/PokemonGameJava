package PokemonGame;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;
import java.util.List;

public abstract class Character {
    protected GridPane gridPane;
    private boolean isMoving;

    // Checken welke richting de speler uitgaat voor de animatie
    private int moveRow;

    // Checken welke richting de speler uitgaat voor de animatie
    private int moveColumn;

    // Zorgt ervoor dat de gif sprites worden afgespeeld
    private AnimationTimer animationTimer;
    protected String name;
    protected char gender;
    protected double charXpos;
    protected double charYpos;
    protected List<Pokemon> trainerPokemons;
    protected Item[] items;
    protected int experience;
    protected int catchCount;
    private ImageView characterImageView;

    public Character(GridPane gridPane, String playerName, char playerGender) {
        this.gridPane = gridPane;
        this.name = playerName;
        this.gender = playerGender;
        this.charXpos = 250;
        this.charYpos = 250;
        this.catchCount = 0;
        this.trainerPokemons = new ArrayList<>();
        this.items = new Item[10];
        this.experience = 0;
    }

    public List<Pokemon> getPokemons() {
        return trainerPokemons;
    }

    public int getCharRow() {
        return (int) (charYpos / 110);
    }

    public void setCharRow(int row) {
        this.charYpos = row * 110;
    }

    public int getCharColumn() {
        return (int) (charXpos / 110);
    }

    public void setCharColumn(int column) {
        this.charXpos = column * 110;
    }

    public void stopMoving() {
        // Checken welke richting de speler als laatste uitging & correcte sprite laten zien
        if (!isMoving) {
            return;
        }
        animationTimer.stop();
        animationTimer = null;

        String pathToImage = "";

        String spriteDirection;
        if (moveColumn > 0) {
            spriteDirection = "Right";
        } else if (moveColumn < 0) {
            spriteDirection = "Left";
        } else if (moveRow > 0) {
            spriteDirection = "Front";
        } else {
            spriteDirection = "Back";
        }

        // Correcte sprite wordt getoond
        String standingStillImage = getMovementImage(pathToImage, spriteDirection);
        characterImageView.setImage(new Image(standingStillImage, 100, 100, false, false));
        isMoving = false;
    }

    // Speler laten bewegen
    public void moveCharacter(int rowMove, int columnMove) {
        int newRow = getCharRow() + rowMove;
        int newColumn = getCharColumn() + columnMove;

        if (inBounds(newRow, newColumn)) {

            // Speler verwijderen van huidige positie
            gridPane.getChildren().remove(characterImageView);
            setCharRow(newRow);
            setCharColumn(newColumn);

            // Positie updaten
            GridPane.setColumnIndex(characterImageView, newColumn);
            GridPane.setRowIndex(characterImageView, newRow);

            // Speler toevoegen op nieuwe positie
            gridPane.getChildren().add(characterImageView);
        }
    }

    // Getter om de juiste sprite te tonen o.b.v. de richting die de speler uitkijkt
    public String getMovementImage(String imagePath, String spriteDirection) {
        return "ImagesAndSprites/" + imagePath + "/Sprite" + spriteDirection + ".gif";
    }

    // Bounds van de gamewereld checken
    private boolean inBounds(int row, int column) {
        return row >= 0 && row < gridPane.getRowConstraints().size() && column >= 0 && column < gridPane.getColumnConstraints().size();
    }

    public void setCharacterImageView(ImageView characterImageView) {
        this.characterImageView = characterImageView;
    }
}
