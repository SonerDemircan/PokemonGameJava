package PokemonGame;


import javafx.scene.layout.Pane;

public class World extends Pane {

    Character player;
    private int characterStep = 10;

    public World() {
        Character player = new Character("Daniel", 'M');
        this.player = player;
    }




    public void moveUp() {
        getChildren().clear();
        getChildren().add(player.moveUp());
    }

    public void moveDown() {
        getChildren().clear();
        getChildren().add(player.moveDown());

    }

    public void moveLeft() {
        getChildren().clear();
        getChildren().add(player.moveLeft());
    }

    public void moveRight() {
        getChildren().clear();
        getChildren().add(player.moveRight());

    }


}
