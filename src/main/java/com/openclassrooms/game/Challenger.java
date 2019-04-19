package com.openclassrooms.game;

import static java.lang.Integer.parseInt;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

abstract class Challenger extends Game {

protected static Integer propositionChallenger;
	/**
     * Retourne la combination générée par le CPU
	 * 
     * @return La combinaison générée par la méthode ThreadLocalRandom
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
     * @throws GameException si les chiffres saisis sont supérieurs ou inférieurs au nombre attendu
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
	    		 demandeSaisie = checkNumber(nbreChiffres, demandeSaisie, combination);
	         }
        }
        int tailleNbre = prop.length();
        if ( nbreChiffres != tailleNbre){
            throw new GameException("Une combinaison à  " + nbreChiffres + " chiffres est attendue");
        }
        proposition = prop.split("");
        System.out.println("La proposition du joueur est : " + prop);
    }
    
}
