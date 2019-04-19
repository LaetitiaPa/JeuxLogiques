package com.openclassrooms.game;

import static java.lang.Integer.parseInt;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import org.apache.log4j.Logger;



abstract class Defender extends Game{
	
	 /**
     * Cr�ation de l'instance Logger pour la classe Game en utilisant la m�thode getLogger()
     */
    private final static Logger log = Logger.getLogger(String.valueOf(Defender.class));

    /**
     *  Saisi de la combinaison secr�te par le joueur pour les modes Challenger et Defender
     *  La combinaison entr�e devient la solution de la partie en cours
     *
     * @throws GameException si les chiffres saisis sont sup�rieurs ou inf�rieurs au nombre attendu
     */
    public static void defenderPlayerCombination() {
    	log.trace("Utilisation de la m�thode DefenderPlayerCombination pour le mode Duel");
        int nbreChiffres = parseInt(Config.getValue("nbreChiffres"));
        System.out.println("Veuillez saisir une combinaison de " + nbreChiffres + " chiffres ");
        boolean demandeSaisie = true;
        boolean isNumber;

        while (demandeSaisie) {
            Scanner scan = new Scanner(System.in);
            try {
                Integer comb = scan.nextInt();
                combination = comb.toString();
                defenderCombination.add(comb);
                isNumber = true;
            }
            catch(InputMismatchException inputException) {
                System.out.println("Veuillez saisir un chiffre !");
                isNumber = false;

            }
            if (isNumber) {
                solution = combination.split("");
                demandeSaisie = checkNumber(nbreChiffres, demandeSaisie, combination);
            }
        }
    }
    
	 /**
     *  G�n�ration de la proposition du CPU
     *  La combinaison g�n�r�e devient la proposition
     */
    public static void generateAIProposition () {
    	log.trace("Utilisation de la m�thode generateAIProposition pour le mode Duel");
    	int digits = parseInt(Config.getValue("nbreChiffres"));
    	if (numTry == 0) {
    		for (int i = 0; i < digits; i++) {
		    	propositionAI.add(5);
	    	}
		    System.out.println("La proposition de votre adversaire est " + propositionAI);
		}
    }
    
    /**
     *  G�n�ration de la proposition du CPU
     *  La combinaison g�n�r�e devient la proposition
     * @throws GameException 
     */
    public static void playerDefenderResponse () throws GameException {
    	log.trace("Utilisation de la m�thode playerDefenderResponse pour le mode Defender et Duel");
    	int nbreChiffres = parseInt(Config.getValue("nbreChiffres"));
    	boolean demandeSaisie = true;
        boolean isSymbol;
       
    	Scanner scan = new Scanner(System.in);
    	System.out.println("Veuillez saisir votre r�ponse:");
    	
    	try {
		    playerResp = scan.nextLine();
		    int playerRespLength = playerResp.length();
		    isSymbol = true;
		    if (playerRespLength != nbreChiffres) {
		    	throw new GameException("Une indication � " + nbreChiffres + " caract�res est attendue");
	    	}
    	}
    	catch (InputMismatchException inputException) {
    		System.out.println("Veuillez saisir des symboles !");
    		isSymbol = false;
    	}
    	if (isSymbol) {
    		responseDefender = playerResp.split("");
    	    arrayProp.removeAll(arrayProp);

    	}
    	Integer newProp = 0;
	    for (int i = 0; i < propositionAI.size(); i++) {	
    		newProp = propositionAI.get(i);

	    	if (responseDefender[i].equals("+")) {		    		
	    		newProp += 1;
	    	} else if (responseDefender[i].equals("-")) {
	    		newProp -= 1;	    			 
	    	} 	
	    	prop = newProp.toString();
    		arrayProp.add(newProp);
	    }
    }
    
    /**
     *  G�n�ration de la proposition du CPU
     *  La combinaison g�n�r�e devient la proposition
     */
    public static void displayNewAIProposition () {
    	log.trace("Utilisation de la m�thode displayNewAIPropositions pour le mode Defender et Duel");
    	System.out.println("Essai n� " + numTry);
	    System.out.println("La nouvelle proposition de votre adversaire est " + arrayProp);
	    propositionAI.removeAll(propositionAI);
	    propositionAI.addAll(arrayProp);
    }
    
    /**
     *  G�n�ration de la proposition du CPU
     *  La combinaison g�n�r�e devient la proposition
     */
    public static void isResolvedDefender () {
    	log.trace("Utilisation de la m�thode isResolvedDefender pour le mode Defender");
	   	String resultT2 = "";
	   	String resultT1 = "";
	   	
	   	for (int value : propositionAI) { 
   	     resultT2 = resultT2 + value;
	   	}
	   	 System.out.println(resultT2);
	   	 resultT1 = defenderCombination.get(0).toString();
	   	 System.out.println(resultT1);
   	 
	   if (resultT2.equals(resultT1)) {
   		 System.out.println("Vous avez gagn�");
   	 } else {
   		 System.out.println("Vous avez perdu");
   	 	}
    	
    }
	 
}


