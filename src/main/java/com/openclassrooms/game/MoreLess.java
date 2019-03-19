package com.openclassrooms.game;


/**
 * La classe MoreLess contient toutes les caractéristiques propres au jeu de recherche +/-
 * Elle hérite de la classe Game
 * @see Game#mode
 * @see Game#gameChoice
 * </p>
 */

public class MoreLess extends Game {
    public MoreLess(int modeChoice, int gameChoice) {
        super(modeChoice, gameChoice);
    }

    /**
     * Vérification des entrées du tableau proposition avec celui du tableau solution
     *
     * Les variables present et wellPlaced sont instanciées selon les résultats
     */
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

    /**
     * Retourne vrai ou faux selon la comparaison obtenue entre les méthodes getProp() contenant la propositon du joueur
     * et la méthode getCombination() contenant la solution
     *
     * @return VRAI si la valeur de getProp() est égal à la valeur de getCombination(), sinon retourne FAUX
     */
    public Boolean isResolved() {
        return getProp() == getCombination() ? true : false;
    }

    public void displayResponse() {
        System.out.println("Proposition: " +this.getProp()+ " -> Réponse : " +this.response);
    }

}


