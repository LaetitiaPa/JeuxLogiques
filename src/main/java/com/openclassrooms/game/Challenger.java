package com.openclassrooms.game;

import static java.lang.Integer.parseInt;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class Challenger extends Game {

	 /**
     * Cr�ation de l'instance Logger pour la classe Challenger en utilisant la m�thode getLogger()
     */
    private final static Logger log = Logger.getLogger(String.valueOf(Challenger.class));

	 /**
    * Proposition saisie par le joueur
    */
    protected static Integer propositionChallenger;

	/**
     * Retourne la combination g�n�r�e par le CPU
	 * 
     * @return La combinaison g�n�r�e par la m�thode ThreadLocalRandom
     */
    public static String[] generateChallengerCombination() {
    	int digits = parseInt(Config.getValue("nbreChiffres"));
    	int minimum = (int) Math.pow(10, digits - 1); // minimum value with 2 digits is 10 (10^1)
        int maximum = (int) Math.pow(10, digits) - 1; // maximum value with 2 digits is 99 (10^2 - 1)
        Random random = new Random();
        Integer comb = minimum + random.nextInt((maximum - minimum) + 1);
        combination = comb.toString();
        solution = combination.split("");

        return solution;
    }
    
    /**
     *  Saisi de la proposition par le joueur
     *  La saisie devient la proposition
     *
     * @throws GameException si les chiffres saisis sont sup�rieurs ou inf�rieurs au nombre attendu
     */
	public static void challenger() throws GameException  {
        int nbreChiffres = parseInt(Config.getValue("nbreChiffres"));
        System.out.println("A vous de jouer !");
        boolean demandeSaisie = true;
        boolean isNumber;
        
        while (demandeSaisie) {
        	Scanner reader = new Scanner(System.in);
        	try {
        		propositionChallenger = reader.nextInt();
        		isNumber = true;
        	} 
        	catch(InputMismatchException inputException) {
        		 System.out.println("Veuillez saisir un chiffre !");
                 isNumber = false;
        	}
	    	 if (isNumber) {
	    		 prop = propositionChallenger.toString();
	    		 demandeSaisie = checkNumber(nbreChiffres, demandeSaisie, prop);
	         }
        }
        proposition = prop.split("");
        System.out.println("La proposition du joueur est : " + prop);
    }
    public void displayResponse() {
    	System.out.println("Proposition: " + getPlayerProp()  + " -> R�ponse : " + playerResponse);
    }
    
	/**
     * V�rification des entr�es du tableau proposition avec celui du tableau solution (mode Duel)
     *
     * Les variables present et wellPlaced sont instanci�es selon les r�sultats
     */
    public void checkProposition() {
    	log.trace("Utilisation de la m�thode checkPlayerProposition pour le mode Duel");
    		playerResponse = "";
        for (int i = 0; i < solutionAI.length; i++) {
            if (playerProposition[i].equals(solutionAI[i])) {
            	playerResponse += "=";            
            }else if (Integer.parseInt(playerProposition[i]) > Integer.parseInt(solutionAI[i])) {
            	playerResponse += "-";
            }else if (Integer.parseInt(playerProposition[i]) < Integer.parseInt(solutionAI[i])) {
            	playerResponse += "+";
            }
        }
    }

	@Override
	Boolean isResolved() {
		// TODO Auto-generated method stub
		return null;
	}
}
