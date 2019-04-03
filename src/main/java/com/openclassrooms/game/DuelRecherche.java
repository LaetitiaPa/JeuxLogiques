package com.openclassrooms.game;

import org.apache.log4j.Logger;

public class DuelRecherche {
	
	/**
     * Création de l'instance Logger en utilisant la méthode getLogger()
     */
    private final static Logger log = Logger.getLogger(String.valueOf(DuelRecherche.class));
    
	/**
     *<p>
     * Lancement d'une partie de Recherche +/- en mode duel
     *</p>
     */
	public static void duelMode() {
		log.info("Début du mode Duel");
		MoreLess gameDuel = new MoreLess();
		int nbEssai = Integer.parseInt(Config.getValue("nbessai"));
		
		int compteur = 0;
		int playerResponse = 0; // Joueur == 0 : humain / joueur == 1 : ordinateur
		String lastAIResponse = "";
		String lastPlayerResponse = "";
		boolean winner = false;
		boolean initPlayerResponse = false;
		
		gameDuel.generateAICombinationDuel();
		if ("true".equals(Config.getValue("cheatmode"))) {
            System.out.println("La combinaison secrète de l'ordinateur: " + gameDuel.getDuelCombinationAI());    
		}
		
		while (compteur < nbEssai) {
			if (!initPlayerResponse) {
				System.out.println("Le joueur propose sa réponse");
				try {
					gameDuel.challenger();
					gameDuel.checkPropositionPlayer();
					gameDuel.displayResponsePlayer();
				} catch (GameException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Le joueur propose sa combinaison");
				gameDuel.generatePlayerCombinationDuel();
				gameDuel.defender();
				gameDuel.checkPropositionAI();
				gameDuel.displayResponseAI();
				playerResponse = 0;
				initPlayerResponse = true;
				compteur += 1;
				Debug.print("Compteur : " + compteur);
			}
			
            if (playerResponse == 0) {   	
  				try {
				gameDuel.challenger();
				gameDuel.checkPropositionPlayer();
				gameDuel.displayResponsePlayer();
				lastPlayerResponse = gameDuel.getPlayerResponse();
				playerResponse = 1;
			} catch (GameException e) {
				e.printStackTrace();
				}
	        }if (playerResponse == 1) {
				gameDuel.defender();
				gameDuel.checkPropositionAI();
				gameDuel.displayResponseAI();	
				lastAIResponse = gameDuel.getAiResponse();
				playerResponse = 0;	
			}
   			compteur += 1;
			Debug.print("Compteur : " + compteur);
			
			if (lastPlayerResponse.equals("======")) {
				System.out.println("Vous avez gagné contre l'ordinateur");
				winner = true;
				break;
				
			}else if (lastAIResponse.equals("======")) {
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


