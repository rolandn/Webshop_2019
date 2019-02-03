package coucheAccesDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ClassMetier.Client;

public class ClientDAO extends BaseDAO<Client>
{
    /**
     * Constructeur
     */
    public ClientDAO(Connection sqlConn) {
        super(sqlConn);
    }

    /**
     * Méthode qui lit dans la DB un client spécifique
     *
     * @param num : le numéro de client
     * @return le client lu dans la DB
     * Le caractère "?" en sql correspond à "1" dans sqlCmd et qui renvoie le "num".
     */

    public Client Charger(int num) throws ExeceptionAccessBD {
        Client client = null;

        try {
            PreparedStatement sqlCmd =
                    SqlConn.prepareCall("select numClient, nom, prenom, adresse, email, mdp" +
                            "from client" + "where numClient = ?");
            sqlCmd.setInt(1, num);
            ResultSet sqlRes = sqlCmd.executeQuery();

            if (sqlRes.next() == true) {
                client = new Client(
                        sqlRes.getInt(1),
                        sqlRes.getString(2),
                        sqlRes.getString(3),
                        sqlRes.getString(4),
                        sqlRes.getString(5),
                        sqlRes.getString(6));
            }

            sqlRes.close();
            return client;
        }

        catch (Exception e)
        {
            throw new ExeceptionAccessBD(e.getMessage());
        }
    }

    /**
     * Méthode qui ajoute un client dans la DB
     *
     * @param obj: le client
     * @return un booléen qui indique si l'ajout s'est bien passé
     */

    public boolean Ajouter(Client obj) throws ExeceptionAccessBD {
        try {
            PreparedStatement sqlCmd = SqlConn.prepareCall("select max(numClient) + 1 from client");
            ResultSet sqlRes = sqlCmd.executeQuery();
            sqlRes.next();

            int numClient = sqlRes.getInt(1);
            if (sqlRes.wasNull()) numClient = 1;

            sqlCmd.close();

            sqlCmd = SqlConn.prepareCall("insert into client values(?,?,?,?,?,'123')");

            sqlCmd.setInt(1, numClient);
            sqlCmd.setString(2, obj.getNom());
            sqlCmd.setString(3, obj.getPrenom());
            sqlCmd.setString(4, obj.getAdresse());
            sqlCmd.setString(5, obj.getEmail());
       //     sqlCmd.setString(6, obj.getMdp());

            if ((sqlCmd.executeUpdate() == 0) ? false : true) return true;
            else return false;
        }

        catch (Exception e)
        {
            throw new ExeceptionAccessBD(e.getMessage());
        }
    }

    /**
     * Méthode qui modifie l'objet dans la base de données
     *
     * @param obj : le client (numClient)
     * @return un booléen indiquant si al modification s'est bien réalisée ou pas
     */

    @Override
    public boolean Modifier(Client obj) throws ExeceptionAccessBD {
        try {
            PreparedStatement sqlCmd = SqlConn.prepareCall("update client" +
                    "set numClient = ?, " +
                    "nom = ?, " +
                    "prenom = ?, " +
                    "adresse = ?, " +
                    "email = ?, " +
                    "mdp = ?, ");
            sqlCmd.setInt(1, obj.getNumClient());
            sqlCmd.setString(2, obj.getNom());
            sqlCmd.setString(3, obj.getPrenom());
            sqlCmd.setString(4, obj.getAdresse());
            sqlCmd.setString(5, obj.getEmail());
            sqlCmd.setString(6, obj.getMdp());

            return (sqlCmd.executeUpdate() == 0) ? false : true;
        } catch (Exception e) {
            throw new ExeceptionAccessBD(e.getMessage());
        }

    }

    /**
     * Méthode qui supprime un client
     *
     * @param num: numClient
     * @return un booléen qui indique si l'opération s'est bien déroulée ou pas
     */

    public boolean Supprimer(int num) throws ExeceptionAccessBD
    {
        try
        {
            PreparedStatement sqlCmd =
                    SqlConn.prepareCall("delete from client where numClient = ?");
            sqlCmd.setInt(1, num);
            sqlCmd.executeUpdate();
            int iRes = sqlCmd.executeUpdate();
            SqlConn.commit();
            SqlConn.setAutoCommit(true);

            return (iRes == 0) ? false : true;
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

            throw new ExeceptionAccessBD(e.getMessage());
        }
    }

    /**
     * Méthode qui liste tous les clients
     * @return la liste des clients
     */

    @Override
    public List<Client> ListerTous() throws ExeceptionAccessBD
    {
        ArrayList<Client> list = new ArrayList<>();

        try
        {
            PreparedStatement sqlCmd = SqlConn.prepareCall("select numClient, nom, prenom," +
                    "adresse, email, mdp" + "from client" + "order by nom asc");

            ResultSet sqlRes = sqlCmd.executeQuery();

            while (sqlRes.next() == true)
                list.add(new Client(sqlRes.getInt(1),
                        sqlRes.getString(2),
                        sqlRes.getString(3),
                        sqlRes.getString(4),
                        sqlRes.getString(5),
                        sqlRes.getString(6)));
            sqlRes.close();
        }

        catch (Exception e)
        {
            throw new ExeceptionAccessBD(e.getMessage());
        }

        return list;
    }
}
