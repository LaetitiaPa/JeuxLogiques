package com.openclassrooms.game;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private int endGameChoice;
    private int gameChoice;
    private int modeChoice;
    private final List<Integer> gameChoices = Arrays.asList(1, 2);
    private final List<Integer> modeChoices = Arrays.asList(1, 2, 3);
    private final List<Integer> endGameChoices = Arrays.asList(1, 2, 3);

    public void display() {
        while (!gameChoices.contains(this.gameChoice)) {
            this.askGame();
        }

        while (!modeChoices.contains(this.modeChoice)){
            this.askMode();
        }
        this.runGame();
    }

    public void displayEndMenu() {
        while(!endGameChoices.contains(this.endGameChoice)) {
            this.endGameMenu();
        }
    }

    private void askGame() {
        Scanner input = new Scanner(System.in);
        System.out.println("Veuillez choisir un jeu :");
        System.out.println(" 1- Plus ou Moins");
        System.out.println(" 2- Mastermind");
        System.out.println(" 3- Quitter");

        this.gameChoice = input.nextInt();
    }

    private void askMode() {
        Scanner input = new Scanner(System.in);
        System.out.println("Veuillez choisir un mode: ");
        System.out.println("1- Challenger : ");
        System.out.println("2- Défenseur : ");
        System.out.println("3- Duel : ");

        this.modeChoice = input.nextInt();
    }

    private void runGame() {
        if (gameChoice == 1) {
            MoreLess moreLess = new MoreLess(modeChoice, gameChoice);
            moreLess.run();

        } else if (gameChoice == 2) {
            Mastermind mastermind = new Mastermind(modeChoice, gameChoice);
            mastermind.run();

        } else if(gameChoice == 3) {
            this.quit();
        }
    }

    private void quit() {
        System.out.println("Bye bye !");
        System.exit(0);
    }

    public void endGameMenu(){
        Scanner input = new Scanner(System.in);
        System.out.println(" 1- Rejouer ");
        System.out.println(" 2- Changer de jeu");
        System.out.println(" 3- Quitter");

        this.endGameChoice = input.nextInt();
        this.endChoiceMenu();
    }

    public void endChoiceMenu() {
        if(endGameChoice == 1) {
            this.askMode();
        } else if(endGameChoice == 2) {
            this.display();
        } else if(endGameChoice == 3) {
            System.exit(0);
        }
    }
}

