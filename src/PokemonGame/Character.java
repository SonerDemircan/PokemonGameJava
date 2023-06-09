package PokemonGame;

import PokemonController.GameWorldController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Character {

    private String name;
    private char gender;
    protected double charXpos;
    protected double charYpos;
    private Pokemon[] playerPokemon;     //gevangen pokemons, 1ste 6 in party
    private Item[] items;           //items zoals pokéballs, potions etc
    private int experience;        //te gebruiken om levelUp en/of aankoop items
    private int catchCount;         //aantal gevangen pokemon

    private double step = 45;

    protected Image[] characterView = new Image[4];
    /*
    private Image frontView;
    private Image backView;
    private Image leftView;
    private Image rightView;
     */


    public Character(String playerName, char playerGender) {
        this.name = playerName;
        this.gender = playerGender;
        this.charXpos = 250;
        this.charYpos = 250;
        this.catchCount = 0;
        this.playerPokemon = new Pokemon[20];
        this.items = new Item[10];
        this.experience = 0;

        //WERKEN MET INHERITANCE

        this.characterView[0] = new Image("ImagesAndSprites\\SpriteFront.png",45,45,false,false);
        this.characterView[1] =  new Image("ImagesAndSprites\\SpriteBack.png",45,45,false,false);
        this.characterView[2] =  new Image("ImagesAndSprites\\SpriteLeft.png",45,45,false,false);
        this.characterView[3] =  new Image("ImagesAndSprites\\SpriteRight.png",45,45,false,false);
    }

    public ImageView moveDown() {
        ImageView imageView = new ImageView();
        imageView.setY(this.charYpos+step);
        imageView.setX(this.charXpos);

        this.charYpos = imageView.getY();
        imageView.setImage(characterView[0]);
        return imageView;
    }

    public ImageView moveUp() {
        ImageView imageView = new ImageView();
        imageView.setY(this.charYpos-step);
        imageView.setX(this.charXpos);

        this.charYpos = imageView.getY();

        imageView.setImage(characterView[1]);
        return imageView;
    }

    public ImageView moveLeft() {
        ImageView imageView = new ImageView();
        imageView.setX(this.charXpos-step);
        imageView.setY(this.charYpos);

        this.charXpos = imageView.getX();

        imageView.setImage(characterView[2]);
        return imageView;
    }

    public ImageView moveRight() {
        ImageView imageView = new ImageView();
        imageView.setX(this.charXpos+step);
        imageView.setY(this.charYpos);

        this.charXpos = imageView.getX();

        imageView.setImage(characterView[3]);
        return imageView;
    }


    public double getCharXpos() {
        return charXpos;
    }

    public double getCharYpos() {
        return charYpos;
    }

    public void setCharXpos(int charXpos) {
        this.charXpos += charXpos;
    }

    public void setCharYpos(double charYpos) {
        this.charYpos += charYpos;
    }


    //Om deze methode aan te roepen in de controller moeten we als world paramater "this" invullen
    public void addPokemonToPlayerParty(int pokemonId, World world) {

        pokemonId--;
        int i = 0;

        while(playerPokemon[i] != null) {
            i++;
        }
        playerPokemon[i] = world.pokemon.get(pokemonId);
    }

    public void changePositionInParty(int firstPokemonPosition, int secondPokemonPosition, World world) {
        Pokemon pokemon = playerPokemon[firstPokemonPosition];
        playerPokemon[firstPokemonPosition] = playerPokemon[secondPokemonPosition];
        playerPokemon[secondPokemonPosition] = pokemon;
    }
}
