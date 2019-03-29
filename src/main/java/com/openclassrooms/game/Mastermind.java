package com.openclassrooms.game;
import org.apache.log4j.Logger;
import java.util.Arrays;
import java.util.List;


/**
 * La classe Mastermind contient toutes les caractéristiques du jeu Mastermind
 * Elle hérite de la classe Game
 * <p>
 * Le Mastermind se caractérise par les informations suivantes :
 * <ul>
 * <li>Un ou une combinaison de chiffres bien placés</li>
 * <li>Un ou une combinaison de chiffres présents</li>
 * </ul>
 *
 * @see Game#mode
 * @see Game#gameChoice
 * </p>
 */
public class Mastermind extends Game {

    /**
     * Création de l'instance Logger en utilisant la méthode getLogger()
     */
    private final static Logger log = Logger.getLogger(String.valueOf(Mastermind.class));

    /**
     *
     */
    private int present;

    /**
     *
     */
    private int wellPlaced;

    public Mastermind(int mode, int gameChoice) {
        super(mode, gameChoice);
    }

    /**
     * Vérification des entrées du tableau proposition avec ceux du tableau solution
     * Incrémenter la variable present lorsque l'élément est présent dans le tableau solution
     * Incrémenter la variable wellPlaced lorsque l'élément se trouve à la même position que dans le tableau solution
     *
     * Les variables present et wellPlaced sont instanciées selon les résultats
     */
    public void checkProposition() {
        log.trace("Utilisation de la méthode checkProposition");
        // To be improved to avoid cast at each call
        List<String> propositionAsList = Arrays.asList(proposition);
        this.present = 0;
        this.wellPlaced = 0;

        for (int i = 0; i < this.solution.length; i++) {
            if (proposition[i].equals(solution[i])) {
                wellPlaced++;
            } else if (propositionAsList.contains(solution[i])) {
                present++;
            }
        }
    }


    /**
     * Retourne vrai ou faux selon la valeur de la variable wellPlaced
     *
     * @return vrai si wellPlaced est égal à 4, sinon retourne faux
     */
    public Boolean isResolved() {
        return wellPlaced == 4 ? true : false;
    }

    /**
     * Génére la réponse affichée selon le contenu des variables present et wellPlaced
     * afin de pouvoir gérer les phrases au singulier ou au pluriel
     *
     */
    public void displayResponse() {
        if (present == 0 && wellPlaced == 1) {
            System.out.println("Proposition : " + this.getProp() + " R�ponse => " + wellPlaced + " bien plac�");
        } else if (present == 0 && wellPlaced <= 3) {
            System.out.println("Proposition : " + this.getProp() + " R�ponse => " + wellPlaced + " bien plac�s");
        } else if (present == 1 && wellPlaced <= 3) {
            System.out.println("Proposition : " + this.getProp() + " R�ponse => " + present + " pr�sent," + wellPlaced + " bien placés");
        } else if (present <= 3 && wellPlaced <= 3) {
            System.out.println("Proposition : " + this.getProp() + " R�ponse => " + present + " pr�sents, " + wellPlaced + " bien placés");
        } else if (present > 1 && wellPlaced == 0) {
            System.out.println("Proposition : " + this.getProp() + " R�ponse => " + present + " pr�sents");
        }
    }
}



