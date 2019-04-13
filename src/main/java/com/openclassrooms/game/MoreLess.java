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
     * Création de l'instance Logger en utilisant la méthode getLogger()
     */
    private final static Logger log = Logger.getLogger(String.valueOf(MoreLess.class));

    public MoreLess(int modeChoice, int gameChoice) {
        super(modeChoice, gameChoice);
    }

    public MoreLess() {
		super();
	}
    
	/**
     * Vérification des entrées du tableau proposition avec celui du tableau solution
     *
     * Les variables present et wellPlaced sont instanciées selon les résultats
     */
    public void checkAIProposition() {
    	log.trace("Utilisation de la méthode checkProposition du jeu de Recherche +/-");
    	this.aiResponse = "";
        for (int i = 0; i < this.solutionPlayer.length; i++) {
            if (this.AIProposition[i].equals(this.solutionPlayer[i])) {
                this.aiResponse += "=";            
            }else if (Integer.parseInt(this.AIProposition[i]) > Integer.parseInt(this.solutionPlayer[i])) {
                this.aiResponse += "-";
            }else if (Integer.parseInt(this.AIProposition[i]) < Integer.parseInt(this.solutionPlayer[i])) {
                this.aiResponse += "+";
            }
        }
    }

	/**
     * Vérification des entrées du tableau proposition avec celui du tableau solution
     *
     * Les variables present et wellPlaced sont instanciées selon les résultats
     */
    public void checkProposition() {
    	this.response = "";
        for (int i = 0; i < this.solution.length; i++) {
            if (this.proposition[i].equals(this.solution[i])) {
                this.response += "=";            
            }else if (Integer.parseInt(this.proposition[i]) > Integer.parseInt(this.solution[i])) {
                this.response += "-";
            }else if (Integer.parseInt(this.proposition[i]) < Integer.parseInt(this.solution[i])) {
                this.response += "+";
            }
        }
    }
    
	/**
     * Vérification des entrées du tableau proposition avec celui du tableau solution
     *
     * Les variables present et wellPlaced sont instanciées selon les résultats
     */
    public void checkPlayerProposition() {
    	this.playerResponse = "";
        for (int i = 0; i < this.solutionAI.length; i++) {
            if (this.playerProposition[i].equals(this.solutionAI[i])) {
                this.playerResponse += "=";            
            }else if (Integer.parseInt(this.playerProposition[i]) > Integer.parseInt(this.solutionAI[i])) {
                this.playerResponse += "-";
            }else if (Integer.parseInt(this.playerProposition[i]) < Integer.parseInt(this.solutionAI[i])) {
                this.playerResponse += "+";
            }
        }
    }
	
    /**
     * Retourne vrai ou faux selon la comparaison obtenue entre les méthodes getProp() contenant la propositon du joueur
     * et la méthode getCombination() contenant la solution
     *
     * @return VRAI si la valeur de getProp() est égal Ã  la valeur de getCombination(), sinon retourne FAUX
     */
    public Boolean isResolved() {
        return getProp() == getCombination() ? true : false;
    }

    public void displayResponse() {
        System.out.println("Proposition: " + this.getProp() + " -> Réponse : " + this.response);
    }
    
    public void displayPlayerResponse() {
        System.out.println("Proposition: " + this.getPlayerProp() + " -> Réponse : " + this.playerResponse);
    }
    
    public void displayAIResponse() {
        System.out.println("Proposition: " + this.getAIProp() + " -> Réponse : " + this.aiResponse);
    }

}
