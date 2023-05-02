package PokemonGame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;


public class GameworldController {

    @FXML
    private World world;

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
    public void keyboardUp(KeyEvent e) {
        System.out.println(e.getCode());
        switch (e.getCode()) {
            case Z:
                up(null);
                break;
            case Q:
                left(null);
                break;
            case D:
                right(null);
                break;
            case S:
                down();
                break;
            default:
        }
    }

    //Aparte method maken voor de if-statement



    public void up(ActionEvent e) {
    /*currentY = myCharacter.getLayoutY();
    newY = currentY - 10;
    myCharacter.setLayoutY(newY);*/

    /*currentY = myCharacter.getTranslateY();
    newY = currentY - 10;
    myCharacter.setTranslateY(newY);*/

        //row = character.getCharXpos();
        //col = character.getCharYpos();




        if (world.player.getCharXpos() > 0) {
           /* myCharacterLeft.setVisible(false);
            myCharacterUp.setVisible(true);
            myCharacterDown.setVisible(false);
            myCharacterRight.setVisible(false);*/

            world.moveUp();
        }
    }

    public void down() {
    /*currentY = myCharacter.getLayoutY();
    newY = currentY + 10;
    myCharacter.setLayoutY(newY);*/

    /*currentY = myCharacter.getTranslateY();
    newY = currentY + 10;
    myCharacter.setTranslateY(newY);*/

        if (world.player.getCharXpos() < 6) {
            /*myCharacterLeft.setVisible(false);
            myCharacterUp.setVisible(false);
            myCharacterRight.setVisible(false);
            myCharacterDown.setVisible(true);*/


        }
    }

    public void left(ActionEvent e) {
    /*currentX = myCharacter.getLayoutX();
    newX = currentX - 10;
    myCharacter.setLayoutX(newX);*/

        if (world.player.getCharYpos() > 0) {
           /* myCharacterLeft.setVisible(true);
            myCharacterUp.setVisible(false);
            myCharacterDown.setVisible(false);
            myCharacterRight.setVisible(false);*/
        }
    }

    public void right(ActionEvent e) {
    /*currentX = myCharacter.getLayoutX();
    newX = currentX + 10;
    myCharacter.setLayoutX(newX);
}*/

        if (world.player.getCharYpos() < 6) {
           /* myCharacterLeft.setVisible(false);
            myCharacterUp.setVisible(false);
            myCharacterDown.setVisible(false);
            myCharacterRight.setVisible(true);*/
        }
    }
}
