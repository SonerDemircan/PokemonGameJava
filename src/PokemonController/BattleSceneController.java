package PokemonController;

import PokemonGame.Pokemon;
import PokemonGame.World;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.util.Random;
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
    private Button btnNext;

    @FXML
    private Label lblBattleScene;

    @FXML
    private Label lblEnemyPokemon;

    @FXML
    private Label lblPlayerPokemon;

    @FXML
    private Label lblEnemyHP;

    @FXML
    private Label lblTrainerPokemonHP;

    @FXML
    private ProgressBar prgsEnemyHP;

    @FXML
    private ProgressBar prgsYourPokemon;

    Random random = new Random();

    private World _world;

    private String playerTurn = "It's your turn!";
    private int attack = 5;
    private Pokemon enemy;
    private Pokemon trainerPokemon;

    public BattleSceneController(World world) {
        this._world = world;
    }

    public void startBattle(Pokemon trainerPok, Pokemon enemy) {


        lblPlayerPokemon.setText(trainerPok.name);
        lblTrainerPokemonHP.setText(updateTrainerPokemonHP());
        lblEnemyPokemon.setText(enemy.name);
        lblEnemyHP.setText(updateEnemyHP());

        updateAttackButtons();
        setAttackButtonsVisible();
    }

    private void updateAttackButtons() {
        btnAttack1.setText(trainerPokemon.getMoveSet()[0].getName() + "\npp: " + trainerPokemon.getMoveSet()[0].getPp());
        btnAttack2.setText(trainerPokemon.getMoveSet()[1].getName() + "\npp: " + trainerPokemon.getMoveSet()[1].getPp());
        btnAttack3.setText(trainerPokemon.getMoveSet()[2].getName() + "\npp: " + trainerPokemon.getMoveSet()[2].getPp());
        btnAttack4.setText(trainerPokemon.getMoveSet()[3].getName() + "\npp: " + trainerPokemon.getMoveSet()[3].getPp());
    }
    private void trainerPokemonAttack(int attackNumber) {
        enemy.setBattleHitPoints(enemy.getBattleHitPoints()-trainerPokemon.attack(trainerPokemon,attackNumber,enemy));
    }

    private Pokemon randomPokemon() {
        return _world.getPokemon().get(random.nextInt(18));
    }

    public boolean isPokemonDefeated(Pokemon pokemon) {
        boolean defeat = false;
        if(pokemon.getBattleHitPoints() < 1) {
            defeat = true;
            btnStartBattle.setVisible(true);
            btnNext.setVisible(false);
            setAttackButtonsInvisible();
            pokemon.setBattleHitPoints(0);
        }
        return defeat;
    }
    private String updateEnemyHP() {
        double progressBar = (double)enemy.getBattleHitPoints()/(double)enemy.getMaxHitPoints();
        prgsEnemyHP.setProgress(progressBar);
        return ("HP: " + enemy.getMaxHitPoints() + "/" + enemy.getBattleHitPoints());
    }

    private String updateTrainerPokemonHP() {
        double progressBar = (double)trainerPokemon.getBattleHitPoints()/(double)trainerPokemon.getMaxHitPoints();
        prgsYourPokemon.setProgress(progressBar);
        return ("HP: " + trainerPokemon.getMaxHitPoints() + "/" + trainerPokemon.getBattleHitPoints());
    }

    private int enemyAttack() {
        int attackNumber = random.nextInt(3);
        trainerPokemon.setBattleHitPoints(trainerPokemon.getBattleHitPoints()-enemy.attack(enemy,attackNumber,trainerPokemon));
        return attackNumber;
    }

    private void setAttackButtonsVisible() {
        updateAttackButtons();
        btnAttack1.setVisible(true);
        btnAttack2.setVisible(true);
        btnAttack3.setVisible(true);
        btnAttack4.setVisible(true);
    }

    private void setAttackButtonsInvisible() {
        btnAttack1.setVisible(false);
        btnAttack2.setVisible(false);
        btnAttack3.setVisible(false);
        btnAttack4.setVisible(false);
    }

    @FXML
    void btnNext(ActionEvent event) {

        lblBattleScene.setText(enemy.name + " used " + enemy.getMoveSet()[enemyAttack()].getName());


        if(isPokemonDefeated(trainerPokemon)) {
            lblBattleScene.setText(enemy.getName() + " has won the fight!");
        } else if (!isPokemonDefeated(trainerPokemon)) {
            setAttackButtonsVisible();
        }
        lblTrainerPokemonHP.setText(updateTrainerPokemonHP());
        btnNext.setVisible(false);
    }

    public void yourattack(int attackNr) {
        trainerPokemonAttack(attackNr);

        lblBattleScene.setText(trainerPokemon.name + " used " + trainerPokemon.getMoveSet()[attackNr].getName());

        lblEnemyHP.setText(updateEnemyHP());
        if(isPokemonDefeated(enemy)) {
            lblBattleScene.setText("you've won!");
            btnNext.setVisible(false);
        } else if (!isPokemonDefeated(enemy)) {
            btnNext.setVisible(true);
        }
        setAttackButtonsInvisible();
    }


    @FXML
    void btnAttack1(ActionEvent event) {
        /*trainerPokemonAttack(0);

        lblBattleScene.setText(trainerPokemon.name + " used " + trainerPokemon.getMoveSet()[0].getName());

        lblEnemyHP.setText(updateEnemyHP());
        if(isPokemonDefeated(enemy)) {
            lblBattleScene.setText("you've won!");
            btnNext.setVisible(false);
        }
        setAttackButtonsInvisible();
        btnNext.setVisible(true);*/
        yourattack(0);
    }

    @FXML
    void btnAttack2(ActionEvent event) {
        /*trainerPokemonAttack(1);
        lblBattleScene.setText(trainerPokemon.name + " used " + trainerPokemon.getMoveSet()[1].getName());

        lblEnemyHP.setText(updateEnemyHP());
        if(isPokemonDefeated(enemy)) {
            lblBattleScene.setText("you've won!");
        }
        setAttackButtonsInvisible();
        btnNext.setVisible(true);

         */
        yourattack(1);
    }

    @FXML
    void btnAttack3(ActionEvent event) {
        /*trainerPokemonAttack(2);
        lblBattleScene.setText(trainerPokemon.name + " used " + trainerPokemon.getMoveSet()[2].getName());

        lblEnemyHP.setText(updateEnemyHP());
        if(isPokemonDefeated(enemy)) {
            lblBattleScene.setText("you've won!");
        }
        setAttackButtonsInvisible();
        btnNext.setVisible(true);

         */
        yourattack(2);
    }

    @FXML
    void btnAttack4(ActionEvent event) {
        /*trainerPokemonAttack(3);
        lblBattleScene.setText(trainerPokemon.name + " used " + trainerPokemon.getMoveSet()[3].getName());

        lblEnemyHP.setText(updateEnemyHP());
        if(isPokemonDefeated(enemy)) {
            lblBattleScene.setText("you've won!");
        }
        setAttackButtonsInvisible();
        btnNext.setVisible(true);*/
        yourattack(3);
    }

    @FXML
    void btnStartBattle(ActionEvent event) {
        enemy = randomPokemon();
        trainerPokemon = _world.getPlayer().getPokemons()[0];

        lblBattleScene.setText("You've encountered a Wild " + enemy.name + "!" + "\nLet's battle!");
        startBattle(trainerPokemon,enemy);

        btnStartBattle.setVisible(false);
    }



}