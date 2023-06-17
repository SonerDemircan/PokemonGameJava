package Interfaces;
import javafx.scene.image.ImageView;

public interface ICharacter {
    ImageView moveDown();
    ImageView moveUp();
    ImageView moveLeft();
    ImageView moveRight();

    double getCharXpos();
    double getCharYpos();
    void setCharXpos(double charXpos);
    void setCharYpos(double charYpos);

    int getCharRow();
    int getCharColumn();
    void setCharRow(int row);
    void setCharColumn(int column);
}
