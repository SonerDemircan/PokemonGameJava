package PokemonGame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;


public class GameworldController {

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

    Character player = new Character("Soner",'M');

    //Kijken om enum te gebruiken
    public void keyboardUp(KeyCode e) {
        System.out.println(e.getCode());
        switch (e.getCode()) {
            case 90:
                up(null);
                break;
            case 81:
                left(null);
                break;
            case 68:
                right(null);
                break;
            case 83:
                down(null);
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

        if (player.getCharXpos() > 0) {
           /* myCharacterLeft.setVisible(false);
            myCharacterUp.setVisible(true);
            myCharacterDown.setVisible(false);
            myCharacterRight.setVisible(false);*/
            player.backViewCharacter();
            player.setCharXpos(player.getCharXpos()-1);
            GridPane.setConstraints(player.backViewCharacter(), player.getCharYpos(), player.getCharXpos());
        }
    }

    public void down(ActionEvent e) {
    /*currentY = myCharacter.getLayoutY();
    newY = currentY + 10;
    myCharacter.setLayoutY(newY);*/

    /*currentY = myCharacter.getTranslateY();
    newY = currentY + 10;
    myCharacter.setTranslateY(newY);*/

        //int row = GridPane.getRowIndex(myCharacter);
        //int col = GridPane.getColumnIndex(myCharacter);
        if (player.getCharXpos() < 6) {
            /*myCharacterLeft.setVisible(false);
            myCharacterUp.setVisible(false);
            myCharacterRight.setVisible(false);
            myCharacterDown.setVisible(true);*/
            player.frontViewCharacter();
            player.setCharXpos(player.getCharXpos()+1);
            GridPane.setConstraints(player.frontViewCharacter(), player.getCharYpos(), player.getCharXpos());
        }
    }

    public void left(ActionEvent e) {
    /*currentX = myCharacter.getLayoutX();
    newX = currentX - 10;
    myCharacter.setLayoutX(newX);*/

        //int row = GridPane.getRowIndex(myCharacter);
        //int col = GridPane.getColumnIndex(myCharacter);
        if (player.getCharYpos() > 0) {
           /* myCharacterLeft.setVisible(true);
            myCharacterUp.setVisible(false);
            myCharacterDown.setVisible(false);
            myCharacterRight.setVisible(false);*/
            player.leftViewCharacter();
            player.setCharYpos(player.getCharYpos()-1);
            GridPane.setConstraints(player.leftViewCharacter(), player.getCharYpos(), player.getCharXpos());
        }
    }

    public void right(ActionEvent e) {
    /*currentX = myCharacter.getLayoutX();
    newX = currentX + 10;
    myCharacter.setLayoutX(newX);
}*/

        //int row = GridPane.getRowIndex(myCharacter);
        //int col = GridPane.getColumnIndex(myCharacter);
        if (player.getCharYpos() < 6) {
           /* myCharacterLeft.setVisible(false);
            myCharacterUp.setVisible(false);
            myCharacterDown.setVisible(false);
            myCharacterRight.setVisible(true);*/
            player.rightViewCharacter();
            player.setCharYpos(player.getCharYpos()+1);
            GridPane.setConstraints(player.rightViewCharacter(), player.getCharYpos(), player.getCharXpos());
        }
    }
}
