package com.openclassrooms.game;
public class GameException extends Exception {
    public GameException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        super.toString();
        return getLocalizedMessage();
    }
}
