/*package PokemonGame;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class NPC extends Character {

    private int stepCounter;
    private boolean isLeftFoot;

    public NPC(GridPane gridPane, String name, char gender) {
        super(gridPane, name, gender);
        stepCounter = 0;
        isLeftFoot = true;
        startWalkingAnimation();
    }

    @Override
    public void moveNPC() {
        switch (stepCounter) {
            case 0:
                moveRight();
                break;
            case 1:
                moveDown();
                break;
            case 2:
                moveLeft();
                break;
            case 3:
                moveUp();
                break;
        }
        stepCounter = (stepCounter + 1) % 4; // Loop back to the beginning of the pattern
    }

    @Override
    public void moveRight() {
        int newColumn = getCharColumn() + 1;
        if (isValidMove(getCharRow(), newColumn)) {
            Platform.runLater(() -> {
                getGridPane().getChildren().remove(getCharacterImageView());
                setCharColumn(newColumn);
                updateCharacterImageView(isLeftFoot ?
                        "ImagesAndSprites/NPC1/SpriteRight1.png" :
                        "ImagesAndSprites/NPC1/SpriteRight2.png");
                getGridPane().add(getCharacterImageView(), getCharColumn(), getCharRow());
                isLeftFoot = !isLeftFoot;
            });
        }
    }

    @Override
    public void moveDown() {
        int newRow = getCharRow() + 1;
        if (isValidMove(newRow, getCharColumn())) {
            Platform.runLater(() -> {
                getGridPane().getChildren().remove(getCharacterImageView());
                setCharRow(newRow);
                updateCharacterImageView(isLeftFoot ?
                        "ImagesAndSprites/NPC1/SpriteFront1.png" :
                        "ImagesAndSprites/NPC1/SpriteFront2.png");
                getGridPane().add(getCharacterImageView(), getCharColumn(), getCharRow());
                isLeftFoot = !isLeftFoot;
            });
        }
    }

    @Override
    public void moveLeft() {
        int newColumn = getCharColumn() - 1;
        if (isValidMove(getCharRow(), newColumn)) {
            Platform.runLater(() -> {
                getGridPane().getChildren().remove(getCharacterImageView());
                setCharColumn(newColumn);
                updateCharacterImageView(isLeftFoot ?
                        "ImagesAndSprites/NPC1/SpriteLeft1.png" :
                        "ImagesAndSprites/NPC1/SpriteLeft2.png");
                getGridPane().add(getCharacterImageView(), getCharColumn(), getCharRow());
                isLeftFoot = !isLeftFoot;
            });
        }
    }

    @Override
    public void moveUp() {
        int newRow = getCharRow() - 1;
        if (isValidMove(newRow, getCharColumn())) {
            Platform.runLater(() -> {
                getGridPane().getChildren().remove(getCharacterImageView());
                setCharRow(newRow);
                updateCharacterImageView(isLeftFoot ?
                        "ImagesAndSprites/NPC1/SpriteBack1.png" :
                        "ImagesAndSprites/NPC1/SpriteBack2.png");
                getGridPane().add(getCharacterImageView(), getCharColumn(), getCharRow());
                isLeftFoot = !isLeftFoot;
            });
        }
    }

    private boolean isValidMove(int row, int column) {
        // Check if the move is within the grid boundaries
        if (row < 0 || row >= getGridPane().getRowCount() ||
                column < 0 || column >= getGridPane().getColumnCount()) {
            return false;
        }
        return true;
    }

    private void updateCharacterImageView(String imagePath) {
        Image image = new Image(imagePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(CHAR_WIDTH);
        imageView.setFitHeight(CHAR_HEIGHT);
        setCharacterImageView(imageView);
    }

    private void startWalkingAnimation() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), event -> {
            moveNPC();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @Override
    public void moveCharacter(int rowMove, int columnMove) {
    }
}*/
