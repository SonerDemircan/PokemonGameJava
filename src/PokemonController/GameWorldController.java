package PokemonController;

import PokemonGame.World;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class GameWorldController implements Initializable {

    @FXML
    private Button btnExitGame;

    @FXML
    private GridPane gridPane;
    private World world;
    private Stage stage;



    @FXML
    void btnExit(ActionEvent event) {
        world.battle(world.getPokemon().get(5), world.getPokemon().get(5) );
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        world = new World(gridPane, new OpenNewScene(), stage);
        gridPane.setOnKeyPressed(this::handleKeyPressed);
        gridPane.requestFocus();
    }

    private void handleKeyPressed(KeyEvent event) {
        KeyCode keyCode = event.getCode();
        switch (keyCode) {
            case UP:
            case DOWN:
            case LEFT:
            case RIGHT:
                world.handleKeyPressed(event);
                break;
            default:
                break;
        }
    }
}


/*
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