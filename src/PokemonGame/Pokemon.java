package PokemonGame;

public class Pokemon {

    private int pokemonId;
    private String name;
    private String type;
    private int level;
    private int hitPoints;
    private int attack;
    private int defense;
    private int speed;
    private boolean isCaught;

    private Pokemon[] pokéDex;

    public Pokemon(String pokemonName, String type,int level,int hitPoints, int attack, int defense, int speed) {
        this.name = pokemonName;
        this.type = type;
        this.level = level;
        this.hitPoints = hitPoints;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.isCaught = false;
    }
}
