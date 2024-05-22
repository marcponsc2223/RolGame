package players;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;

public class Player {
    public Player() {
    }
    public void showRankingsOption(String[] charactersToPlay) {
        String[] ranking = {"Global Ranking", "Type Ranking"};
        int selectedRanking = JOptionPane.showOptionDialog(null,
                "Select Ranking",
                "World Of Warcraft",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null,
                ranking, ranking[0]);
        switch (selectedRanking) {
            case 0 -> {
                showGlobalRanking();
            } case 1 -> {
                int selectedType = JOptionPane.showOptionDialog(null,
                        "Select Category",
                        "World Of Warcraft",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null,
                        charactersToPlay, charactersToPlay[0]);
                if (selectedType == 0) {
                    // WARRIOR
                    showRankingForType(" Warrior, ");
                } else if(selectedType == 1) {
                    //MAGICIAN
                    showRankingForType(" Magician, ");
                } else {
                    // PRIEST
                    showRankingForType(" Priest, ");
                }
            }
        }
    }
    public void showGlobalRanking(){
        Path path = Paths.get("src/resources/game_results.csv");
        try {
            java.util.List<String> lines = Files.readAllLines(path);
            String unificated = String.join("\n" , lines);
            System.out.println(" ------ GLOBAL RANKING ------");
            System.out.println(unificated);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void showRankingForType(String characterType){
        Path path = Paths.get("src/resources/game_results.csv");
        try {
            java.util.List<String> lines = Files.readAllLines(path);
            System.out.println(" ------ RANKING FOR TYPE ------ ");
            for (String line: lines) {
                if (line.contains(characterType)){
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}