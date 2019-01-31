package coucheAccesDB;


import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import ClassMetier.Commande;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CommandeDAO extends BaseDAO<Commande> {
    /**
     * Constructeur
     *
     * @param sqlConn
     */
    public CommandeDAO(Connection sqlConn) {
        super(sqlConn);
    }

    /**
     * Méthodes abstaites
     *
     * @param idCommande
     */
    @Override
    public Commande Charger(int idCommande) throws ExeceptionAccessBD, ExeceptionAccessBD {
        Commande commande = null;

        try
        {
            // le point d'interrogation est un parameter placeholder
            PreparedStatement sqlCmd=
                    SqlConn.prepareCall("select * from commande"+
                            "where idCommande = ?");

            sqlCmd.setInt(1,idCommande);

            ResultSet sqlRes = sqlCmd.executeQuery();

            if(sqlRes.next() == true)
            {
                commande = new Commande(idCommande,
                        LocalDate.parse(sqlRes.getString(2),DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                        LocalTime.parse(sqlRes.getString(3),DateTimeFormatter.ofPattern("hh:mm:ss")),
                        sqlRes.getInt(4),
                        sqlRes.getInt(5),
                        sqlRes.getBoolean(6));
            }

            sqlRes.close();

            return commande;

        }catch (Exception e)
        {
            throw new ExeceptionAccessBD(e.getMessage());
        }
    }

    @Override
    public boolean Ajouter(Commande obj) throws ExeceptionAccessBD {
        return false;
    }

    @Override
    public boolean Modifier(Commande obj) throws ExeceptionAccessBD, ExeceptionAccessBD {
        try {
            PreparedStatement sqlCmd = SqlConn.prepareCall("UPDATE Commande " +
                    "SET livre = ? " +
                    "WHERE idCommande = ?");

            String livre = (obj.isLivre())?"1":"0";
            sqlCmd.setString(1,livre);
            sqlCmd.setInt(2,obj.getIdCommande());

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
    public List<Commande> ListerTous() throws ExeceptionAccessBD, ExeceptionAccessBD {
        List<Commande> liste = new ArrayList<>();

        try {
            PreparedStatement sqlCmd =
                    SqlConn.prepareCall("SELECT * FROM commande");

            ResultSet sqlRes = sqlCmd.executeQuery();

            while (sqlRes.next())
            {
                liste.add(new Commande(sqlRes.getInt(1),
                        LocalDate.parse(sqlRes.getString(2),
                                DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                        LocalTime.parse(sqlRes.getString(3)),
                        sqlRes.getInt(4),
                        sqlRes.getInt(5),
                        sqlRes.getBoolean(6)));
            }

            sqlRes.close();
        } catch (Exception e) {
            throw new ExeceptionAccessBD(e.getMessage());
        }

        return liste;
    }
}
