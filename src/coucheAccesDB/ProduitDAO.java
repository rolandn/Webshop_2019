package coucheAccesDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import ClassMetier.Client;
import ClassMetier.Produit;

public class ProduitDAO extends BaseDAO<Produit>
{


    /**
     * Constructeur
     *
     * @param sqlConn
     */
    public ProduitDAO(Connection sqlConn) {
        super(sqlConn);
    }

    @Override
    public Produit Charger(int num) throws ExeceptionAccessBD {
        return null;
    }

    @Override
    public boolean Ajouter(Produit obj) throws ExeceptionAccessBD {
        return false;
    }

    /**
     *  A FAIRE !!! (c'est un copier/coller d'autre choses)
     *
     */
    @Override
    public boolean Modifier(Produit obj) throws ExeceptionAccessBD {

        try {
            PreparedStatement sqlCmd = SqlConn.prepareCall("UPDATE produit SET quantiteStock = ?, Active = ? WHERE NumArticle = ?");

            sqlCmd.setInt(1,obj.getQuantiteStock());
            sqlCmd.setBoolean(2, obj.getActive());
            sqlCmd.setInt(3,obj.getNumArticle());

            return (sqlCmd.executeUpdate() == 0)? false : true;

        } catch (Exception e) {
            throw new ExeceptionAccessBD(e.getMessage());
        }

    }

    @Override
    public boolean Supprimer(int num) throws ExeceptionAccessBD {
        return false;
    }

    @Override
    public List<Produit> ListerTous() throws ExeceptionAccessBD {

        ArrayList<Produit> list = new ArrayList<>();

        try
        {
            PreparedStatement sqlCmd = SqlConn.prepareCall("select NumArticle, nom, nomImage, prix, quantiteStock, Active from produit");

            ResultSet sqlRes = sqlCmd.executeQuery();

            while (sqlRes.next() == true)
                list.add(new Produit(sqlRes.getInt(1),
                        sqlRes.getString(2),
                        sqlRes.getString(3),
                        sqlRes.getInt(4),
                        sqlRes.getInt(5),
                        sqlRes.getBoolean(6)));
            sqlRes.close();
        }

        catch (Exception e)
        {
            throw new ExeceptionAccessBD(e.getMessage());
        }

        return list;
    }

    public  List<Produit> ListerCommande(int idCommande)throws ExeceptionAccessBD
    {
        List<Produit> liste = new ArrayList<>();

        try {
            PreparedStatement sqlCmd =
                    SqlConn.prepareCall("SELECT produit.NumArticle, produit.nom, ligne.quantite,"+
                            "produit.prix,produit.nomImage " +
                            "FROM produit, commande, ligne " +
                            "where ligne.idCommande = commande.idCommande " +
                            "AND ligne.idProduit = produit.NumArticle "+
                            "AND commande.idCommande = ? "
                    );

            sqlCmd.setInt(1,idCommande);
            ResultSet sqlRes = sqlCmd.executeQuery();

            while (sqlRes.next() == true)
            {
                int quantiteCommande = sqlRes.getInt(3);
                double prixTotal= quantiteCommande*sqlRes.getDouble(4);
                liste.add(new Produit(sqlRes.getInt(1),
                        sqlRes.getString(2),
                        quantiteCommande,
                        prixTotal));
            }

            sqlRes.close();
        } catch (Exception e) {
            throw new ExeceptionAccessBD(e.getMessage());
        }

        return liste;
    }

}
