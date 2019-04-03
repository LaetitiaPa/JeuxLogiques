package com.openclassrooms.game;
public class GameException extends Exception {
    public GameException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Utilisation c�t� utilisateur, affiche seulement le message d'erreur.
     */
    @Override
    public String toString() {
        super.toString();
        return getLocalizedMessage();
    }
}
