package com.openclassrooms.game;
import java.util.Scanner;

public class MoreLess extends Game {
    public MoreLess(int modeChoice, int gameChoice) {
        super(modeChoice, gameChoice);
    }

    public void checkProposition() {
        for (int i = 0; i < this.solution.length; i++) {
                if (this.proposition[i].equals(this.solution[i])) {
                    response += "=";
                } else if (Integer.parseInt(this.proposition[i]) > Integer.parseInt(this.solution[i])) {
                    this.response += "-";
                } else if (Integer.parseInt(this.proposition[i]) < Integer.parseInt(this.solution[i])) {
                    this.response += "+";
                }
            }
    }

    public Boolean isResolved() {
        return getProp() == getCombination() ? true : false;
    }

    public void displayResponse() {
        System.out.println("Proposition: " +this.getProp()+ " -> Réponse : "+this.response);
    }

}


