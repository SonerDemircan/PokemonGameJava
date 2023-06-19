package PokemonGame;

import PokemonGame.Item;
import PokemonGame.Pokemon;
import javafx.scene.image.Image;

public class Character {

    protected String name;
    protected char gender;
    protected double charXpos;
    protected double charYpos;
    protected Pokemon[] pokemons;     //gevangen pokemons, 1ste 6 in party
    protected Item[] items;           //items zoals pokéballs, potions etc
    protected int experience;        //te gebruiken om levelUp en/of aankoop items
    protected int catchCount;         //aantal gevangen pokemon
    protected double step = 45;
    protected Image[] characterView = new Image[4];

    public Character(String playerName, char playerGender) {
        this.name = playerName;
        this.gender = playerGender;
        this.charXpos = 250;
        this.charYpos = 250;
        this.catchCount = 0;
        this.pokemons = new Pokemon[20];
        this.items = new Item[10];
        this.experience = 0;

        initializeCharacterView();
    }

    private void initializeCharacterView() {
        this.characterView = new Image[4];
        this.characterView[0] = new Image("ImagesAndSprites/SpriteFront.png", 45, 45, false, false);
        this.characterView[1] = new Image("ImagesAndSprites/SpriteBack.png", 45, 45, false, false);
        this.characterView[2] = new Image("ImagesAndSprites/SpriteLeft.png", 45, 45, false, false);
        this.characterView[3] = new Image("ImagesAndSprites/SpriteRight.png", 45, 45, false, false);
    }

    public void healPokemon(Pokemon pokemon) {
        pokemon.battleHitPoints = pokemon.maxHitPoints;
    }

    public void healParty() {
        for(Pokemon pokemon: pokemons) {
            pokemon.battleHitPoints = pokemon.maxHitPoints;
        }
    }

    public int getCharRow() {
        return (int) (charYpos / 110);
    }

    public void setCharRow(int row) {
        this.charYpos = row * 110;
    }

    public int getCharColumn() {
        return (int) (charXpos / 110);
    }

    public void setCharColumn(int column) {
        this.charXpos = column * 110;
    }

}
