package coucheAccesDB;

import ClassMetier.*;
import couchePresentation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class FabriqueDAO
{
    private static  FabriqueDAO instance = new FabriqueDAO();

    private  Connection SqlConn = null;

    private  FabriqueDAO(){}

    public static  FabriqueDAO getInstance()
    {
        return instance;
    }

    public  void  creerConnexion() throws ExeceptionAccessBD
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
            throw new ExeceptionAccessBD(e.getMessage());
        }
    }

 //   public ClientDAO getInstClientDAO() {return new ClientDAO(SqlConn); }

    public AlcoolDAO getInstAlcoolDAO() {return new AlcoolDAO(SqlConn);}

    public BiereDAO getInstBiereDAO() {return new BiereDAO(SqlConn);}

 //   public ProduitDAO getInsProduitDAO() {return new ProduitDAO(SqlConn);}

    public CommandeDAO getInsCommandeDAO() {return  new CommandeDAO(SqlConn);}


//    public LigneComDAO getInsLigneComDAO() {return new LigneComDAO(SqlConn);}


}
