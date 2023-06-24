package Interfaces;

import PokemonGame.Pokemon;

public interface IPokemon {

    int attack(Pokemon attackingPokemon, int attackMove, Pokemon pok, int isHit);
    void healPokemon();
}
