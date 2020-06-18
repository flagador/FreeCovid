package com.iutlan.freecovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button button;
    IdEnvoyes idenv = new IdEnvoyes();
    IdRecus idrec = new IdRecus();
    Bluetooth bt = new Bluetooth();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt.run();
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


    }
    public void openLoginActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}
