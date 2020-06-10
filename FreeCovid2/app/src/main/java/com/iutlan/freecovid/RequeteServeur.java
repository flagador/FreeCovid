package com.iutlan.freecovid;

import java.util.ArrayList;

public class RequeteServeur {
    public int comparaisonRecus(ArrayList<Integer> listeRecu){
        int nbMaladesCroises = 0;
        for (int i = 0; i<listeRecu.size(); i++){
            //A REMPLIR : COMPARER ID RECUS AVEC ID DE LA BDD
        }
        return nbMaladesCroises;
    }
    public void publierEnvoyes(ArrayList<Integer> listeEnvoyes){
        for (int i = 0; i<listeEnvoyes.size(); i++){
            //A REMPLIR : ENVOYER LES ID ENVOYES SUR LA BDD
        }
    }
}
