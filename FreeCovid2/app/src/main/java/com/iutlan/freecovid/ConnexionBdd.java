package com.iutlan.freecovid;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ConnexionBdd {
    public ListeID lireBdd() {
        IdEnvoyes listebdd = new IdEnvoyes();
        try {
            Class.forName("org.postgresql.Driver");

            String url = "jdbc:postgresql://localhost:5432/Ecole"; // ADRESSE A MODIFIER ( LE MOT DE PASSE DU SERVEUR REND IMPOSSIBLE LA CONNECTION)
            String user = "root";
            String passwd = "pigeon42";

            Connection conn = DriverManager.getConnection(url, user, passwd);

            //Création d'un objet Statement
            Statement state = conn.createStatement();
            //L'objet ResultSet contient le résultat de la requête SQL
            ResultSet result = state.executeQuery("SELECT * FROM idMalades");
            //On récupère les MetaData
            ResultSetMetaData resultMeta = result.getMetaData();

            while(result.next()){
                for(int i = 1; i <= resultMeta.getColumnCount(); i++) {
                    listebdd.addId((Integer) result.getObject(i));
                }
            }

            result.close();
            state.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listebdd;
    }

    public void updateBdd(IdEnvoyes listeEnvoyes) {
        try {
            Class.forName("org.postgresql.Driver");

            String url = "jdbc:postgresql://localhost:5432/Ecole"; // ADRESSE A MODIFIER ( LE MOT DE PASSE DU SERVEUR REND IMPOSSIBLE LA CONNECTION)
            String user = "root";
            String passwd = "pigeon42";

            Connection conn = DriverManager.getConnection(url, user, passwd);

            //Création d'un objet Statement
            Statement state = conn.createStatement();
            //L'objet ResultSet contient le résultat de la requête SQL
            for(int i = 0; i< listeEnvoyes.getListe().size(); i++){
                state.executeUpdate("INSERT INTO idMalades (id) VALUES('"+listeEnvoyes.getListe().get(i)+"')");
            }

            state.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

