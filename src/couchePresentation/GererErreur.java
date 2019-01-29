package couchePresentation;

import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import coucheAccesDB.ExeceptionAccessBD;
import coucheAccesDB.FabriqueDAO;


public class GererErreur
{
    public static  void GererErreurSQL(String classe, String methode, String msqgDetail)
    {
        FabriqueDAO.getInstance().creerConnexion();
    }

    public static void GererErreurGen(String classe,String methode, String msqgDetail)
    {
        try {
            FileWriter fichier = new FileWriter("trace.log", true);

            fichier.append(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + " " +
                    LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")) + " " +
                    "méthode " + methode + " " + msqgDetail + System.lineSeparator());

            fichier.close();

            }

            catch (Exception e)
            {
                System.out.println("problème lors de l'écriture dans le fichier de trace");
                e.printStackTrace();
            }
    }
}
