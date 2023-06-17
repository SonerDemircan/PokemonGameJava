package PokemonController;

import PokemonGame.Pokemon;
import PokemonGame.World;
import WriterReader.CSVParameters;
import WriterReader.CSVReader;
import WriterReader.CSVWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class HomeScreenController {

    //private Scene HomeScreenScene;

    //public void setHomeScreenScene(Scene scene) {
    //    this.HomeScreenScene = scene;
    //}

    private World world;

    @FXML
    private Button btnStartGame;

    @FXML
    private Button btnContinuGame1;

    @FXML
    private Label lblStartText;

    @FXML
    private ImageView imgPokeballs;

    private Pokemon pokemon;
    CSVWriter writer = new CSVWriter();

    @FXML
    void btnStartGame(ActionEvent event) {
        // Onderstaand heb ik moeten opzoeken, Stage currentStage zoekt & vindt de actieve stage
        // Dit wordt dan gebruikt als parameter in de method openNewScreen
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        OpenNewScene newScene = new OpenNewScene();

        // Werken met een try-catch (bv. als de fmxl file verkeerd is of niet bestaat)
        try {
            newScene.openNewScene("Intro", currentStage,"Welcome to the world of Pokémon!");
        } catch (IOException ex) {
            ex.printStackTrace();
        }



    }

    @FXML
    void btnContinuGame(ActionEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        OpenNewScene newScene = new OpenNewScene();

        world = new World();

        //CSV Moves lezen en van elke attackmove een object maken die in een lijst komt
        CSVParameters moveParameters = new CSVParameters("src/CSV/Moves.csv",5,",",true);
        CSVReader moveReader = new CSVReader(moveParameters);
        String[][] moveList = moveReader.CSVTo2DArray(moveParameters);
        world.createMoves(moveList);


        //CSV SaveGame lezen
        CSVParameters pokemonParameters = new CSVParameters("src/CSV/SaveGamePokemon.csv",12,",", false);
        CSVReader pokemonReader = new CSVReader(pokemonParameters);

        String[][] pokemonList = pokemonReader.CSVTo2DArray(pokemonParameters);



        world.createPokemon(pokemonList);
        world.addMoveToPokemon();

        //test als pokemon object word aangemaakt
        for (int i  = 0; i < pokemonList.length; i++) {
            for (int j = 0; j < pokemonList[i].length; j++) {
                System.out.print(pokemonList[i][j]);
            }
            System.out.println();
        }

        //test writer 100-102
        /*
        List <String> savedPokemon = world.presentPokemon();
        writer.writeFile(savedPokemon, "SaveGamePokemon");
        System.out.println(world.getPokemon().get(0).pokemonId + world.getPokemon().get(0).name);
         */

        try {
            newScene.openNewSceneWithParam("GameWorld", currentStage,"Intro",world);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}