package PokemonGame;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class PlayerCharacter extends Character {
    private ImageView characterImageView;

    // Checken of de speler nog beweegt
    private boolean isMoving;

    // Checken welke richting de speler uitgaat voor de animatie
    private int moveRow;

    // Checken welke richting de speler uitgaat voor de animatie
    private int moveColumn;

    // Vertraging wanneer de speler beweegt
    private final Duration MOVE_DELAY = Duration.millis(175);

    // Zorgt ervoor dat de gif sprites worden afgespeeld
    private AnimationTimer animationTimer;


    private long lastMoveTime;


    public PlayerCharacter(GridPane gridPane, String playerName, char playerGender) {
        super(gridPane, playerName, playerGender);
        this.isMoving = false;
        this.moveRow = 0;
        this.moveColumn = 0;
    }

    public void addPokemonToPlayerParty(Pokemon pokemon) {
        Pokemon pok = pokemon;
        int i = 0;
        while (pokemons[i] != null) {
            i++;
        }
        pokemons[i] = pok;
        pokemons[i].isCaught = true;
    }

    // Keyboard controls voor movement
    public void handleKeyPressed(KeyEvent event) {
        KeyCode keyCode = event.getCode();

        if (!isMoving) {
            switch (keyCode) {
                case UP:
                    startMoving(-1, 0, "Back");
                    break;
                case DOWN:
                    startMoving(1, 0, "Front");
                    break;
                case LEFT:
                    startMoving(0, -1, "Left");
                    break;
                case RIGHT:
                    startMoving(0, 1, "Right");
                    break;
            }
        }
    }

    // Keyboard controls om movement te stoppen
    public void handleKeyReleased(KeyEvent event) {
        KeyCode keyCode = event.getCode();

        if (isMoving) {
            switch (keyCode) {
                case UP:
                    // Checken welke richting de speler uitgaat
                    stopMoving();
                    break;
                case DOWN:
                    stopMoving();
                    break;
                case LEFT:
                    stopMoving();
                    break;
                case RIGHT:
                    stopMoving();
                    break;
            }
        }
    }

    // Method start wanneer de speler beweegt, zorgt ervoor dat de animatie start
    private void startMoving(int rowMove, int columnMove, String spriteDirection) {

        // Check om te zien of de speler al aan het bewegen is, als dit false is gaat die steeds een nieuwe animatie maken
        if (isMoving) {
            return;
        }
        moveRow = rowMove;
        moveColumn = columnMove;


        String imagePath = getMovementImage(spriteDirection);
        characterImageView.setImage(new Image(imagePath, 100, 100, false, false));
        isMoving = true;

        // Check om te zien hoeveel tijd er zit tussen de movement
        lastMoveTime = System.currentTimeMillis();

        animationTimer = new AnimationTimer() {

            @Override
            public void handle(long now) {
                long elapsedMillis = System.currentTimeMillis() - lastMoveTime;
                if (elapsedMillis >= MOVE_DELAY.toMillis()) {

                    // Speler kan bewegen als er genoeg tijd is gepasseerd
                    moveCharacter(rowMove, columnMove);
                    lastMoveTime = System.currentTimeMillis();
                }
            }
        };
        animationTimer.start();
    }

    @Override
    public void stopMoving() {
        // Checken welke richting de speler als laatste uitging & correcte sprite laten zien
        if (!isMoving) {
            return;
        }
        animationTimer.stop();
        animationTimer = null;

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
        String standingStillImage = getMovementImage(spriteDirection);
        characterImageView.setImage(new Image(standingStillImage, 100, 100, false, false));
        isMoving = false;
    }

    // Speler laten bewegen
    @Override
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
    private String getMovementImage(String spriteDirection) {
        return "ImagesAndSprites/PlayerCharacterMale/Sprite" + spriteDirection + ".gif";
    }

    // Bounds van de gamewereld checken
    private boolean inBounds(int row, int column) {
        return row >= 0 && row < gridPane.getRowConstraints().size() && column >= 0 && column < gridPane.getColumnConstraints().size();
    }

    public void setCharacterImageView(ImageView characterImageView) {
        this.characterImageView = characterImageView;
    }
}
