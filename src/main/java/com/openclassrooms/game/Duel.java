package com.openclassrooms.game;

import static java.lang.Integer.parseInt;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import org.apache.log4j.Logger;

abstract class Duel extends Game {
	
	/**
     * Création de l'instance Logger pour la classe Duel en utilisant la méthode getLogger()
     */
    private final static Logger log = Logger.getLogger(String.valueOf(Game.class));

	/**
     * Retourne la combination générée
     *
     * @return La combinaison générée par la méthode ThreadLocalRandom
     */
    public static int generateAICombinationDuel() {
    	log.info("Le CPU génère une combinaison secrète");
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
     *  Saisi de la combinaison secrète par le joueur en mode duel
     *  La combinaison entrée devient la solution de la partie en cours
     *
     * @throws GameException si les chiffres saisis sont supérieurs ou inférieurs au nombre attendu
     */
    public static void generatePlayerCombinationDuel() {
    	log.info("Le joueur génère une combinaison secrète");
        int nbreChiffres = parseInt(Config.getValue("nbreChiffres"));
        System.out.println("Veuillez saisir une combinaison de " + nbreChiffres + " chiffres");
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
                solutionPlayer = Integer.toString(duelCombinationPlayer).split("");
                demandeSaisie = checkNumberPlayer(nbreChiffres, demandeSaisie, duelCombinationPlayer);
            }
        }
    }
    
    /**
     *  Génération de la proposition de l'AI via la méthode ThreadLocalRandom
     *  La combinaison générée devient la proposition
     */
    public static void defenderDuel() {
    	log.info("Le CPU génère une proposition");
    	int digits = parseInt(Config.getValue("nbreChiffres"));
    	int minimum = (int) Math.pow(10, digits - 1); // minimum value with 2 digits is 10 (10^1)
        int maximum = (int) Math.pow(10, digits) - 1; // maximum value with 2 digits is 99 (10^2 - 1)
        Random random = new Random();
        AIProp = minimum + random.nextInt((maximum - minimum) + 1);
        String prop_ = Integer.toString(AIProp);
        AIProposition = prop_.split("");
        System.out.println("La proposition de l'ordinateur est : " + AIProp);
    }

    
    /**
     *  Saisi de la proposition par le joueur
     *  La saisie devient la proposition
     *
     * @throws GameException si les chiffres saisis sont supérieurs ou inférieurs au nombre attendu
     */
    public static void challengerDuel() throws GameException  {
    	log.info("Le joueur génère une proposition");
        int nbreChiffres = parseInt(Config.getValue("nbreChiffres"));
        System.out.println("A vous de jouer !");
        boolean demandeSaisie = true;
        boolean isNumber;
        
        while (demandeSaisie) {
        	Scanner reader = new Scanner(System.in);
	        try {
	        	playerProp = reader.nextInt();
	        	Integer playerComb = playerProp;
		        prop = playerComb.toString();
		        int tailleNbre = prop.length();
		        isNumber = true;
		        if (nbreChiffres != tailleNbre){
			        throw new GameException("Une combinaison à  " + nbreChiffres + " chiffres est attendue");
		        }   
	        }
	        catch (InputMismatchException inputException) {
	            System.out.println("Veuillez saisir un chiffre !");
	            isNumber = false;
	        }
	        if (isNumber) {
	        	playerProposition = prop.split("");;
	        	demandeSaisie = checkNumber(nbreChiffres, demandeSaisie, prop);
            }
        }
        System.out.println("La proposition du joueur est : " + prop);
    }
    
    
	/**
	 * Combinaison de type Integer convertie en type String pour obtenir sa taille (mode Duel)
	 * Affiche un message demandant la saisie d'une nouvelle combinaison si tailleNombre != nbChiffres
	 * 
	 * @return true
	 */
    public static boolean checkNumberPlayer(int nbreChiffres, boolean demandeSaisie, Integer duelCombinationPlayer) {
        int tailleNbre = duelCombinationPlayer.toString().length();
        if (tailleNbre != nbreChiffres) {
            System.out.println("Merci de ressaisir une combinaison de " + nbreChiffres);
        } else if(tailleNbre == nbreChiffres) {
            demandeSaisie = false;
        }
        return demandeSaisie;
    }

	public static void isGameResolved() {
		if (Duel.getDuelCombinationAI() == Duel.getPlayerProp()){
			System.out.println("Vous avez gagné contre l'ordinateur");
			winner = true;
		} else if (Duel.getAIProp() == Duel.getDuelCombinationPlayer() && numTry > 0){
			System.out.println("Vous avez perdu contre l'ordinateur");
			winner = true;
		}

	}


}
