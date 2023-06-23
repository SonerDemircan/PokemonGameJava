package PokemonGame;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class NPC implements Runnable {
    private GridPane gridPane;
    private String name;
    private char gender;
    private int npcRow;
    private int npcColumn;
    private ImageView npcCharacterImageView;

    // Imageview van de npc wordt in de constructor aangemaakt
    public NPC(GridPane gridPane, String playerName, char playerGender) {
        this.gridPane = gridPane;
        this.name = playerName;
        this.gender = playerGender;
        this.npcCharacterImageView = new ImageView();
        this.npcCharacterImageView.setImage(new Image("ImagesAndSprites/NPC1/SpriteFront.gif"));
        npcCharacterImageView.setFitHeight(100);
        npcCharacterImageView.setFitWidth(100);
    }

    // Correcte sprite wordt getoond
    private void moveNPC(int row, int column, int moveDuration, int moveDistance) {
        Platform.runLater(() -> {

            // Npc verwijderen van huidige positie
            gridPane.getChildren().remove(npcCharacterImageView);

            // Positie updaten om de juiste sprite in volgende stap te tonen
            GridPane.setRowIndex(npcCharacterImageView, row);
            GridPane.setColumnIndex(npcCharacterImageView, column);

            // Setter om de juiste sprite te tonen o.b.v. de richting die de speler uitkijkt
            if (column > npcColumn) {
                npcCharacterImageView.setImage(new Image("ImagesAndSprites/NPC1/SpriteRight.gif"));
            } else if (column < npcColumn) {
                npcCharacterImageView.setImage(new Image("ImagesAndSprites/NPC1/SpriteLeft.gif"));
            } else if (row > npcRow) {
                npcCharacterImageView.setImage(new Image("ImagesAndSprites/NPC1/SpriteFront.gif"));
            } else if (row < npcRow) {
                npcCharacterImageView.setImage(new Image("ImagesAndSprites/NPC1/SpriteBack.gif"));
            }

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
    @Override
    public void run() {

        // Loopen zodat de npc blijft bewegen
        boolean keepMoving = true;

        while (keepMoving) {

            // Duur uitgedrukt in ms
            int moveDuration = 500;

            // Aantal pixels dat de npc beweegt
            int moveDistance = 100;

            int currentRow = npcRow;
            int currentColumn = npcColumn;

            // Naar rechts bewegen
            for (int i = 0; i < 4; i++) {
                moveNPC(currentRow, ++currentColumn, moveDuration, moveDistance);
            }

            // Naar onder bewegen
            for (int i = 0; i < 4; i++) {
                moveNPC(++currentRow, currentColumn, moveDuration, moveDistance);
            }

            // Naar links bewegen
            for (int i = 0; i < 4; i++) {
                moveNPC(currentRow, --currentColumn, moveDuration, moveDistance);
            }

            // Naar boven bewegen
            for (int i = 0; i < 4; i++) {
                moveNPC(--currentRow, currentColumn, moveDuration, moveDistance);
            }
        }
    }

    // Setters
    public void setCharRow(int npcRow) {
        this.npcRow = npcRow;
    }

    public void setCharColumn(int npcColumn) {
        this.npcColumn = npcColumn;
    }
}
