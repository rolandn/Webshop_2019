package ClassMetier;

public class Alcool extends Produit {
    private int DegreAlcool;
    private String Gout;
    private String Provenance;


    public Alcool(Alcool e) {
        super(e.getNumArticle(), e.getNom(), e.getNomImage(), e.getPrix(), e.getQuantiteStock(), e.getActive());
        DegreAlcool = e.getDegreAlcool();
        Gout = e.getGout();
        Provenance = e.getProvenance();
    }

    public Alcool(int numArticle, String nom, String nomImage, int prix, int quantiteStock, boolean active, int degreAlcool, String gout, String provenance)
    {
        super(numArticle, nom, nomImage, prix, quantiteStock, active);
        DegreAlcool = degreAlcool;
        Gout = gout;
        Provenance = provenance;

    }

    public Alcool() {
        super();
    }


    public int getDegreAlcool() {
        return DegreAlcool;
    }

    public int setDegreAlcool(int i) {
        DegreAlcool = i;
        return i;
    }

    public String getGout() {
        return Gout;
    }

    public void setGout(String g) {
        Gout = g;
    }

    public String getProvenance() {
        return Provenance;
    }

    public void setProvenance(String p) {
        Provenance = p;
    }


    /**
     * Méthode "SET" pour les variables venant de Produit
     */

    public int setNumArticle(int numArticle){
        NumArticle = numArticle;
        return numArticle;
    }

    public void setNom(String nom) {Nom = nom;}

    public void setNomImage(String nomImage){
        NomImage = nomImage;
    }

    public int setPrix(int prix){
        Prix = prix;
        return prix;
    }

    public boolean setActive (boolean active) {
        Active = active;
        return active;
    }

    public int setQuantiteStock(int quantiteStock){
        QuantiteStock = quantiteStock;
        return quantiteStock;
    }




}





