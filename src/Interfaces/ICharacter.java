package Interfaces;

import PokemonGame.Pokemon;

import java.util.List;

public interface ICharacter {
    List<Pokemon> getPokemons();
    int getCharRow();
    void setCharRow(int row);
    int getCharColumn();
    void setCharColumn(int column);
    void stopMoving();
    void moveCharacter(int rowMove, int columnMove);
    String getMovementImage(String spriteDirection);
}

