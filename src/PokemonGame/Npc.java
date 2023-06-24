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

    // Imageview van de npc wordt in de constructor aangemaakt
    public Npc(GridPane gridPane, String playerName) {
        this.gridPane = gridPane;
        this.name = playerName;
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
    public List<Pokemon> getPokemons() {
        // Implement the method to return the list of Npc's Pokemon
        return null;
    }

    @Override
    public int getCharRow() {
        return npcRow;
    }

    @Override
    public void setCharRow(int row) {
        npcRow = row;
    }

    @Override
    public int getCharColumn() {
        return npcColumn;
    }

    @Override
    public void setCharColumn(int column) {
        npcColumn = column;
    }

    @Override
    public void stopMoving() {
        // Implement the method to stop the Npc's movement
    }

    @Override
    public void moveCharacter(int rowMove, int columnMove) {
        // Implement the method to move the Npc's character
    }

    @Override
    public String getMovementImage(String spriteDirection) {
        // Implement the method to get the Npc's movement image based on the given sprite direction
        return null;
    }

    //@Override
    public void run() {

        // Random tussen 1-4 aanmaken zodat de npc's random bewegen in een vierkant
        Random random = new Random();
        int distance = random.nextInt(4) + 1;

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
            for (int i = 0; i < distance; i++) {
                moveNPC(currentRow, ++currentColumn, moveDuration, moveDistance);
            }

            // Naar onder bewegen
            for (int i = 0; i < distance; i++) {
                moveNPC(++currentRow, currentColumn, moveDuration, moveDistance);
            }

            // Naar links bewegen
            for (int i = 0; i < distance; i++) {
                moveNPC(currentRow, --currentColumn, moveDuration, moveDistance);
            }

            // Naar boven bewegen
            for (int i = 0; i < distance; i++) {
                moveNPC(--currentRow, currentColumn, moveDuration, moveDistance);
            }
        }
    }

    public String getName() {
        return name;
    }
}
