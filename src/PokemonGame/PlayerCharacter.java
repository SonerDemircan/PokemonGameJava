package PokemonGame;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

public class PlayerCharacter extends Character {
    private GridPane gridPane;
    private ImageView characterImageView;

    // Checken of de speler nog beweegt
    private boolean isMoving;

    // Checken welke richting de speler uitgaat voor de animatie
    private int moveRow;

    // Checken welke richting de speler uitgaat voor de animatie
    private int moveColumn;

    // Checken welke richting de speler uitgaat
    private boolean isMovingUp;
    private boolean isMovingDown;
    private boolean isMovingLeft;
    private boolean isMovingRight;


    public PlayerCharacter(GridPane gridPane, String playerName, char playerGender) {
        super(playerName, playerGender);
        this.gridPane = gridPane;
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
    }

    public void changePositionInParty(int firstPokemonPosition, int secondPokemonPosition, World world) {
        Pokemon pokemon = pokemons[firstPokemonPosition];
        pokemons[firstPokemonPosition] = pokemons[secondPokemonPosition];
        pokemons[secondPokemonPosition] = pokemon;
    }

    // Keyboard controls voor movement
    public void handleKeyPressed(KeyEvent event) {
        KeyCode keyCode = event.getCode();

        if (!isMoving) {
            switch (keyCode) {
                case UP:
                    startMoving(-1, 0, "BackWalk");
                    break;
                case DOWN:
                    startMoving(1, 0, "FrontWalk");
                    break;
                case LEFT:
                    startMoving(0, -1, "LeftWalk");
                    break;
                case RIGHT:
                    startMoving(0, 1, "RightWalk");
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
                    isMovingUp = false;
                    stopMoving();
                    break;
                case DOWN:
                    isMovingDown = false;
                    stopMoving();
                    break;
                case LEFT:
                    isMovingLeft = false;
                    stopMoving();
                    break;
                case RIGHT:
                    isMovingRight = false;
                    stopMoving();
                    break;
            }
        }
    }

    private void startMoving(int rowMove, int columnMove, String spriteDirection) {
        moveRow = rowMove;
        moveColumn = columnMove;

        String imagePath = getMovementImage(spriteDirection, 1);
        characterImageView.setImage(new Image(imagePath, 100, 100, false, false));
        isMoving = true;

        // Animatie timer starten
        AnimationTimer animationTimer = new AnimationTimer() {
            private int spriteNumber = 1;
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 200_000_000) {
                    String imagePath = getMovementImage(spriteDirection, spriteNumber);
                    characterImageView.setImage(new Image(imagePath, 100, 100, false, false));
                    spriteNumber++;
                    if (spriteNumber > 2) {
                        spriteNumber = 1;
                    }
                    lastUpdate = now;
                }
            }
        };
        animationTimer.start();

        moveCharacter(rowMove, columnMove);

        // Stopt de animatie wanneer de speler de key loslaat (dus locatie heeft bereikt)
        AnimationTimer stopMovingAnimationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                int newRow = getCharRow() + rowMove;
                int newColumn = getCharColumn() + columnMove;

                if (!isMoving || (rowMove > 0 && getCharRow() >= newRow)
                        || (rowMove < 0 && getCharRow() <= newRow)
                        || (columnMove > 0 && getCharColumn() >= newColumn)
                        || (columnMove < 0 && getCharColumn() <= newColumn)) {
                    stopMoving();
                    animationTimer.stop();
                    this.stop();
                }
            }
        };
        stopMovingAnimationTimer.start();
    }


    public void stopMoving() {
        // Checken welke richting de speler als laatste uitging & correcte sprite laten zien
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
        String standingStillImage = getMovementImage(spriteDirection, 0);
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
    private String getMovementImage(String spriteDirection, int spriteNumber) {
        return "ImagesAndSprites/PlayerCharacterMale/Sprite" + spriteDirection + spriteNumber + ".png";
    }

    // Bounds van de gamewereld checken
    private boolean inBounds(int row, int column) {
        return row >= 0 && row < gridPane.getRowConstraints().size() && column >= 0 && column < gridPane.getColumnConstraints().size();
    }

    public void setCharacterImageView(ImageView characterImageView) {
        this.characterImageView = characterImageView;
    }
}
