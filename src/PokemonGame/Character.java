package PokemonGame;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public abstract class Character {
    protected String name;
    protected char gender;
    protected double charXpos;
    protected double charYpos;
    protected Pokemon[] pokemons;
    protected Item[] items;
    protected int experience;
    protected int catchCount;
    protected Image[] characterView;
    private ImageView characterImageView;

    protected Image[] walkAnimationFrames;
    protected Timeline walkAnimationTimeline;
    protected int currentFrameIndex;

    public Character(String playerName, char playerGender) {
        this.name = playerName;
        this.gender = playerGender;
        this.charXpos = 250;
        this.charYpos = 250;
        this.catchCount = 0;
        this.pokemons = new Pokemon[20];
        this.items = new Item[10];
        this.experience = 0;

        initializeCharacterView("ImagesAndSprites/PlayerCharacterMale");
        initializeWalkAnimation();
    }

    // Sprites van de speler worden aangemaakt
    public void initializeCharacterView(String imagePath) {
        this.characterView = new Image[4];
        String[] characterSprites = {"Front0", "Back0", "Left0", "Right0"};

        for (int i = 0; i < this.characterView.length; i++) {
            String characterSprite = imagePath + "/Sprite" + characterSprites[i] + ".png";
            this.characterView[i] = new Image(characterSprite, 100, 100, false, false);
        }
    }

    // Sprites van de speler die beweegt worden aangemaakt
    protected void initializeWalkAnimation() {
        walkAnimationFrames = new Image[] {
                new Image("ImagesAndSprites/PlayerCharacterMale/SpriteFrontWalk1.png"),
                new Image("ImagesAndSprites/PlayerCharacterMale/SpriteBackWalk1.png"),
                new Image("ImagesAndSprites/PlayerCharacterMale/SpriteLeftWalk1.png"),
                new Image("ImagesAndSprites/PlayerCharacterMale/SpriteRightWalk1.png"),
        };

        // Animatie logica
        walkAnimationTimeline = new Timeline();
        walkAnimationTimeline.setCycleCount(Animation.INDEFINITE);
        walkAnimationTimeline.getKeyFrames().add(
                new KeyFrame(Duration.millis(200), e -> {
                    currentFrameIndex = (currentFrameIndex + 1) % walkAnimationFrames.length;

                    // Richting van de speler wordt geüpdated
                    for (int i = 0; i < characterView.length; i++) {
                        characterView[i] = walkAnimationFrames[currentFrameIndex];
                    }
                })
        );
    }

    public void healPokemon(Pokemon pokemon) {
        pokemon.battleHitPoints = pokemon.maxHitPoints;
    }

    public void healParty() {
        for (Pokemon pokemon : pokemons) {
            pokemon.battleHitPoints = pokemon.maxHitPoints;
        }
    }

    public int getCharRow() {
        return (int) (charYpos / 110);
    }

    public void setCharRow(int row) {
        this.charYpos = row * 110;
    }

    public int getCharColumn() {
        return (int) (charXpos / 110);
    }

    public void setCharColumn(int column) {
        this.charXpos = column * 110;
    }

    public abstract void moveCharacter(int rowMove, int columnMove);

    public void setCharacterImageView(ImageView characterImageView) {
        this.characterImageView = characterImageView;
    }
}
