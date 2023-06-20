package PokemonController;

import PokemonGame.Pokemon;
import PokemonGame.World;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.util.Scanner;

public class BattleSceneController {

    @FXML
    private Button btnAttack1;

    @FXML
    private Button btnAttack2;

    @FXML
    private Button btnAttack3;

    @FXML
    private Button btnAttack4;

    @FXML
    private Button btnStartBattle;

    @FXML
    private Label lblBattleScene;

    @FXML
    private Label lblEnemyPokemon;

    @FXML
    private Label lblPlayerPokemon;

    @FXML
    private ProgressBar prgsEnemyHP;

    @FXML
    private ProgressBar prgsYourPokemon;

    private World _world;

    private String text = "It's your turn!";

    public BattleSceneController(World world) {
        this._world = world;
    }

    public void battle(Pokemon trainerPok, Pokemon enemy) {

        Scanner scanner = new Scanner(System.in);

        while(trainerPok.getBattleHitPoints() > 0 && enemy.getBattleHitPoints() > 0) {
            //System.out.println("It's " + trainerPok.name + " turn!");
            lblBattleScene.setText(text);
            btnAttack1.setText(trainerPok.getMoveSet()[0].getName() + " -- pp: " + trainerPok.getMoveSet()[0].getPp());
            System.out.println("pick a move: \n" + "0: " +  trainerPok.getMoveSet()[0].getName() + " -- pp: " + trainerPok.getMoveSet()[0].getPp() + "\n1: " + trainerPok.getMoveSet()[1].getName() + " -- pp: " + trainerPok.getMoveSet()[1].getPp() + "\n2: " + trainerPok.getMoveSet()[2].getName() + " -- pp: " + trainerPok.getMoveSet()[2].getPp() + "\n3: " + trainerPok.getMoveSet()[3].getName()+ " -- pp: " + trainerPok.getMoveSet()[3].getPp());
            int attackmove = scanner.nextInt();
            enemy.setBattleHitPoints(-trainerPok.attack(trainerPok,attackmove,enemy));

            if(isPokemonDefeated(enemy)) {
                System.out.println("You and " + trainerPok.name + " won!");
                continue;
            }

            System.out.println("It's " + enemy.name + " turn!");
            System.out.println("pick a move: \n" + "0: " +  enemy.getMoveSet()[0].getName() + " -- pp: " + enemy.getMoveSet()[0].getPp() + "\n1: " + enemy.getMoveSet()[1].getName() + " -- pp: " + enemy.getMoveSet()[1].getPp() + "\n2: " + enemy.getMoveSet()[2].getName() + " -- pp: " + enemy.getMoveSet()[2].getPp() + "\n3: " + enemy.getMoveSet()[3].getPp()+ " -- pp: " + enemy.getMoveSet()[3].getPp() );
            attackmove = scanner.nextInt();
            trainerPok.setBattleHitPoints(-enemy.attack(enemy,attackmove,trainerPok));

            if(isPokemonDefeated(trainerPok)) {
                System.out.println("the enemy and " + enemy.name + " won!");
                continue;
            }

            //System.out.println(trainerPok.name + " HP: " + trainerPok.maxHitPoints + "/" + trainerPok.battleHitPoints);
            //System.out.println(enemy.name + " HP: " + enemy.maxHitPoints + "/" + enemy.battleHitPoints);
            System.out.println();
        }
    }

    public boolean isPokemonDefeated(Pokemon pokemon) {
        boolean defeat = false;
        if(pokemon.getBattleHitPoints() < 1) {
            defeat = true;
        }
        return defeat;
    }


    @FXML
    void btnAttack1(ActionEvent event) {

    }

    @FXML
    void btnAttack2(ActionEvent event) {

    }

    @FXML
    void btnAttack3(ActionEvent event) {

    }

    @FXML
    void btnAttack4(ActionEvent event) {

    }

    @FXML
    void btnStartBattle(ActionEvent event) {
        battle(_world.getPokemon().get(5), _world.getPokemon().get(10));
    }


}