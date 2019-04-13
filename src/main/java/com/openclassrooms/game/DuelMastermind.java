package com.openclassrooms.game;

import org.apache.log4j.Logger;

public class DuelMastermind {
	
	/**
     * Création de l'instance Logger en utilisant la méthode getLogger()
     */
    private final static Logger log = Logger.getLogger(String.valueOf(DuelMastermind.class));
    
    /**
     *<p>
     * Lancement d'une partie de Recherche +/- en mode duel
     *</p>
     */
	public static void duelMode() {
		log.info("Début du jeu Mastermind en mode duel");
		int nbEssai = Integer.parseInt(Config.getValue("nbessai"));
	
		int compteur = 0;
		int playerResponse = 0; // Joueur == 0 : humain / joueur == 1 : ordinateur
		boolean winner = false;
		boolean initPlayerResponse = false;

		Mastermind duelMastermind = new Mastermind();
		duelMastermind.generateAICombinationDuel();
		
		if ("true".equals(Config.getValue("cheatmode"))) {
            System.out.println("La combinaison secrète de l'ordinateur: " + duelMastermind.getDuelCombinationAI());    
		}
		while (compteur < nbEssai) {
		System.out.println("Le joueur propose sa réponse");
		try {
			duelMastermind.challenger();
		} catch (GameException e) {
			// TODO Auto-generated catch block
			log.error("Erreur du lancement du mode Challenger" + e.getMessage());
		}
		duelMastermind.checkPlayerProposition();
		duelMastermind.displayAIResponse();
			
		
		if (!initPlayerResponse) {
		System.out.println("Le joueur propose sa combinaison");
		duelMastermind.generatePlayerCombinationDuel();
		}
		duelMastermind.defender();
		duelMastermind.checkAIProposition();
		duelMastermind.displayPlayerResponse();		
	
		playerResponse = 0;
		initPlayerResponse = true;
		compteur += 1;
		Debug.print("Compteur : " + compteur);
		
        if (playerResponse == 0) {   	
		try {
		duelMastermind.challenger();
		duelMastermind.checkPlayerProposition();
		duelMastermind.displayPlayerResponse();
		playerResponse = 1;
		} catch (GameException e) {
			e.printStackTrace();
			}
        }
        if (playerResponse == 1) {
        	duelMastermind.defender();
        	duelMastermind.checkAIProposition();
        	duelMastermind.displayAIResponse();	
			playerResponse = 0;	
		} 
		compteur += 1;
		Debug.print("Compteur : " + compteur);
		
		if (duelMastermind.getDuelCombinationAI() == duelMastermind.getPlayerProp()){
			System.out.println("Vous avez gagné contre l'ordinateur");
			winner = true;
			break;
		}else if (duelMastermind.getAIProp() == duelMastermind.getDuelCombinationPlayer()){
			System.out.println("Vous avez perdu contre l'ordinateur");
			winner = true;
			break;
			}
		}	
		if (winner == false) {
			System.out.println("Egalité, pas de vainqueur");
	
		}
	}
	
}
	

