package ClassMetier;

public class LigneCom
{
    private int IdLigne;
    public int IdCom;
    private int IdProduit;
    private int Quantite;

    /**
     * propriété GET pour obtenir la valeur
     * de chaque variable.
     */

    public int getIdLigne() {return IdLigne;}
    public int getIdCom() {return IdCom;}
    public int getIdProduit() {return IdProduit;}
    public int getQuantite () {return Quantite;}

    public LigneCom(int anInt, String string, String sqlResString, String resString, String s, String string1) {}

    public LigneCom(LigneCom ligneCom)
    {
        IdLigne = ligneCom.IdLigne;
        IdCom = ligneCom.IdCom;
        IdProduit = ligneCom.IdProduit;
        Quantite = ligneCom.Quantite;
    }

    public LigneCom (int idLigne,int idCom, int idProduit, int quantite)
    {
        IdLigne = idLigne;
        IdCom = idCom;
        IdProduit = idProduit;
        Quantite = quantite;
    }

}
