package com.iutlan.freecovid;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        if(MainActivity.con.verifConnexion(email,motdepasse)==true){
            Log.d("BDD","Email et mot de passe correctes");
            insertbdd(MainActivity.idenv);
            this.finish();
        } else {
            Log.e("BDD","Email ou mot de passe incorrect");
        }
    }

    public void insertbdd(ListeID idenv){
        MainActivity.con.updateBdd(idenv);
        StockageLocal.enregistrer(idenv,"id_envoyes.txt", getApplicationContext());
        Log.d("SAVE", "Enregistrement effectué");
        Toast.makeText(getApplicationContext(), "Vous avez été déclaré malade", Toast.LENGTH_SHORT).show();

    }
}
