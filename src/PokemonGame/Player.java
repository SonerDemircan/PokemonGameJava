package PokemonGame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player {

    private String name;
    private char gender;
    private int charXpos;
    private int charYpos;
    private Pokemon[] pokemons;     //gevangen pokemons, 1ste 6 in party
    private Item[] items;           //items zoals pokéballs, potions etc
    private int experience;        //te gebruiken om levelUp en/of aankoop items
    private int catchCount;         //aantal gevangen pokemon
    private Image frontView;
    private Image backView;
    private Image leftView;
    private Image rightView;


    public Player(String playerName, char playerGender) {
        this.name = playerName;
        this.gender = playerGender;
        this.charXpos = 3;
        this.charYpos = 3;
        this.catchCount = 0;
        this.pokemons = new Pokemon[20];
        this.items = new Item[10];
        this.experience = 0;

        this.frontView = new Image("ImagesAndSprites\\SpriteFront.png");
        this.backView = new Image("ImagesAndSprites\\SpriteBack.png");
        this.leftView = new Image("ImagesAndSprites\\SpriteLeft.png");
        this.rightView = new Image("ImagesAndSprites\\SpriteRight.png");
    }

    public ImageView frontViewCharacter() {
        ImageView imageView = new ImageView(frontView);
        imageView.setY(this.charYpos);
        imageView.setX(this.charXpos);
        return imageView;
    }





    public ImageView backViewCharacter() {
        ImageView imageView = new ImageView(backView);
        imageView.setY(this.charYpos);
        imageView.setX(this.charXpos);
        return imageView;
    }

    public ImageView leftViewCharacter() {
        ImageView imageView = new ImageView(leftView);
        imageView.setY(this.charYpos);
        imageView.setX(this.charXpos);
        return imageView;
    }

    public ImageView rightViewCharacter() {
        ImageView imageView = new ImageView(rightView);
        imageView.setY(this.charYpos);
        imageView.setX(this.charXpos);
        return imageView;
    }








    public int getCharXpos() {
        return charXpos;
    }

    public int getCharYpos() {
        return charYpos;
    }

    public void setCharXpos(int charXpos) {
        this.charXpos = charXpos;
    }

    public void setCharYpos(int charYpos) {
        this.charYpos = charYpos;
    }
}
