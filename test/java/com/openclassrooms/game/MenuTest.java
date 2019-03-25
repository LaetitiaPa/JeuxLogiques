package com.openclassrooms.game;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

class MenuTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
    }
    @Test
    public void Given_GameChoice1_When_AskGame_Then_DisplayMoreLessSentence() {
        Menu menu = new Menu();
        menu.askGame();
        assertEquals("Veuillez choisir un jeu :\n" +
                " 1- Plus ou Moins\n" +
                " 2- Mastermind\n" +
                " 3- Quitter\n" +
                "Vous avez choisi comme jeu : Recherche +/-" +
                "\n", outContent.toString().replace("\r\n", "\n"));
    }

    @Test
    public void Given_GameChoice2_When_AskGame_Then_DisplayMastermindSentence() {
        Menu menu = new Menu();
        menu.askGame();
        assertEquals("Veuillez choisir un jeu :\n" +
                " 1- Plus ou Moins\n" +
                " 2- Mastermind\n" +
                " 3- Quitter\n" +
                "Vous avez choisi comme jeu : Mastermind" +
                "\n", outContent.toString().replace("\r\n", "\n"));
    }

    @Test
    public void Given_GameChoice3_When_AskGame_Then_DisplayMastermindSentence() {
        Menu menu = new Menu();
        menu.askGame();
        assertEquals("Veuillez choisir un jeu :\n" +
                " 1- Plus ou Moins\n" +
                " 2- Mastermind\n" +
                " 3- Quitter\n" +
                "Vous avez choisi de quitter le jeu" +
                "\n", outContent.toString().replace("\r\n", "\n"));
    }
}