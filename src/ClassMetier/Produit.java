package ClassMetier;

public class Produit
{
    public int NumArticle;
    public String Nom;
    public String NomImage;
    public int Prix;
    public int QuantiteStock;
    public boolean Active;

    /**
     * Contructeurs
     *
     */

    public Produit (Produit produit)
    {
        NumArticle = produit.getNumArticle();
        Nom = produit.getNom();
        NomImage = produit.getNomImage();
        Prix = produit.getPrix();
        QuantiteStock = produit.getQuantiteStock();
        Active = produit.getActive();
    }

    public Produit (int numArticle, String nom, String nomImage, int prix, int quantiteStock, boolean active)
    {
        NumArticle = numArticle;
        Nom = nom;
        NomImage =nomImage;
        Prix = prix;
        QuantiteStock = quantiteStock;
        Active = active;
    }

    public Produit(int anInt, String string, int quantiteCommande, double prixTotal) {

    }

    public Produit() {

    }

    /**
     * Accès aux variables
     */

    public int getNumArticle() {
        return NumArticle;
    }

    public int setNumArticle() {
        return NumArticle;
    }

    public boolean getActive() {
        return Active;
    }

    public boolean setActive(boolean active) {

        return active;
    }

    public String getNom() {
        return Nom;
    }



    public String getNomImage() {
        return NomImage;
    }

    public int getPrix() {
        return Prix;
    }

    public int getQuantiteStock() {
        return QuantiteStock;
    }


    public int setQuantiteStock(int quantiteStock)
    {
        QuantiteStock = quantiteStock;
        return QuantiteStock;
    }

    @Override
    public String toString() {
        return Nom + " "+ NumArticle; }




    /**
     * Modifier une quantité en stock
     */



}
