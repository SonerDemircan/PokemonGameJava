package PokemonController;

import PokemonGame.Pokemon;
import PokemonGame.World;
import SwitchScenes.SwitchScene;
import WriterReader.CSVWriter;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;
import java.util.Random;

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
    private Button btnRun;

    @FXML
    private Button btnCatch;

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

    @FXML
    private ImageView imgvEnemy;

    @FXML
    private ImageView imgvTrainerPokemon;
    Random random = new Random();
    private World _world;
    private Pokemon enemy;
    private Pokemon trainerPokemon;
    private CSVWriter writer;

    public BattleSceneController(World world) {
        this._world = world;
    }

    @FXML
    void btnAttack1(ActionEvent event) {
        yourattack(0);
    }

    @FXML
    void btnAttack2(ActionEvent event) {
        yourattack(1);
    }

    @FXML
    void btnAttack3(ActionEvent event) {
        yourattack(2);
    }

    @FXML
    void btnAttack4(ActionEvent event) {
        yourattack(3);
    }

    @FXML
    void btnStartBattle(ActionEvent event) {
        enemy = randomPokemon();
        trainerPokemon = _world.getPlayer().getPokemons().get(0);

        Image pokemon1 = new Image(getPokemonImagePath(trainerPokemon));
        imgvTrainerPokemon.setImage(pokemon1);

        Image pokemon2 = new Image(getPokemonImagePath(enemy));
        imgvEnemy.setImage(pokemon2);

        lblBattleScene.setText("You've encountered a Wild " + enemy.getName() + "!" + "\nLet's battle!");
        startBattle(trainerPokemon,enemy);
        btnStartBattle.setVisible(false);
        healPokemon(trainerPokemon,enemy);
    }

    @FXML
    void btnNext(ActionEvent event) {

        lblBattleScene.setText(enemy.getName() + " used " + enemy.getMoveSet()[enemyAttack()].getName());

        if(isPokemonDefeated(trainerPokemon)) {
            lblBattleScene.setText("Enemy " + enemy.getName() + " has won the fight!");
            healPokemon(trainerPokemon,enemy);
        } else if (!isPokemonDefeated(trainerPokemon)) {
            setAttackButtonsVisible();
        }
        lblTrainerPokemonHP.setText(updateTrainerPokemonHP());
        btnNext.setVisible(false);
        btnCatch.setVisible(true);
    }

    @FXML
    void btnRun(ActionEvent event) {
        saveCaughtPokemon();
        openWorldScene(event);
    }

    @FXML
    void btnCatch(ActionEvent event) {
        boolean catched = _world.getPlayer().catchPokemon(enemy);
        if(catched) {
            lblBattleScene.setText("You've caught a " + enemy.getName() + "!\nIs added to your party.");
        } else {
            lblBattleScene.setText("Ohw, you didn't catch it!");
            btnNext.setVisible(true);
        }
        setAttackButtonsInvisible();
        btnCatch.setVisible(false);
        btnStartBattle.setVisible(true);
    }

    public void startBattle(Pokemon trainerPok, Pokemon enemy) {

        lblPlayerPokemon.setText(trainerPok.getName());
        lblTrainerPokemonHP.setText(updateTrainerPokemonHP());
        lblEnemyPokemon.setText(enemy.getName());
        lblEnemyHP.setText(updateEnemyHP());

        updateAttackButtons();
        setAttackButtonsVisible();
        btnCatch.setVisible(true);
    }

    private void updateAttackButtons() {
        btnAttack1.setText(trainerPokemon.getMoveSet()[0].getName() + "\npp: " + trainerPokemon.getMoveSet()[0].getPp());
        btnAttack2.setText(trainerPokemon.getMoveSet()[1].getName() + "\npp: " + trainerPokemon.getMoveSet()[1].getPp());
        btnAttack3.setText(trainerPokemon.getMoveSet()[2].getName() + "\npp: " + trainerPokemon.getMoveSet()[2].getPp());
        btnAttack4.setText(trainerPokemon.getMoveSet()[3].getName() + "\npp: " + trainerPokemon.getMoveSet()[3].getPp());
    }
    private void trainerPokemonAttack(int attackNumber) {

        int damage = trainerPokemon.attack(trainerPokemon,attackNumber,enemy,isHit());
        if(damage < 1) {
            lblBattleScene.setText("That did nothing!");
        }
        enemy.setBattleHitPoints(enemy.getBattleHitPoints()-damage);
    }

    private Pokemon randomPokemon() {
        return _world.getPokemon().get(random.nextInt(17));
    }

    public boolean isPokemonDefeated(Pokemon pokemon) {
        boolean defeat = false;
        if(pokemon.getBattleHitPoints() < 1) {
            defeat = true;
            btnStartBattle.setVisible(true);
            btnNext.setVisible(false);
            setAttackButtonsInvisible();
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
        trainerPokemon.setBattleHitPoints(trainerPokemon.getBattleHitPoints()-enemy.attack(enemy,attackNumber,trainerPokemon, isHit()));
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

    private void healPokemon(Pokemon trainerPokemon, Pokemon enemy) {
        trainerPokemon.setBattleHitPoints(trainerPokemon.getMaxHitPoints());
        enemy.setBattleHitPoints(enemy.getMaxHitPoints());
    }

    public void yourattack(int attackNr) {
        trainerPokemonAttack(attackNr);

        lblBattleScene.setText(trainerPokemon.getName() + " used " + trainerPokemon.getMoveSet()[attackNr].getName());

        lblEnemyHP.setText(updateEnemyHP());
        if(isPokemonDefeated(enemy)) {
            lblBattleScene.setText("you've won!");
            btnNext.setVisible(false);
            enemy.setBattleHitPoints(0);
        } else if (!isPokemonDefeated(enemy)) {
            btnNext.setVisible(true);
        }
        setAttackButtonsInvisible();
        btnCatch.setVisible(false);
    }

    private int isHit() {
        Random random = new Random();
        return random.nextInt(100);
    }

    private void saveCaughtPokemon() {
        List<String> saveParty = _world.presentPokemon(_world.getPlayer().getPokemons());

        writer = new CSVWriter();
        writer.writeFile(saveParty,"SaveGamePokemon");
    }

    private void openWorldScene(Event event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SwitchScene newScene = new SwitchScene();

        try {
            newScene.openNewScene("World", currentStage,"Gameworld");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void generatePokemonImage() {
        Image pok1 = new Image("ImagesAndSprites/Haxorus.gif");
        imgvTrainerPokemon.setImage(pok1);

    }

    private String getPokemonImagePath(Pokemon pok) {
        int pokemonId = pok.getPokemonId();
        String path = "";
        switch (pokemonId) {
            case 1:
                path = "ImagesAndSprites/Venusaur.gif";
                break;
            case 2:
                path = "ImagesAndSprites/Charizard.gif";
                break;
            case 3:
                path = "ImagesAndSprites/Blastoise.gif";
                break;
            case 4:
                path = "ImagesAndSprites/snorlax.gif";
                break;
            case 5:
                path = "ImagesAndSprites/Pangoro.gif";
                break;
            case 6:
                path = "ImagesAndSprites/Zoroark.gif";
                break;
            case 7:
                path = "ImagesAndSprites/Mewtwo.gif";
                break;
            case 8:
                path = "ImagesAndSprites/Haxorus.gif";
                break;
            case 9:
                path = "ImagesAndSprites/sylveon.gif";
                break;
            case 10:
                path = "ImagesAndSprites/lapras.gif";
                break;
            case 11:
                path = "ImagesAndSprites/raichu.gif";
                break;
            case 12:
                path = "ImagesAndSprites/krookodile.gif";
                break;
            case 13:
                path = "ImagesAndSprites/scizor.gif";
                break;
            case 14:
                path = "ImagesAndSprites/Aegislash.gif";
                break;
            case 15:
                path = "ImagesAndSprites/Nidoking.gif";
                break;
            case 16:
                path = "ImagesAndSprites/Tyranitar.gif";
                break;
            case 17:
                path = "ImagesAndSprites/corviknight.gif";
                break;
            case 18:
                path = "ImagesAndSprites/Gengar.gif";
                break;
        }
        return path;
    }

}