/*package PokemonGame;

public class NPC extends Character {

    public NPC(String playerName, char playerGender) {
        super(playerName, playerGender);
    }

    // Override om de moveCharacter van de npc aan te passen
    @Override
    public void moveCharacter(int rowMove, int columnMove) {
        Thread movementNpcThread = new Thread(() -> {
            while (true) {
                setCharRow(getCharRow() + rowMove);
                setCharColumn(getCharColumn() + columnMove);

                try {
                    // Vertraging
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        movementNpcThread.start();
    }
}
*/