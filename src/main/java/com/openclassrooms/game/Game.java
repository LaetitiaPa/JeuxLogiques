package com.openclassrooms.game;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

abstract class Game {
    final int MAX_NUM_TRY = 3;
    protected int gameChoice;
    protected int combination;
    protected  int prop;
    protected String[] proposition;
    protected String[] solution;
    protected String response;
    protected int numTry;
    protected int mode;

    public Game(int mode, int gameChoice) {
        this.mode = mode;
        this.gameChoice = gameChoice;
    }

    public void run() {
        if(getMode() == 1) {
            generateChallengerCombination();
        }else if(getMode() == 2) {
            try {
                generateDefenderCombination();
            } catch (GameException e) {
                System.out.println(e.toString());
            }
        }
       if ("true".equals(Config.getValue("cheatmode"))) {
           System.out.println(getCombination());
       }
        while (isRunning()) {
            this.response = "";

            if(this.gameChoice == 1) {
                try {
                    challenger();

                } catch (GameException e) {
                    System.out.println(e.toString());
                    continue;
                }
            } else if(this.gameChoice == 2) {
                defender();
            }
                checkProposition();
                displayResponse();
                this.numTry++;
    }

        if(isResolved())
            System.out.println("Vous avez gagné !");
        else
            System.out.println("Vous avez perdu !");

        // not good, find an other way
        Menu endMenu = new Menu();
        endMenu.displayEndMenu();
    }

    public int generateChallengerCombination() {
        combination = ThreadLocalRandom.current().nextInt(1000, 9000);
        String solution_ = Integer.toString(combination);
        solution = solution_.split("");
        return combination;
    }

    public void generateDefenderCombination() throws  GameException {
        System.out.println("Entrez votre combinaison secrète: ");
        Scanner reader = new Scanner(System.in);
        combination = reader.nextInt();
        if (combination >= 10000) {
            throw new GameException("Une combinaison à 4 chiffres est attendue");
        }
        this.solution = Integer.toString(combination).split("");
    }

    public void challenger() throws GameException  {
        System.out.println("A vous de jouer !");
        Scanner reader = new Scanner(System.in);
        int n = reader.nextInt();
        prop = n;
        if (n >= 10000) {
            throw new GameException("Une combinaison à 4 chiffres est attendue");
        }
        this.proposition = Integer.toString(n).split("");
    }

    public void defender() {
        prop = ThreadLocalRandom.current().nextInt(1000, 9000);
        String prop_ = Integer.toString(prop);
        proposition = prop_.split("");
        System.out.println(prop);
    }

    public int getProp() {
        return prop;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getCombination() {
        return combination;
    }

    public String[] getProposition() {
        return proposition;
    }

    public String getResponse() {
        return response;
    }

    public int getNumTry() {
        return numTry;
    }

    public void setProposition(String[] proposition) {

        this.proposition = proposition;
    }

    public void setCombination(int combination) {
        this.combination = combination;
    }

    protected void setResponse(String response) {
        this.response = response;
    }

    private void setNumTry(int numTry) {
        this.numTry = numTry;
    }

    private boolean checkNumTry() {
        return this.numTry < MAX_NUM_TRY ? true : false;
    }

    private boolean isRunning() {
        return checkNumTry() && isResolved() == false;
    }


    abstract void checkProposition();
    abstract Boolean isResolved();
    abstract void displayResponse();
}
