package PokemonGame;

import PokemonController.OpenNewScene;
import WriterReader.CSVParameters;
import WriterReader.CSVReader;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.*;
import java.net.URL;

public class World implements Initializable {

    // Gridpane wordt geïnjecteerd in de wereld
    @FXML
    private GridPane gridPane;

    private NPC npc;
    private int npcRow;
    private int npcColumn;
    private ImageView npcCharacterImageView;
    private PlayerCharacter player;
    private int characterRow;
    private int characterColumn;
    private ImageView characterImageView;
    private OpenNewScene openNewScene;
    private Stage stage;
    private List<String> trainerPokemon = new ArrayList<>();
    protected List<Pokemon> pokemon;
    //private List<Pokemon> trainerPokemons;
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
                    // Path
                    imageView = new ImageView(new Image("ImagesAndSprites/Path.png"));
                } else if (isSandPath(row, column)) {
                    // Zand
                    imageView = new ImageView(new Image("ImagesAndSprites/SandPath.png"));
                } else {
                    // Al de overige tiles zijn grass
                    imageView = new ImageView(new Image("ImagesAndSprites/Grass.png"));
                }


                imageView.setFitWidth(119);
                imageView.setFitHeight(107);
                gridPane.add(imageView, column, row);
            }
        }

        // NPC wordt aangemaakt
        npc = new NPC(gridPane, "Rocco",'M');

        // Startpositie van de NPC
        npcRow = 0;
        npcColumn = 10;
        npc.setCharRow(npcRow);
        npc.setCharColumn(npcColumn);

        // NPC afbeelding wordt ingelezen
        npcCharacterImageView = createImageView("ImagesAndSprites/NPC1/SpriteFront.gif", true);
        npcCharacterImageView.setVisible(false);
        gridPane.add(npcCharacterImageView, npcColumn, npcRow);

        // Thread aanmaken & starten
        Thread npcThread = new Thread(npc);
        npcThread.start();



        // Speler wordt aangemaakt
        player = new PlayerCharacter(gridPane,"Soner", 'M');

        // Startpositie van de speler
        characterRow = 7;
        characterColumn = 5;
        player.setCharRow(characterRow);
        player.setCharColumn(characterColumn);

        // Speler afbeelding wordt ingelezen
        characterImageView = createImageView("ImagesAndSprites/PlayerCharacterMale/SpriteFront.gif", true);
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


        //CSV niet gevangen Pokemon lezen (kan nog in aparte methode gestoken worden
        CSVParameters wildPokemonParameters = new CSVParameters("src/CSV/Pokemon.csv",12,",", true);
        CSVReader pokemonReader = new CSVReader(wildPokemonParameters);

        String[][] pokemonList = pokemonReader.CSVTo2DArray(wildPokemonParameters);

        createPokemon(pokemonList, pokemon);

        CSVParameters trainerPokemonParameters = new CSVParameters("src/CSV/SaveGamePokemon.csv",12,",", false);
        CSVReader trainerPokemonReader = new CSVReader(trainerPokemonParameters);

        String[][] trainerPokemonList = trainerPokemonReader.CSVTo2DArray(trainerPokemonParameters);

        createPokemon(trainerPokemonList,player.trainerPokemons);

        addMoveToPokemon();

    }

    // Random encounter calculator
    private boolean pokemonSpawn() {
        double spawnChance = 0.75;
        double random = Math.random();
        return random < spawnChance;
    }

    // Checken op een random encounter
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

    private boolean isTallgrass(int row, int column) {
        return (row >= 6 && row <= 8 && column >= 1 && column <= 4);
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
        return (row >= 0 && row <= 4 && column >= 10 && column <= 14);
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

    private boolean canMoveToTile(int row, int column) {
        TileType tileType = getTileType(row, column);

        // Checken op water tile
        if (tileType == TileType.WATER) {
            return false;
        }

        // Checken op overige tiles
        if (tileType == TileType.GRASS || tileType == TileType.TALLGRASS || tileType == TileType.PATH || tileType == TileType.SANDPATH) {
            return true;
        }
        return true;
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

    public void createPokemon(String[][] pokemonList, List<Pokemon> pok) {
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
            pok.add(newPokemon);
        }
    }

    public void addMoveToPokemon() {
        for (Pokemon pokemon1 : pokemon) {
            pokemon1.moveSet[0] = attackMoves.get(pokemon1.moveOne-1);
            pokemon1.moveSet[1] = attackMoves.get(pokemon1.moveTwo-1);
            pokemon1.moveSet[2] = attackMoves.get(pokemon1.moveThree-1);
            pokemon1.moveSet[3] = attackMoves.get(pokemon1.moveFour-1);
        }
        for (Pokemon pokemon1 : player.trainerPokemons) {
            pokemon1.moveSet[0] = attackMoves.get(pokemon1.moveOne-1);
            pokemon1.moveSet[1] = attackMoves.get(pokemon1.moveTwo-1);
            pokemon1.moveSet[2] = attackMoves.get(pokemon1.moveThree-1);
            pokemon1.moveSet[3] = attackMoves.get(pokemon1.moveFour-1);
        }
    }

    public List<String> presentPokemon(List<Pokemon> pok) {
        String output = "";
        for(Pokemon pokemon1: pok) {
            output = pokemon1.pokemonId + "," + pokemon1.name + "," + pokemon1.type + "," + pokemon1.level + "," + pokemon1.maxHitPoints + "," + pokemon1.attack + "," + pokemon1.defense + "," + pokemon1.speed + "," + pokemon1.moveOne + "," + pokemon1.moveTwo + "," + pokemon1.moveThree + "," + pokemon1.moveFour + "\n";
            trainerPokemon.add(output);
        }
        return trainerPokemon;
    }

    public List<Pokemon> getPokemon() {
        return pokemon;
    }
    public PlayerCharacter getPlayer() {
        return player;
    }

    // Enum voor de verschillende tiles in de wereld
    enum TileType {
        GRASS,
        TALLGRASS,
        PATH,
        SANDPATH,
        WATER
    }

    // Getter om de juiste tile te vinden
    private TileType getTileType(int row, int column) {
        if (isPath(row, column)) {
            return TileType.PATH;
        } else if (isSandPath(row, column)) {
            return TileType.SANDPATH;
        } else if (isWater(row, column)) {
            return TileType.WATER;
        } else if (isTallgrass(row, column)) {
            return TileType.TALLGRASS;
        } else {
            // Overige tiles zijn sowieso grass tiles
            return TileType.GRASS;
        }
    }
}
