package com.openclassrooms.game;
import org.apache.log4j.Logger;
import java.util.Arrays;
import java.util.List;


/**
 * La classe Mastermind contient toutes les caractéristiques du jeu Mastermind
 * Elle hérite de la classe Game
 * <p>
 * Le Mastermind se caractérise par les informations suivantes :
 * <ul>
 * <li>Un ou une combinaison de chiffres bien placés</li>
 * <li>Un ou une combinaison de chiffres présents</li>
 * </ul>
 *
 * @see Game#mode
 * @see Game#gameChoice
 * </p>
 */
public class Mastermind extends Game {

	/**
	 * Création de l'instance Logger en utilisant la méthode getLogger()
	 */
	private final static Logger log = Logger.getLogger(String.valueOf(Mastermind.class));

	/**
	 * Le nombre de chiffres présents
	 */
	private int present;

	/**
	 * Le nombre de chiffres bien placés
	 */
	private int wellPlaced;


	public Mastermind(int mode, int gameChoice) {
		super(mode, gameChoice);
	}

	public Mastermind() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Vérification des entrées du tableau proposition avec ceux du tableau solution
	 * Incrémenter la variable present lorsque l'élément est présent dans le tableau solution
	 * Incrémenter la variable wellPlaced lorsque l'élément se trouve à  la même position que dans le tableau solution
	 *
	 * Les variables present et wellPlaced sont instanciées selon les résultats
	 */
	public void checkProposition() {
		log.trace("Utilisation de la méthode checkProposition du jeu Mastermind");
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
	 * Vérification des entrées du tableau proposition avec ceux du tableau solution
	 * Incrémenter la variable present lorsque l'élément est présent dans le tableau solution
	 * Incrémenter la variable wellPlaced lorsque l'élément se trouve à  la même position que dans le tableau solution
	 *
	 * Les variables present et wellPlaced sont instanciées selon les résultats
	 */
	public void checkPlayerProposition() {
		log.trace("Utilisation de la méthode checkProposition du jeu Mastermind côté joueur");
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
	 * Vérification des entrées du tableau proposition avec ceux du tableau solution
	 * Incrémenter la variable present lorsque l'élément est présent dans le tableau solution
	 * Incrémenter la variable wellPlaced lorsque l'élément se trouve à  la même position que dans le tableau solution
	 *
	 * Les variables present et wellPlaced sont instanciées selon les résultats
	 */
	public void checkAIProposition() {
		log.trace("Utilisation de la méthode checkProposition du jeu Mastermind côté AI");
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
	 * @return vrai si wellPlaced est égal au nombre de chiffres configuré, sinon retourne faux
	 */
	public Boolean isResolved() {
		return wellPlaced == Integer.parseInt(Config.getValue("nbreChiffres")) ? true : false;
	}

	/**
	 * Génére la réponse affichée selon le contenu des variables present et wellPlaced
	 * afin de pouvoir gérer les phrases au singulier ou au pluriel
	 *
	 */
	public void displayResponse() {
		if (present == 0 && wellPlaced == 1) {
			System.out.println("Proposition : " + this.getProp() + " Réponse => " + wellPlaced + " bien placé");
		}else if (present == 0 && wellPlaced <= 10) {
			System.out.println("Proposition : " + this.getProp() + " Réponse => " + wellPlaced + " bien placés");
		}else if (present == 1 && wellPlaced <= 10) {
			System.out.println("Proposition : " + this.getProp() + " Réponse => " + present + " présent," + wellPlaced + " bien placés");
		}else if (present <= 10 && wellPlaced <= 10) {
			System.out.println("Proposition : " + this.getProp() + " Réponse => " + present + " présents, " + wellPlaced + " bien placés");
		}else if (present > 1 && wellPlaced == 0) {
			System.out.println("Proposition : " + this.getProp() + " Réponse => " + present + " présents");
		}else if (present == 1 && wellPlaced == 0) {
			System.out.println("Proposition : " + this.getProp() + " Réponse => " + present + " présent");
		}
	}

	/**
	 * Génére la réponse du joueur selon le contenu des variables present et wellPlaced
	 * afin de pouvoir gérer les phrases au singulier ou au pluriel
	 *
	 */
	public void displayPlayerResponse() {
		if (present == 0 && wellPlaced == 1) {
			System.out.println("Proposition : " + this.getPlayerProp() + " Réponse => " + wellPlaced + " bien placé");
		}else if (present == 0 && wellPlaced <= 10) {
			System.out.println("Proposition : " + this.getPlayerProp() + " Réponse => " + wellPlaced + " bien placés");
		}else if (present == 1 && wellPlaced <= 10) {
			System.out.println("Proposition : " + this.getPlayerProp() + " Réponse => " + present + " présent," + wellPlaced + " bien placés");
		}else if (present <= 10 && wellPlaced <= 10) {
			System.out.println("Proposition : " + this.getPlayerProp() + " Réponse => " + present + " présents, " + wellPlaced + " bien placés");
		}else if (present > 1 && wellPlaced == 0) {
			System.out.println("Proposition : " + this.getPlayerProp() + " Réponse => " + present + " présents");
		}else if (present == 1 && wellPlaced == 0) {
			System.out.println("Proposition : " + this.getPlayerProp() + " Réponse => " + present + " présent");
		}
	}
	
	/**
	 * Génére la réponse de l'AI selon le contenu des variables present et wellPlaced
	 * afin de pouvoir gérer les phrases au singulier ou au pluriel
	 *
	 */
	public void displayAIResponse() {
		if (present == 0 && wellPlaced == 1) {
			System.out.println("Proposition : " + this.getAIProp() + " Réponse => " + wellPlaced + " bien placé");
		}else if (present == 0 && wellPlaced <= 10) {
			System.out.println("Proposition : " + this.getAIProp() + " Réponse => " + wellPlaced + " bien placés");
		}else if (present == 1 && wellPlaced <= 10) {
			System.out.println("Proposition : " + this.getAIProp() + " Réponse => " + present + " présent," + wellPlaced + " bien placés");
		}else if (present <= 10 && wellPlaced <= 10) {
			System.out.println("Proposition : " + this.getAIProp() + " Réponse => " + present + " présents, " + wellPlaced + " bien placés");
		}else if (present > 1 && wellPlaced == 0) {
			System.out.println("Proposition : " + this.getAIProp() + " Réponse => " + present + " présents");
		}else if (present == 1 && wellPlaced == 0) {
			System.out.println("Proposition : " + this.getAIProp() + " Réponse => " + present + " présent");
		}
	}
}



