package ClassMetier;

public class Biere extends Produit {
    private String Gout;
    private String Recipient;
    private Boolean Alcool;


    public Biere(Biere e) {
        super(e.getNumArticle(), e.getNom(), e.getNomImage(), e.getPrix(), e.getQuantiteStock(), e.getActive());
        Alcool = e.getAlcool();
        Gout = e.getGout();
        Recipient = e.getRecipient();
    }

    public Biere(int numArticle, String nom, String nomImage, int prix, int quantiteStock, boolean active, boolean alcool, String gout, String recipient)
    {
        super(numArticle, nom, nomImage, prix, quantiteStock, active);
        Alcool = alcool;
        Gout = gout;
        Recipient = recipient;

    }

    public Biere() {
        super();
    }

    /**
     * Méthodes GET SET pour les variables venant de Biere
     */


    public boolean getAlcool() {
        return this.Alcool;
    }

    public void setAlcool(Boolean Alcool) {this.Alcool = Alcool;}



    public String getGout() {
        return Gout;
    }

    public void setGout(String g) {
        Gout = g;
    }



    public String getRecipient() {
        return Recipient;
    }

    public void setRecipient(String p) {
        Recipient = p;
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

    public boolean setAlcool (boolean active) {
        Active = active;
        return active;
    }

    public int setQuantiteStock(int quantiteStock){
        QuantiteStock = quantiteStock;
        return quantiteStock;
    }


    public void setAlcool(String text) {
    }
}





