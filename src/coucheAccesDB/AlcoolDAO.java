package coucheAccesDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ClassMetier.Alcool;

public class AlcoolDAO extends BaseDAO<Alcool>
{
    /**
     * Constructeur
     */
    public AlcoolDAO(Connection sqlConn) {
        super(sqlConn);
    }

    /**
     * Méthode qui lit dans la DB un client spécifique
     *
     * @param num : le numéro de produit
     * @return l'alcool lu dans la DB
     * Le caractère "?" en sql correspond à "1" dans sqlCmd et qui renvoie le "num".
     */

    public Alcool Charger(int num) throws ExceptionAccessBD
    {
        Alcool alcool = null;

        try
        {
            SqlConn.setAutoCommit(false);
            PreparedStatement sqlCmd =
                    SqlConn.prepareCall("select numArticle, nom, nomImage, prix, quantiteStock, Active" +
                            "from article" + "where numArticle = ? and Active = 1");
            sqlCmd.setInt(1, num);

            sqlCmd =
                    SqlConn.prepareCall("select degreAlcool, gout, provenance" +
                            "from alcool" + "where numArticle = ?");
            sqlCmd.setInt(1, num);

            ResultSet iRes = sqlCmd.executeQuery();

            SqlConn.commit();
            SqlConn.setAutoCommit(true);


            if ((iRes.next() == true))
            {
                alcool = new Alcool(
                        iRes.getInt(1),       // numArticle
                        iRes.getString(2),    // nom
                        iRes.getString(3),    // nomImage
                        iRes.getInt(4),    // prix
                        iRes.getInt(5),    // quantiteStock
                        iRes.getBoolean(6), // Active
                        iRes.getInt(7),   // degreAlcool
                        iRes.getString(8),    // gout
                        iRes.getString(9));  // provenance
            }
            iRes.close();
            return alcool;
        }

        catch (Exception e)
        {
            try
            {
                SqlConn.rollback();
                SqlConn.setAutoCommit(true);
            }
            catch (Exception e1)
            {
            }
            throw new ExceptionAccessBD(e.getMessage());
        }
    }

    public boolean Ajouter(Alcool obj) throws ExceptionAccessBD
    {

        try
        {
            SqlConn.setAutoCommit(false);

            PreparedStatement sqlCmd = SqlConn.prepareCall("select max(NumArticle) + 1 from produit");
            ResultSet sqlRes = sqlCmd.executeQuery();
            sqlRes.next();

            int NumArticle = sqlRes.getInt(1);
            if (sqlRes.wasNull()) NumArticle = 1;

            sqlCmd.close();

            sqlCmd = SqlConn.prepareCall("insert into produit values(?,?,?,?,?)");

            sqlCmd.setInt(1, NumArticle);
            sqlCmd.setString(2, obj.getNom());
            sqlCmd.setString(3, obj.getNomImage());
            sqlCmd.setInt(4, obj.getPrix());
            sqlCmd.setInt(5, obj.getQuantiteStock());

            sqlCmd.executeUpdate();

            /// VERIFIER SI PAS D'ERREUR ICI !!! //////

            //PreparedStatement sqlCmd2 = SqlConn.prepareCall("select max(NumArticle) from produit");
            //ResultSet sqlRes2 = sqlCmd.executeQuery();
            //sqlRes.next();

           // int NumArticle2 = sqlRes.getInt(1);


            sqlCmd = SqlConn.prepareCall("insert into alcool values(?,?,?,?)");

            sqlCmd.setInt(1, NumArticle);
            sqlCmd.setInt(2, obj.getDegreAlcool());
            sqlCmd.setString(3, obj.getGout());
            sqlCmd.setString(4, obj.getProvenance());

            sqlCmd.executeUpdate();
            SqlConn.commit();
            SqlConn.setAutoCommit(true);

            if ((sqlCmd.executeUpdate() == 0) ? false : true) return true;
            else return false;
        }

        catch (Exception e)
        {
            try
            {
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
    public boolean Modifier(Alcool obj) {
        return false;
    }

    @Override
    public List<Alcool> ListerTous() {
        return null;
    }

    @Override
    public boolean Supprimer(int num) {
        return false;
    }
}
