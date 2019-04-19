package com.openclassrooms.game;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * La classe Config contient les éléments permettant la lecture et l'extraction du fichier config.properties
 */
public class Config {

    /**
     * Création de l'instance Logger de la classe Config en utilisant la méthode getLogger()
     */
    private final static Logger log = Logger.getLogger(String.valueOf(Config.class));

    /**
     * Liste contenant les propriétés extraites du fichier config.properties
     */
    private static Properties props;

    /**
     * Lit le fichier et charge les éléments contenus dans le fichier config.properties
     *
     */
    private static void loadProps() {
        try {
            String propFileName = "config.properties";
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(propFileName);
            props = new Properties();
            props.load(inputStream);
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Récupére la proprieté qui a pour valeur le contenu de la variable passée en paramètre
     *
     * @return La valeur de la proprieté correspondant Ã  la valeur de la variable value
     */
    public static String getValue(String value) {
        if (props == null)
            loadProps();

        return props.getProperty(value);
    }
}