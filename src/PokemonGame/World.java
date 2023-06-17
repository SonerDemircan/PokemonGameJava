package PokemonGame;

import Interfaces.ICharacter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class World implements Initializable {

    //SONER
    private ICharacter character;
    private ImageView characterImageview;

    //DANIEL
    //private int characterStep = 10;
    protected List<Pokemon> pokemon;
    private List<String> savedPokemon;
    private List<Attack> attackMoves;

    //SONER
    @FXML
    private GridPane gridPane;

    private ImageView characterImageView;
    private int characterRow;
    private int characterColumn;


    //DANIEL
    public World() {
        //Character player = new Character("Daniel", 'M');
        //this.player = player;
        this.pokemon = new ArrayList<>();
        this.savedPokemon = new ArrayList<>();
        this.attackMoves = new ArrayList<>();
    }

    //DANIEL
    public void createMoves(String[][] moves) {
        for (String[] strings : moves) {
            Attack newAttack = new Attack();

            newAttack.moveId = Integer.parseInt(strings[0]);
            newAttack.name = strings[1];
            newAttack.power = Integer.parseInt(strings[2]);
            newAttack.accuracy = Integer.parseInt(strings[3]);
            newAttack.pp = Integer.parseInt(strings[4]);

            attackMoves.add(newAttack);
        }
    }

    public void createPokemon(String[][] pokemonList) {

        for (String[] strings : pokemonList) {
            Pokemon newPokemon = new Pokemon();

            newPokemon.pokemonId = Integer.parseInt(strings[0]);
            newPokemon.name = strings[1];
            newPokemon.type = strings[2];
            newPokemon.level = Integer.parseInt(strings[3]);
            newPokemon.hitPoints = Integer.parseInt(strings[4]);
            newPokemon.attack = Integer.parseInt(strings[5]);
            newPokemon.defense = Integer.parseInt(strings[6]);
            newPokemon.speed = Integer.parseInt(strings[7]);
            newPokemon.moveOne = Integer.parseInt(strings[8]);
            newPokemon.moveTwo = Integer.parseInt(strings[9]);
            newPokemon.moveThree = Integer.parseInt(strings[10]);
            newPokemon.moveFour = Integer.parseInt(strings[11]);

            pokemon.add(newPokemon);
        }
    }

    public void addMoveToPokemon() {
        for (Pokemon pokemon1: pokemon) {
            pokemon1.moveSet[0] = attackMoves.get(pokemon1.moveOne);
            pokemon1.moveSet[1] = attackMoves.get(pokemon1.moveTwo);
            pokemon1.moveSet[2] = attackMoves.get(pokemon1.moveThree);
            pokemon1.moveSet[3] = attackMoves.get(pokemon1.moveFour);
        }
    }

    public List<String> presentPokemon() {
        String output = "";
        for(Pokemon pokemon1: pokemon) {
            output = pokemon1.pokemonId + "," + pokemon1.name + "," + pokemon1.type + "," + pokemon1.level + "," + pokemon1.hitPoints + "," + pokemon1.attack + "," + pokemon1.defense + "," + pokemon1.speed + "," + pokemon1.moveOne + "," + pokemon1.moveTwo + "," + pokemon1.moveThree + "," + pokemon1.moveFour + "\n";
            savedPokemon.add(output);
        }
        return savedPokemon;
    }

    public List<Pokemon> getPokemon() {
        return pokemon;
    }

    //SONER //////////////////////////////////////////////////////////////////////////////////////

    // Initializable zodat onderstaand meteen wordt aangemaakt
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Rijen & kolommen checken
        int numRows = gridPane.getRowConstraints().size();
        int numColumns = gridPane.getColumnConstraints().size();

        // Gridpane vullen met gras
        for (int row = 0; row < numRows; row++) {
            for (int column = 0; column < numColumns; column++) {
                ImageView imageView;

                // Wereld vullen met gras, tall grass etc.

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

        // Speler aanmaken
        // INPUT VAN INTRO MOET HIER KOMEN
        character = new Character("Soner",'M');

        // Startpositie van de speler kiezen
        characterRow = 9;
        characterColumn = 7;
        //character.setCharXpos(9);
        //character.setCharXpos(7);

        // Front sprite aanmaken & in de wereld zetten
        characterImageView = createImageView("ImagesAndSprites/SpriteFront.png", true);
        characterImageView.setFitWidth(100);
        characterImageView.setFitHeight(100);
        //gridPane.add(characterImageView, characterColumn, characterRow);
        gridPane.add(characterImageView, characterColumn, characterRow);

        // Keyboard controls toevoegen
        //Scene scene = gridPane.getScene();
        gridPane.setOnKeyPressed(this::handleKeyPressed);
        gridPane.requestFocus();
    }

    // Checken of vak in de gridpane tall grass is
    private boolean isTallgrass(int row, int column) {
        return (row == 6 && column >= 1 && column <= 4) || (row >= 7 && row <= 8 && column >= 1 && column <= 4);
    }

    // Checken of vak in de gridpane een wandelpad is
    private boolean isPath(int row, int column) {
        return (row == 7 && column >= 5 && column <= 7) || (row == 3 && column >= 0) || (column == 7 && row >= 4);
    }

    // Checken of vak in de gridpane water is
    private boolean isWater(int row, int column) {
        return (row == 0 && column >= 0 && column <= 4) || (row >= 1 && row <= 2 && column >= 0 && column <= 4);
    }

    // Nieuwe imageview aanmaken met parameters: locatie van de image, preserveRatio zorgt ervoor dat de ratio's gelijk blijven
    private ImageView createImageView(String imagePath, boolean preserveRatio) {
        ImageView imageView = new ImageView(new Image(imagePath));
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        imageView.setPreserveRatio(preserveRatio);
        imageView.setSmooth(true);
        imageView.setCache(true);
        return imageView;
    }

    // Keyboard controls om de speler te bewegen
    private void handleKeyPressed(KeyEvent event) {
        KeyCode keyCode = event.getCode();
        int moveRow = 0;
        int moveColumn = 0;

        switch (keyCode) {
            case UP:
                moveRow = -1;
                System.out.println("W: UP");
                break;
            case DOWN:
                moveRow = 1;
                System.out.println("S: DOWN");
                break;
            case LEFT:
                moveColumn = -1;
                System.out.println("A: LEFT");
                break;
            case RIGHT:
                moveColumn = 1;
                System.out.println("D: RIGHT");
                break;
        }
        moveCharacter(moveRow,moveColumn);
    }

    private void moveCharacter(int rowMove, int columnMove) {

        // Nieuwe positie van de speler bepalen
        int newRow = characterRow + rowMove;
        int newColumn = characterColumn + columnMove;

        if (inBounds(newRow, newColumn)) {
            // Speler verwijderen van huidige positie
            gridPane.getChildren().remove(characterImageView);

            // Speler updaten naar nieuwe positie
            characterRow = newRow;
            characterColumn = newColumn;
            //character.setCharRow(newRow);
            //character.setCharColumn(newColumn);

            // Sprite van de speler veranderen op basis van de positie waarin hij/zij beweegt
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

            // Laatste correcte sprite (bv. naar links bewogen, dan staat die op SpriteLeft)
            characterImageView = createImageView(imagePath, true);

            // Speler terug in de wereld plaatsen op de nieuwe positie
            gridPane.add(characterImageView, newColumn, newRow);
        }
    }

    // Checken of de speler niet out of bounds gaat
    private boolean inBounds(int row, int column) {
        return row >= 0 && row < gridPane.getRowConstraints().size() && column >= 0 && column < gridPane.getColumnConstraints().size();
    }
}