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
		log.info("Début du jeu de recherche +/- en mode duel");
		int nbEssai = Integer.parseInt(Config.getValue("nbessai"));
	
		int compteur = 0;
		int playerResponse = 0; // Joueur == 0 : humain / joueur == 1 : ordinateur
		boolean winner = false;
		boolean initPlayerResponse = false;
		
		MoreLess duelMoreLess = new MoreLess();	
		duelMoreLess.generateAICombinationDuel();
		if ("true".equals(Config.getValue("cheatmode"))) {
            System.out.println("La combinaison secrète de l'ordinateur: " + duelMoreLess.getDuelCombinationAI());    
		}
		while (compteur < nbEssai) {
			System.out.println("Le joueur propose sa réponse");
			try {
				duelMoreLess.challenger();
			} catch (GameException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			duelMoreLess.checkPlayerProposition();
			duelMoreLess.displayPlayerResponse();		
			
			if (!initPlayerResponse) {
			System.out.println("Le joueur propose sa combinaison");
			duelMoreLess.generatePlayerCombinationDuel();
			}
			duelMoreLess.defender();
			duelMoreLess.checkAIProposition();
			duelMoreLess.displayAIResponse();		
			playerResponse = 0;
			initPlayerResponse = true;
			compteur += 1;
			Debug.print("Compteur : " + compteur);

            if (playerResponse == 0) {   	
            try {
  				duelMoreLess.challenger();
				duelMoreLess.checkPlayerProposition();
				duelMoreLess.displayPlayerResponse();
				playerResponse = 1;
			} catch (GameException e) {
				e.printStackTrace();
				}
	        }
            if (playerResponse == 1) {
				duelMoreLess.defender();
				duelMoreLess.checkAIProposition();
				duelMoreLess.displayAIResponse();	
				playerResponse = 0;	
			} 
   			compteur += 1;
			Debug.print("Compteur : " + compteur);
			
			if (duelMoreLess.getDuelCombinationAI() == duelMoreLess.getPlayerProp()){
				System.out.println("Vous avez gagné contre l'ordinateur");
				winner = true;
				break;
			}else if (duelMoreLess.getAIProp() == duelMoreLess.getDuelCombinationPlayer()){
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
	

	


				
			


	
		

