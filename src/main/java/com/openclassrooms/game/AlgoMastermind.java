package com.openclassrooms.game;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import static java.lang.String.*;

public class AlgoMastermind {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String response = "";
        int nbPlace = 0;
        int nbPresent = 0;


        // 1 - génération chiffre aléatoire (4 chiffres)

        int secretKey = ThreadLocalRandom.current().nextInt(1000, 9000);
        System.out.println(secretKey);

        // 2 - Stocker la combinaison secrète dans une liste

        String secretKey_ = Integer.toString(secretKey);
        String[] keys = secretKey_.split("");
        List<String> secretKeys = Arrays.asList(keys);

        // 3 - Tant que la réponse != combinaison

        while (response != "4 bien placés") {

            response = "";

            // 4 - Joueur saisie une proposition
            System.out.println("A vous de jouer: ");

            int proposition = input.nextInt();

            // 5 - Converti propositon dans une liste
            String proposition_ = Integer.toString(proposition);
            String[] props = proposition_.split("");
            List<String> propositions = Arrays.asList(props);



            // 6 - Parcours des 2 listes et comparaisons
            for (int i = 0; i < secretKeys.size(); i++) {

                System.out.println(propositions.get(i));
                System.out.println(secretKeys.get(i));

            }

        }

    }
}



        // 7 - Création d'une 3e liste stockant le résultat
        // 6 - Vérification résultat
        // 8 - réponse et saisie d'une nouvelle proposition
        // 9 - Si proposition == combinaison alors afficher "vous avez gagné"


        // 1 - génération chiffre aléatoire (4 chiffres)


        // 2 - Stocker la combinaison secrète dans une liste

        // 3 - Tant que la réponse != combinaison
        // 3 - Joueur saisie une proposition
        // 4 - Converti propositon dans une liste
        // 4 - Parcours des 2 listes et comparaisons
        // 5 - Création d'une 3e liste stockant le résultat
        // 6 - Vérification résultat
        // 7 - réponse et saisie d'une nouvelle proposition
        // 7 - Si == combinaison alors afficher "vous avez gagné"




