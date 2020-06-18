package com.iutlan.freecovid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // mettre en place l'interface
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        Button button = (Button) findViewById(R.id.button);
        final EditText editEmail, editMotdepasse;
        editEmail = (EditText) findViewById(R.id.email);
        editMotdepasse = (EditText) findViewById(R.id.motdepasse);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString();
                String motdepasse = editMotdepasse.getText().toString();
                ConnexionBdd con = new ConnexionBdd();
                if(con.verifConnexion(email,motdepasse)==true){
                    // UTILISER LA METHODE UPDATEBDD POUR ENVOYER LA LISTE D'ID ENVOYES SUR LA BDD
                } else {
                    System.out.println("Email ou mot de passe incorrect");
                }

            }
        });

    }
}
