package PokemonGame;

import PokemonController.OpenNewScene;
import WriterReader.CSVParameters;
import WriterReader.CSVReader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.*;
import java.net.URL;

public class World implements Initializable {

    // Gridpane wordt geïnjecteerd in de wereld
    @FXML
    private GridPane gridPane;

    private PlayerCharacter player;
    private int characterRow;
    private int characterColumn;
    private ImageView characterImageView;
    private OpenNewScene openNewScene;
    private Stage stage;
    protected List<Pokemon> pokemon;
    private List<String> savedPokemon;
    private List<Attack> attackMoves;

    public World(GridPane gridPane, OpenNewScene openNewScene, Stage stage) {
        this.gridPane = gridPane;
        this.openNewScene = openNewScene;
        this.stage = stage;
        pokemon = new ArrayList<>();
        attackMoves = new ArrayList<>();
        initializeWorld();
    }

    public void initializeWorld() {

        // Bounds van de gridpane
        int numRows = gridPane.getRowConstraints().size();
        int numColumns = gridPane.getColumnConstraints().size();

        // Gras, water etc. wordt aangemaakt in de gridpane
        for (int row = 0; row < numRows; row++) {
            for (int column = 0; column < numColumns; column++) {
                ImageView imageView;

                if (isTallgrass(row, column)) {
                    // Tall grass
                    imageView = new ImageView(new Image("ImagesAndSprites/Tallgrass.png"));
                } else if (isWater(row, column)) {
                    // Water
                    imageView = new ImageView(new Image("ImagesAndSprites/Water.png"));
                } else if (isPath(row, column)) {
                    // Path area
                    imageView = new ImageView(new Image("ImagesAndSprites/Path.png"));
                } else if (isSandPath(row, column)) {
                    imageView = new ImageView(new Image("ImagesAndSprites/SandPath.png"));
                } else {
                    // Default grass area
                    imageView = new ImageView(new Image("ImagesAndSprites/Grass.png"));
                }


                imageView.setFitWidth(107);
                imageView.setFitHeight(107);
                gridPane.add(imageView, column, row);
            }
        }

        // Speler wordt aangemaakt
        player = new PlayerCharacter(gridPane,"Soner", 'M');

        // Startpositie van de speler
        characterRow = 9;
        characterColumn = 7;

        // Positie van de speler wordt geüpdatet op de gridpane
        player.setCharRow(characterRow);
        player.setCharColumn(characterColumn);

        characterImageView = createImageView("ImagesAndSprites/PlayerCharacterMale/SpriteFront0.png", true);
        characterImageView.setFitWidth(100);
        characterImageView.setFitHeight(100);
        gridPane.add(characterImageView, characterColumn, characterRow);
        player.setCharacterImageView(characterImageView);

        gridPane.setOnKeyPressed(this::handleKeyPressed);
        gridPane.setOnKeyReleased(this::handleKeyReleased);
        gridPane.requestFocus();

        //CSV Moves lezen en van elke attackmove een object maken die in een lijst komt
        CSVParameters moveParameters = new CSVParameters("src/CSV/Moves.csv",5,",",true);
        CSVReader moveReader = new CSVReader(moveParameters);
        String[][] moveList = moveReader.CSVTo2DArray(moveParameters);
        createMoves(moveList);


        //CSV SaveGame lezen
        CSVParameters pokemonParameters = new CSVParameters("src/CSV/SaveGamePokemon.csv",12,",", false);
        CSVReader pokemonReader = new CSVReader(pokemonParameters);

        String[][] pokemonList = pokemonReader.CSVTo2DArray(pokemonParameters);

        createPokemon(pokemonList);
        addMoveToPokemon();

        player.addPokemonToPlayerParty(pokemon.get(4));
        player.addPokemonToPlayerParty(pokemon.get(2));
        player.addPokemonToPlayerParty(pokemon.get(1));
        player.addPokemonToPlayerParty(pokemon.get(11));
    }


    private boolean isTallgrass(int row, int column) {
        return (row >= 6 && row <= 8 && column >= 1 && column <= 4);
    }

    // Random encounter
    private boolean pokemonSpawn() {
        double spawnChance = 0.75;
        double random = Math.random();
        return random < spawnChance;
    }

    public boolean checkWildPokemonEncounter() {
        int playerRow = player.getCharRow();
        int playerColumn = player.getCharColumn();
        boolean bool = false;

        if (isTallgrass(playerRow, playerColumn) && pokemonSpawn()) {
        bool = true;
        } else {
            bool = false;
        }
        return bool;
    }

    private boolean isPath(int row, int column) {
        return (row >= 0 && row <= 3 && column == 0) || (row == 3 && column >= 1 && column <= 5) ||
                (row >= 0 && row <= 2 && column == 5) || (row >= 3 && row <= 9 && column == 7) ||
                (row >= 6 && row <= 9 && column == 0) || (row == 9 && column >= 1 && column <= 7) ||
                (row == 3 && column == 6) || (row == 7 && column == 5) || (row == 8 && column == 5) ||
                (row == 4 && column == 0) || (row == 5 && column == 0) || (row == 5 && column >= 1 && column <= 5) ||
                (row == 6 && column == 5);
    }

    private boolean isSandPath(int row, int column) {
        return (row >= 0 && row <= 3 && column >= 10 && column <= 14);
    }

    private boolean isWater(int row, int column) {
        return (row >= 0 && row <= 2 && column >= 1 && column <= 4);
    }

    private ImageView createImageView(String imagePath, boolean preserveRatio) {
        ImageView imageView = new ImageView(new Image(imagePath));
        imageView.setPreserveRatio(preserveRatio);
        return imageView;
    }

    public void handleKeyPressed(KeyEvent event) {
        KeyCode keyCode = event.getCode();

        switch (keyCode) {
            case UP:
            case DOWN:
            case LEFT:
            case RIGHT:
                player.handleKeyPressed(event);
                break;
        }
    }

    public void handleKeyReleased(KeyEvent event) {
        KeyCode keyCode = event.getCode();

        switch (keyCode) {
            case UP:
            case DOWN:
            case LEFT:
            case RIGHT:
                player.handleKeyReleased(event);
                break;
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

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
            newPokemon.maxHitPoints = Integer.parseInt(strings[4]);
            newPokemon.battleHitPoints = Integer.parseInt(strings[4]);
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
        for (Pokemon pokemon1 : pokemon) {
            pokemon1.moveSet[0] = attackMoves.get(pokemon1.moveOne-1);
            pokemon1.moveSet[1] = attackMoves.get(pokemon1.moveTwo-1);
            pokemon1.moveSet[2] = attackMoves.get(pokemon1.moveThree-1);
            pokemon1.moveSet[3] = attackMoves.get(pokemon1.moveFour-1);
        }
    }

    public List<String> presentPokemon() {
        String output = "";
        for(Pokemon pokemon1: pokemon) {
            output = pokemon1.pokemonId + "," + pokemon1.name + "," + pokemon1.type + "," + pokemon1.level + "," + pokemon1.maxHitPoints + "," + pokemon1.attack + "," + pokemon1.defense + "," + pokemon1.speed + "," + pokemon1.moveOne + "," + pokemon1.moveTwo + "," + pokemon1.moveThree + "," + pokemon1.moveFour + "\n";
            savedPokemon.add(output);
        }
        return savedPokemon;
    }

    public boolean isPokemonDefeated(Pokemon pokemon) {
        boolean defeat = false;
        if(pokemon.battleHitPoints < 1) {
            defeat = true;
        }
        return defeat;
    }

    public List<Pokemon> getPokemon() {
        return pokemon;
    }
    public PlayerCharacter getPlayer() {
        return player;
    }
}
