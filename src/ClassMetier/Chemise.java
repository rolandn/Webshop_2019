package ClassMetier;

public class Chemise extends Produit
{
    private int Taille;
    private String Couleur;
    private String Matiere;

    public Chemise(Chemise e) {
        super(e.getNumArticle(), e.getNom(), e.getNomImage(), e.getPrix(), e.getQuantiteStock(), e.getActive());
        Taille = e.getTaille();
        Couleur = e.getCouleur();
        Matiere = e.getMatiere();
    }

    public int getTaille() {
        return Taille;
    }

    public void setTaille(int t) {
        Taille = t;
    }

    public String getMatiere() {
        return Matiere;
    }

    public void setMatiere(String m) {
        Matiere = m;
    }

    public String getCouleur() {
        return Couleur;
    }

    public void setCouleur(String c) {
        Couleur = c;
    }


    public Chemise(int numArticle, String nom, String nomImage, int prix, int quantiteStock, boolean active, int taille, String couleur, String matiere) {
        super(numArticle, nom, nomImage, prix, quantiteStock, active);
        Taille = taille;
        Couleur = couleur;
        Matiere = matiere;
    }

}
