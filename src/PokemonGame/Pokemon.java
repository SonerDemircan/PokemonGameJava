package PokemonGame;

import Interfaces.IPokemon;

public class Pokemon implements IPokemon {

    protected int pokemonId;
    protected String name;
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
    protected int moveFour;

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


    public Attack[] getMoveSet() {
        return moveSet;
    }

    protected Attack[] moveSet = new Attack[4];

    @Override
    public int attack(Pokemon attackingPokemon, int attackMove, Pokemon pok, int isHit) {
        int damage = 0;
        if (attackingPokemon.getMoveSet()[attackMove].power != 0) {
            if (attackingPokemon.moveSet[attackMove].accuracy >= isHit) {
                damage = ((2 * attackingPokemon.level / 5 + 2) * attackingPokemon.moveSet[attackMove].power * (attackingPokemon.attack / pok.defense) / 50 + 2);

                attackingPokemon.moveSet[attackMove].pp--;
            }

        }
        return damage;
    }
}