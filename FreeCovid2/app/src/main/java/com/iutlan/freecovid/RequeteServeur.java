package com.iutlan.freecovid;

import java.util.ArrayList;
import java.util.List;

public class RequeteServeur {
    public int comparaisonRecus(ListeID idRecus){ //compte le nombre d'id recus contenus dans la bdd
        List<Integer> listeRecu = idRecus.getListe();
        ConnexionBdd con = new ConnexionBdd();
        List<Integer> listeBdd = (List<Integer>) con.lireBdd();
        int nbMaladesCroises = 0;
        for (int i = 0; i<listeRecu.size(); i++){
            for(int j =0; j<listeBdd.size(); j++){
                if(listeRecu.get(i)==listeBdd.get(j)){
                    nbMaladesCroises++;
                }
            }
        }
        return nbMaladesCroises;
    }
}
