package PokemonGame;

import java.util.Random;

public class Pokemon {

    public int pokemonId;
    public String name;
    protected String type;
    protected int level;
    protected int maxHitPoints;
    protected int battleHitPoints;
    protected int attack;
    protected int defense;
    protected int speed;
    protected boolean isCaught;
    protected int moveOne;
    protected int moveTwo;
    protected int moveThree;

    public int getPokemonId() {
        return pokemonId;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getMaxHitPoints() {
        return maxHitPoints;
    }

    public int getBattleHitPoints() {
        return battleHitPoints;
    }
    public void setBattleHitPoints(int battleHitPoints) {
        this.battleHitPoints = battleHitPoints;
    }


    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpeed() {
        return speed;
    }

    public Attack[] getMoveSet() {
        return moveSet;
    }

    protected int moveFour;
    protected Attack[] moveSet = new Attack[4];


    public Pokemon() {

    }

    public int attack(Pokemon trainerPokemon,int attackMove, Pokemon enemy) {
        int damage = 0;
        if (trainerPokemon.moveSet[attackMove].accuracy >= isHit()) {
            damage = ((2 * trainerPokemon.level / 5 + 2) * trainerPokemon.moveSet[attackMove].power * (trainerPokemon.attack / enemy.defense) / 50 + 2);
        } else {
            System.out.println("That was a miss!");
        }
        trainerPokemon.moveSet[attackMove].pp--;

        return damage;
    }

    private int isHit() {
        Random random = new Random();
        return random.nextInt(100);
    }

    public void healPokemon(Pokemon pokemon) {
        this.battleHitPoints = maxHitPoints;
    }
}
