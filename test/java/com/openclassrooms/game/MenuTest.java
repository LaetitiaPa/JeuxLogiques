package com.openclassrooms.game;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

class MenuTest {

    /**
     * Creates a new byte array output stream
     */
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    /**
     * Set up the byte array output stream
     */
    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Restore the byte array output stream
     */
    @AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
    }

    /**
     * Testing behaviour when gameChoice == 1
     */
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

    /**
     * Testing behaviour when gameChoice == 2
     */
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

    /**
     * Testing behaviour when gameChoice == 3
     */
    @Test
    public void Given_GameChoice3_When_AskGame_Then_DisplayQuittingSentence() {
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