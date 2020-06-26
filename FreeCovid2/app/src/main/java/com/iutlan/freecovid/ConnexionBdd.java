package com.iutlan.freecovid;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ConnexionBdd {
    public ListeID lireBdd() { //retourne une liste contenant tous les id de la bdd
        IdEnvoyes listebdd = new IdEnvoyes();
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://193.168.147.250:3306/freecovid";
            String user = "freecovid";
            String passwd = "freecovid";

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

    public void updateBdd(IdEnvoyes listeEnvoyes) { //Insere une liste d'id dans la BDD
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://193.168.147.250:3306/freecovid";
            String user = "freecovid";
            String passwd = "freecovid";

            Connection conn = DriverManager.getConnection(url, user, passwd);

            //Création d'un objet Statement
            Statement state = conn.createStatement();
            for(int i = 1; i< listeEnvoyes.getListe().size(); i++){
                state.executeUpdate("INSERT INTO idMalades (id) VALUES('"+listeEnvoyes.getListe().get(i)+"')");
            }

            state.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean verifConnexion(String email, String mdp) { //retourne un boolean si l'email et le mot de passe sont contenus dans la bdd
        boolean bonmdp = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://193.168.147.250:3306/freecovid";
            String user = "freecovid";
            String passwd = "freecovid";

            Connection conn = DriverManager.getConnection(url, user, passwd);

            //Création d'un objet Statement
            Statement state = conn.createStatement();
            //L'objet ResultSet contient le résultat de la requête SQL
            ResultSet result = state.executeQuery("SELECT nom_utilisateur FROM Medecins");
            //On récupère les MetaData
            ResultSetMetaData resultMeta = result.getMetaData();

            while(result.next()){

                for(int i = 1; i <= resultMeta.getColumnCount(); i++) {
                    if(email.toString().equals(result.getObject(i).toString())){
                        Statement state2 = conn.createStatement();
                        ResultSet result2 = state.executeQuery("SELECT mdp FROM Medecins WHERE nom_utilisateur='"+email.toString()+"'");
                        if(mdp.toString().equals(result2.getObject(1).toString())){
                            bonmdp=true;
                        }

                    }
                }
            }

            result.close();
            state.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bonmdp;
    }
}

