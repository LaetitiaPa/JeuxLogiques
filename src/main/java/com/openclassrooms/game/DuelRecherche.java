package com.openclassrooms.game;

import org.apache.log4j.Logger;

public class DuelRecherche {
	
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
		MoreLess moreLess = new MoreLess();
		log.info("D�but du mode duel pour le jeu de recherche +/-");
		int nbEssai = Integer.parseInt(Config.getValue("nbessai"));
	
		int compteur = 0;
		int playerResponse = 0; // Joueur == 0 : humain / joueur == 1 : ordinateur
		boolean winner = false;
		boolean initPlayerResponse = false;

		Duel.generateAICombinationDuel();
		log.info("Le CPU g�n�re une combinaison secr�te");
		
		if ("true".equals(Config.getValue("cheatmode"))) {
            System.out.println("La combinaison secr�te de l'ordinateur: " + Duel.getDuelCombinationAI());    
		}
		
		while (compteur < nbEssai) {
			log.info("Le joueur g�n�re une combinaison secr�te");
			System.out.println("Le joueur propose sa r�ponse");
			try {
				Duel.challengerDuel();
			} catch (GameException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			moreLess.checkPlayerProposition();
			moreLess.displayPlayerResponse();		
			
			if (!initPlayerResponse) {
				Duel.generatePlayerCombinationDuel();
			}
			Duel.defenderDuel();
			Defender.playerDefenderResponse();
			moreLess.displayAIResponse();		
			playerResponse = 0;
			initPlayerResponse = true;
			compteur += 1;
			System.out.println("Tour n�" + compteur);

            if (playerResponse == 0) {   	
	            try {
	            	Duel.challengerDuel();
	            	moreLess.checkPlayerProposition();
	            	moreLess.displayPlayerResponse();
					playerResponse = 1;
				} catch (GameException e) {
					e.printStackTrace();
				}
	        }
            
            if (playerResponse == 1) {
            	Duel.defenderDuel();
            	Defender.playerDefenderResponse();
            	moreLess.displayAIResponse();	
				playerResponse = 0;	
			} 
   			compteur += 1;
			Debug.print("Tour n�" + compteur);
			
			if (Duel.getDuelCombinationAI() == Duel.getPlayerProp()){
				System.out.println("Vous avez gagn� contre l'ordinateur");
				winner = true;
				break;
			} else if (Duel.getAIProp() == Duel.getDuelCombinationPlayer()){
				System.out.println("Vous avez perdu contre l'ordinateur");
				winner = true;
				break;
			}
		}
		if (winner == false) {
			System.out.println("Egalit�, pas de vainqueur");
		}
	}
}