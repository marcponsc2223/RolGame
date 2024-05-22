package rol_game.characters;

import javax.swing.*;
import java.awt.*;

public class Magician extends Characters{

    /**
     * Constructor from the father class.
     * @param name The name of the character.
     * @param lifes The number of lifes have the character.
     * @param gold The number of gold have the character.
     * @param v The character speed.
     * @param x The position x of the character.
     * @param y The position y of the character.
     */
    public Magician(String name, int lifes, int gold, int v, int x, int y, JLabel labelCharacter) {
        super(name, lifes, gold, v, x, y, labelCharacter);
    }

    /**
     * The method return x position for the character, to move.
     * @param x current character position.
     * @param timesCharacterIsGoingRight The number of times the character has moved right.
     *  *                                  Used to determine if the icon needs to be changed.
     * @return return new x position of the character after moving.
     */
    @Override
    public int characterMoveRight(int x, int timesCharacterIsGoingRight) {
        x += this.v;
        if (timesCharacterIsGoingRight == 0) {
            changeTheIconToRight();
        }
        return x;
    }

    /**
     * The method return x position for the character, to move.
     * @param x current character position.
     * @param timesCharacterIsGoingLeft The number of times the character has moved left.
     *  *                                  Used to determine if the icon needs to be changed.
     * @return return new x position of the character after moving.
     */
    @Override
    public int characterMoveLeft(int x, int timesCharacterIsGoingLeft) {
        x -= this.v;
        if (timesCharacterIsGoingLeft == 0) {
            changeTheIconToLeft();
        }
        return x;
    }

    /**
     * The method return x position for the character, to move.
     * @param y current character position.
     * @param timesCharacterIsGoingUp The number of times the character has moved up.
     *  *                                  Used to determine if the icon needs to be changed.
     * @return return new y position of the character after moving.
     */
    @Override
    public int characterMoveUp(int y, int timesCharacterIsGoingUp) {
        y -= this.v;
        if (timesCharacterIsGoingUp == 0) {
            changeTheIconToUp();
        }
        return y;
    }

    /**
     * The method return x position for the character, to move.
     * @param y current character position.
     * @param timesCharacterIsGoingDown The number of times the character has moved right.
     *  *                                  Used to determine if the icon needs to be changed.
     * @return return new y position of the character after moving.
     */
    @Override
    public int characterMoveDown(int y, int timesCharacterIsGoingDown) {
        y += this.v;
        if (timesCharacterIsGoingDown == 0){
            changeTheIconToDown();
        }
        return y;
    }

    /**
     * The method return the path of the image for the character moving up.
     * @return return the path of the image for the character moving up.
     */
    @Override
    public String showCharacterUp(){
        return "src/images/wizard/wizard_up.gif";
    }

    /**
     * The method return the path of the image for the character moving down.
     * @return return the path of the image for the character moving up.
     */
    @Override
    public String showCharacterDown(){
        return "src/images/wizard/wizard_down.gif";
    }

    /**
     * The method return the path of the image for the character moving left.
     * @return return the path of the image for the character moving left.
     */
    @Override
    public String showCharacterLeft(){
        return "src/images/wizard/wizard_left.gif";
    }

    /**
     * The method return the path of the image for the character moving right.
     * @return return the path of the image for the character moving right.
     */
    @Override
    public String showCharacterRight(){
        return "src/images/wizard/wizard_right.gif";
    }

    /**
     * Change the character image to right .
     */
    @Override
    public void changeTheIconToRight() {
        ImageIcon icon = new ImageIcon((showCharacterRight()));
        Icon icon1 = new ImageIcon(
                icon.getImage().getScaledInstance(labelCharacter.getWidth(), labelCharacter.getHeight(), Image.SCALE_DEFAULT)
        );
        labelCharacter.setIcon(icon1);
    }

    /**
     * Change the character image to left .
     */
    @Override
    public void changeTheIconToLeft() {
        ImageIcon icon = new ImageIcon((showCharacterLeft()));
        Icon icon1 = new ImageIcon(
                icon.getImage().getScaledInstance(labelCharacter.getWidth(), labelCharacter.getHeight(), Image.SCALE_DEFAULT)
        );
        labelCharacter.setIcon(icon1);
    }

    /**
     * Change the character image to up .
     */
    @Override
    public void changeTheIconToUp() {
        ImageIcon icon = new ImageIcon((showCharacterUp()));
        Icon icon1 = new ImageIcon(
                icon.getImage().getScaledInstance(labelCharacter.getWidth(), labelCharacter.getHeight(), Image.SCALE_DEFAULT)
        );
        labelCharacter.setIcon(icon1);
    }

    /**
     * Change the character image to down .
     */
    @Override
    public void changeTheIconToDown() {
        ImageIcon icon = new ImageIcon((showCharacterDown()));
        Icon icon1 = new ImageIcon(
                icon.getImage().getScaledInstance(labelCharacter.getWidth(), labelCharacter.getHeight(), Image.SCALE_DEFAULT)
        );
        labelCharacter.setIcon(icon1);
    }
}
