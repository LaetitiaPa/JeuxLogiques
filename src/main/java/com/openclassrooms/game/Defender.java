package com.openclassrooms.game;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;



abstract class Defender extends Game{
	
	 /**
     * Cr�ation de l'instance Logger pour la classe Defender en utilisant la m�thode getLogger()
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
        System.out.println("Veuillez saisir une combinaison de " + digits + " chiffres ");
        boolean demandeSaisie = true;
        boolean isNumber;
        combination = "";

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
                demandeSaisie = checkNumber(digits, demandeSaisie, combination);
            }
        }
    }
    
	 /**
     *  G�n�ration de la proposition du CPU
     *  La combinaison g�n�r�e devient la proposition
     */
    public static void generateAIRandom() {
    	log.trace("Utilisation de la m�thode generateAIProposition pour le mode Duel");
    	prop = "";
    	
		for (int i = 0; i < digits; i++) {
	    	propositionAI.add(5);
	    	prop = prop + propositionAI.get(i).toString();
    	}
	    System.out.println("La proposition de votre adversaire est " + prop);
		
    }
    
    /**
     *  G�n�ration de la r�ponse d'apr�s la proposition du CPU
     * @throws GameException 
     */
	public static void playerDefenderResponse () throws GameException {
    	log.trace("Utilisation de la m�thode playerDefenderResponse pour le mode Defender et Duel");
    	System.out.println("Veuillez saisir votre r�ponse:");
    	
    	boolean demandeSaisie = true;
      
    	while (demandeSaisie) {
			Scanner scan = new Scanner(System.in);
	        response = scan.nextLine();
	        
	        String pattern = "([=+-]{4})";
	        
			// Create a Pattern object
			Pattern r = Pattern.compile(pattern);

			// Now create matcher object.
			Matcher m = r.matcher(response);
			
			if (m.find()) {
				responseDefender = response.split("");
				arrayProp.removeAll(arrayProp);
			    demandeSaisie = checkNumber(digits, demandeSaisie, response);
			} else {
				System.out.println("Veuillez saisir - + ou =");
			}
			
    	}
    	System.out.println("La r�ponse du joueur est : " + response);
    }
    	

	  
	    /**
	     *  G�n�ration de la nouvelle proposition d'apr�s la r�ponse du joueur
	     *  La nouvelle combinaison g�n�r�e devient la proposition
	     */
	    public static void NewPropositionAI() {
	    	Debug.print("Valeurs contenues dans le tableau propositionAI avant comparaison: " + propositionAI);
	    	prop = "";
		    Integer newProp = 0;
		    for (int i = 0; i < propositionAI.size(); i++) {	
	    		newProp = propositionAI.get(i);
	
		    	if (responseDefender[i].equals("+")) {		    		
		    		newProp += 1;
		    	} else if (responseDefender[i].equals("-")) {
		    		newProp -= 1;	    			 
		    	} 	
		    	arrayProp.add(newProp);
		    	prop = prop + newProp.toString();
		    	AIProp = Integer.parseInt(prop);
		    }
		    propositionAI.removeAll(propositionAI);
		    propositionAI.addAll(arrayProp);
		    Debug.print("Nouvelles valeurs contenues dans le tableau propositionAI: " + propositionAI);
		    
		    System.out.println("La nouvelle proposition de votre adversaire est " + prop);
	    }
	
    
    
    
    /**
     *  Affichage de la nouvelle proposition du CPU g�n�r�e � partir de la derni�re r�ponse du joueur
     *  Le tableau propositionAI contenant la derni�re proposition est effac�
     *  La nouvelle proposition est ajout� au tableau propositionAI
     */
    public static void displayNewAIProposition () {
    	log.trace("Utilisation de la m�thode displayNewAIPropositions pour le mode Defender et Duel");
	    
    }
    
    /**
     *  Changement des tableaux PropositionAI et defenderCombination de type Integer en String
     *  Comparaison des valeurs des deux tableaux afin de d�terminer si la partie est gagn�e ou perdue
     */
    public Boolean isResolved() {
    	log.trace("Utilisation de la m�thode isResolvedDefender pour le mode Defender");
	   	
	   	for (int value : propositionAI) { 
   	     prop = prop + value;
	   	}
	   	 
	   	 combination = defenderCombination.get(0).toString();

	   	 return prop.equals(combination) ? true : false;
  }
    
    public static void displayPlayerResponse() {
    	System.out.println("Proposition: " + getProp() + " -> R�ponse : " + response );
    }
    
}


