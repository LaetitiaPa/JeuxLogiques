package com.openclassrooms.game;

import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Integer.parseInt;

/**
 * La classe Game contient toutes les caractéristiques d'un jeu
 * <p>
 * un jeu se caractérise par les informations suivantes :
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
     * Création de l'instance Logger en utilisant la méthode getLogger()
     */
    private final static Logger log = Logger.getLogger(String.valueOf(Game.class));

    /**
     * Le choix du jeu. Peut être récupérée
     */
    protected int gameChoice;

    /**
     * La combinaison secrète: Peut être récupérée
     * @see Game#getCombination()
     */
    protected int combination = 0;

    /**
     * La valeur de la proposition: Peut être récupérée
     * @see Game#getProp()
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
     * A la construction d'un objet Game, le "mode" et le jeu choisis sont attendus en paramètre
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
     */
    public void run() {
        log.info("Début du jeu");
        if(getMode() == 1) {
            generateChallengerCombination();
        } else if(getMode() == 2) {
            generateDefenderCombination();
        }
        if ("true".equals(Config.getValue("cheatmode"))) {
            log.debug(getCombination());
            System.out.println(getCombination());
       }
        while (isRunning()) {
            this.response = "";

            if (this.getMode() == 1) {
                try {
                    challenger();
                } catch (GameException e) {
                    System.out.println(e.toString());
                    continue;
                }
            } else if (this.getMode() == 2) {
                defender();
            }
            checkProposition();
            displayResponse();
            this.numTry++;
        }

        if (isResolved()) {
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
     * @throws GameException si les chiffres saisis sont supérieurs ou inférieurs au nombre attendu
     */
    public void generateDefenderCombination() {
        int nbreChiffres = parseInt(Config.getValue("nbreChiffres"));
        System.out.println("Veuillez saisir une combinaison de " + nbreChiffres + " chiffres ");
        boolean demandeSaisie = true;
        boolean isNumber;

        while (demandeSaisie) {
            Scanner scan = new Scanner(System.in);
            try {
                combination = scan.nextInt();
                isNumber = true;
            }
            catch(InputMismatchException inputException) {
                System.out.println("Veuillez saisir un chiffre !");
                isNumber = false;

            }

            if (isNumber) {
                this.solution = Integer.toString(combination).split("");
                demandeSaisie = checkNumber(nbreChiffres, demandeSaisie, combination);
            }
        }
    }

    /**
     * Int combination to String to get length
     * Display error message if tailleNombre != nbChiffres
     * @return true
     */
    private boolean checkNumber(int nbreChiffres, boolean demandeSaisie, Integer combination) {
        int tailleNbre = combination.toString().length();
        if (tailleNbre != nbreChiffres) {
            System.out.println("Merci de ressaisir une combinaison de " + nbreChiffres);
        } else if(tailleNbre == nbreChiffres) {
            demandeSaisie = false;
        }
        return demandeSaisie;
    }

    /**
     *  Saisi de la proposition par le joueur
     *  La saisie devient la proposition
     *
     * @throws GameException si les chiffres saisis sont supérieurs ou inférieurs au nombre attendu
     */
    public void challenger() throws GameException  {
        int nbreChiffres = parseInt(Config.getValue("nbreChiffres"));
        System.out.println("A vous de jouer !");
        System.out.println(this.numTry);
        Scanner reader = new Scanner(System.in);
        try {
            prop = reader.nextInt();
        }
        catch(InputMismatchException inputException){
            System.out.println("Veuillez saisir un chiffre !");
        }
        if (prop != nbreChiffres){
            throw new GameException("Une combinaison à " + nbreChiffres + " chiffres est attendue");
        }
        this.proposition = Integer.toString(prop).split("");
    }

    /**
     *  Génération de la proposition de l'AI via la méthode ThreadLocalRandom
     *  La combinaison générée devient la proposition
     */
    //Find a way to generate the correct amount of numbers expected
    public void defender() {
        prop = ThreadLocalRandom.current().nextInt(1000, 9000);
        String prop_ = Integer.toString(prop);
        proposition = prop_.split("");
        System.out.println(prop);
    }

    /*public int defender(int n) {
        System.out.println(n);
        int m = (int) Math.pow(10, n - 1);
        return m + new Random().nextInt(9 * m);
    }*/


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
     * Retourne VRAI ou FAUX selon la valeur de la variable numTry
     *
     * @return vrai si numTry < MAX_NUM_TRY, sinon retourne faux
     */
    // à vérifier !
    private boolean checkNumTry() {
        return this.numTry < parseInt(Config.getValue("nbreChiffres"))? true : false;
    }

    /**
     * Retourne vrai ou faux selon les valeurs des méthodes CheckNumTry et isResolved
     *
     * @return VRAI si CheckNumTry == VRAI et isResolved == faux, sinon retourne FAUX
     */
    private boolean isRunning() {
        return checkNumTry() && !isResolved();
    }
    abstract void checkProposition();
    abstract Boolean isResolved();
    abstract void displayResponse();
}
