package PokemonGame;

public class Pokemon {

    public int pokemonId;
    public String name;
    protected String type;
    protected int level;
    protected int hitPoints;
    protected int attack;
    protected int defense;
    protected int speed;
    private boolean isCaught;
    protected int moveOne;
    protected int moveTwo;
    protected int moveThree;
    protected int moveFour;
    protected Attack[] moveSet = new Attack[4];


    public Pokemon() {

    }
}
