package com.iutlan.freecovid;

import java.util.Random;

public class IdentifiantSimple implements IIdentifiant{
    int value;

        public IIdentifiant generer(){
            //Génere un identifiant au hasard
            int min = 100000000;
            int max = 999999999;
            Random random = new Random();
            int randomNumber = random.nextInt(max-min) + min;
            value = randomNumber;
            return this;
        }

    public int getValue() {
        return value;
    }
}
