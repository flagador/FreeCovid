package com.iutlan.freecovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
//    Button button;
    ListeID idenv = new IdEnvoyes();
    ListeID idrec = new IdRecus();
    IdentifiantSimple id = new IdentifiantSimple();
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View v) {
                 openLoginActivity();
              }
          });

        //TEST CONNEXION BDD :
        /*
        ConnexionBdd con = new ConnexionBdd();
        for(int i =0; i<con.lireBdd().getListe().size(); i++){ //ERREUR ICI LORS DE TESTS
            System.out.println(con.lireBdd().getListe().get(i));
        }*/

        // ************* Test Sauvegarde et chargement ****************

        idenv = new IdEnvoyes();
        idenv.addId(42);
        idenv.addId(66);
        idenv.addId(321864);
        id.generer((IdEnvoyes) this.idenv);


        StockageLocal.enregistrer(idenv,"proutest.txt", getApplicationContext());
        Log.d("SAVE", "Enregistrement effectué");
        String réponse =  "";
        réponse = StockageLocal.charger("proutest.txt", getApplicationContext());

        Log.d("SAVE", réponse);

//        / *************************************************************//

    }
    public void openLoginActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}
