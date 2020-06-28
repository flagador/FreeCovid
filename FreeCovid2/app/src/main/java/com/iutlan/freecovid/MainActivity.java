package com.iutlan.freecovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
//    Button button;
    static ListeID idenv = new IdEnvoyes();
    ListeID idrec = new IdRecus();
    IIdentifiant id = new IdentifiantSimple();
    Button button;
    public static ConnexionBdd con = new ConnexionBdd();

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

        String[] files = getApplicationContext().fileList();
        for(int i = 0; i<files.length;i++) {
            System.out.println(files[i]);
        }

        StockageLocal.enregistrerUnIdDansFichier(id.generer(),"id_envoyes.txt", getApplicationContext());
        Log.d("SAVE", "Enregistrement effectué");
        ListeID réponse =  null;
        réponse = StockageLocal.charger("id_envoyes.txt", getApplicationContext());
        Log.d("SAVE", réponse.toString());
//        affichagebdd(con); // log les id contenus dans la bdd

//        / *************************************************************//

        afficheNbMaladesCroises(); //affiche le nombre de malades croises

       /* id.setValue(158962495);
        StockageLocal.enregistrerUnIdDansFichier(id,"id_recus.txt", getApplicationContext());
        id.setValue(123456789);
        StockageLocal.enregistrerUnIdDansFichier(id,"id_recus.txt", getApplicationContext());
        ListeID reponse2 = StockageLocal.charger("id_recus.txt", getApplicationContext());
        int rep = con.comparaisonIdRecusBdd(reponse2);
        Log.d("NBMALADES", String.valueOf(rep));*/

    }

    public void openLoginActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }




    //TEST CONNEXION BDD :
    public void affichagebdd(ConnexionBdd con){
        ListeID liste = con.lireBdd();
        for(int i =0; i<liste.getListe().size(); i++){
            Log.d("BDD",liste.getListe().get(i).toString());
        }
    }

    public void afficheNbMaladesCroises(){
        ListeID reponse2 = StockageLocal.charger("id_recus.txt", getApplicationContext());
        int rep = con.comparaisonIdRecusBdd(reponse2);
        TextView TextNbMaladesCroises = (TextView) findViewById(R.id.nbMaladesCroises);
        TextNbMaladesCroises.setText(String.valueOf(rep));
    }


}
