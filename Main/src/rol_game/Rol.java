package rol_game;

import players.Player;
import rol_game.characters.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

/**
 *This is the main class of Rol game
 * @author : Marc Pons
 * @version: 19/05/2023/A
 * @see <a href ="https://pagina.com" />
 */
public class Rol {
    // JPANEL
    private JPanel principalPanel;
    private JPanel panelCenter;
    private JPanel panelHeader;
    private JPanel panelFooter;

    // JLABEL
    private JLabel[] jLabelLifes;
    private JLabel labelGold;
    private JLabel textGold;
    private JLabel heartLabelForBox;
    private JLabel backroundLabel;
    private JLabel backgroundFooterAndHeader;
    private JLabel labelCharacter;
    private JLabel labelSword;
    private JLabel labelMitra;
    private JLabel labelTime;
    private JLabel labelBoss;

    // ICON
    private Icon iconGold;
    private ImageIcon iconCharacter;

    // STRING
    private String name;
    private final Characters[] CHARACTERS;
    private String characterType;
    private String[] charactersToPlay;


    // CLASS
    private Skeleton verticalSkeleton;
    private Skeleton verticalSkeleton1;
    private Skeleton horiztontalSkeleton;
    private Skeleton horiztontalSkeleton1;

    // RECTANGLE
    private Rectangle charactersBox;
    private Rectangle enemyBox;
    private Rectangle enemyBox1;
    private Rectangle horitzontalEnemyBox;
    private Rectangle horitzontalEnemyBox1;

    // INT
    private int selectedCharacter;
    private int timesCharacterIsGoingRight = 0;
    private int timesCharacterIsGoingLeft = 0;
    private int timesCharacterIsGoingUp = 0;
    private int timesCharacterIsGoingDown = 0;
    private int v;
    private int life;
    private int gold;
    private int seconds = 0;
    private int puntuation = 0;
//    private int idUser = 0;

    // BOOLEAN
    private boolean header = false;
    private boolean characterIsDead = false;
    private boolean warriorHaveSword = false;
    private boolean priestHaveMitra = false;
    private boolean isWarrior = false;
    private boolean isMagician = false;
    private boolean isPriest = false;
    private boolean characterFightWithBoss = false;
    private boolean bossIsDead = false;

    //TIMERS
    Timer skeletonMovement;
    Timer startT;

    // GAME
    Player p1 = new Player();
    public Rol() {

        // DIMENSIONS
        principalPanel.setPreferredSize(new Dimension(900, 700));
        principalPanel.setSize(900, 700);
        principalPanel.setLayout(null);
        principalPanel.setFocusable(true);

        // MAKE THE PANELS HEADER FOOTER AND CENTER
        showPanelHeader();
        showPanelFooter();
        showPanelCenter();

        // INIZIALIZE THE CHARACTERS
        CHARACTERS = new Characters[3];
        showTheSelectCharacters();
        iconCharacter = showCasesCharacters();
        showCharacter(iconCharacter);


        // MAKE THE LABELS FOR GOLD , LIFE , SWORD AND MITRA, ...
        showGold();
        showIconsGoldAndLife();
        putALenghtForJLabelLifesArray();
        makeJLabelLifes();
        showSword();
        showMitra();
        showObjectsInARandomValue();
        startT = new Timer(1000, new TimerActionListener());
        startT.start();
        showTimerLabel();
        showNameLabel();

        // MAKE THE BACKGROUND IMAGE AND THE BACKGROUND HEADER/FOOTER
        makeBackgroundImage();
        showBackgroundHeader();
        showBackgroundFooter();

        // SHOW THE LABELS FOR THE ENEMIES
        showEnemies();

        // SHOW THE CHARACTER MOVEMENT AND THE SKELETON MOVEMENT
        skeletonMovement = new Timer(50, new EnemyMovement());
        skeletonMovement.start();

        principalPanel.addKeyListener(new CharactersMovement());

    }

    // MAKE THE PANELS HEADER FOOTER AND CENTER
    private void showPanelHeader() {
        panelHeader = new JPanel();
        panelHeader.setLayout(null);
        panelHeader.setLocation(0, 0);
        panelHeader.setSize(principalPanel.getWidth(), 40);
        principalPanel.add(panelHeader);

    }
    private void showPanelFooter() {
        panelFooter = new JPanel();
        panelFooter.setLayout(null);
        panelFooter.setSize(principalPanel.getWidth(), 40);
        panelFooter.setLocation(0, principalPanel.getHeight() - (panelHeader.getHeight() ) );
        principalPanel.add(panelFooter);
    }
    private void showPanelCenter() {
        panelCenter = new JPanel();
        panelCenter.setLayout(null);
        panelCenter.setLocation(0, panelHeader.getHeight());
        panelCenter.setSize(principalPanel.getWidth(), principalPanel.getHeight() - panelHeader.getHeight());
        principalPanel.add(panelCenter);

    }

    // INIZIALIZE THE CHARACTERS
    private ImageIcon showCasesCharacters() {
        ImageIcon icon = null;
        labelCharacter = new JLabel();
        labelCharacter.setSize(64, 64);
        switch (selectedCharacter) {
            case 0 -> {
                 icon = switchCaseWarrior(name);
            }
            case 1 -> {
                icon = switchCaseMagician(name);
            }
            case 2 -> {
                 icon = switchCasePriest(name);
            }
        }
        return icon;

    }
    private void showCharacter(ImageIcon icon) {
        ImageIcon imageIcon = icon;
        icon = new ImageIcon(
                imageIcon.getImage().getScaledInstance(labelCharacter.getWidth(), labelCharacter.getHeight(), Image.SCALE_DEFAULT)
        );
        labelCharacter.setIcon(icon);

        labelCharacter.setLocation(0, 0);
        panelCenter.add(labelCharacter, 0);
    }
    private void showTheSelectCharacters() {
        charactersToPlay = new String[3];
        charactersToPlay[0] = "Warrior";
        charactersToPlay[1] = "Magician";
        charactersToPlay[2] = "Priest";
        selectedCharacter = JOptionPane.showOptionDialog(null,
                "Select Character",
                "World Of Warcraft",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null,
                charactersToPlay, charactersToPlay[0]);
        name = putNameOnCharacter();
    }
    private ImageIcon switchCasePriest(String name) {
        characterType = "Priest";
        isPriest = true;
        v = 5;
        life = 4;
        CHARACTERS[selectedCharacter] = new Priest(name, life, 0, v, 0, 0, labelCharacter);
        return new ImageIcon(CHARACTERS[selectedCharacter].showCharacterDown());
    }
    private ImageIcon switchCaseMagician(String name) {
        characterType = "Magician";
        isMagician = true;
        v = 7;
        life = 3;
        CHARACTERS[selectedCharacter] = new Magician(name, life, 0, v, 0, 0, labelCharacter);
        return new ImageIcon(CHARACTERS[selectedCharacter].showCharacterDown());
    }
    private ImageIcon switchCaseWarrior(String name) {
        characterType = "Warrior";
        isWarrior = true;
        v = 3;
        life = 5;
        CHARACTERS[selectedCharacter] = new Warrior(name, life, 0, v, 0, 0, labelCharacter);
        return new ImageIcon(CHARACTERS[selectedCharacter].showCharacterDown());
    }
    private String putNameOnCharacter() {
        return JOptionPane.showInputDialog(null, "Put your character name");
    }

    // MAKE THE LABELS FOR GOLD AND LIFE
    private void showGold() {
        labelGold = new JLabel();
        labelGold.setSize(32, 32);
        iconGold = makeIconGold();
        labelGold.setIcon(iconGold);
        panelCenter.add(labelGold, 0);
    }
    private void showIconsGoldAndLife() {
        // ICON WITH TEXT ICON
        JLabel iconWithText = new JLabel();
        iconWithText.setSize(labelGold.getWidth(), labelGold.getHeight());
        iconWithText.setLocation(panelHeader.getWidth() - iconWithText.getWidth(), 0);
        Icon icon = makeIconGold();
        iconWithText.setIcon(icon);
        panelHeader.add(iconWithText);

        // TEXT GOLD
        textGold = new JLabel();
        textGold.setSize(labelGold.getWidth(), labelGold.getHeight());
        textGold.setLocation(iconWithText.getX() - 50, 0);
        textGold.setForeground(Color.yellow);
        textGold.setText("X");
        panelHeader.add(textGold);

        // LABEL LIFE
        heartLabelForBox = new JLabel();
        heartLabelForBox.setSize(32, 32);
        makeIconDropFromSkeletonHearth();
        panelCenter.add(heartLabelForBox);
    }
    private Icon makeIconGold() {
        ImageIcon imageIcon = new ImageIcon("src/images/dungeon/dollar.png");
        return new ImageIcon(
                imageIcon.getImage().getScaledInstance(labelGold.getWidth(), labelGold.getHeight(), Image.SCALE_DEFAULT)
        );
    }
    private void putALenghtForJLabelLifesArray() {
        jLabelLifes = new JLabel[CHARACTERS[selectedCharacter].getLifes()];
        for (int i = 0; i < CHARACTERS[selectedCharacter].getLifes(); i++) {
            jLabelLifes[i] = new JLabel();
        }
    }
    private void makeJLabelLifes(){

        for (int j = 0; j < jLabelLifes.length; j++) {
            jLabelLifes[j].setSize(30, 20);
            jLabelLifes[j].setLocation(jLabelLifes[j].getWidth() * j, jLabelLifes[j].getHeight() /2);
            ImageIcon icon2 = new ImageIcon("src/images/dungeon/heart.png");
            Icon heart = new ImageIcon(
                    icon2.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)
            );
            jLabelLifes[j].setIcon(heart);
            panelHeader.add(jLabelLifes[j]);
        }
    }
    private void showSword(){
        labelSword = showIconsMitraAndSword();
        ImageIcon imageIcon = new ImageIcon("src/images/dungeon/sword.png");
        Icon swordIcon =  new ImageIcon(
                imageIcon.getImage().getScaledInstance(labelSword.getWidth(), labelSword.getHeight(), Image.SCALE_DEFAULT)
        );
        labelSword.setIcon(swordIcon);
        panelCenter.add(labelSword, 0);
    }
    private void showMitra() {
        labelMitra = showIconsMitraAndSword();
        ImageIcon imageIcon = new ImageIcon("src/images/dungeon/mitra.png");
        Icon mitraIcon = new ImageIcon(
                imageIcon.getImage().getScaledInstance(labelMitra.getWidth(), labelMitra.getHeight(), Image.SCALE_DEFAULT)
        );
        labelMitra.setIcon(mitraIcon);
        panelCenter.add(labelMitra, 0);
    }
    private JLabel showIconsMitraAndSword() {
        JLabel label = new JLabel();
        label.setSize(35, 35);
        return label;
    }
    private void showObjectsInARandomValue() {
        Random randomGold = new Random();
        labelGold.setLocation(randomGold.nextInt(0, panelCenter.getWidth() - labelGold.getWidth()), randomGold.nextInt(0, panelCenter.getHeight() - ((panelFooter.getHeight() + panelHeader.getHeight()) * 2)));
        labelSword.setLocation(randomGold.nextInt(0, panelCenter.getWidth() - labelSword.getWidth()), randomGold.nextInt(0, panelCenter.getHeight() - ((panelFooter.getHeight() + panelHeader.getHeight()) * 2)));
        labelMitra.setLocation(randomGold.nextInt(0, panelCenter.getWidth() - labelMitra.getWidth()), randomGold.nextInt(0, panelCenter.getHeight() - ((panelFooter.getHeight() + panelHeader.getHeight()) * 2)));
    }
    private void showTimerLabel(){
        labelTime = new JLabel();
        labelTime.setSize(70, 45);
        labelTime.setText("0 Sec");
        labelTime.setForeground(Color.white);
        labelTime.setLocation(panelHeader.getWidth() /2 - labelTime.getWidth(), 0);
        panelHeader.add(labelTime);
    }
    private class TimerActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!(characterIsDead)) {
                seconds++;
                labelTime.setText(seconds + " Sec");
            }
        }
    }
    private void showNameLabel(){
        JLabel labelCharName = new JLabel();
        labelCharName.setSize(115, 45);
        labelCharName.setText("name: " + name);
        labelCharName.setForeground(Color.white);
        labelCharName.setLocation(labelTime.getX() - (labelCharName.getWidth()*2), 0);
        panelHeader.add(labelCharName);
    }

    // MAKE THE BACKGROUND IMAGE AND THE BACKGROUND HEADER/FOOTER
    private void makeBackgroundImage() {
        for (int j = 1; j < panelCenter.getHeight(); j += backroundLabel.getHeight()) {
            for (int i = 1; i < panelCenter.getWidth(); i += backroundLabel.getWidth()) {
                backroundLabel = new JLabel();
                backroundLabel.setSize(32, 32);

                ImageIcon icon = new ImageIcon("src/images/dungeon/tile001.png");
                Icon ground = new ImageIcon(
                        icon.getImage().getScaledInstance(backroundLabel.getWidth(), backroundLabel.getHeight(), Image.SCALE_DEFAULT)
                );

                backroundLabel.setIcon(ground);
                backroundLabel.setLocation(panelHeader.getWidth() - backroundLabel.getWidth() - i, backroundLabel.getY() - panelHeader.getHeight() + j);
                panelCenter.add(backroundLabel);

            }
        }
    }
    private void showBackgroundHeader() {
        header = true;
        for (int i = 1; i < panelHeader.getWidth(); i += backgroundFooterAndHeader.getWidth()) {
            makeBackgroundHeaderAndFooter(i);
        }
    }
    private void showBackgroundFooter() {
        header = false;

            for (int i = 1; i < panelCenter.getWidth(); i += backgroundFooterAndHeader.getWidth()) {

                makeBackgroundHeaderAndFooter(i);
            }


    }
    private void makeBackgroundHeaderAndFooter(int i) {
        backgroundFooterAndHeader = new JLabel();
        backgroundFooterAndHeader.setSize(32, 50);
        ImageIcon icon = new ImageIcon("src/images/dungeon/tile004.png");
        Icon ground = new ImageIcon(
                icon.getImage().getScaledInstance(backgroundFooterAndHeader.getWidth(), backgroundFooterAndHeader.getHeight(), Image.SCALE_DEFAULT)
        );
        backgroundFooterAndHeader.setIcon(ground);

        backgroundFooterAndHeader.setLocation(panelHeader.getWidth() - backgroundFooterAndHeader.getWidth() - i, panelHeader.getHeight() - backgroundFooterAndHeader.getHeight());

        if (header) {
            panelHeader.add(backgroundFooterAndHeader);
        } else {
            panelFooter.add(backgroundFooterAndHeader);
        }
    }
    private void showExitLabel() {
        JLabel exitLabel = new JLabel();
        exitLabel.setSize(64, 64);
        Icon door = showDoorImageIcon(exitLabel);
        exitLabel.setIcon(door);
        exitLabel.setLocation(panelCenter.getWidth() - exitLabel.getWidth(), panelCenter.getHeight() - panelCenter.getHeight());
        panelCenter.add(exitLabel, 0);

        // TO SHOW THE OPENED DOOR WHEN THE GOLD IS BIGGER THAN 50
        if (gold >= 50) {
            exitLabel.setVisible(false);
            JLabel exitLabel2 = new JLabel();
            exitLabel2.setSize(64, 64);
            Icon openedDoor = showOpenedDoor(exitLabel2);
            exitLabel2.setIcon(openedDoor);
            exitLabel2.setLocation(panelCenter.getWidth() - exitLabel2.getWidth(), panelCenter.getHeight() - panelCenter.getHeight());
            panelCenter.add(exitLabel2, 0);
            Rectangle doorBox = exitLabel2.getBounds();
            makeIntersectionBetweenDoorAndCharacter(doorBox);
        }
    }
    private void makeIntersectionBetweenDoorAndCharacter(Rectangle doorBox) {
        if (charactersBox.intersects(doorBox) || doorBox.intersects(charactersBox)) {
            startT.stop();
            skeletonMovement.stop();
            int wouldYouFight = JOptionPane.showConfirmDialog(null, "¿Would you fight the King Skeleton (Min 50 gold)?", null, JOptionPane.YES_NO_OPTION);
            if (wouldYouFight == JOptionPane.YES_OPTION) {
                // Mandar a panel de boss
                showLabelBoss();
                characterFightWithBoss = true;
            }
            skeletonMovement.start();
            startT.start();
            labelCharacter.setLocation(0, 0);
        }
    }
    private Icon showOpenedDoor(JLabel exitLabel) {
        ImageIcon door = new ImageIcon("src/images/dungeon/openedDoor.png");
        return new ImageIcon(
                door.getImage().getScaledInstance(exitLabel.getWidth(), exitLabel.getHeight(), Image.SCALE_DEFAULT)
        );
    }
    private Icon showDoorImageIcon(JLabel exitlabel) {
        ImageIcon door = new ImageIcon("src/images/dungeon/closedDoor.png");
        return new ImageIcon(
                door.getImage().getScaledInstance(exitlabel.getWidth(), exitlabel.getHeight(), Image.SCALE_DEFAULT)
        );
    }
    private void showLabelBoss(){
        labelBoss = new JLabel();
        labelBoss.setSize(150, 150);
        ImageIcon icon = new ImageIcon("src/images/skeleton/kingSkeleton.gif");
        Icon icon1 = new ImageIcon(
                icon.getImage().getScaledInstance(labelBoss.getWidth(), labelBoss.getHeight(), Image.SCALE_DEFAULT)
        );
        labelBoss.setLocation(panelCenter.getWidth() /2 - labelBoss.getWidth() , panelCenter.getHeight() /2 - labelBoss.getHeight());
        labelBoss.setIcon(icon1);
        panelCenter.add(labelBoss, 0);

    }

    // SHOW THE LABELS FOR THE ENEMIES
    private void showEnemies() {

        makeTheSkeletons();

        makeSizeLabelSkeletons();

        makeIconsSkeletons();

        makeSkeletonSetLocation();

    }
    private void makeTheSkeletons() {
        verticalSkeleton = new Skeleton( new JLabel());
        verticalSkeleton1 = new Skeleton( new JLabel());
        horiztontalSkeleton = new Skeleton( new JLabel());
        horiztontalSkeleton1 = new Skeleton(new JLabel());
    }
    private void makeSizeLabelSkeletons() {
        verticalSkeleton.getENEMYLABEL().setSize(64, 64);
        verticalSkeleton1.getENEMYLABEL1().setSize(64, 64);
        horiztontalSkeleton.getHoritzontalENEMYLABEL().setSize(64, 64);
        horiztontalSkeleton1.getHoritzontalENEMYLABEL1().setSize(64, 64);
    }
    private void makeIconsSkeletons() {
        ImageIcon imageIcon = new ImageIcon(verticalSkeleton.showSkeletonDown());
        ImageIcon imageIcon1 = new ImageIcon(verticalSkeleton1.showSkeletonDown());
        ImageIcon imageIcon3 = new ImageIcon(horiztontalSkeleton.showSkeletonRight());
        ImageIcon imageIcon4 = new ImageIcon(horiztontalSkeleton1.showSkeletonLeft());

        Icon icon = new ImageIcon(
                imageIcon.getImage().getScaledInstance(verticalSkeleton.getENEMYLABEL().getWidth(), verticalSkeleton.getENEMYLABEL().getHeight(), Image.SCALE_DEFAULT)
        );
        Icon icon1 = new ImageIcon(
                imageIcon1.getImage().getScaledInstance(verticalSkeleton1.getENEMYLABEL1().getWidth(), verticalSkeleton1.getENEMYLABEL1().getHeight(), Image.SCALE_DEFAULT)
        );

        Icon icon3 = new ImageIcon(
                imageIcon3.getImage().getScaledInstance(horiztontalSkeleton.getHoritzontalENEMYLABEL().getWidth(), horiztontalSkeleton.getHoritzontalENEMYLABEL().getHeight(), Image.SCALE_DEFAULT)
        );
        Icon icon4 = new ImageIcon(
                imageIcon4.getImage().getScaledInstance(horiztontalSkeleton1.getHoritzontalENEMYLABEL1().getWidth(), horiztontalSkeleton1.getHoritzontalENEMYLABEL1().getHeight(), Image.SCALE_DEFAULT)
        );

        verticalSkeleton.getENEMYLABEL().setIcon(icon);
        verticalSkeleton1.getENEMYLABEL1().setIcon(icon1);
        horiztontalSkeleton.getHoritzontalENEMYLABEL().setIcon(icon3);
        horiztontalSkeleton1.getHoritzontalENEMYLABEL1().setIcon(icon4);
    }
    private void makeSkeletonSetLocation() {
        Random randomEnemy = new Random();
        verticalSkeleton.getENEMYLABEL().setLocation(randomEnemy.nextInt(0, panelCenter.getWidth() - verticalSkeleton.getENEMYLABEL().getWidth()), randomEnemy.nextInt(0, panelCenter.getHeight() - (panelFooter.getHeight() + panelHeader.getHeight()) * 2));
        panelCenter.add(verticalSkeleton.getENEMYLABEL(), 0);

        verticalSkeleton1.getENEMYLABEL1().setLocation(randomEnemy.nextInt(0, panelCenter.getWidth() - verticalSkeleton1.getENEMYLABEL1().getWidth()), randomEnemy.nextInt(0, panelCenter.getHeight() - (panelFooter.getHeight() + panelHeader.getHeight()) * 2));
        panelCenter.add(verticalSkeleton1.getENEMYLABEL1(), 0);

        horiztontalSkeleton.getHoritzontalENEMYLABEL().setLocation(randomEnemy.nextInt(0, panelCenter.getWidth() - horiztontalSkeleton.getHoritzontalENEMYLABEL().getWidth()), randomEnemy.nextInt(0, panelCenter.getHeight() - (panelFooter.getHeight() + panelHeader.getHeight()) * 2));
        panelCenter.add(horiztontalSkeleton.getHoritzontalENEMYLABEL(), 0);

        horiztontalSkeleton1.getENEMYLABEL1().setLocation(randomEnemy.nextInt(0, panelCenter.getWidth() - horiztontalSkeleton1.getHoritzontalENEMYLABEL1().getWidth()), randomEnemy.nextInt(0, panelCenter.getHeight() - (panelFooter.getHeight() + panelHeader.getHeight()) * 2));
        panelCenter.add(horiztontalSkeleton1.getHoritzontalENEMYLABEL1(), 0);

    }
    private void whenHorizontalEnemyHitsTheCharacter() {
        Random randomEnemy = new Random();
        showAllEnemiesAndCharactersBox();
        showIntersectionsBetweenCharacterAndEnemy(enemyBox, charactersBox, enemyBox1, verticalSkeleton1);
        if (life > 0) {
            if (horitzontalEnemyBox.intersects(charactersBox)) {
                labelGold.setLocation(horiztontalSkeleton.getENEMYLABEL().getX(), horiztontalSkeleton.getENEMYLABEL().getY());
                warriorHaveSwordPriestHaveMitra();
                horiztontalSkeleton.getHoritzontalENEMYLABEL().setLocation(randomEnemy.nextInt(0, panelCenter.getWidth() - horiztontalSkeleton.getHoritzontalENEMYLABEL().getWidth()), randomEnemy.nextInt(0, panelCenter.getHeight() - ((panelFooter.getHeight() + panelHeader.getHeight()) * 2)));
            }
            if (horitzontalEnemyBox1.intersects(charactersBox)) {
                warriorHaveSwordPriestHaveMitra();
                horiztontalSkeleton1.getHoritzontalENEMYLABEL1().setLocation(randomEnemy.nextInt(0, panelCenter.getWidth() - horiztontalSkeleton1.getHoritzontalENEMYLABEL1().getWidth()), randomEnemy.nextInt(0, panelCenter.getHeight() - ((panelFooter.getHeight() + panelHeader.getHeight()) * 2)));
            }
            puntuation++;
        }
    }
    private void warriorHaveSwordPriestHaveMitra() {
        if (warriorHaveSword) {
            labelCharacter.setLocation(0, 0);
            warriorHaveSword = false;
            labelSword.setVisible(false);
            puntuation++;
        }else if (priestHaveMitra) {
            labelCharacter.setLocation(0, 0);
            priestHaveMitra = false;
            labelMitra.setVisible(false);
            puntuation++;
        }
        else {
            life--;
            jLabelLifes[life].setVisible(false);
        }
    }
    private void showAllEnemiesAndCharactersBox() {
        enemyBox = verticalSkeleton.getENEMYLABEL().getBounds();
        enemyBox1 = verticalSkeleton1.getENEMYLABEL1().getBounds();
        horitzontalEnemyBox = horiztontalSkeleton.getHoritzontalENEMYLABEL().getBounds();
        horitzontalEnemyBox1 = horiztontalSkeleton1.getHoritzontalENEMYLABEL1().getBounds();
        charactersBox = labelCharacter.getBounds();
    }

    /**
     * Character movement class
     */
    private class CharactersMovement extends KeyAdapter {
        /**
         * Summoned when a key is touch.
         *
         * @param e The key event associated.
         */
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);

            if ((!characterIsDead) && (!bossIsDead)) {
                showAllEnemiesAndCharactersBox();
                int x = labelCharacter.getX();
                int y = labelCharacter.getY();
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT -> {
                        x = CHARACTERS[selectedCharacter].characterMoveRight(x, timesCharacterIsGoingRight);
                        timesCharacterIsGoingRight++;
                        timesCharacterIsGoingLeft = 0;
                        timesCharacterIsGoingUp = 0;
                        timesCharacterIsGoingDown = 0;
                    }
                    case KeyEvent.VK_LEFT -> {
                        x = CHARACTERS[selectedCharacter].characterMoveLeft(x, timesCharacterIsGoingLeft);
                        timesCharacterIsGoingLeft++;
                        timesCharacterIsGoingRight = 0;
                        timesCharacterIsGoingUp = 0;
                        timesCharacterIsGoingDown = 0;
                    }
                    case KeyEvent.VK_UP -> {
                        y = CHARACTERS[selectedCharacter].characterMoveUp(y, timesCharacterIsGoingUp);
                        timesCharacterIsGoingUp++;
                        timesCharacterIsGoingDown = 0;
                        timesCharacterIsGoingLeft = 0;
                        timesCharacterIsGoingRight = 0;
                    }

                    case KeyEvent.VK_DOWN -> {
                        y = CHARACTERS[selectedCharacter].characterMoveDown(y, timesCharacterIsGoingDown);
                        timesCharacterIsGoingDown++;
                        timesCharacterIsGoingUp = 0;
                        timesCharacterIsGoingLeft = 0;
                        timesCharacterIsGoingRight = 0;
                    }
                    case KeyEvent.VK_P -> {
                        skeletonMovement.stop();
                        startT.stop();
                        ImageIcon icon = new ImageIcon("src/images/pause.png");
                        Icon pause = new ImageIcon(
                                icon.getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT)
                        );
                        JOptionPane.showMessageDialog(null, "You have to collect 50 gold to open the door and kill the King Skeleton", "Pause", JOptionPane.INFORMATION_MESSAGE, pause);
                        skeletonMovement.start();
                        startT.start();

                    }
                }
                CHARACTERS[selectedCharacter].setX(x);
                CHARACTERS[selectedCharacter].setY(y);
                makeTheCharacterDontTouchTheWallsAndHeaderFooter(x, y);
                showIntersectionsBetweenCharacterAndEnemy(charactersBox, enemyBox, enemyBox1, verticalSkeleton);
                characterIntersectWithGoldSwordAndMitra();
                deathCondition();
                showExitLabel();
            } else {
                JOptionPane.showMessageDialog(null, "The warrior " + name + " has killed the King Skeleton and save de princess, he become a hero and a new King.");
                showRankingsAndMysqlConection();
            }
        }
    }
    /**
     * Method to restring the map
     * @param x represents the actual position x.
     * @param y represents the actual position y.
     */
    private void makeTheCharacterDontTouchTheWallsAndHeaderFooter(int x, int y) {
        if (x >= 0 && x <= principalPanel.getWidth() - labelCharacter.getWidth() && y >= 0 && y <= panelFooter.getY() - panelFooter.getSize().height - labelCharacter.getHeight()) {
            labelCharacter.setLocation(x, y);
        }
    }
    /**
     * Show the skeleton intersection with the character,
     * and make some actions.
     *
     * @param enemyBox        Rectangle that represent the enemy area.
     * @param charactersBox   Rectangle that represent the character area.
     * @param enemyBox1       Additional rectangle that represent the enemy area.
     * @param verticalSkeleton1 Vertical skeleton used in game.
     */
    private void showIntersectionsBetweenCharacterAndEnemy(Rectangle enemyBox, Rectangle charactersBox, Rectangle enemyBox1, Skeleton verticalSkeleton1) {
        Random randomEnemy = new Random();
        if (life > 0) {
            if (enemyBox.intersects(charactersBox)) {
                warriorHaveSwordPriestHaveMitra();
                verticalSkeleton.getENEMYLABEL().setLocation(randomEnemy.nextInt(0, panelCenter.getWidth() - verticalSkeleton.getENEMYLABEL().getWidth()), randomEnemy.nextInt(0, panelCenter.getHeight() - ((panelFooter.getHeight() + panelHeader.getHeight()) * 2)));
            }
            if (enemyBox1.intersects(charactersBox)) {
                labelGold.setLocation(verticalSkeleton1.getENEMYLABEL1().getX(), verticalSkeleton1.getENEMYLABEL1().getY());
                warriorHaveSwordPriestHaveMitra();
                verticalSkeleton1.getENEMYLABEL1().setLocation(randomEnemy.nextInt(0, panelCenter.getWidth() - verticalSkeleton1.getENEMYLABEL1().getWidth()), randomEnemy.nextInt(0, panelCenter.getHeight() - ((panelFooter.getHeight() + panelHeader.getHeight()) * 2)));
            }

            if (characterFightWithBoss) {
                Rectangle kingSkeletonBox = labelBoss.getBounds();
                if (charactersBox.intersects(kingSkeletonBox)) {
                    if (gold >= 100 && warriorHaveSword) {
                        panelCenter.remove(labelBoss);
                        bossIsDead = true;
                        puntuation += 50;
                    } else {
                        if (life > 0) {
                            life--;
                            jLabelLifes[life].setVisible(false);
                            panelCenter.remove(labelCharacter);
                        }
                    }
                }
            }
            puntuation++;
        }
    }
    private void characterIntersectWithGoldSwordAndMitra() {
        Random random = new Random();
        Rectangle goldBox = labelGold.getBounds();
        Rectangle lifeBox = heartLabelForBox.getBounds();
        Rectangle swordBox = labelSword.getBounds();
        Rectangle mitraBox = labelMitra.getBounds();
        if (charactersBox.intersects(goldBox) || goldBox.intersects(charactersBox)) {
            gold += 10;
            textGold.setText(gold + " X");
            labelGold.setLocation(random.nextInt(0, panelCenter.getWidth() - labelSword.getWidth()), random.nextInt(0, panelCenter.getHeight() - ((panelFooter.getHeight() + panelHeader.getHeight()) * 2)));
        }

        if (charactersBox.intersects(lifeBox) || lifeBox.intersects(charactersBox)) {
            showConditionalCharacterIntersectWithLife();
            heartLabelForBox.setLocation(random.nextInt(0, panelCenter.getWidth() - labelGold.getWidth()), random.nextInt(0, panelCenter.getHeight() - ((panelFooter.getHeight() + panelHeader.getHeight()) * 2)));
        }

        if (charactersBox.intersects(swordBox) ) {
            if (isWarrior) {
                warriorHaveSword = true;
            }
            panelCenter.remove(labelSword);
            panelHeader.add(labelSword);
            labelSword.setLocation(labelTime.getX() + (labelSword.getWidth() * 3), 0);
            panelHeader.add(labelSword, 0);
        }

        if (charactersBox.intersects(mitraBox)) {
            if (isPriest) {
                priestHaveMitra = true;
            }
            panelCenter.remove(labelMitra);
            panelHeader.add(labelMitra);
            labelMitra.setLocation(labelTime.getX() + (labelMitra.getWidth() * 5), 0);
            panelHeader.add(labelMitra, 0);
        }

        // TO SAVE THE CHANGES, AND UPDATE THE PANEL HEADER.
        panelHeader.revalidate();
        panelHeader.repaint();
    }
    private void showConditionalCharacterIntersectWithLife() {
        if (isWarrior) {
            if (life < 5) {
                jLabelLifes[life].setVisible(true);
                life++;
            }
        } else if (isMagician) {
            if (life < 3) {
                jLabelLifes[life].setVisible(true);
                life++;
            }
        } else if (isPriest) {
            if (life < 4) {
                jLabelLifes[life].setVisible(true);
                life++;
            }
        }
    }

    /**
     * Enemy movement class.
     */
    private class EnemyMovement implements ActionListener {
        private boolean touchFooter = false;
        private boolean touchFooter1 = false;
        private boolean touchWall = false;
        private boolean touchWall1 = false;

        /**
         * Summoned when something happen in the timer.
         *
         * @param e The event associated.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if ((!characterIsDead) && (!bossIsDead)) {

                touchFooter = conditionSkeleton(touchFooter);

                touchFooter1 = conditionSkeleton1(touchFooter1);

                touchWall = conditionHoritzontalSkeleton(touchWall);

                touchWall1 = conditionHoritzontalSkeleton1(touchWall1);

            }
            whenHorizontalEnemyHitsTheCharacter();
            characterIntersectWithGoldSwordAndMitra();
        }
    }
    private boolean conditionHoritzontalSkeleton(boolean touchWall) {
        if (!touchWall) {
            if (horiztontalSkeleton.getHoritzontalENEMYLABEL().getX() <= principalPanel.getWidth() - horiztontalSkeleton.getHoritzontalENEMYLABEL().getWidth()) {
                horiztontalSkeleton.enemyDontTouchTheWallCondition();
            } else {
                touchWall = true;
                ImageIcon imageIcon = new ImageIcon(horiztontalSkeleton.showSkeletonLeft());
                Icon icon = changeIcon(imageIcon);
                horiztontalSkeleton.getHoritzontalENEMYLABEL().setIcon(icon);
            }
        }
        if (touchWall) {
            if (horiztontalSkeleton.getHoritzontalENEMYLABEL().getX() > 0) {
                horiztontalSkeleton.enemyTouchTheWallCondition();
            } else {
                touchWall = false;
                ImageIcon imageIcon = new ImageIcon(horiztontalSkeleton.showSkeletonRight());
                Icon icon = changeIcon(imageIcon);
                horiztontalSkeleton.getHoritzontalENEMYLABEL().setIcon(icon);
            }
        }
        return touchWall;
    }
    private boolean conditionHoritzontalSkeleton1(boolean touchWall1) {
        if (!touchWall1) {
            if (horiztontalSkeleton1.getHoritzontalENEMYLABEL1().getX() <= principalPanel.getWidth() - horiztontalSkeleton1.getHoritzontalENEMYLABEL1().getWidth()) {
                horiztontalSkeleton1.enemyDontTouchTheWallCondition1();
            } else {
                touchWall1 = true;
                ImageIcon imageIcon = new ImageIcon(horiztontalSkeleton1.showSkeletonLeft());
                Icon icon = changeIcon(imageIcon);
                horiztontalSkeleton1.getHoritzontalENEMYLABEL1().setIcon(icon);
            }
        }
        if (touchWall1) {
            if (horiztontalSkeleton1.getHoritzontalENEMYLABEL1().getX() > 0) {
                horiztontalSkeleton1.enemyTouchTheWallCondition1();
            } else {
                touchWall1 = false;
                ImageIcon imageIcon = new ImageIcon(horiztontalSkeleton1.showSkeletonRight());
                Icon icon = changeIcon(imageIcon);
                horiztontalSkeleton1.getHoritzontalENEMYLABEL1().setIcon(icon);
            }
        }
        return touchWall1;
    }
    private boolean conditionSkeleton1(boolean touchFooter1) {
        if (!touchFooter1) {
            if (verticalSkeleton1.getENEMYLABEL1().getY() >= 0 && verticalSkeleton1.getENEMYLABEL1().getY() < panelFooter.getY() - panelFooter.getSize().height - verticalSkeleton1.getENEMYLABEL1().getHeight()) {
                verticalSkeleton1.enemyDontTouchTheFooterCondition1();
            } else {
                touchFooter1 = true;
                ImageIcon imageIcon = new ImageIcon(verticalSkeleton1.showSkeletonUp());
                Icon icon = changeIcon(imageIcon);
                verticalSkeleton1.getENEMYLABEL1().setIcon(icon);
            }
        }
        if (touchFooter1) {
            if (verticalSkeleton1.getENEMYLABEL1().getY() > 0 && verticalSkeleton1.getENEMYLABEL1().getY() > panelHeader.getY() - (panelHeader.getSize().height - verticalSkeleton1.getENEMYLABEL1().getHeight())) {
                verticalSkeleton1.enemyTouchTheFooterCondition1();
            } else {
                touchFooter1 = false;
                ImageIcon imageIcon = new ImageIcon(verticalSkeleton1.showSkeletonDown());
                Icon icon = changeIcon(imageIcon);
                verticalSkeleton1.getENEMYLABEL1().setIcon(icon);
            }
        }
        return touchFooter1;
    }
    private boolean conditionSkeleton(boolean touchFooter) {
        if (!touchFooter) {
            if (verticalSkeleton.getENEMYLABEL().getY() >= 0 && verticalSkeleton.getENEMYLABEL().getY() < panelFooter.getY() - panelFooter.getSize().height - verticalSkeleton.getENEMYLABEL().getHeight()) {
                verticalSkeleton.enemyDontTouchTheFooterCondition();
            } else {
                touchFooter = true;
                ImageIcon imageIcon = new ImageIcon(verticalSkeleton.showSkeletonUp());
                Icon icon = changeIcon(imageIcon);
                verticalSkeleton.getENEMYLABEL().setIcon(icon);
            }
        }
        if (touchFooter) {

            if (verticalSkeleton.getENEMYLABEL().getY() > 0 && verticalSkeleton.getENEMYLABEL().getY() > panelHeader.getY() - (panelHeader.getSize().height - verticalSkeleton.getENEMYLABEL().getHeight())) {
                verticalSkeleton.enemyTouchTheFooterCondition();
            } else {
                touchFooter = false;
                ImageIcon imageIcon = new ImageIcon(verticalSkeleton.showSkeletonDown());
                Icon icon = changeIcon(imageIcon);
                verticalSkeleton.getENEMYLABEL().setIcon(icon);
            }
        }
        return touchFooter;
    }
    private void makeIconDropFromSkeletonHearth() {
        ImageIcon icon = new ImageIcon("src/images/dungeon/potion.png");
        Icon icon2 = new ImageIcon(
                icon.getImage().getScaledInstance(heartLabelForBox.getWidth(), heartLabelForBox.getHeight(), Image.SCALE_DEFAULT)
        );
        heartLabelForBox.setIcon(icon2);
    }
    private void deathCondition() {
        if (life == 0) {
            characterIsDead = true;
            JOptionPane.showMessageDialog(null, "You Lose");
            showRankingsAndMysqlConection();
        }
    }
    private Icon changeIcon(ImageIcon icon) {
        return new ImageIcon(
                icon.getImage().getScaledInstance(labelCharacter.getWidth(), labelCharacter.getHeight(), Image.SCALE_DEFAULT)
        );
    }
    public void showRankingsAndMysqlConection(){
        saveResults();
        p1.showRankingsOption(charactersToPlay);
        mysqlConnection();
    }

    /**
     * Summoned when the game is finished, to save the dates.
     */
    public void saveResults() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/resources/game_results.csv", true));
            writer.write("character name: " + name + ", type: " + characterType + ", seconds: " + seconds + ", life: " + life + ", gold: " + gold + " has killed the King Skeleton: " + bossIsDead + " Puntuation: " + puntuation);
            writer.newLine();
            writer.close();
            System.out.println("Successfully saved game data..");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
    private void mysqlConnection(){
        String db_url = "jdbc:mysql://localhost:3306/rolGame";
        String user = "root";
        String passwd = "mysql";
        String query = "select name, type, seconds, hasKilledTheBoss, puntuation from playerRanking";
        String insertQuery = "insert into playerRanking(name, type, seconds, hasKilledTheBoss, puntuation) values (?, ?, ?, ?, ?)";
        try {
            Connection con = DriverManager.getConnection(db_url, user, passwd);
            PreparedStatement ps2 = con.prepareStatement(insertQuery);
            PreparedStatement ps = con.prepareStatement(query);
            ps2.setString(1, name);
            ps2.setString(2, characterType);
            ps2.setInt(3, seconds);
            ps2.setBoolean(4, bossIsDead);
            ps2.setInt(5, puntuation);
            int addRows = ps2.executeUpdate();
            if (addRows > 0) {
                System.out.println("Successfully inserts...");
            }
            ResultSet rs = ps.executeQuery();
            System.out.println(" ------ MYSQL CONNECTIONS ------");
            while (rs.next()){
                System.out.println(
                        rs.getString(1) + " - " +
                        rs.getString(2) + " - " +
                        rs.getInt(3) + " - " +
                        rs.getBoolean(4) + " - " +
                        rs.getInt(5));
            }
            rs.close();
            ps.close();
            ps2.close();
            con.close();
            System.out.println("Successfully database connection..");
        } catch (Exception e){
            System.out.println("Something wen wrong with the connection to database...");
        }
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("World Of Warcraft 2");
        frame.setContentPane(new Rol().principalPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        Toolkit pantalla = Toolkit.getDefaultToolkit();
        Image icon = pantalla.getImage("src/images/wowLogo.png");
        frame.setIconImage(icon);
    }
}
