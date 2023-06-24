package PokemonGame;

import Interfaces.ICharacter;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.List;
import java.util.Random;

public class Npc implements Runnable, ICharacter {
    private GridPane gridPane;
    private String name;
    private int npcRow;
    private int npcColumn;
    private ImageView npcCharacterImageView;
    private int npcId;

    // Imageview van de npc wordt in de constructor aangemaakt
    public Npc(GridPane gridPane, String playerName, int npcId) {
        this.gridPane = gridPane;
        this.name = playerName;
        this.npcId = npcId;
        this.npcCharacterImageView = new ImageView();
        setNpcSpriteImage(npcId, "Front"); // Set initial sprite image
        npcCharacterImageView.setFitHeight(100);
        npcCharacterImageView.setFitWidth(100);
    }

    // Correcte sprite wordt getoond
    // Parameter npcId zorgt voor de correcte sprites
    private void moveNPC(int row, int column, int moveDuration, int moveDistance, int npcId) {
        Platform.runLater(() -> {
            // Npc verwijderen van huidige positie
            gridPane.getChildren().remove(npcCharacterImageView);

            // Positie updaten om de juiste sprite in volgende stap te tonen
            GridPane.setRowIndex(npcCharacterImageView, row);
            GridPane.setColumnIndex(npcCharacterImageView, column);

            // Bepaal de richting van de NPC
            String spriteDirection;
            if (column > npcColumn) {
                spriteDirection = "Right";
            } else if (column < npcColumn) {
                spriteDirection = "Left";
            } else if (row > npcRow) {
                spriteDirection = "Front";
            } else if (row < npcRow) {
                spriteDirection = "Back";
            } else {
                spriteDirection = ""; // Geen verandering in richting
            }

            // Setter om de juiste sprite te tonen
            setNpcSpriteImage(npcId, spriteDirection);

            // Positie updaten
            GridPane.setRowIndex(npcCharacterImageView, row);
            GridPane.setColumnIndex(npcCharacterImageView, column);

            // Speler toevoegen op nieuwe positie
            gridPane.getChildren().add(npcCharacterImageView);
        });

        try {
            Thread.sleep(moveDuration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Positie updaten van de npc
        npcRow = row;
        npcColumn = column;
    }


    public List<Pokemon> getPokemons() {
        // Implement the method to return the list of Npc's Pokemon
        return null;
    }

    public int getCharRow() {
        return npcRow;
    }

    public void setCharRow(int row) {
        npcRow = row;
    }

    public int getCharColumn() {
        return npcColumn;
    }

    public void setCharColumn(int column) {
        npcColumn = column;
    }

    @Override
    public void stopMoving() {
        // Implement the logic to stop the NPC's movement
    }

    @Override
    public void moveCharacter(int rowMove, int columnMove) {
        // Implement the logic to move the NPC's character
        int newRow = npcRow + rowMove;
        int newColumn = npcColumn + columnMove;
        moveNPC(newRow, newColumn, 500, 100, npcId);
    }

    // Getter om de juiste image te krijgen
    private String getMovementImage(String spriteDirection) {
        String basePath = "ImagesAndSprites/NPC/Sprite";
        String imageExtension = ".gif";

        if (spriteDirection.equals("Right")) {
            return basePath + "Right" + imageExtension;
        } else if (spriteDirection.equals("Left")) {
            return basePath + "Left" + imageExtension;
        } else if (spriteDirection.equals("Front")) {
            return basePath + "Front" + imageExtension;
        } else if (spriteDirection.equals("Back")) {
            return basePath + "Back" + imageExtension;
        } else {
            // Geen verandering in richting, behoud huidige sprite
            return npcCharacterImageView.getImage().getUrl();
        }
    }

    // Setter voor de juiste npc sprite
    private void setNpcSpriteImage(int npcId, String spriteDirection) {
        String basePath = "ImagesAndSprites/NPC/Sprite";
        String imageExtension = ".gif";
        String imagePath = basePath + spriteDirection + npcId + imageExtension;
        npcCharacterImageView.setImage(new Image(imagePath));
    }

    // Wordt uitgevoerd in de thread
    public void run() {

        // Random tussen 1-4 zodat de npc's bewegen in een vierkant
        Random random = new Random();
        int distance = random.nextInt(4) + 1;
        int moveDuration = 500;
        int moveDistance = 100;

        // Loop zodat de npc blijft bewegen
        boolean keepMoving = true;
        while (keepMoving) {

            int currentRow = npcRow;
            int currentColumn = npcColumn;

            // Rechts
            for (int i = 0; i < distance; i++) {
                moveNPC(currentRow, ++currentColumn, moveDuration, moveDistance, npcId);
            }

            // Onder
            for (int i = 0; i < distance; i++) {
                moveNPC(++currentRow, currentColumn, moveDuration, moveDistance, npcId);
            }

            // Links
            for (int i = 0; i < distance; i++) {
                moveNPC(currentRow, --currentColumn, moveDuration, moveDistance, npcId);
            }

            // Boven
            for (int i = 0; i < distance; i++) {
                moveNPC(--currentRow, currentColumn, moveDuration, moveDistance, npcId);
            }
        }
    }

    public String getName() {
        return name;
    }
}
