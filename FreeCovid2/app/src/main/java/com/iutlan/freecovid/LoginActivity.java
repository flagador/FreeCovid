package com.iutlan.freecovid;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // mettre en place l'interface
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etablirConnexion();
            }
        });
    }
    public void etablirConnexion(){
        EditText editEmail, editMotdepasse;
        editEmail = (EditText) findViewById(R.id.email);
        editMotdepasse = (EditText) findViewById(R.id.motdepasse);
        String email = editEmail.getText().toString();
        String motdepasse = editMotdepasse.getText().toString();
        ConnexionBdd con = new ConnexionBdd();
        if(con.verifConnexion(email,motdepasse)==true){
            // UTILISER LA METHODE UPDATEBDD POUR ENVOYER LA LISTE D'ID ENVOYES SUR LA BDD
            Log.d("BDD","Email et mot de passe correctes");
            this.finish();
        } else {
            Log.e("BDD","Email ou mot de passe incorrect");
        }
    }
}
