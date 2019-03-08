package com.openclassrooms.game;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Mastermind extends Game {
    // Bien placé (en anglais) est un attribut de l'instance de Mastermind
    private int present;
    private int wellPlaced;

    public Mastermind(int mode, int gameChoice) {
        super(mode, gameChoice);
    }

    public void checkProposition() {
        // To be improved to avoid cast at each call
        List<String> propositionAsList = Arrays.asList(proposition);

        this.present = 0;
        this.wellPlaced = 0;

        for (int i = 0; i < this.solution.length; i++) {
            if (proposition[i].equals(solution[i]))
                wellPlaced++;
            else if (propositionAsList.contains(solution[i]))
                present++;
        }
    }

    public Boolean isResolved() {
        return wellPlaced == 4 ? true : false;
    }

    public void displayResponse(){
        System.out.println("Proposition : "+this.getProp()+ " Réponse => " +this.present+ " présent et " +this.wellPlaced+ "  bien placé" );
    }
}


