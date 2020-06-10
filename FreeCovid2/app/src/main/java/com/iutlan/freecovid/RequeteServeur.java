package com.iutlan.freecovid;

import java.util.ArrayList;
import java.util.List;

public class RequeteServeur {
    public int comparaisonRecus(ListeID idRecus){
        List<Integer> listeRecu = idRecus.getListe();
        int nbMaladesCroises = 0;
        for (int i = 0; i<listeRecu.size(); i++){
            //A REMPLIR : COMPARER ID RECUS AVEC ID DE LA BDD
        }
        return nbMaladesCroises;
    }
    public void publierEnvoyes(ListeID idEnvoyes){
        List<Integer> listeEnvoyes = idEnvoyes.getListe();
        for (int i = 0; i<listeEnvoyes.size(); i++){
            //A REMPLIR : ENVOYER LES ID ENVOYES SUR LA BDD
        }
    }
}
