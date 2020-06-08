package com.iutlan.freecovid;

import java.util.Random;

public class Identifiant {

    public int generer(){
        int min = 100000000;
        int max = 999999999;
        Random random = new Random();
        int randomNumber = random.nextInt(max–min) + min;
        return randomNumber;
    }
}
