package com.openclassrooms.game;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    private final static Logger log = Logger.getLogger(String.valueOf(Main.class));

    public static void main(String[] args) {
        log.info("Lancement du jeu");
        Menu menu = new Menu();
        menu.display();
    }
}
