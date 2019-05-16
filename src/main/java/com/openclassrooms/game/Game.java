package com.openclassrooms.game;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
 * <li>Un choix du mode</li>
 * </ul>
 * </p>
 */
  abstract class Game {

    /**
     * Création de l'instance Logger pour la classe Game en utilisant la méthode getLogger()
     */
    private final static Logger log = Logger.getLogger(String.valueOf(Game.class));
    
    /**
     *  Booléen indiquant pour le mode Duel si un joueur a gagné la partie en retournant VRAI
     */
    protected static Boolean winner = false;
    
    /**
     *  Booléen retournant VRAI si la saisie comporte uniquement des les symboles attendus (+, -, =) 
     */
    protected static boolean isSymbol = true;

    /**
     * La combinaison secrète (mode Challenger et Defender)
     * @see Game#getCombination()
     */
    public static String combination = "";
    
    /**
     * Liste contenant les symboles autorisés à la saisie par le joueur
     */
    protected static String[] symbols = new String[] {"+", "-", "="};

    /**
     * Liste contenant les symboles saisis par le joueur
     */
    protected static String[] playerSymbols;
    
    /**
     * La combinaison secrète (mode Challenger et Defender): Peut être récupérée
     * 
     */
    protected static ArrayList<Integer> defenderCombination = new ArrayList<Integer>();

    /**
     * La combinaison secrète du CPU (mode Duel): Peut être récupérée
     * @see Game#getDuelCombinationAI()
     */
    protected static int duelCombinationAI = 0;
    
    /**
     * Le nombre de chiffres attendus
     */
    protected static int digits = parseInt(Config.getValue("nbreChiffres"));
    
    /**
     * La combinaison secrète du joueur (mode Duel): Peut être récupérée
     * @see Game#getDuelCombinationPlayer()
     */
    protected static int duelCombinationPlayer = 0;

    /**
     * La valeur de la proposition: Peut être récupérée
     * @see Game#getProp()
     */
    protected static  String prop = "";
    
    /**
     * La valeur de la proposition (mode Duel): Peut être récupérée
     * @see Game#getPlayerProp()
     */
    protected static int playerProp;
    
    /**
     * La valeur de la proposition (mode Duel): Peut être récupérée
     * @see Game#getAIProp()
     */
    public static int AIProp;

    /**
     * Liste contenant la proposition du CPU
     */
    protected static ArrayList<Integer> propositionAI = new ArrayList<Integer>();
    
    /**
     * Liste contenant la proposition du CPU après la réponse du joueur (mode Defender et Duel)
     */
    protected static ArrayList<Integer> arrayProp = new ArrayList<Integer>();
    
    /**
     * Tableau contenant la proposition du joueur
     */
    protected static String[] proposition;
    
    /**
     * Tableau contenant la proposition du CPU pour le mode Duel
     */
    protected static String AIProposition[];
    
    /**
     * Tableau contenant la proposition du joueur pour le mode Duel
     */
    protected static String[] playerProposition;
    
    
    /**
     * Tableau contenant la solution du jeu 
     */
    protected static String[] solution;

    /**
     * Tableau contenant la solution du joueur du jeu (mode Duel)
     */
    protected static String[] solutionPlayer;
    
    /**
     * Tableau contenant la solution du CPU (mode Duel)
     */
    protected static String[] solutionAI;

    /**
     * Valeur de la réponse générée par la méthode checkProposition()
     */
    protected static String response;
    
    /**
     * Liste contenant la proposition du défenseur générée par la méthode playerDefenderResponse()
     */
    protected static String[] responseDefender;
    
   /**
    *  Valeur de la réponse générée par la méthode checkPlayerProposition()
    */
   protected static String playerResponse;
   
   /**
    *  Valeur de la réponse de l'AI (mode Duel)
    */
   protected String aiResponse;
   
   /**
    *  Valeur de la réponse du joueur saisie (mode Defender)
    */
   protected static String playerResp;
   
   /**
    *  Valeur de la proposition de l'AI
    */
   protected ArrayList<Integer> joinedProp = new ArrayList<Integer>();

    /**
     * Valeur du nombre de tours
     */
    protected static int numTry;
    
    /**
     * Valeur de la nouvelle proposition du CPU après indications
     */
    protected static int newProp = 0;

    /**
     * La valeur du mode: Peut être récupérée
     * @see Game#getMode()
     */
    protected int mode;
    
    /**
     * Constructeur Game
     * <p>
     * A la construction d'un objet Game, le choix du mode est attendu en paramètre
     * vide.
     * </p>
     *
     * @param mode
     *            Le mode du jeu choisi
     */
    public Game(int mode) {
        this.mode = mode;
    }

    public Game() {
		super();
	}

	/**
     *<p>
     *  Lance une partie selon le mode choisi
     *  Gère les conditions de sorties du jeu
     *  Affiche le message de fin de partie
     *  Affiche le menu de fin
     *</p>
	 * @throws GameException 
     */
	public void run() throws GameException {
       log.info("Début du jeu");
       if (getMode() == 1) {
        	Challenger.generateChallengerCombination();
       } else if (getMode() == 2) {
    	   Defender.defenderPlayerCombination();
    	   Defender.generateAIRandom();
       } else if (this.getMode() == 3) {     	
    	   DuelRecherche.duelMode();
       }
       
       if ("true".equals(Config.getValue("cheatmode"))) {
	       log.debug(getCombination());
	       System.out.println(getCombination());
       }
           
	   while (isRunning()) {
		   response = "";
		
		    if (this.getMode() == 1) {
		        try {
		            Challenger.challenger();
		            checkProposition();
		            displayResponse();
		        } catch (GameException e) {
		            System.out.println(e.toString());
		            continue;
		        }
		    } else if (this.getMode() == 2) {
		    	Defender.playerDefenderResponse();
		    	Defender.NewPropositionAI();
		    }
		    numTry++;
		    if (this.mode != 3) {
		    	numTryLeft();
		    }
		 }
	     if (isResolved() && this.mode != 3) {
	    	 switch(this.getMode()) {
	    	 	case 1: 
	    	 		System.out.println("Bravo !!! Vous avez gagné !");
	    	 		break;
	    	 	case 2: System.out.println("Oh ! Vous avez perdu !");
	    	 }
			
		 } else if (!isResolved() && !checkNumTry()) {
			 switch(this.getMode()) {
	    	 	case 1: 
	    	 		System.out.println("Oh ! Vous avez perdu !");
	    	 		System.out.println("La combinaison était : " + getCombination());
	    	 		break;
	    	 	case 2: System.out.println("Bravo !!! Vous avez gagné !");
	    	 }
		 }
	     numTry = 0;
   	     propositionAI.removeAll(propositionAI);
   		 Menu.endGameMenu();
	}
	  
  	 	
    /**
     * Combinaison de type Integer convertie en type String pour obtenir sa taille
     * Affiche un message demandant la saisie d'une nouvelle combinaison si tailleNombre != nbChiffres
     * 
     * @return false
     */
    protected static boolean checkNumber(int digits, boolean demandeSaisie, String prop) {
        int tailleNbre = prop.length();
      
         if (tailleNbre != digits) {
            System.out.println("Merci de ressaisir une combinaison de " + digits + " chiffres");
         } else if(tailleNbre == digits) {
            demandeSaisie = false;
         }
         return demandeSaisie;
    }
    
 
    protected static void numTryLeft() {
    	int nbTryLeft = parseInt(Config.getValue("nbessai"));
	    nbTryLeft -= numTry;
	    System.out.println("Nombre d'essais restant: " + nbTryLeft) ;
    }
    
    /**
     * Retourne la proposition saisie
     *
     * @return la chaîne de caractère correspondant à la proposition entrée
     */
    public static String getProp() {
        return prop;
    }
    
    /**
     * Retourne la proposion saisie du joueur (mode Duel)
     *
     * @return la chaîne de caractère correspondant à la proposition entrée
     */
    public static int getPlayerProp() {
        return playerProp;
    }
    
    /**
     * Retourne la proposition du CPU (mode Duel)
     *
     * @return la chaîne de caractère correspondant à la proposition du CPU
     */
    public static int getAIProp() {
        return AIProp;
    }

    /**
     * Retourne le mode choisi
     *
     * @return la chaîne de caractère correspondant au mode sélectionné
     */
    public int getMode() {
        return mode;
    }
    
    
    /**
     * Retourne la combinaison saisie (mode Challenger et Defender)
     *
     * @return la chaîne de caractère correspondant à la combinaison saisie
     */
    public String getCombination() {
    	return combination;
    }
    
    /**
     * Retourne la combinaison saisie par le CPU (mode Duel)
     *
     * @return la chaîne de caractère correspondant à la combinaison saisie
     */
    public static int getDuelCombinationAI() {
        return duelCombinationAI;
    }
    
    /**
     * Retourne la combinaison saisie par le joueur (mode Duel)
     *
     * @return la chaîne de caractère correspondant à la combinaison saisie
     */
    public static int getDuelCombinationPlayer() {
        return duelCombinationPlayer;
    }
    
    /**
     * Retourne la réponse (mode Challenger et Défenseur)
     *
     * @return la chaîne de caractère correspondant à la réponse saisie
     */
    public String getResponse() {
        return response;
    }
    
    /**
     * Retourne la réponse du CPU
     *
     * @return la chaîne de caractère correspondant à la réponse du CPU
     */
    public String getAIResponse() {
        return aiResponse;
    }
    
    /**
     * Retourne la réponse du joueur (mode Duel)
     *
     * @return la chaîne de caractère correspondant à la réponse saisie par le joueur
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
        return numTry < parseInt(Config.getValue("nbessai"))? true : false;
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