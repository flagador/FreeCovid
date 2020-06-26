package com.iutlan.freecovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Collection;

public class MainActivity extends AppCompatActivity {
//    Button button;
    ListeID idenv = new IdEnvoyes();
    ListeID idrec = new IdRecus();
    IdentifiantSimple id = new IdentifiantSimple();
    Button button;
    ConnexionBdd con = new ConnexionBdd();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View v) {
                 openLoginActivity();
              }
          });



        // ************* Test Sauvegarde et chargement ****************

        //getApplicationContext().deleteFile("id_envoyes.txt"); //utilisé pour supprimer le fichier id_envoyes.txt

        idenv = new IdEnvoyes();
        id.generer((IdEnvoyes) this.idenv);
        String[] files = getApplicationContext().fileList();
        for(int i = 0; i<files.length;i++) {
            System.out.println(files[i]);
        }

        StockageLocal.enregistrer(idenv,"id_envoyes.txt", getApplicationContext());
        Log.d("SAVE", "Enregistrement effectué");
        String réponse =  "";
        réponse = StockageLocal.charger("id_envoyes.txt", getApplicationContext());
        Log.d("SAVE", réponse);

//        / *************************************************************//

        testbdd(con);
    }

    public void openLoginActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }




    //TEST CONNEXION BDD :
    public void testbdd(ConnexionBdd con){
        ListeID liste = con.lireBdd();
        for(int i =0; i<con.lireBdd().getListe().size(); i++){ //ERREUR ICI LORS DE TESTS
            System.out.println(liste.getListe().get(i));
        }
    }

}
