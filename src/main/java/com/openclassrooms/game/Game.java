package com.openclassrooms.game;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * La classe Game contient toutes les caractéristiques d'un jeu
 * <p>
 * un jeu est caractérisé par les informations suivantes :
 * <ul>
 * <li>Une combinaison secrète.</li>
 * <li>Une proposition entrée par l'adversaire.</li>
 * <li>Une réponse affichée.</li>
 * <li>Un nombre d'essai.</li>
 * <li>Un choix du jeu</li>
 * <li>Un choix du mode</li>
 * </ul>
 * </p>
 */
abstract class Game {

    /**
     * Le nombre d'essais n'est pas modifiable depuis la classe
     */
    final int MAX_NUM_TRY = 3;

    /**
     * Le choix du jeu. Peut être modifiée
     */
    protected int gameChoice;

    /**
     * La combinaison secrète: Peut être récupérée
     * @see Game#getCombination()
     */
    protected int combination;

    /**
     * La valeur de la proposition: Peut être récupérée
     * @see Game#getCombination()
     */
    protected  int prop;

    /**
     * Tableau contenant la proposition du joueur
     */
    protected String[] proposition;

    /**
     * Tableau contenant la solution du jeu
     */
    protected String[] solution;

    /**
     * valeur de la réponse retournée
     */
    protected String response;

    /**
     * valeur du nombre de tours
     */
    protected int numTry;

    /**
     * La valeur du mode: Peut être récupérée
     * @see Game#getMode()
     */
    protected int mode;

    /**
     * Constructeur Game
     * <p>
     * A la construction d'un objet Game, le "mode" et le jeu du jeu sont attendus en paramètre
     * vide.
     * </p>
     *
     * @param mode
     *            Le mode du jeu choisi
     * @param gameChoice
     *            Le jeu choisi
     */
    public Game(int mode, int gameChoice) {
        this.mode = mode;
        this.gameChoice = gameChoice;
    }

    /**
     *<p>
     *  Affiche le jeu séléctionné selon le mode choisi
     *  Gère les conditions de sorties du jeu
     *  Affiche le message de fin de partie
     *  Affiche le menu de fin
     *</p>
     * @throws GameException si les chiffres saisis sont supérieurs à 4
     */

    public void run() {
        if(getMode() == 1 || getMode() == 3) {
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

            if(this.getMode() == 1) {
                try {
                    challenger();

                } catch (GameException e) {
                    System.out.println(e.toString());
                    continue;
                }
            } else if(this.getMode() == 2) {
                defender();

            } else if(this.getMode() == 3) {
                defender();

                checkProposition();
                displayResponse();
                this.numTry++;

                try {
                    challenger();
                } catch (GameException e) {
                    e.printStackTrace();
                }
            }
                checkProposition();
                displayResponse();
                this.numTry++;
        }
        if(isResolved()) {
            System.out.println("Vous avez gagné !");
        } else {
            System.out.println("Vous avez perdu !");
        }
            Menu.displayEndMenu();
    }

    /**
     * Retourne la combination générée
     *
     * @return La combinaison générée par la méthode ThreadLocalRandom
     */
    public int generateChallengerCombination() {
        combination = ThreadLocalRandom.current().nextInt(1000, 9000);
        String solution_ = Integer.toString(combination);
        solution = solution_.split("");
        return combination;
    }

    /**
     *  Saisi de la combinaison secrète par le joueur
     *  La combinaison entrée devient la solution de la partie en cours
     *
     * @throws GameException si les chiffres saisis sont supérieurs à 4
     */
    public void generateDefenderCombination() throws  GameException {
        System.out.println("Entrez votre combinaison secrète: ");
        Scanner reader = new Scanner(System.in);
        combination = reader.nextInt();
        if (combination >= 10000) {
            throw new GameException("Une combinaison à 4 chiffres est attendue");
        }
        this.solution = Integer.toString(combination).split("");
    }

    /**
     *  Saisi de la proposition par le joueur
     *  La saisie devient la proposition
     *
     * @throws GameException si les chiffres saisis sont supérieurs à 4
     */

    public void challenger() throws GameException  {
        System.out.println("A vous de jouer !");
        System.out.println(this.numTry);
        Scanner reader = new Scanner(System.in);
        int n = reader.nextInt();
        prop = n;
        if (n >= 10000) {
            throw new GameException("Une combinaison à 4 chiffres est attendue");
        }
        this.proposition = Integer.toString(n).split("");
    }

    /**
     *  Génération de la proposition de l'AI via la méthode ThreadLocalRandom
     *  La combinaison générée devient la proposition
     *
     * @throws GameException si les chiffres saisis sont supérieurs à 4
     */
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

    public int getCombination() {
        return combination;
    }

    /**
     * Retourne vrai ou faux selon la valeur de la variable numTry
     *
     * @return vrai si numTry est inférieur à MAX_NUM_TRY, sinon retourne faux
     */
    private boolean checkNumTry() {
        return this.numTry < MAX_NUM_TRY ? true : false;
    }

    /**
     * Retourne vrai ou faux selon les valeurs des méthodes CheckNumTry et isResolved
     *
     * @return vrai si CheckNumTry vaut "Vrai" et isResolved vaut faux, sinon retourne faux
     */
    private boolean isRunning() {
        return checkNumTry() && !isResolved();
    }
    abstract void checkProposition();
    abstract Boolean isResolved();
    abstract void displayResponse();
}
