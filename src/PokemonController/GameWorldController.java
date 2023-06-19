/*package PokemonController;

import PokemonGame.World;
import WriterReader.CSVParameters;
import WriterReader.CSVReader;
import WriterReader.CSVWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.Reader;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class GameWorldController implements Initializable {

    @FXML
    private GridPane gridPane;

    @FXML
    private World _world;

    @FXML
    private ImageView myCharacterUp;
    @FXML
    private ImageView myCharacterLeft;

    @FXML
    private ImageView myCharacterRight;
    @FXML
    private ImageView myCharacterDown;

    private Character player;

    CSVWriter writer = new CSVWriter();
    CSVReader reader;
    CSVParameters parameters;



    public GameWorldController(World currentWorld)
    {
     this._world=currentWorld;
    }

    @FXML
    void moveUp(ActionEvent event){
        _world.moveUp();
    }

    @FXML
    void moveLeft(ActionEvent event){
        _world.moveLeft();


        //csv file schrijven voor savegame (pokemon)
        List <String> savedPokemon = this._world.presentPokemon();
        if(savedPokemon.size()>0)
        {
            writer.writeFile(savedPokemon, "SaveGamePokemon");

            System.out.println(_world.getPokemon().get(0).pokemonId + _world.getPokemon().get(0).name);
        }


    }

    @FXML
    void moveRight(ActionEvent event){
        _world.moveRight();
        _world.getPlayer().addPokemonToPlayerParty(1,this._world);
        _world.getPlayer().addPokemonToPlayerParty(2,this._world);
        _world.getPlayer().addPokemonToPlayerParty(3,this._world);
        _world.getPlayer().addPokemonToPlayerParty(4,this._world);
        _world.getPlayer().addPokemonToPlayerParty(5,this._world);
        _world.getPlayer().changePositionInParty(1,3,this._world);
    }

    @FXML
    void moveDown(ActionEvent event){
        _world.moveDown();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("In de initialize");

    }
}*/