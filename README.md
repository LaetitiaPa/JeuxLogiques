# Presentation du mini projet maven
C'est un mini projet maven qui intègre la gestion des dependances log4j et junit et l'intégration de log4j.xml et config.properties de base
et configexternal.properties pour externaliser le .properties hors du jar.

# Pré requis
- Avoir minimum JAVA 1.8
- Installer Maven (disponible en ligne de commande)

# Build du projet 

mvn  clean package

# Execution du projet
** Aller dans le 'target':
cd  target
** Lancer l'exceution
java -jar  game.jar

** Pour externaliser le fichier de config.properties
créer un repertoire 'config' au niveau que game.jar et ajouter un fichier  configexternal.properties
** lancer
java  -jar game.jar  --ext

# Logs
les fichiers de logs sont dans le "logs/game.log"  au même niveau que game.jar