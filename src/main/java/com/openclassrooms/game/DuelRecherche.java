package com.openclassrooms.game;

import org.apache.log4j.Logger;

public class DuelRecherche extends Game {
	
	/**
     * Cr�ation de l'instance Logger de la classe DuelRecherche en utilisant la m�thode getLogger()
     */
    private final static Logger log = Logger.getLogger(String.valueOf(DuelRecherche.class));
    
   
	/**
     *<p>
     * Lancement d'une partie de Recherche +/- en mode duel
     *</p>
	 * @throws GameException 
     */
	public static void duelMode() throws GameException {
		Defender defender = new Defender();
		Challenger challenger = new Challenger();
		Duel duel = new Duel();
		
		log.info("D�but du mode duel pour le jeu de recherche +/-");
		int nbEssai = Integer.parseInt(Config.getValue("nbessai"));
	
		int playerResponse = 0; // Joueur == 0 : humain / joueur == 1 : ordinateur
		boolean initPlayerResponse = false;
		
		log.info("Le CPU g�n�re une combinaison secr�te");
		Duel.generateAICombinationDuel();
		
		
		if ("true".equals(Config.getValue("cheatmode"))) {
            System.out.println("La combinaison secr�te de l'ordinateur: " + Duel.getDuelCombinationAI());    
		}
		log.info("Le joueur indique sa r�ponse");
		System.out.println("Le joueur propose sa r�ponse");
		try {
			Duel.challengerDuel();
			duel.checkProposition();
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		challenger.checkProposition();
		challenger.displayResponse();
		duel.checkProposition();
		numTry++;

		while (numTry < nbEssai && !winner) {
			if (!initPlayerResponse) {
				Duel.generatePlayerCombinationDuel();
			}
			if (numTry == 1) {
				defender.generateAIRandom();
				Defender.playerDefenderResponse();
				initPlayerResponse = true;
				numTryLeft();
			}
			playerResponse = 0;

	        if (playerResponse == 0) {   	
	            try {
	            	Duel.challengerDuel();
	            	challenger.checkProposition();
	              	Defender.NewPropositionAI();
					playerResponse = 1;
				} catch (GameException e) {
					e.printStackTrace();
				}
	        }
	        if (playerResponse == 1) {
	        	Defender.playerDefenderResponse();
	        	defender.displayResponse();	
				playerResponse = 0;
			}
	        numTry++;
			numTryLeft();
			duel.checkProposition();
		}
		if(!winner) {
			System.out.println("Egalit�, pas de vainqueur"); 
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



	@Override
	void generateAIRandom() {
		// TODO Auto-generated method stub
		
	}
}