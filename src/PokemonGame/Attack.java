package PokemonGame;

public class Attack {

    protected int moveId;
    protected String name;
    protected int power;
    protected int accuracy;
    protected int pp;

    public Attack(int moveId, String name, int power, int accuracy, int pp) {
        this.moveId = moveId;
        this.name = name;
        this.power = power;
        this.accuracy = accuracy;
        this.pp = pp;
    }

    public String getName() {
        return name;
    }

    public int getPp() {
        return pp;
    }
}