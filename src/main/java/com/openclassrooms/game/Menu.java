package com.openclassrooms.game;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * La classe Menu définit la structure des différents menus présent dans le programme
 * Elle est également le point d'entrée et permet de lancer une partie Ã  partir de la méthode display()
 * <p>
 * La classe Menu est caractérisée par les informations suivantes :
 * <ul>
 * <li>Un choix de jeu</li>
 * <li>Un choix de mode</li>
 * <li>Un choix de menu de fin de partie</li>
 * </ul>
 * </p>
 */
public class Menu {

    /**
     * Création de l'instance Logger en utilisant la méthode getLogger()
     */
    private final static Logger log = Logger.getLogger(String.valueOf(Menu.class));

    /**
     *
     */
    private static int endGameChoice;

    /**
     *
     */
    public static int gameChoice;

    /**
     *
     */
    private static int modeChoice;

    /**
     *
     */
    private static final List<Integer> gameChoices = Arrays.asList(1, 2, 3);

    /**
     *
     */
    private static final List<Integer> modeChoices = Arrays.asList(1, 2, 3);

    /**
     *
     */
    private static final List<Integer> endGameChoices = Arrays.asList(1, 2, 3);

    /**
     *
     * Affiche le menu des jeux et des modes Ã  partir de la saisie du joueur
     *
     * TANT QUE la valeur saisie n'est pas dans la liste, on affiche le menu correspondant
     *
     * Enfin on appelle la méthode runGame() qui instancie le jeu choisi
     *
     */
    public static void display() {
        while (!gameChoices.contains(gameChoice)) {
            askGame();
        }

        while (!modeChoices.contains(modeChoice)){
            askMode();
        }
            runGame();
    }

    /**
     * TANT QUE la valeur saisie n'est pas dans la liste, on affiche le menu
     *
     */
    public static void displayEndMenu() {
        while(!endGameChoices.contains(endGameChoice)) {
            endGameMenu();
        }
    }

    /**
     * Menu des jeux proposant les possibilités suivantes:
     *
     * 1/ Plus ou Moins
     * 2/ Mastermind
     * 3/ Quitter
     *
     * Stock la saisie du joueur dans la variable gameChoice
     * Affiche le choix
     * @param
     */
    public static void askGame() {
        Scanner input = new Scanner(System.in);
        System.out.println("Veuillez choisir un jeu :");
        System.out.println(" 1- Plus ou Moins");
        System.out.println(" 2- Mastermind");
        System.out.println(" 3- Quitter");

        try {
        	gameChoice = input.nextInt();
        } catch(InputMismatchException inputException) {
            System.out.println("Veuillez saisir un chiffre !");
        }

        switch(gameChoice) {
            case 1:
                System.out.println("Vous avez choisi comme jeu : Recherche +/-");
                break;

            case 2:
                System.out.println("Vous avez choisi comme jeu : Mastermind");
                break;

            case 3:
                System.out.println("Vous avez choisi de quitter le jeu");
                break;
        }
    }

    /**
     * Menu des modes proposant les possibilités suivantes:
     *
     * 1/ Challenger
     * 2/ Défenseur
     * 3/ Duel
     *
     * Stock la saisie du joueur dans la variable modeChoice
     */
    public static void askMode() {
        Scanner input = new Scanner(System.in);
        System.out.println("Veuillez choisir un mode");
        System.out.println("1- Challenger");
        System.out.println("2- Défenseur");
        System.out.println("3- Duel");
        
        try {
            modeChoice = input.nextInt();
        } catch(InputMismatchException inputException) {
            System.out.println("Veuillez saisir un chiffre !");
        }
    }

    /**
     * Instancie le jeu choisi à partir de la classe du jeu
     * Lance la partie en utilisant la méthode run() de la classe Game
     *
     * 1/ Instancie la classe MoreLess
     * 2/ Instancie la classe Mastermind
     * 3/ La méthode quit() est exécutée
     */
    private static void runGame() {
        if (gameChoice == 1) {
            MoreLess moreLess = new MoreLess(modeChoice, gameChoice);
            moreLess.run();

        } else if (gameChoice == 2) {
            Mastermind mastermind = new Mastermind(modeChoice, gameChoice);
            mastermind.run();

        } else if(gameChoice == 3) {
            quit();
        }
    }

    /**
     * Utilise la méthode exit() afin d'interrompre le programme
     */
    private static void quit() {
        log.info("Fin du jeu");
        System.out.println("Bye bye !");
        System.exit(0);
    }

    /**
     * Menu de fin de partie proposant les choix suivantes:
     *
     * 1/ Rejouer
     * 2/ Changer de jeu
     * 3/ Quitter
     *
     * Stock la saisie du joueur dans la variable endGameChoice
     */
    public static void endGameMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println(" 1- Rejouer");
        System.out.println(" 2- Changer de jeu");
        System.out.println(" 3- Quitter");

        try {
            endGameChoice = input.nextInt();
        } catch(InputMismatchException inputException) {
            System.out.println("Veuillez saisir un chiffre !");
        }
        endChoiceMenu();
    }

    /**
     * Lance les méthodes correspondant au choix saisi dans la méthode endGameMenu()
     */
    public static void endChoiceMenu() {
        if(endGameChoice == 1) {
            runGame();
        } else if(endGameChoice == 2) {
            askGame();
            askMode();
            runGame();
        } else if(endGameChoice == 3) {
            System.exit(0);
        }
    }
}