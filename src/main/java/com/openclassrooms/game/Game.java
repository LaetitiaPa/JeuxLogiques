package com.openclassrooms.game;
import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Integer.parseInt;

/**
 * La classe Game contient toutes les caract�ristiques d'un jeu
 * <p>
 * un jeu se caract�rise par les informations suivantes :
 * <ul>
 * <li>Une combinaison secr�te.</li>
 * <li>Une proposition entr�e par l'adversaire.</li>
 * <li>Une r�ponse affich�e.</li>
 * <li>Un nombre d'essai.</li>
 * <li>Un choix du jeu</li>
 * <li>Un choix du mode</li>
 * </ul>
 * </p>
 */
  abstract class Game {

    /**
     * Cr�ation de l'instance Logger en utilisant la m�thode getLogger()
     */
    private final static Logger log = Logger.getLogger(String.valueOf(Game.class));

    /**
     * Le choix du jeu. Peut �tre r�up�r�
     */
    protected int gameChoice;
    
    /**
     * La combinaison secr�te pour les modes challenger et defender
     * @see Game#getCombination()
     */
    protected int combination = 0;
    

    /**
     * La combinaison secr�te de l'AI pour le mode Duel: Peut �tre r�cup�r�e
     * @see Game#getDuelCombinationAI()
     */
    protected int duelCombinationAI = 0;
    
    /**
     * La combinaison secr�te du joueur pour le mode Duel: Peut �tre r�cup�r�e
     * @see Game#getDuelCombinationPlayer()
     */
    protected int duelCombinationPlayer = 0;

    /**
     * La valeur de la proposition: Peut �tre r�cup�r�e
     * @see Game#getProp()
     */
    protected  int prop;
    
    /**
     * La valeur de la proposition: Peut �tre r�cup�r�e
     * @see Game#getProp()
     */
    protected  int playerProp;
    
    /**
     * La valeur de la proposition: Peut �tre r�cup�r�e
     * @see Game#getProp()
     */
    protected  int AIProp;

    /**
     * Tableau contenant la proposition du joueur
     */
    protected String[] proposition;
    
    /**
     * Tableau contenant la proposition du joueur
     */
    protected String[] AIProposition;
    
    /**
     * Tableau contenant la proposition du joueur
     */
    protected String[] playerProposition;
    
    
    /**
     * Tableau contenant la solution du jeu 
     */
    protected String[] solution;

    /**
     * Tableau contenant la solution du jeu pour le mode Duel / Player
     */
    protected String[] solutionPlayer;
    
    /**
     * Tableau contenant la solution du jeu pour le mode Duel / AI
     */
    protected String[] solutionAI;

    /**
     * valeur de la r�ponse retourn�e
     */
    protected String response;
    
   /**
    *  valeur de la r�ponse du joueur
    */
   protected String playerResponse;
   
   /**
    *  valeur de la r�ponse de l'AI 
    */
   protected String aiResponse;

    /**
     * valeur du nombre de tours
     */
    protected int numTry;

    /**
     * La valeur du mode: Peut �tre r�cup�r�e
     * @see Game#getMode()
     */
    protected int mode;

    /**
     * Constructeur Game
     * <p>
     * A la construction d'un objet Game, le "mode" et le jeu choisis sont attendus en param�tre
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

    public Game() {
		super();
	}

	/**
     *<p>
     *  Affiche le jeu s�lectionn� selon le mode choisi
     *  G�re les conditions de sorties du jeu
     *  Affiche le message de fin de partie
     *  Affiche le menu de fin
     *</p>
     */
    public void run() {
        log.info("D�but du jeu");
        if(getMode() == 1) {
        	generateChallengerCombination();
       }else if (getMode() == 2) {
            generateDefenderCombination();
       }else if (this.getMode() == 3 && this.gameChoice == 1) {     	
       		DuelRecherche.duelMode();
       }else if (this.getMode() == 3 && this.gameChoice == 2) {
    	   DuelMastermind.duelMode();
       }
       if ("true".equals(Config.getValue("cheatmode"))) {
	       log.debug(getCombination());
	       System.out.println(getCombination());   
           
	   while (isRunning()) {
		    this.response = "";
		
		    if (this.getMode() == 1) {
		        try {
		            challenger();
		        } catch (GameException e) {
		            System.out.println(e.toString());
		            continue;
		        }
		    }else if (this.getMode() == 2) {
		    	defender();

            }if (this.getMode() == 1 || this.getMode() == 2 ) {
	        	  checkProposition();
	              displayResponse();
	              this.numTry++;
            	}
	        }
	        if (this.getMode() == 1 || this.getMode() == 2 ) {
	            if(isResolved()) {
	                System.out.println("Vous avez gagn� !");
	            } else {
	                System.out.println("Vous avez perdu !");
	            }
	        }
	        	Menu.displayEndMenu();    
       }
    }

    /**
     * Retourne la combination g�n�r�e
     *
     * @return La combinaison g�n�r�e par la m�thode ThreadLocalRandom
     */
    public int generateAICombinationDuel() {
    	int digits = parseInt(Config.getValue("nbreChiffres"));
    	int minimum = (int) Math.pow(10, digits - 1); // minimum value with 2 digits is 10 (10^1)
        int maximum = (int) Math.pow(10, digits) - 1; // maximum value with 2 digits is 99 (10^2 - 1)
        Random random = new Random();
        duelCombinationAI = minimum + random.nextInt((maximum - minimum) + 1);
        String solution_ = Integer.toString(duelCombinationAI);
        solutionAI = solution_.split("");

        return duelCombinationAI;
}
    
    /**
     * Retourne la combination g�n�r�e
     *
     * @return La combinaison g�n�r�e par la m�thode ThreadLocalRandom
     */
    
    public int generateChallengerCombination() {
    	int digits = parseInt(Config.getValue("nbreChiffres"));
    	int minimum = (int) Math.pow(10, digits - 1); // minimum value with 2 digits is 10 (10^1)
        int maximum = (int) Math.pow(10, digits) - 1; // maximum value with 2 digits is 99 (10^2 - 1)
        Random random = new Random();
        combination = minimum + random.nextInt((maximum - minimum) + 1);
        String solution_ = Integer.toString(combination);
        solution = solution_.split("");

        return combination;
}

    /**
     *  Saisi de la combinaison secr�te par le joueur en mode duel
     *  La combinaison entr�e devient la solution de la partie en cours
     *
     * @throws GameException si les chiffres saisis sont sup�rieurs ou inf�rieurs au nombre attendu
     */
    public void generatePlayerCombinationDuel() {
        int nbreChiffres = parseInt(Config.getValue("nbreChiffres"));
        System.out.println("Veuillez saisir une combinaison de " + nbreChiffres + " chiffres ");
        boolean demandeSaisie = true;
        boolean isNumber;

        while (demandeSaisie) {
            Scanner scan = new Scanner(System.in);
            try {
                duelCombinationPlayer = scan.nextInt();
                isNumber = true;
            }
            catch(InputMismatchException inputException) {
                System.out.println("Veuillez saisir un chiffre !");
                isNumber = false;

            }if(isNumber) {
                this.solutionPlayer = Integer.toString(duelCombinationPlayer).split("");
                demandeSaisie = checkNumberPlayer(nbreChiffres, demandeSaisie, duelCombinationPlayer);
            }
        }
    }
   
    /**
     *  Saisi de la combinaison secr�te par le joueur pour les modes Challenger et D�fenseur
     *  La combinaison entr�e devient la solution de la partie en cours
     *
     * @throws GameException si les chiffres saisis sont sup�rieurs ou inf�rieurs au nombre attendu
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

            }if(isNumber) {
                this.solution = Integer.toString(combination).split("");
                demandeSaisie = checkNumber(nbreChiffres, demandeSaisie, combination);
            }
        }
    }

    /**
     * Combinaison de type Integer convertie en type String pour obtenir sa taille
     * Affiche un message demandant la saisie d'une nouvelle combinaison si tailleNombre != nbChiffres
     * 
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
	 * Utilis�e pour le mode duel
	 * Combinaison de type Integer convertie en type String pour obtenir sa taille
	 * Affiche un message demandant la saisie d'une nouvelle combinaison si tailleNombre != nbChiffres
	 * 
	 * @return true
	 */
    private boolean checkNumberPlayer(int nbreChiffres, boolean demandeSaisie, Integer duelCombinationPlayer) {
        int tailleNbre = duelCombinationPlayer.toString().length();
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
     * @throws GameException si les chiffres saisis sont sup�rieurs ou inf�rieurs au nombre attendu
     */
    public void challenger() throws GameException  {
        int nbreChiffres = parseInt(Config.getValue("nbreChiffres"));
        System.out.println("A vous de jouer !");
        System.out.println(this.numTry);
        Scanner reader = new Scanner(System.in);
        prop = reader.nextInt();
        int tailleNbre = Integer.toString(prop).length();
        if ( nbreChiffres != tailleNbre){
            throw new GameException("Une combinaison � " + nbreChiffres + " chiffres est attendue");
        }
        this.proposition = Integer.toString(prop).split("");
        System.out.println("La proposition du joueur est : " + prop);
    }

    /**
     *  G�n�ration de la proposition de l'AI via la m�thode ThreadLocalRandom
     *  La combinaison g�n�r�e devient la proposition
     */
    public void defender() {
    	int digits = parseInt(Config.getValue("nbreChiffres"));
    	int minimum = (int) Math.pow(10, digits - 1); // minimum value with 2 digits is 10 (10^1)
        int maximum = (int) Math.pow(10, digits) - 1; // maximum value with 2 digits is 99 (10^2 - 1)
        Random random = new Random();
        prop = minimum + random.nextInt((maximum - minimum) + 1);
        String prop_ = Integer.toString(prop);
        proposition = prop_.split("");
        System.out.println("La proposition de l'ordinateur est : " + prop);
    }
   
    /**
     * Retourne la proposion saisie
     *
     * @return la cha�ne de caract�re correspondant � la proposition entr�e
     */
    public int getProp() {
        return prop;
    }
    
    /**
     * Retourne la proposion saisie
     *
     * @return la cha�ne de caract�re correspondant � la proposition entr�e
     */
    public int getPlayerProp() {
        return playerProp;
    }
    
    /**
     * Retourne la proposion saisie
     *
     * @return la cha�ne de caract�re correspondant � la proposition entr�e
     */
    public int getAIProp() {
        return AIProp;
    }

    /**
     * Retourne le mode choisi
     *
     * @return la cha�ne de caract�re correspondant au mode s�lectionn�
     */
    public int getMode() {
        return mode;
    }
    
    /**
     * Retourne le jeux choisi
     *
     * @return la cha�ne de caract�re correspondant au jeu s�lectionn�
     */
    public int getGameChoice() {
        return gameChoice;
    }
    
    /**
     * Retourne la combinaison saisie pour le mode Challenger et D�fenseur
     *
     * @return la cha�ne de caract�re correspondant � la combinaison saisie
     */
    public int getCombination() {
    	return combination;
    }
    
    /**
     * Retourne la combinaison saisie par l'ordinateur
     *
     * @return la cha�ne de caract�re correspondant � la combinaison saisie
     */
    public int getDuelCombinationAI() {
        return duelCombinationAI;
    }
    
    /**
     * Retourne la combinaison saisie par le joueur
     *
     * @return la cha�ne de caract�re correspondant � la combinaison saisie
     */
    public int getDuelCombinationPlayer() {
        return duelCombinationPlayer;
    }
    
    /**
     * Retourne la r�ponse pour le mode Challenger et D�fenseur
     *
     * @return la cha�ne de caract�re correspondant � la r�ponse saisie
     */
    public String getResponse() {
        return response;
    }
    
    /**
     * Retourne la r�ponse de l'ordinateur
     *
     * @return la cha�ne de caract�re correspondant � la r�ponse de l'ordinateur
     */
    public String getAIResponse() {
        return aiResponse;
    }
    
    /**
     * Retourne la r�ponse du joueur
     *
     * @return la cha�ne de caract�re correspondant � la r�ponse saisie par le joueur
     */
    public String getPlayerResponse() {
        return playerResponse;
    }

    /**
     * Retourne VRAI ou FAUX selon la valeur de la variable numTry
     *
     * @return vrai si numTry < MAX_NUM_TRY, sinon retourne faux
     */
    private boolean checkNumTry() {
        return this.numTry < parseInt(Config.getValue("nbreChiffres"))? true : false;
    }

    /**
     * Retourne vrai ou faux selon les valeurs des m�thodes CheckNumTry et isResolved
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
