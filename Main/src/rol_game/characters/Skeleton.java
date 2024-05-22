package rol_game.characters;

import javax.swing.*;

public class Skeleton {
    private final JLabel ENEMYLABEL;
    public Skeleton(JLabel ENEMYLABEL) {
        this.ENEMYLABEL = ENEMYLABEL;
    }

    public JLabel getENEMYLABEL() {
        return ENEMYLABEL;
    }
    public JLabel getENEMYLABEL1() {
        return ENEMYLABEL;
    }


    public JLabel getHoritzontalENEMYLABEL() {
        return ENEMYLABEL;
    }

    public JLabel getHoritzontalENEMYLABEL1() {
        return ENEMYLABEL;
    }


    public String showSkeletonUp(){
        return "src/images/skeleton/skeleton_up.gif";
    }
    public String showSkeletonDown(){
        return "src/images/skeleton/skeleton_down.gif";
    }
    public String showSkeletonLeft(){
        return "src/images/skeleton/skeleton_left.gif";
    }
    public String showSkeletonRight(){
        return "src/images/skeleton/skeleton_right.gif";
    }

    // HORITZONTAL SKELETON METHODS
    public void enemyTouchTheWallCondition() {
        ENEMYLABEL.setLocation(ENEMYLABEL.getX() - 5, ENEMYLABEL.getY());

    }
    public void enemyTouchTheWallCondition1() {
        ENEMYLABEL.setLocation(ENEMYLABEL.getX() - 5, ENEMYLABEL.getY());
    }
    public void enemyDontTouchTheWallCondition() {
        ENEMYLABEL.setLocation(ENEMYLABEL.getX() + 5, ENEMYLABEL.getY());
    }
    public void enemyDontTouchTheWallCondition1() {
        ENEMYLABEL.setLocation(ENEMYLABEL.getX() + 5, ENEMYLABEL.getY());
    }

    // VERTICAL SKELETON METHODS
    public void enemyDontTouchTheFooterCondition() {
            ENEMYLABEL.setLocation(ENEMYLABEL.getX(), ENEMYLABEL.getY() + 5);
    }
    public void enemyDontTouchTheFooterCondition1() {
        ENEMYLABEL.setLocation(ENEMYLABEL.getX(), ENEMYLABEL.getY() + 5);
    }
    public void enemyTouchTheFooterCondition(){
            ENEMYLABEL.setLocation(ENEMYLABEL.getX(), ENEMYLABEL.getY() - 5);
        }
    public void enemyTouchTheFooterCondition1(){
        ENEMYLABEL.setLocation(ENEMYLABEL.getX(), ENEMYLABEL.getY() - 5);

    }

}

