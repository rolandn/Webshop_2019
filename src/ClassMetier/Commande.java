package ClassMetier;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Commande
{
    private int IdCommande;                 // id de la commande
    private LocalDate Date;                 //Date de la commande
    private LocalTime Heure;                // Heure de la commande
    private int IdClient;                   // Id du client qui passe la commande
    private int Montant;                    // Montant de la commande
    private boolean Livre;                  // Si livré = true, si pas encore livré = false
//    private int IdListe;                    // Id de la liste qui regroupe les lignes de commande


    /**
     * Constructeurs
     * @param num
     * @param parse
     * @param string
     * @param aDouble
     * @param aBoolean
     * @param sqlResBoolean
     */

    public Commande(int num, LocalDate parse, String string, double aDouble, boolean aBoolean, boolean sqlResBoolean) {}

    public Commande(Commande commande)
    {
        IdCommande = commande.IdCommande;
        Date = commande.Date;
        Heure = commande.Heure;
        IdClient = commande.IdClient;
        Montant = commande.Montant;
        Livre = commande.Livre;
//        IdListe = commande.IdListe;
    }

    public Commande(int idCommande, LocalDate date, LocalTime heure, int idClient, int montant, boolean livre)
    {
        IdCommande = idCommande;
        Date = date;
        Heure = heure;
        IdClient = idClient;
        Montant = montant;
        Livre = livre;
       // IdListe = idListe;
    }


    /**
     * propriété GET pour obtenir la valeur
     * de chaque variable.
     */

    public int getIdCommande() {return IdCommande;}
    public LocalDate getDate() {
        return Date;
    }
    public LocalTime getHeure() {
        return Heure;
    }
    public int getIdClient() {
        return IdClient;
    }
    public int getMontant() {
        return Montant;
    }
    public boolean getLivre() {
        return Livre;
    }
//    public int getIdListe() {return IdListe;}

    /** SET pour modifier la valeur du boolean livré ou pas
     * @param livre
     */
    public void setlivre(boolean livre) {Livre = livre;}

    public boolean isLivre() {
        return Livre;
    }


    @Override
    public String toString() { return IdCommande + " " + IdClient; }

}
