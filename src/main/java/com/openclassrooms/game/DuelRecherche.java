package com.openclassrooms.game;

import org.apache.log4j.Logger;

public class DuelRecherche extends Game {
	
	/**
     * Création de l'instance Logger de la classe DuelRecherche en utilisant la méthode getLogger()
     */
    private final static Logger log = Logger.getLogger(String.valueOf(DuelRecherche.class));

	/**
     *<p>
     * Lancement d'une partie de Recherche +/- en mode duel
     *</p>
	 * @throws GameException 
     */
	public static void duelMode() throws GameException {
		
		log.info("Début du mode duel pour le jeu de recherche +/-");
		int nbEssai = Integer.parseInt(Config.getValue("nbessai"));
	
		int playerResponse = 0; // Joueur == 0 : humain / joueur == 1 : ordinateur
		boolean initPlayerResponse = false;
		
		log.info("Le CPU génère une combinaison secrète");
		Duel.generateAICombinationDuel();
		
		
		if ("true".equals(Config.getValue("cheatmode"))) {
            System.out.println("La combinaison secrète de l'ordinateur: " + Duel.getDuelCombinationAI());    
		}
		log.info("Le joueur indique sa réponse");
		System.out.println("Le joueur propose sa réponse");
		try {
			Duel.challengerDuel();
			Duel.isGameResolved();
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Challenger.checkChallengerProposition();
		Challenger.displayPlayerResponse();
		Duel.isGameResolved();
		numTry++;

		while (numTry < nbEssai && !winner) {
			if (!initPlayerResponse) {
				Duel.generatePlayerCombinationDuel();
			}
			if (numTry == 1) {
				Defender.generateAIRandom();
				Defender.playerDefenderResponse();
				initPlayerResponse = true;
				numTryLeft();
			}
			playerResponse = 0;

	        if (playerResponse == 0) {   	
	            try {
	            	Duel.challengerDuel();
	            	Challenger.checkChallengerProposition();
	              	Defender.NewPropositionAI();
					playerResponse = 1;
				} catch (GameException e) {
					e.printStackTrace();
				}
	        }
	        if (playerResponse == 1) {
	        	Defender.playerDefenderResponse();
	        	Defender.displayPlayerResponse();	
				playerResponse = 0;
			}
	        numTry++;
			numTryLeft();
			Duel.isGameResolved();
		}
		if(!winner) {
			System.out.println("Egalité, pas de vainqueur");
			System.out.println("La combinaison était : " + Duel.getDuelCombinationAI());
		}
}

	@Override
	void displayResponse() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void checkProposition() {
		// TODO Auto-generated method stub
		
	}


	@Override
	Boolean isResolved() {
		// TODO Auto-generated method stub
		return null;
	}

}