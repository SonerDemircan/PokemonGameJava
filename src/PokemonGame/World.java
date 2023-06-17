package PokemonGame;



import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class World extends Pane {

    private Character player;
    private int characterStep = 10;
    protected List<Pokemon> pokemon;
    private List<String> savedPokemon;
    private List <Attack> attackMoves;


    //private CSVReader reader;
    //private String[][] pokemonList;

    public World() {
        Character player = new Character("Daniel", 'M');
        this.player = player;
        this.pokemon = new ArrayList<>();
        this.savedPokemon = new ArrayList<>();
        this.attackMoves = new ArrayList<>();
    }


    public void createMoves(String[][] moves) {
        for (String[] strings : moves) {
            Attack newAttack = new Attack();

            newAttack.moveId = Integer.parseInt(strings[0]);
            newAttack.name = strings[1];
            newAttack.power = Integer.parseInt(strings[2]);
            newAttack.accuracy = Integer.parseInt(strings[3]);
            newAttack.pp = Integer.parseInt(strings[4]);

            attackMoves.add(newAttack);
        }
    }

    public void createPokemon(String[][] pokemonList) {

        for (String[] strings : pokemonList) {
            Pokemon newPokemon = new Pokemon();

            newPokemon.pokemonId = Integer.parseInt(strings[0]);
            newPokemon.name = strings[1];
            newPokemon.type = strings[2];
            newPokemon.level = Integer.parseInt(strings[3]);
            newPokemon.hitPoints = Integer.parseInt(strings[4]);
            newPokemon.attack = Integer.parseInt(strings[5]);
            newPokemon.defense = Integer.parseInt(strings[6]);
            newPokemon.speed = Integer.parseInt(strings[7]);
            newPokemon.moveOne = Integer.parseInt(strings[8]);
            newPokemon.moveTwo = Integer.parseInt(strings[9]);
            newPokemon.moveThree = Integer.parseInt(strings[10]);
            newPokemon.moveFour = Integer.parseInt(strings[11]);

            pokemon.add(newPokemon);
        }
    }

    public void addMoveToPokemon() {
        for (Pokemon pokemon1: pokemon) {
                pokemon1.moveSet[0] = attackMoves.get(pokemon1.moveOne);
                pokemon1.moveSet[1] = attackMoves.get(pokemon1.moveTwo);
                pokemon1.moveSet[2] = attackMoves.get(pokemon1.moveThree);
                pokemon1.moveSet[3] = attackMoves.get(pokemon1.moveFour);
            }
    }

    public List<String> presentPokemon() {
        String output = "";
        for(Pokemon pokemon1: pokemon) {
            output = pokemon1.pokemonId + "," + pokemon1.name + "," + pokemon1.type + "," + pokemon1.level + "," + pokemon1.hitPoints + "," + pokemon1.attack + "," + pokemon1.defense + "," + pokemon1.speed + "," + pokemon1.moveOne + "," + pokemon1.moveTwo + "," + pokemon1.moveThree + "," + pokemon1.moveFour + "\n";
            savedPokemon.add(output);
        }
        return savedPokemon;
    }

    public List<Pokemon> getPokemon() {
        return pokemon;
    }

    public void moveUp() {
        getChildren().clear();
        getChildren().add(player.moveUp());
    }

    public void moveDown() {
        getChildren().clear();
        getChildren().add(player.moveDown());
    }

    public void moveLeft() {
        getChildren().clear();
        getChildren().add(player.moveLeft());
    }

    public void moveRight() {
        getChildren().clear();
        getChildren().add(player.moveRight());
    }

    public Character getPlayer() { return this.player; }


}
