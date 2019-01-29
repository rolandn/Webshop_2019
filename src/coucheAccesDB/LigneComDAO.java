package coucheAccesDB;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ClassMetier.LigneCom;

public class LigneComDAO extends BaseDAO<LigneCom>
{
    /**
     * Constructeur
     */
    public LigneComDAO(Connection sqlConn) {
        super(sqlConn);
    }

    /**
     * Méthode qui lit dans la DB une ligne spécifique
     *
     * @param num : le numéro de ligne de commande
     */

    public LigneCom Charger(int num) throws ExceptionAccessBD
    {
        LigneCom ligneCom = null;

        try {
            SqlConn.setAutoCommit(false);
            PreparedStatement sqlCmd =
                    SqlConn.prepareCall("select idLigne, idCom, idProduit, quantite" +
                            "from ligne" + "where idCom = ?");
            sqlCmd.setInt(1, num);

            ResultSet iRes = sqlCmd.executeQuery();

            SqlConn.commit();
            SqlConn.setAutoCommit(true);


            if (iRes.next() == true)
            {
                ligneCom = new LigneCom(
                        iRes.getInt(1),  // Id Ligne
                        iRes.getInt(2),  // Id Commande
                        iRes.getInt(3),  // Id Produit
                        iRes.getInt(4));  // Quantite
            }

            iRes.close();
            return ligneCom;
        }

        catch (Exception e)
        {
            try {
                SqlConn.rollback();
                SqlConn.setAutoCommit(true);
            }
            catch (Exception e1)
            {
            }
            throw new ExceptionAccessBD(e.getMessage());
        }


    }

    @Override
    public boolean Ajouter(LigneCom obj) throws ExceptionAccessBD {
        return false;
    }

    @Override
    public boolean Modifier(LigneCom obj) throws ExceptionAccessBD {
        return false;
    }

    @Override
    public boolean Supprimer(int num) throws ExceptionAccessBD {
        return false;
    }

    @Override
    public List<LigneCom> ListerTous() throws ExceptionAccessBD {
        ArrayList<LigneCom> list = new ArrayList<>();

        try
        {
            PreparedStatement sqlCmd = SqlConn.prepareCall("select IdLigne, IdProduit, quantite"+ "from ligne" + "where idCommande = ?");

            ResultSet sqlRes = sqlCmd.executeQuery();

            while (sqlRes.next() == true)
                list.add(new LigneCom(sqlRes.getInt(1),
                        sqlRes.getString(2),
                        sqlRes.getString(3),
                        sqlRes.getString(4),
                        sqlRes.getString(5),
                        sqlRes.getString(6)));
            sqlRes.close();
        }

        catch (Exception e)
        {
            try {
                SqlConn.rollback();
                SqlConn.setAutoCommit(true);
            }
            catch (Exception e1)
            {
                throw new ExceptionAccessBD(e.getMessage());
            }
            throw new ExceptionAccessBD(e.getMessage());
        }
            return list;
    }
}
