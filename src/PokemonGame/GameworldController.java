/*package PokemonGame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class GameworldController {

    @FXML
    private BorderPane borderPaneIntro;

    @FXML
    private Pane gamePane;

    @FXML
    private World world;

    public GameworldController() {
        this.world = new World();
    }

    // Wereld wordt aangemaakt & wordt toegevoegd aan de pane gamePane
    public void initialize() {
        world = new World();
        gamePane = new Pane();
        gamePane.getChildren().add(world);
        //gamePane.requestFocus();

        //String musicFile = "/Users/wutangclan/Downloads/Pokémon Sun & Moon- Guzma Battle Music (Highest Quality).mp3";
        //Media sound = new Media(new File(musicFile).toURI().toString());
        //MediaPlayer mediaPlayer = new MediaPlayer(sound);
        //mediaPlayer.play();
    }

    @FXML
    private AnchorPane scene;

    @FXML
    private ImageView myCharacterUp;

    @FXML
    private ImageView myCharacterLeft;

    @FXML
    private ImageView myCharacterRight;

    @FXML
    private ImageView myCharacterDown;

    private double currentX;
    private double newX;
    private double currentY;
    private double newY;

    int row;
    int col;


    @FXML
    void moveUp(ActionEvent event){
        world.moveUp();
    }

    @FXML
    void moveLeft(ActionEvent event){
        world.moveLeft();
    }

    @FXML
    void moveRight(ActionEvent event){
        world.moveRight();
    }

    @FXML
    void moveDown(ActionEvent event){
        world.moveDown();
    }


    //Kijken om enum te gebruiken
    @FXML
    public void keyboardMove(KeyEvent e) {
        switch (e.getCode()) {
            case W :
                world.moveUp();
                //if (world.player.getCharYpos() >= 0) {
                  //  world.moveUp();
                //}
                break;
            case A:
                world.moveLeft();
                //if (world.player.getCharXpos() >= 0) {
                  //  world.moveLeft();
                //}
                break;
            case D:
                world.moveRight();
                //if (world.player.getCharXpos() < 745) {
                  //  world.moveRight();
                //}
                break;
            case S:
                world.moveDown();
                //if (world.player.getCharYpos() < 790) {
                  //  world.moveDown();
                //}
                break;
            default:
        }
    }



    //Aparte method maken voor de if-statement

    /*public void up(ActionEvent e) {
    currentY = myCharacter.getLayoutY();
    newY = currentY - 10;
    myCharacter.setLayoutY(newY);

    currentY = myCharacter.getTranslateY();
    newY = currentY - 10;
    myCharacter.setTranslateY(newY);

        //row = character.getCharXpos();
        //col = character.getCharYpos();




        if (world.player.getCharXpos() > 0) {
            myCharacterLeft.setVisible(false);
            myCharacterUp.setVisible(true);
            myCharacterDown.setVisible(false);
            myCharacterRight.setVisible(false);

            world.moveUp();
        }
    }

    public void down() {
    currentY = myCharacter.getLayoutY();
    newY = currentY + 10;
    myCharacter.setLayoutY(newY);

    currentY = myCharacter.getTranslateY();
    newY = currentY + 10;
    myCharacter.setTranslateY(newY);

        if (world.player.getCharXpos() < 6) {
            world.moveDown();
            myCharacterLeft.setVisible(false);
            myCharacterUp.setVisible(false);
            myCharacterRight.setVisible(false);
            myCharacterDown.setVisible(true);


        }
    }

    public void left(ActionEvent e) {
    currentX = myCharacter.getLayoutX();
    newX = currentX - 10;
    myCharacter.setLayoutX(newX);

        if (world.player.getCharYpos() > 0) {
            world.moveLeft();
            myCharacterLeft.setVisible(true);
            myCharacterUp.setVisible(false);
            myCharacterDown.setVisible(false);
            myCharacterRight.setVisible(false);
        }
    }

    public void right(ActionEvent e) {
    currentX = myCharacter.getLayoutX();
    newX = currentX + 10;
    myCharacter.setLayoutX(newX);
}

        if (world.player.getCharYpos() < 6) {
            world.moveRight();
            myCharacterLeft.setVisible(false);
            myCharacterUp.setVisible(false);
            myCharacterDown.setVisible(false);
            myCharacterRight.setVisible(true);
        }
    }
}*/
