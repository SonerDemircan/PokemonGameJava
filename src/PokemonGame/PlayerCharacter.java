package PokemonGame;

import Interfaces.ICharacter;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class PlayerCharacter implements ICharacter {
    private ImageView characterImageView;

    private boolean isMoving;
    private int moveRow;
    private int moveColumn;
    private final Duration MOVE_DELAY = Duration.millis(250);
    private AnimationTimer animationTimer;
    private long lastMoveTime;
    private GridPane gridPane;
    private String name;
    private char gender;
    private double charXpos;
    private double charYpos;
    public List<Pokemon> trainerPokemons;
    private int experience;
    private int catchCount;

    public PlayerCharacter(GridPane gridPane, String playerName, char playerGender) {
        this.gridPane = gridPane;
        this.name = playerName;
        this.gender = playerGender;
        this.charXpos = 250;
        this.charYpos = 250;
        this.catchCount = 0;
        this.trainerPokemons = new ArrayList<>();
        this.experience = 0;
        this.isMoving = false;
        this.moveRow = 0;
        this.moveColumn = 0;
    }

    public boolean catchPokemon(Pokemon pokemon) {
        boolean catched = false;
        Random random = new Random();
        int catchRate = random.nextInt(pokemon.maxHitPoints/2);
        if(pokemon.battleHitPoints < catchRate) {
            Pokemon pok = pokemon;
            pok.isCaught = true;
            trainerPokemons.add(pok);
            catched = true;
        }
        return catched;
    }

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

    public void handleKeyReleased(KeyEvent event) {
        KeyCode keyCode = event.getCode();

        if (isMoving) {
            switch (keyCode) {
                case UP:
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

    private void startMoving(int rowMove, int columnMove, String spriteDirection) {
        if (isMoving) {
            return;
        }
        moveRow = rowMove;
        moveColumn = columnMove;
        String movementImage = getMovementImage(spriteDirection);
        characterImageView.setImage(new Image(movementImage));
        isMoving = true;
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
        //isMoving = false;
        //animationTimer.stop();
        //characterImageView.setImage(new Image(getMovementImage("Front")));

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

    @Override
    public void moveCharacter(int rowMove, int columnMove) {
        int newRow = getCharRow() + rowMove;
        int newColumn = getCharColumn() + columnMove;
        if (newRow >= 0 && newRow < gridPane.getRowCount() && newColumn >= 0 && newColumn < gridPane.getColumnCount()) {
            setCharRow(newRow);
            setCharColumn(newColumn);
            GridPane.setColumnIndex(characterImageView, newColumn);
            GridPane.setRowIndex(characterImageView, newRow);
        }
    }

    public String getMovementImage(String spriteDirection) {
        return "ImagesAndSprites/PlayerCharacterMale/Sprite" + spriteDirection + ".gif";
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

    public void setCharacterImageView(ImageView characterImageView) {
        this.characterImageView = characterImageView;
    }
}
