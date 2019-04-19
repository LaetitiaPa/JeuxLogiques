package com.openclassrooms.game;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * La classe Config contient les �l�ments permettant la lecture et l'extraction du fichier config.properties
 */
public class Config {

    /**
     * Cr�ation de l'instance Logger de la classe Config en utilisant la m�thode getLogger()
     */
    private final static Logger log = Logger.getLogger(String.valueOf(Config.class));

    /**
     * Liste contenant les propri�t�s extraites du fichier config.properties
     */
    private static Properties props;

    /**
     * Lit le fichier et charge les �l�ments contenus dans le fichier config.properties
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
     * R�cup�re la propriet� qui a pour valeur le contenu de la variable pass�e en param�tre
     *
     * @return La valeur de la propriet� correspondant à la valeur de la variable value
     */
    public static String getValue(String value) {
        if (props == null)
            loadProps();

        return props.getProperty(value);
    }
}