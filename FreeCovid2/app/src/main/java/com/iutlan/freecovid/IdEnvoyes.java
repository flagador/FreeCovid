package com.iutlan.freecovid;

import java.util.ArrayList;

public class IdEnvoyes extends ListeID {
    static ArrayList<Integer> listeEnvoye = new ArrayList<Integer>();



    public static void ajoutListe(int id){
        listeEnvoye.add(id);
    }
}
