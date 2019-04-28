package com.openclassrooms.game;
import org.apache.log4j.Logger;

/**
 * La classe MoreLess contient toutes les caractéristiques propres au jeu de recherche +/-
 * Elle hérite de la classe Game
 * @see Game#mode
 * @see Game#gameChoice
 * </p>
 */

public class MoreLess extends Game {

    /**
     * Création de l'instance Logger pour la classe Moreless en utilisant la méthode getLogger()
     */
    private final static Logger log = Logger.getLogger(String.valueOf(MoreLess.class));

    public MoreLess(int modeChoice) {
        super(modeChoice);
    }

    public MoreLess() {
		super();
	}
    
	

	/**
     * Vérification des entrées du tableau proposition avec celui du tableau solution
     *
     * Les variables present et wellPlaced sont instanciées selon les résultats
     */
    public void checkProposition() {
    	log.trace("Utilisation de la méthode checkProposition pour le mode Challenger et Defender");
    	response = "";
        for (int i = 0; i < solution.length; i++) {
            if (proposition[i].equals(solution[i])) {
            	response += "=";            
            }else if (Integer.parseInt(proposition[i]) > Integer.parseInt(solution[i])) {
            	response += "-";
            }else if (Integer.parseInt(Game.proposition[i]) < Integer.parseInt(solution[i])) {
            	response += "+";
            }
        }
    }
    

	
    /**
     * Retourne vrai ou faux selon la comparaison obtenue entre les méthodes getProp() contenant la propositon du joueur
     * et la méthode getCombination() contenant la solution
     *
     * @return VRAI si la valeur de getProp() est égal à la valeur de getCombination(), sinon retourne FAUX
     */
    public Boolean isResolved() {
        return getProp().contentEquals(getCombination()) ? true : false;
    }

    /**
     * Affiche la proposition du joueur et la réponse
     *
     */
	public void displayResponse() {
        System.out.println("Proposition: " + getProp() + " -> Réponse : " + response);
    }


}
