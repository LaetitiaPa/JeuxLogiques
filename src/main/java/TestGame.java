import com.openclassrooms.game.Config;
import com.openclassrooms.game.GameException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TestGame {

    public static void main(String[] toto) {
        TestGame game = new TestGame();
        game.getScanNumber();

    }

    public void getScanNumber() {
        int nbreChiffres = Integer.parseInt(Config.getValue("nbreChiffres"));
        System.out.println("Veuillez saisir une combinaison de " + nbreChiffres + " chiffres ");
        boolean demandeSaisie = true;
        boolean isANumber = true;

        while (demandeSaisie) {
            Scanner scan = new Scanner(System.in);
            Integer nbreSaisi = 0;
            try {
                nbreSaisi = scan.nextInt();
                isANumber = true;
            }
            catch(InputMismatchException inputException) {
                System.out.println("Veuillez saisir un chiffre !!!! ");
                isANumber = false;
            }
             if(isANumber) {
                 demandeSaisie = verifNumber(nbreChiffres, demandeSaisie, nbreSaisi);
             }
        }
        System.out.println("Vous êtes sorti de la boucle");
    }

    private boolean verifNumber(int nbreChiffres, boolean demandeSaisie, Integer nbreSaisi) {
        int tailleNbre = nbreSaisi.toString().length();
        System.out.println("La taille du nombre est: " + tailleNbre);

        if (tailleNbre == nbreChiffres) {
            System.out.println("Bravo vous avez tapé " + nbreChiffres + " chiffres");
            demandeSaisie = false;
        } else {
            System.out.println("Merci de ressaisir une combinaison de " + nbreChiffres);
        }
        return demandeSaisie;
    }
}



