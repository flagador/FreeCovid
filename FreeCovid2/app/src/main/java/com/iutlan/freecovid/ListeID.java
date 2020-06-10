package com.iutlan.freecovid;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

abstract class ListeID {
    protected List<Integer> liste = new ArrayList<>();


    public List<Integer> getListe() {
        return liste;
    }

    public void setListe(List<Integer> liste) {
        this.liste = liste;
    }

    public void addId(int id){
        liste.add(id);
    }
}
