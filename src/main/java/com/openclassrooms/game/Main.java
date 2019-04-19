package com.openclassrooms.game;
import java.util.logging.Logger;

public class Main {

    /**
     * Création de l'instance Logger en utilisant la méthode getLogger()
     */
    private final static Logger log = Logger.getLogger(String.valueOf(Main.class));

    public static void main(String[] args) throws GameException {
        log.info("Lancement du jeu");
         Menu.display();
    }
}
