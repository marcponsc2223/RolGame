package rol_game.characters;

import javax.swing.*;
import java.util.ArrayList;

public abstract class Characters {
    protected String name;
    protected int lifes;
    protected int gold;
    protected  int v;
    protected ArrayList <Characters> Objects;
    protected int x;
    protected int y;
    protected String characterUp;
    protected String characterDown;
    protected String characterRight;
    protected String characterLeft;
    JLabel labelCharacter;


    /**
     * Constructor from the father class.
     * @param name The name of the character.
     * @param lifes The number of lifes have the character.
     * @param gold The number of gold have the character.
     * @param v The character speed.
     * @param x The position x of the character.
     * @param y The position y of the character.
     */
    public Characters(String name, int lifes, int gold, int v, int x, int y,JLabel labelCharacter) {
        this.name = name;
        this.lifes = lifes;
        this.gold = gold;
        this.v = v;
        this.x = x;
        this.y = y;
        this.labelCharacter = labelCharacter;
    }

    /**
     * Return the name.
     *
     * @return Actual name.
     */
    public String getName() {
        return name;
    }

    /**
     * Return the life.
     *
     * @return Actual life quantity.
     */
    public int getLifes() {
        return lifes;
    }

    /**
     * Return the position on x.
     *
     * @param x the new x position.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Return the position on y.
     *
     * @param y the new y position.
     */
    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * The method return x position for the character, to move.
     * @param x current character position.
     * @param timesCharacterIsGoingRight The number of times the character has moved right.
     *  *                                  Used to determine if the icon needs to be changed.
     * @return return new x position of the character after moving.
     */
    public int characterMoveRight(int x, int timesCharacterIsGoingRight){
        x += this.v;
        changeTheIconToRight();
        return x;
    }

    /**
     * The method return x position for the character, to move.
     * @param x current character position.
     * @param timesCharacterIsGoingLeft The number of times the character has moved left.
     *  *                                  Used to determine if the icon needs to be changed.
     * @return return new x position of the character after moving.
     */
    public int characterMoveLeft(int x, int timesCharacterIsGoingLeft) {
        x -= this.v;
        changeTheIconToLeft();
        return x;
    }

    /**
     * The method return x position for the character, to move.
     * @param y current character position.
     * @param timesCharacterIsGoingUp The number of times the character has moved up.
     *  *                                  Used to determine if the icon needs to be changed.
     * @return return new y position of the character after moving.
     */
    public int characterMoveUp(int y, int timesCharacterIsGoingUp) {
        y -= this.v;
        changeTheIconToUp();
        return y;
    }

    /**
     * The method return x position for the character, to move.
     * @param y current character position.
     * @param timesCharacterIsGoingDown The number of times the character has moved down.
     *  *                                  Used to determine if the icon needs to be changed.
     * @return return new y position of the character after moving.
     */
    public int characterMoveDown(int y, int timesCharacterIsGoingDown) {
        y += this.v;
        changeTheIconToDown();
        return y;
    }

    /**
     * Change the character image to right.
     */
    public abstract void changeTheIconToRight();

    /**
     * Change the character image to left.
     */
    public abstract void changeTheIconToLeft();

    /**
     * Change the character image to up.
     */
    public abstract void changeTheIconToUp();

    /**
     * Change the character image to down.
     */
    public abstract void changeTheIconToDown();

    /**
     * The method return the path of the image for the character moving up.
     * @return return the path of the image for the character moving up.
     */
    public String showCharacterUp(){
        return characterUp = "";
    }

    /**
     * The method return the path of the image for the character moving down.
     * @return return the path of the image for the character moving down.
     */
    public String showCharacterDown(){
        return characterDown = "";
    }

    /**
     * The method return the path of the image for the character moving left.
     * @return return the path of the image for the character moving left.
     */
    public String showCharacterLeft(){
        return characterLeft = "";
    }

    /**
     * The method return the path of the image for the character moving right.
     * @return return the path of the image for the character moving right.
     */
    public String showCharacterRight(){
        return characterRight = "";
    }
}
