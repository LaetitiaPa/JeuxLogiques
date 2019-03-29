package com.openclassrooms.game;

import static java.lang.Integer.parseInt;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class DuelRecherche {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MoreLess gameDuel = new MoreLess();
		int nbEssai = Integer.parseInt(Config.getValue("nbessai"));
		
		int compteur = 1;
		int nbrePointsJoueur = 2;
		int nbrePointsIA = 2;
		int playerResponse = 0; // Joueur == 0 : humain / joueur == 1 : ordinateur 
		
		gameDuel.generateChallengerCombination();		
		while(compteur < nbEssai) {
			if ("true".equals(Config.getValue("cheatmode"))) {
	            System.out.println(gameDuel.getCombination());
	       }
			
			if(playerResponse == 0) {
				try {
					gameDuel.challenger();
					playerResponse = 1;
				} catch (GameException e) {
					e.printStackTrace();
				}
				if(playerResponse == 1) {
					gameDuel.defender();
					playerResponse = 0;
				}
				compteur = compteur + 1;
				System.out.println(compteur);
			}
		}
		
		if(nbrePointsJoueur > nbrePointsIA) {
			System.out.println("Vous avez gagné contre l'ordinateur");
		}
		else if(nbrePointsIA > nbrePointsJoueur) {
			System.out.println("Vous avez perdu contre l'ordinateur");
		}
		else {
			System.out.println("Egalité, pas de vainqueur");
		}
		
	}
	
}
