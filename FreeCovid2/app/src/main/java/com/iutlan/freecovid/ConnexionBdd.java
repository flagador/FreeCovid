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
            Class.forName("org.postgresql.Driver"); //ERREUR ICI LORS DE TESTS

            String url = "jdbc:postgresql://193.168.147.250"; // ADRESSE A MODIFIER ( LE MOT DE PASSE DU SERVEUR REND IMPOSSIBLE LA CONNECTION)
            String user = "root";
            String passwd = "";

            Connection conn = DriverManager.getConnection(url, user, passwd);

            //Création d'un objet Statement
            Statement state = conn.createStatement();
            //L'objet ResultSet contient le résultat de la requête SQL
            ResultSet result = state.executeQuery("SELECT * FROM freecovid.idMalades");
            //On récupère les MetaData
            ResultSetMetaData resultMeta = result.getMetaData();

            while(result.next()){
                for(int i = 0; i <= resultMeta.getColumnCount(); i++) {
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
            Class.forName("org.postgresql.Driver"); //ERREUR ICI LORS DE TESTS

            String url = "jdbc:postgresql://193.168.147.250"; // ADRESSE A MODIFIER ( LE MOT DE PASSE DU SERVEUR REND IMPOSSIBLE LA CONNECTION)
            String user = "root";
            String passwd = "";

            Connection conn = DriverManager.getConnection(url, user, passwd);

            //Création d'un objet Statement
            Statement state = conn.createStatement();
            for(int i = 0; i< listeEnvoyes.getListe().size(); i++){
                state.executeUpdate("INSERT INTO freecovid.idMalades (id) VALUES('"+listeEnvoyes.getListe().get(i)+"')");
            }

            state.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean verifConnexion(String email, String mdp) { //retourne un boolean si l'email et le mot de passe sont contenus dans la bdd
        boolean bonmdp = false;
        try {
            Class.forName("org.postgresql.Driver"); //ERREUR ICI LORS DE TESTS

            String url = "jdbc:postgresql://193.168.147.250"; // ADRESSE A MODIFIER ( LE MOT DE PASSE DU SERVEUR REND IMPOSSIBLE LA CONNECTION)
            String user = "root";
            String passwd = "";

            Connection conn = DriverManager.getConnection(url, user, passwd);

            //Création d'un objet Statement
            Statement state = conn.createStatement();
            //L'objet ResultSet contient le résultat de la requête SQL
            ResultSet result = state.executeQuery("SELECT nom_utilisateur FROM freecovid.Medecins");
            //On récupère les MetaData
            ResultSetMetaData resultMeta = result.getMetaData();

            while(result.next()){

                for(int i = 0; i <= resultMeta.getColumnCount(); i++) {
                    if(email.toString().equals(result.getObject(i).toString())){
                        Statement state2 = conn.createStatement();
                        ResultSet result2 = state.executeQuery("SELECT mdp FROM freecovid.Medecins WHERE nom_utilisateur='"+email.toString()+"'");
                        if(mdp.toString().equals(result2.getObject(0).toString())){
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

