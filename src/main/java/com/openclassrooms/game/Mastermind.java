package com.openclassrooms.game;
import org.apache.log4j.Logger;
import java.util.Arrays;
import java.util.List;


/**
 * La classe Mastermind contient toutes les caract�ristiques du jeu Mastermind
 * Elle h�rite de la classe Game
 * <p>
 * Le Mastermind se caract�rise par les informations suivantes :
 * <ul>
 * <li>Un ou une combinaison de chiffres bien plac�s</li>
 * <li>Un ou une combinaison de chiffres pr�sents</li>
 * </ul>
 *
 * @see Game#mode
 * @see Game#gameChoice
 * </p>
 */
public class Mastermind extends Game {

	/**
	 * Cr�ation de l'instance Logger en utilisant la m�thode getLogger()
	 */
	private final static Logger log = Logger.getLogger(String.valueOf(Mastermind.class));

	/**
	 * Le nombre de chiffres pr�sents
	 */
	private int present;

	/**
	 * Le nombre de chiffres bien plac�s
	 */
	private int wellPlaced;


	public Mastermind(int mode, int gameChoice) {
		super(mode, gameChoice);
	}

	public Mastermind() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * V�rification des entr�es du tableau proposition avec ceux du tableau solution
	 * Incr�menter la variable present lorsque l'�l�ment est pr�sent dans le tableau solution
	 * Incr�menter la variable wellPlaced lorsque l'�l�ment se trouve � la m�me position que dans le tableau solution
	 *
	 * Les variables present et wellPlaced sont instanci�es selon les r�sultats
	 */
	public void checkProposition() {
		log.trace("Utilisation de la m�thode checkProposition du jeu Mastermind");
		// To be improved to avoid cast at each call
		List<String> propositionAsList = Arrays.asList(proposition);
		this.present = 0;
		this.wellPlaced = 0;

		for (int i = 0; i < this.solution.length; i++) {
			if (proposition[i].equals(solution[i])) {
				wellPlaced++;
			}else if (propositionAsList.contains(solution[i])) {
				present++;
			}
		}
	}

	/**
	 * V�rification des entr�es du tableau proposition avec ceux du tableau solution
	 * Incr�menter la variable present lorsque l'�l�ment est pr�sent dans le tableau solution
	 * Incr�menter la variable wellPlaced lorsque l'�l�ment se trouve � la m�me position que dans le tableau solution
	 *
	 * Les variables present et wellPlaced sont instanci�es selon les r�sultats
	 */
	public void checkPlayerProposition() {
		log.trace("Utilisation de la m�thode checkProposition du jeu Mastermind c�t� joueur");
		// To be improved to avoid cast at each call
		List<String> propositionPlayerAsList = Arrays.asList(playerProposition);
		this.present = 0;
		this.wellPlaced = 0;

		for (int i = 0; i < this.solutionAI.length; i++) {
			if (playerProposition[i].equals(solutionAI[i])) {
				wellPlaced++;
			}else if (propositionPlayerAsList.contains(solutionAI[i])) {
				present++;
			}
		}
	}

	/**
	 * V�rification des entr�es du tableau proposition avec ceux du tableau solution
	 * Incr�menter la variable present lorsque l'�l�ment est pr�sent dans le tableau solution
	 * Incr�menter la variable wellPlaced lorsque l'�l�ment se trouve � la m�me position que dans le tableau solution
	 *
	 * Les variables present et wellPlaced sont instanci�es selon les r�sultats
	 */
	public void checkAIProposition() {
		log.trace("Utilisation de la m�thode checkProposition du jeu Mastermind c�t� AI");
		// To be improved to avoid cast at each call
		List<String> propositionAIAsList = Arrays.asList(AIProposition);
		this.present = 0;
		this.wellPlaced = 0;

		for (int i = 0; i < this.solutionPlayer.length; i++) {
			if (AIProposition[i].equals(solutionPlayer[i])) {
				wellPlaced++;
			}else if (propositionAIAsList.contains(solutionPlayer[i])) {
				present++;
			}
		}
	}


	/**
	 * Retourne vrai ou faux selon la valeur de la variable wellPlaced
	 *
	 * @return vrai si wellPlaced est �gal au nombre de chiffres configur�, sinon retourne faux
	 */
	public Boolean isResolved() {
		return wellPlaced == Integer.parseInt(Config.getValue("nbreChiffres")) ? true : false;
	}

	/**
	 * G�n�re la r�ponse affich�e selon le contenu des variables present et wellPlaced
	 * afin de pouvoir g�rer les phrases au singulier ou au pluriel
	 *
	 */
	public void displayResponse() {
		if (present == 0 && wellPlaced == 1) {
			System.out.println("Proposition : " + this.getProp() + " R�ponse => " + wellPlaced + " bien plac�");
		}else if (present == 0 && wellPlaced <= 10) {
			System.out.println("Proposition : " + this.getProp() + " R�ponse => " + wellPlaced + " bien plac�s");
		}else if (present == 1 && wellPlaced <= 10) {
			System.out.println("Proposition : " + this.getProp() + " R�ponse => " + present + " pr�sent," + wellPlaced + " bien plac�s");
		}else if (present <= 10 && wellPlaced <= 10) {
			System.out.println("Proposition : " + this.getProp() + " R�ponse => " + present + " pr�sents, " + wellPlaced + " bien plac�s");
		}else if (present > 1 && wellPlaced == 0) {
			System.out.println("Proposition : " + this.getProp() + " R�ponse => " + present + " pr�sents");
		}else if (present == 1 && wellPlaced == 0) {
			System.out.println("Proposition : " + this.getProp() + " R�ponse => " + present + " pr�sent");
		}
	}

	/**
	 * G�n�re la r�ponse du joueur selon le contenu des variables present et wellPlaced
	 * afin de pouvoir g�rer les phrases au singulier ou au pluriel
	 *
	 */
	public void displayPlayerResponse() {
		if (present == 0 && wellPlaced == 1) {
			System.out.println("Proposition : " + this.getPlayerProp() + " R�ponse => " + wellPlaced + " bien plac�");
		}else if (present == 0 && wellPlaced <= 10) {
			System.out.println("Proposition : " + this.getPlayerProp() + " R�ponse => " + wellPlaced + " bien plac�s");
		}else if (present == 1 && wellPlaced <= 10) {
			System.out.println("Proposition : " + this.getPlayerProp() + " R�ponse => " + present + " pr�sent," + wellPlaced + " bien plac�s");
		}else if (present <= 10 && wellPlaced <= 10) {
			System.out.println("Proposition : " + this.getPlayerProp() + " R�ponse => " + present + " pr�sents, " + wellPlaced + " bien plac�s");
		}else if (present > 1 && wellPlaced == 0) {
			System.out.println("Proposition : " + this.getPlayerProp() + " R�ponse => " + present + " pr�sents");
		}else if (present == 1 && wellPlaced == 0) {
			System.out.println("Proposition : " + this.getPlayerProp() + " R�ponse => " + present + " pr�sent");
		}
	}
	
	/**
	 * G�n�re la r�ponse de l'AI selon le contenu des variables present et wellPlaced
	 * afin de pouvoir g�rer les phrases au singulier ou au pluriel
	 *
	 */
	public void displayAIResponse() {
		if (present == 0 && wellPlaced == 1) {
			System.out.println("Proposition : " + this.getAIProp() + " R�ponse => " + wellPlaced + " bien plac�");
		}else if (present == 0 && wellPlaced <= 10) {
			System.out.println("Proposition : " + this.getAIProp() + " R�ponse => " + wellPlaced + " bien plac�s");
		}else if (present == 1 && wellPlaced <= 10) {
			System.out.println("Proposition : " + this.getAIProp() + " R�ponse => " + present + " pr�sent," + wellPlaced + " bien plac�s");
		}else if (present <= 10 && wellPlaced <= 10) {
			System.out.println("Proposition : " + this.getAIProp() + " R�ponse => " + present + " pr�sents, " + wellPlaced + " bien plac�s");
		}else if (present > 1 && wellPlaced == 0) {
			System.out.println("Proposition : " + this.getAIProp() + " R�ponse => " + present + " pr�sents");
		}else if (present == 1 && wellPlaced == 0) {
			System.out.println("Proposition : " + this.getAIProp() + " R�ponse => " + present + " pr�sent");
		}
	}
}



