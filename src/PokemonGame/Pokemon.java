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
    protected Attack[] moveSet = new Attack[4];

    public Pokemon(int pokemonId, String name, String type, int level, int maxHitPoints, int attack, int defense, int speed, int moveOne, int moveTwo, int moveThree, int moveFour) {
        this.pokemonId = pokemonId;
        this.name = name;
        this.type = type;
        this.level = level;
        this.maxHitPoints = maxHitPoints;
        this.battleHitPoints = maxHitPoints;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.moveOne = moveOne;
        this.moveTwo = moveTwo;
        this.moveThree = moveThree;
        this.moveFour = moveFour;
    }

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

    @Override
    public void healPokemon() {
        battleHitPoints = maxHitPoints;
    }

    public String getPokemonImagePath(Pokemon pok) {
        int pokemonId = pok.pokemonId;
        String path = "";
        switch (pokemonId) {
            case 1:
                path = "ImagesAndSprites/PokemonSprites/Venusaur.gif";
                break;
            case 2:
                path = "ImagesAndSprites/PokemonSprites/Charizard.gif";
                break;
            case 3:
                path = "ImagesAndSprites/PokemonSprites/Blastoise.gif";
                break;
            case 4:
                path = "ImagesAndSprites/PokemonSprites/snorlax.gif";
                break;
            case 5:
                path = "ImagesAndSprites/PokemonSprites/Pangoro.gif";
                break;
            case 6:
                path = "ImagesAndSprites/PokemonSprites/Zoroark.gif";
                break;
            case 7:
                path = "ImagesAndSprites/PokemonSprites/Mewtwo.gif";
                break;
            case 8:
                path = "ImagesAndSprites/PokemonSprites/Haxorus.gif";
                break;
            case 9:
                path = "ImagesAndSprites/PokemonSprites/sylveon.gif";
                break;
            case 10:
                path = "ImagesAndSprites/PokemonSprites/lapras.gif";
                break;
            case 11:
                path = "ImagesAndSprites/PokemonSprites/raichu.gif";
                break;
            case 12:
                path = "ImagesAndSprites/PokemonSprites/krookodile.gif";
                break;
            case 13:
                path = "ImagesAndSprites/PokemonSprites/scizor.gif";
                break;
            case 14:
                path = "ImagesAndSprites/PokemonSprites/Aegislash.gif";
                break;
            case 15:
                path = "ImagesAndSprites/PokemonSprites/Nidoking.gif";
                break;
            case 16:
                path = "ImagesAndSprites/PokemonSprites/Tyranitar.gif";
                break;
            case 17:
                path = "ImagesAndSprites/PokemonSprites/corviknight.gif";
                break;
            case 18:
                path = "ImagesAndSprites/PokemonSprites/Gengar.gif";
                break;
        }
        return path;
    }

    public String getName() {
        return name;
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
}