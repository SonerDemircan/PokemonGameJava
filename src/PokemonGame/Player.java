package PokemonGame;

public class Player {

    private String name;
    private boolean gender;
    private int charXpos;
    private int charYpos;
    private Pokemon[] pokemons;     //gevangen pokemons, 1ste 6 in party
    private Item[] items;           //items zoals pokéballs, potions etc
    private int experience;        //te gebruiken om levelUp en/of aankoop items
    private int catchCount;         //aantal gevangen pokemon

    public Player(String playerName, boolean playerGender) {
        this.name = playerName;
        this.gender = playerGender;
        this.charXpos = 3;
        this.charYpos = 3;
        this.catchCount = 0;
        this.pokemons = new Pokemon[20];
        this.items = new Item[10];
        this.experience = 0;
    }

    public void setCharXpos(int charXpos) {
        this.charXpos += charXpos;
    }

    public void setCharYpos(int charYpos) {
        this.charYpos += charYpos;
    }
}
