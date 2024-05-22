import rol_game.Rol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    private final JPanel menuPanel;
    private JLabel descriptionPanel;
    private JLabel kingSkeleton;
    private JLabel gameLore;
    private int i = 0;
    private Icon icon2;
    public Menu() {
        menuPanel = new JPanel();
      //  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        menuPanel.setPreferredSize(new Dimension(1300, 900));
        menuPanel.setSize(1300, 900);
        menuPanel.setLayout(null);
        menuPanel.setFocusable(true);
        showDescriptionPanel();
        showBackgroudPanel();
        showKingSkeletonOnScreen();
        showGameLoreLabel();
    }
    private void showBackgroudPanel() {
        JLabel backImage = new JLabel();
        backImage.setSize(menuPanel.getWidth(), menuPanel.getHeight());
        ImageIcon icon1 = new ImageIcon("src/images/dungeon/dungeonBack.jpg");
        icon2 = new ImageIcon(
                icon1.getImage().getScaledInstance(backImage.getWidth(), backImage.getHeight(), Image.SCALE_DEFAULT)
        );
        backImage.setIcon(icon2);
        menuPanel.add(backImage);
    }
    private void showGameLoreLabel() {
        gameLore = new JLabel();
        gameLore.setSize(500,64);
        gameLore.setLocation((descriptionPanel.getWidth() /2 - gameLore.getWidth()/2) , kingSkeleton.getHeight() + gameLore.getHeight());
        Timer showTextOnScreen = new Timer(2000, new TextActionListener());
        showTextOnScreen.start();
        gameLore.setForeground(Color.white);
        descriptionPanel.add(gameLore);
    }
    private class TextActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (i == 0) {
                gameLore.setText("The King Skeleton is waiting for you....");
            }
             else if (i < 4) {
                gameLore.setText("You have to collect 50 Gold , and go to the exit for fight with him and save the pricess.");
            } else if (i < 7) {
                  gameLore.setText("but, be careful because there is an army inside the castle, the King army...");
              } else if (i < 10) {
                gameLore.setText("If you lose your lifes (you can see it at the top left) you lose...");
            } else if (i < 20) {
                 gameLore.setText("*Under this text there is a button*");
            }
            i++;
        }
    }
    private void showKingSkeletonOnScreen() {
        kingSkeleton = new JLabel();
        kingSkeleton.setSize(200,200);
        ImageIcon icon = new ImageIcon("src/images/skeleton/kingSkeleton.gif");
        Icon icon1 = new ImageIcon(
           icon.getImage().getScaledInstance(kingSkeleton.getWidth(), kingSkeleton.getHeight(), Image.SCALE_DEFAULT)
        );
        kingSkeleton.setLocation((descriptionPanel.getWidth() /2 - kingSkeleton.getWidth() - 50) , kingSkeleton.getHeight()/5);
        kingSkeleton.setIcon(icon1);
        descriptionPanel.add(kingSkeleton, 0);
    }
    private void showDescriptionPanel() {
        descriptionPanel = new JLabel();
        descriptionPanel.setPreferredSize(new Dimension(700, 500));
        descriptionPanel.setSize(700, 500);
        descriptionPanel.setIcon(icon2);
        descriptionPanel.setLocation((menuPanel.getWidth() /2 - descriptionPanel.getWidth()  /2), menuPanel.getHeight()/2 - descriptionPanel.getHeight()/2);
        descriptionPanel.setFocusable(true);
        descriptionPanel.setLayout(null);
        menuPanel.add(descriptionPanel);

    }

    public static void main(String[] args) {
        JFrame frame2 = new JFrame("World Of Warcraft 2");
        frame2.setContentPane(new Menu().menuPanel);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.pack();
        frame2.setVisible(true);
        frame2.setLocationRelativeTo(null);
        frame2.setLayout(null);
        Toolkit pantalla = Toolkit.getDefaultToolkit();
        Image icon = pantalla.getImage("src/images/wowLogo.png");
        frame2.setIconImage(icon);
        JButton boton = new JButton("Start game");
        boton.setSize(200, 64);
        boton.setLocation(700 /2 - 64 / 2, 210 + (64 * 5) );
        boton.setVisible(true);
        boton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Cerrar el marco actual
                frame2.dispose();

                // Mostrar un nuevo marco
                 Rol.main(args);
            }
        });
        frame2.getContentPane().add(boton);
    }



}
