package couchePresentation;

import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import coucheAccesDB.ExeceptionAccessBD;
import coucheAccesDB.FabriqueDAO;


public class GererErreur
{
    /**
     * méthode qui ajoute un message d'erreur détaillé relatif à un problème de base de données...
     * ... dans un fichier de trace et qui crée une nouvelle connexion avec la base de données
     * @param classe : la classe dans le programme où est survenue l'erreur
     * @param methode : la méthode dans la classe où est survenue l'erreur
     * @param msgDetail : le message d'erreur détaillé
     */
    public static void ErreurSQL(String classe, String methode, String msgDetail)
    {
        ErreurGen(classe, methode, msgDetail);

        // tenter de créer une nouvelle connexion avec la base de données.
        // si cette tentative échoue, terminer l'exécution du programme

        try
        {
            FabriqueDAO.getInstance().creerConnexion();
        }
        catch(ExeceptionAccessBD ex)
        {
            System.out.println("Problème pour se connecter à la base de données!");
            System.exit(0);
        }
    }

/**
 * méthode qui ajoute un message d'erreur détaillé dans un fichier de trace
 * @param classe : la classe dans le programme où est survenue l'erreur
 * @param methode : la méthode dans la classe où est survenue l'erreur
 * @param msgDetail : le message d'erreur détaillé
 */

public static void ErreurGen(String classe, String methode, String msgDetail)
{
    try
    {
        FileWriter fichier = new FileWriter("trace.log", true);
        fichier.append(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + " " +
                LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")) + " -- " +
                        "classe " + classe + " -- " +
                        "méthode " + methode + " -- " +
                        msgDetail + System.lineSeparator());

        fichier.close();
    }
    catch(Exception e)
    {
        System.out.println("Problème lors de l'écriture dans le fichier de trace!");
    }
}
}

