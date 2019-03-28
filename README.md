# Présentation du projet  


Ce projet s'inscrit dans le parcours de formation Openclassrooms de Développeur d'Application Java.
Il a pour but le développement d'une application comprenant 2 jeux:

- Jeu de recherche +/-

- Mastermind

Le joueur a le choix entre 3 modes:

- Challenger: Le joueur doit trouver la combinaison secrète de l'ordinateur

- Défenseur: L'ordinateur doit à son tour trouver la combinaison secrète du joueur

- Duel: Tour à tour, le joueur et l'ordinateur s'affrontent pour trouver la combinaison secrète de chacun


# Présentation des livrables attendus

Ce projet contient les livrables suivants:

- Le code source de l'application
- La Javadoc correspondante
- Les fichiers de configuration


# Préquis

Vous devez disposer au minimum de la version JDK 1.8.
Si ce n'est pas le cas, vous pouvez télécharger la version sur ce lien: https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html



# Synthèse règle des jeux

Voici les règles des jeux présents dans l'application:


- Jeu de recherche +/-

Découvrir la combinaison à x chiffres de l'adversaire (le défenseur). Pour ce faire, l'attaquant fait une proposition. 
Le défenseur indique pour chaque chiffre de la combinaison proposée si le chiffre de sa combinaison est plus grand (+), plus petit (-) ou si c'est le bon chiffre (=).

L'attaquant doit deviner la combinaison secrète en un nombre limité d'essais.

- Mastermind

Le but : découvrir la combinaison à x chiffres/couleurs de l'adversaire (le défenseur). Pour ce faire, l'attaquant fait une proposition. 
Le défenseur indique pour chaque proposition le nombre de chiffre/couleur de la proposition qui apparaissent à la bonne place ou présent mais à la mauvaise place dans la combinaison secrète.

L'attaquant doit deviner la combinaison secrète en un nombre limité d'essais.


# Lancement du jeu

** Aller dans le 'target':
cd  target

** Lancer l'exécution
java -jar  game.jar

ou 

Exécuter le fichier game.bat se trouvant dans le dossier target.


# Lien Github

https://github.com/LaetitiaPa/JeuxLogiques
