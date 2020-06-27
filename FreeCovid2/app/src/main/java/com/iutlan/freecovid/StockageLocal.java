package com.iutlan.freecovid;

import android.content.Context;
import android.util.Log;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class StockageLocal {

    public static void enregistrerUnIdDansFichier(IIdentifiant id, String fichier, Context context){
        try {
            File file = new File(context.getFilesDir(), fichier);

            FileOutputStream fos = new FileOutputStream(file,true);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos);

            outputStreamWriter.write(id + "\n");
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "Erreur d'écriture " + e.toString());
        }
    }
    public static ListeID charger(String fichier, Context context){


        try {
            InputStream inputStream = context.openFileInput(fichier);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append("\n").append(receiveString);
                }

                inputStream.close();
                return parseChargement(stringBuilder.toString());
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return null;
    }

    public static ListeID parseChargement(String input){
        ListeID output = new IdEnvoyes();
        String[] strings = input.split("\n");
        for (String s :
                strings) {
            output.addId(Integer.valueOf(s));
        }
        return output;
    }
}
