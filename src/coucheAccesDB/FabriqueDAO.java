package coucheAccesDB;

import ClassMetier.Commande;
import org.jetbrains.annotations.Contract;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class FabriqueDAO
{
    private static  FabriqueDAO instance = new FabriqueDAO();

    private  Connection SqlConn = null;

    private  FabriqueDAO(){}

    @Contract(pure = true)
    public static  FabriqueDAO getInstance()
    {
        return instance;
    }

    public  void  creerConnexion() throws ExceptionAccessBD
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            SqlConn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;" +
            "database=WEBSHOP;" +
            "user=genial;" +
            "password=super;");
        }

        catch (Exception e)
        {
            e.printStackTrace();
            throw new ExceptionAccessBD(e.getMessage());
        }
    }

    public ClientDAO getInstClientDAO()
    {
        return new ClientDAO(SqlConn);
    }

    public AlcoolDAO getInstAlcoolDAO() {return new AlcoolDAO(SqlConn);}

    public ProduitDAO getInsProduitDAO() {return new ProduitDAO(SqlConn);}

    public CommandeDAO getInsCommandeDAO() {return  new CommandeDAO(SqlConn);}


    public LigneComDAO getInsLigneComDAO() {return new LigneComDAO(SqlConn);}
}
